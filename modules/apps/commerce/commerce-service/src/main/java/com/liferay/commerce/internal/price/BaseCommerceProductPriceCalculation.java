/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.commerce.internal.price;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.model.CommerceMoney;
import com.liferay.commerce.currency.model.CommerceMoneyFactory;
import com.liferay.commerce.currency.util.PriceFormat;
import com.liferay.commerce.discount.CommerceDiscountValue;
import com.liferay.commerce.internal.util.CommercePriceConverterUtil;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.price.CommerceProductOptionValueRelativePriceRequest;
import com.liferay.commerce.price.CommerceProductPrice;
import com.liferay.commerce.price.CommerceProductPriceCalculation;
import com.liferay.commerce.price.CommerceProductPriceImpl;
import com.liferay.commerce.product.constants.CPConstants;
import com.liferay.commerce.product.model.CPDefinitionOptionRel;
import com.liferay.commerce.product.model.CPDefinitionOptionValueRel;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.option.CommerceOptionValue;
import com.liferay.commerce.product.service.CPDefinitionOptionRelLocalService;
import com.liferay.commerce.product.service.CPInstanceLocalService;
import com.liferay.commerce.tax.CommerceTaxCalculation;
import com.liferay.portal.kernel.exception.PortalException;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Matija Petanjek
 */
public abstract class BaseCommerceProductPriceCalculation
	implements CommerceProductPriceCalculation {

	@Override
	public CommerceMoney getCPDefinitionMinimumPrice(
			long cpDefinitionId, CommerceContext commerceContext)
		throws PortalException {

		BigDecimal cpDefinitionMinimumPrice = BigDecimal.ZERO;

		CommerceMoney commerceMoney = getUnitMinPrice(
			cpDefinitionId, 1, commerceContext);

		cpDefinitionMinimumPrice = cpDefinitionMinimumPrice.add(
			commerceMoney.getPrice());

		List<CPDefinitionOptionRel> cpDefinitionOptionRels =
			cpDefinitionOptionRelLocalService.getCPDefinitionOptionRels(
				cpDefinitionId);

		for (CPDefinitionOptionRel cpDefinitionOptionRel :
				cpDefinitionOptionRels) {

			if (!_isRequiredPriceContributor(cpDefinitionOptionRel)) {
				continue;
			}

			if (cpDefinitionOptionRel.isPriceTypeStatic()) {
				cpDefinitionMinimumPrice = cpDefinitionMinimumPrice.add(
					_getCPDefinitionOptionMinStaticPrice(
						cpDefinitionOptionRel, commerceContext));

				continue;
			}

			cpDefinitionMinimumPrice = cpDefinitionMinimumPrice.add(
				_getCPDefinitionOptionMinDynamicPrice(
					cpDefinitionOptionRel, commerceContext));
		}

		return commerceMoneyFactory.create(
			commerceContext.getCommerceCurrency(), cpDefinitionMinimumPrice);
	}

	@Override
	public CommerceMoney getCPDefinitionOptionValueRelativePrice(
			CommerceProductOptionValueRelativePriceRequest
				commerceProductOptionValueRelativePriceRequest)
		throws PortalException {

		_validate(
			commerceProductOptionValueRelativePriceRequest.
				getCPDefinitionOptionValueRel(),
			commerceProductOptionValueRelativePriceRequest.
				getSelectedCPDefinitionOptionValueRel());

		BigDecimal relativePrice = BigDecimal.ZERO;

		CommerceContext commerceContext =
			commerceProductOptionValueRelativePriceRequest.getCommerceContext();

		CPDefinitionOptionValueRel cpDefinitionOptionValueRel =
			commerceProductOptionValueRelativePriceRequest.
				getCPDefinitionOptionValueRel();

		CPDefinitionOptionRel cpDefinitionOptionRel =
			cpDefinitionOptionValueRel.getCPDefinitionOptionRel();

		if (!cpDefinitionOptionRel.isPriceContributor()) {
			return commerceMoneyFactory.create(
				commerceContext.getCommerceCurrency(), relativePrice,
				PriceFormat.RELATIVE);
		}

		relativePrice = relativePrice.add(
			_getCPInstancePriceDifference(
				commerceProductOptionValueRelativePriceRequest.
					getCPInstanceId(),
				commerceProductOptionValueRelativePriceRequest.
					getCPInstanceMinQuantity(),
				commerceProductOptionValueRelativePriceRequest.
					getSelectedCPInstanceId(),
				commerceProductOptionValueRelativePriceRequest.
					getSelectedCPInstanceMinQuantity(),
				commerceContext));

		relativePrice = relativePrice.add(
			_getCPDefinitionOptionValuePriceDifference(
				commerceProductOptionValueRelativePriceRequest.
					getCPDefinitionOptionValueRel(),
				commerceProductOptionValueRelativePriceRequest.
					getSelectedCPDefinitionOptionValueRel(),
				cpDefinitionOptionRel.getPriceType(), commerceContext));

		return commerceMoneyFactory.create(
			commerceContext.getCommerceCurrency(), relativePrice,
			PriceFormat.RELATIVE);
	}

	protected BigDecimal getConvertedPrice(
			long cpInstanceId, BigDecimal price, boolean includeTax,
			CommerceContext commerceContext)
		throws PortalException {

		long commerceChannelGroupId =
			commerceContext.getCommerceChannelGroupId();
		long commerceBillingAddressId = 0;
		long commerceShippingAddressId = 0;

		CommerceOrder commerceOrder = commerceContext.getCommerceOrder();

		if (commerceOrder != null) {
			commerceChannelGroupId = commerceOrder.getGroupId();
			commerceBillingAddressId = commerceOrder.getBillingAddressId();
			commerceShippingAddressId = commerceOrder.getShippingAddressId();
		}
		else {
			CommerceAccount commerceAccount =
				commerceContext.getCommerceAccount();

			if (commerceAccount != null) {
				commerceBillingAddressId =
					commerceAccount.getDefaultBillingAddressId();
				commerceShippingAddressId =
					commerceAccount.getDefaultShippingAddressId();
			}
		}

		return CommercePriceConverterUtil.getConvertedPrice(
			commerceChannelGroupId, cpInstanceId, commerceBillingAddressId,
			commerceShippingAddressId, price, includeTax,
			commerceTaxCalculation);
	}

	protected BigDecimal[] getUpdatedPrices(
			BigDecimal unitPrice, BigDecimal promoPrice, BigDecimal finalPrice,
			CommerceContext commerceContext,
			List<CommerceOptionValue> commerceOptionValues)
		throws PortalException {

		if ((commerceOptionValues == null) || commerceOptionValues.isEmpty()) {
			return new BigDecimal[] {unitPrice, promoPrice, finalPrice};
		}

		for (CommerceOptionValue commerceOptionValue : commerceOptionValues) {
			if (_isStaticPriceType(commerceOptionValue.getPriceType())) {
				BigDecimal optionValuePrice = commerceOptionValue.getPrice();

				if ((optionValuePrice != null) &&
					(optionValuePrice.compareTo(BigDecimal.ZERO) > 0)) {

					if (commerceOptionValue.getCPInstanceId() > 0) {
						optionValuePrice = optionValuePrice.multiply(
							BigDecimal.valueOf(
								commerceOptionValue.getQuantity()));
					}

					unitPrice = unitPrice.add(optionValuePrice);

					if ((promoPrice != null) &&
						(promoPrice.compareTo(BigDecimal.ZERO) > 0)) {

						promoPrice = promoPrice.add(optionValuePrice);
					}

					finalPrice = finalPrice.add(optionValuePrice);
				}
			}
			else if (Objects.equals(
						commerceOptionValue.getPriceType(),
						CPConstants.PRODUCT_OPTION_PRICE_TYPE_DYNAMIC)) {

				int optionValueQuantity = commerceOptionValue.getQuantity();

				CommerceProductPrice optionValueProductPrice =
					getCommerceProductPrice(
						commerceOptionValue.getCPInstanceId(),
						optionValueQuantity, true, commerceContext);

				CommerceMoney optionValueUnitPriceMoney =
					optionValueProductPrice.getUnitPrice();

				BigDecimal optionValueUnitPrice =
					optionValueUnitPriceMoney.getPrice();

				CommerceMoney optionValueUnitPromoPriceMoney =
					optionValueProductPrice.getUnitPromoPrice();

				BigDecimal optionValueUnitPromoPrice =
					optionValueUnitPromoPriceMoney.getPrice();

				if ((optionValueUnitPromoPrice.compareTo(BigDecimal.ZERO) >
						0) &&
					(promoPrice.compareTo(BigDecimal.ZERO) == 0)) {

					promoPrice = promoPrice.add(unitPrice);
				}
				else if ((optionValueUnitPromoPrice.compareTo(
							BigDecimal.ZERO) == 0) &&
						 (promoPrice.compareTo(BigDecimal.ZERO) > 0)) {

					promoPrice = promoPrice.add(
						optionValueUnitPrice.multiply(
							BigDecimal.valueOf(optionValueQuantity)));
				}

				unitPrice = unitPrice.add(
					optionValueUnitPrice.multiply(
						BigDecimal.valueOf(optionValueQuantity)));

				promoPrice = promoPrice.add(
					optionValueUnitPromoPrice.multiply(
						BigDecimal.valueOf(optionValueQuantity)));

				CommerceMoney optionValueFinalPriceMoney =
					optionValueProductPrice.getFinalPrice();

				finalPrice = finalPrice.add(
					optionValueFinalPriceMoney.getPrice());
			}
		}

		return new BigDecimal[] {unitPrice, promoPrice, finalPrice};
	}

	protected void setCommerceProductPriceWithTaxAmount(
			long cpInstanceId, BigDecimal finalPriceWithTaxAmount,
			CommerceProductPriceImpl commerceProductPriceImpl,
			CommerceContext commerceContext,
			CommerceDiscountValue commerceDiscountValue,
			boolean discountsTargetNetPrice)
		throws PortalException {

		CommerceMoney unitPriceMoney = commerceProductPriceImpl.getUnitPrice();

		BigDecimal unitPriceWithTaxAmount = getConvertedPrice(
			cpInstanceId, unitPriceMoney.getPrice(), false, commerceContext);

		BigDecimal activePrice = unitPriceWithTaxAmount;

		CommerceMoney promoPriceMoney =
			commerceProductPriceImpl.getUnitPromoPrice();

		BigDecimal promoPrice = promoPriceMoney.getPrice();

		if ((promoPrice != null) &&
			(promoPrice.compareTo(BigDecimal.ZERO) > 0)) {

			BigDecimal unitPromoPriceWithTaxAmount = getConvertedPrice(
				cpInstanceId, promoPriceMoney.getPrice(), false,
				commerceContext);

			commerceProductPriceImpl.setUnitPromoPriceWithTaxAmount(
				commerceMoneyFactory.create(
					commerceContext.getCommerceCurrency(),
					unitPromoPriceWithTaxAmount));

			activePrice = unitPromoPriceWithTaxAmount;
		}
		else {
			commerceProductPriceImpl.setUnitPromoPriceWithTaxAmount(
				commerceMoneyFactory.create(
					commerceContext.getCommerceCurrency(), BigDecimal.ZERO));
		}

		commerceProductPriceImpl.setUnitPriceWithTaxAmount(
			commerceMoneyFactory.create(
				commerceContext.getCommerceCurrency(), unitPriceWithTaxAmount));

		CommerceCurrency commerceCurrency =
			commerceContext.getCommerceCurrency();

		int quantity = commerceProductPriceImpl.getQuantity();

		activePrice = activePrice.multiply(BigDecimal.valueOf(quantity));

		if (discountsTargetNetPrice) {
			commerceProductPriceImpl.setCommerceDiscountValueWithTaxAmount(
				CommercePriceConverterUtil.getConvertedCommerceDiscountValue(
					commerceDiscountValue, activePrice, finalPriceWithTaxAmount,
					commerceMoneyFactory,
					RoundingMode.valueOf(commerceCurrency.getRoundingMode())));
		}
		else {
			commerceProductPriceImpl.setCommerceDiscountValueWithTaxAmount(
				commerceDiscountValue);
		}

		commerceProductPriceImpl.setFinalPriceWithTaxAmount(
			commerceMoneyFactory.create(
				commerceContext.getCommerceCurrency(),
				finalPriceWithTaxAmount));
	}

	@Reference
	protected CommerceMoneyFactory commerceMoneyFactory;

	@Reference
	protected CommerceTaxCalculation commerceTaxCalculation;

	@Reference
	protected CPDefinitionOptionRelLocalService
		cpDefinitionOptionRelLocalService;

	@Reference
	protected CPInstanceLocalService cpInstanceLocalService;

	private BigDecimal _getCPDefinitionOptionMinDynamicPrice(
			CPDefinitionOptionRel cpDefinitionOptionRel,
			CommerceContext commerceContext)
		throws PortalException {

		List<CPDefinitionOptionValueRel> cpDefinitionOptionValueRels =
			cpDefinitionOptionRel.getCPDefinitionOptionValueRels();

		if (cpDefinitionOptionValueRels.isEmpty()) {
			return BigDecimal.ZERO;
		}

		Iterator<CPDefinitionOptionValueRel> iterator =
			cpDefinitionOptionValueRels.iterator();

		CPDefinitionOptionValueRel cpDefinitionOptionValueRel = iterator.next();

		BigDecimal cpDefinitionOptionMinDynamicPrice = _getCPInstanceFinalPrice(
			cpDefinitionOptionValueRel.getCProductId(),
			cpDefinitionOptionValueRel.getCPInstanceUuid(),
			cpDefinitionOptionValueRel.getQuantity(), commerceContext);

		while (iterator.hasNext()) {
			cpDefinitionOptionValueRel = iterator.next();

			BigDecimal cpInstanceFinalPrice = _getCPInstanceFinalPrice(
				cpDefinitionOptionValueRel.getCProductId(),
				cpDefinitionOptionValueRel.getCPInstanceUuid(),
				cpDefinitionOptionValueRel.getQuantity(), commerceContext);

			if (cpDefinitionOptionMinDynamicPrice.compareTo(
					cpInstanceFinalPrice) > 0) {

				cpDefinitionOptionMinDynamicPrice = cpInstanceFinalPrice;
			}
		}

		return cpDefinitionOptionMinDynamicPrice;
	}

	private BigDecimal _getCPDefinitionOptionMinStaticPrice(
			CPDefinitionOptionRel cpDefinitionOptionRel,
			CommerceContext commerceContext)
		throws PortalException {

		List<CPDefinitionOptionValueRel> cpDefinitionOptionValueRels =
			cpDefinitionOptionRel.getCPDefinitionOptionValueRels();

		if (cpDefinitionOptionValueRels.isEmpty()) {
			return BigDecimal.ZERO;
		}

		Iterator<CPDefinitionOptionValueRel> iterator =
			cpDefinitionOptionValueRels.iterator();

		CPDefinitionOptionValueRel cpDefinitionOptionValueRel = iterator.next();

		BigDecimal cpDefinitionOptionMinStaticPrice =
			_getCPDefinitionOptionValueFinalPrice(
				cpDefinitionOptionValueRel.getPrice(),
				cpDefinitionOptionValueRel.getQuantity());

		while (iterator.hasNext()) {
			cpDefinitionOptionValueRel = iterator.next();

			BigDecimal cpDefinitionOptionValueFinalPrice =
				_getCPDefinitionOptionValueFinalPrice(
					cpDefinitionOptionValueRel.getPrice(),
					cpDefinitionOptionValueRel.getQuantity());

			if (cpDefinitionOptionMinStaticPrice.compareTo(
					cpDefinitionOptionValueFinalPrice) > 0) {

				cpDefinitionOptionMinStaticPrice =
					cpDefinitionOptionValueFinalPrice;
			}
		}

		CommerceCurrency commerceCurrency =
			commerceContext.getCommerceCurrency();

		return cpDefinitionOptionMinStaticPrice.multiply(
			commerceCurrency.getRate());
	}

	private BigDecimal _getCPDefinitionOptionValueFinalPrice(
		BigDecimal price, int quantity) {

		return price.multiply(BigDecimal.valueOf(quantity));
	}

	private BigDecimal _getCPDefinitionOptionValuePriceDifference(
			CPDefinitionOptionValueRel cpDefinitionOptionValueRel,
			CPDefinitionOptionValueRel selectedCPDefinitionOptionValueRel,
			String priceType, CommerceContext commerceContext)
		throws PortalException {

		CommerceCurrency commerceCurrency =
			commerceContext.getCommerceCurrency();

		if (_isStaticPriceType(priceType)) {
			BigDecimal price = cpDefinitionOptionValueRel.getPrice();

			if (selectedCPDefinitionOptionValueRel != null) {
				price = price.subtract(
					selectedCPDefinitionOptionValueRel.getPrice());
			}

			return price.multiply(commerceCurrency.getRate());
		}

		BigDecimal cpInstanceFinalPrice = _getCPInstanceFinalPrice(
			cpDefinitionOptionValueRel.getCProductId(),
			cpDefinitionOptionValueRel.getCPInstanceUuid(),
			cpDefinitionOptionValueRel.getQuantity(), commerceContext);

		if (selectedCPDefinitionOptionValueRel == null) {
			return cpInstanceFinalPrice;
		}

		BigDecimal selectedCPInstanceFinalPrice = _getCPInstanceFinalPrice(
			selectedCPDefinitionOptionValueRel.getCProductId(),
			selectedCPDefinitionOptionValueRel.getCPInstanceUuid(),
			selectedCPDefinitionOptionValueRel.getQuantity(), commerceContext);

		return cpInstanceFinalPrice.subtract(selectedCPInstanceFinalPrice);
	}

	private BigDecimal _getCPInstanceFinalPrice(
			long cProductId, String cpInstanceUuid, int quantity,
			CommerceContext commerceContext)
		throws PortalException {

		CPInstance cpInstance = cpInstanceLocalService.getCProductInstance(
			cProductId, cpInstanceUuid);

		CommerceMoney commerceMoney = getFinalPrice(
			cpInstance.getCPInstanceId(), quantity, commerceContext);

		return commerceMoney.getPrice();
	}

	private BigDecimal _getCPInstancePriceDifference(
			long cpInstanceId, int cpInstanceMinQuantity,
			long selectedCPInstanceId, int selectedCPInstanceMinQuantity,
			CommerceContext commerceContext)
		throws PortalException {

		BigDecimal priceDifference = BigDecimal.ZERO;

		if (cpInstanceId > 0) {
			CommerceMoney cpInstanceFinalPrice = getFinalPrice(
				cpInstanceId, cpInstanceMinQuantity, commerceContext);

			priceDifference = priceDifference.add(
				cpInstanceFinalPrice.getPrice());
		}

		BigDecimal selectedCPInstanceFinalPrice = BigDecimal.ZERO;

		if (selectedCPInstanceId > 0) {
			CommerceMoney commerceMoney = getFinalPrice(
				selectedCPInstanceId, selectedCPInstanceMinQuantity,
				commerceContext);

			selectedCPInstanceFinalPrice = commerceMoney.getPrice();
		}

		return priceDifference.subtract(selectedCPInstanceFinalPrice);
	}

	private boolean _isRequiredPriceContributor(
		CPDefinitionOptionRel cpDefinitionOptionRel) {

		if (cpDefinitionOptionRel.isPriceContributor() &&
			(cpDefinitionOptionRel.isRequired() ||
			 cpDefinitionOptionRel.isSkuContributor())) {

			return true;
		}

		return false;
	}

	private boolean _isStaticPriceType(String value) {
		if (Objects.equals(
				value, CPConstants.PRODUCT_OPTION_PRICE_TYPE_STATIC)) {

			return true;
		}

		return false;
	}

	private void _validate(
		CPDefinitionOptionValueRel cpDefinitionOptionValueRel,
		CPDefinitionOptionValueRel selectedCPDefinitionOptionValueRel) {

		if ((selectedCPDefinitionOptionValueRel != null) &&
			(cpDefinitionOptionValueRel.getCPDefinitionOptionRelId() !=
				selectedCPDefinitionOptionValueRel.
					getCPDefinitionOptionRelId())) {

			throw new IllegalArgumentException(
				"Provided CPDefinitionOptionValueRel parameters must belong " +
					"to the same CPDefinitionOptionRel");
		}
	}

}