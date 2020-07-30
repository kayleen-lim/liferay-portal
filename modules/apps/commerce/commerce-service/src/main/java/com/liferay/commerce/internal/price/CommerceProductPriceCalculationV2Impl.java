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
import com.liferay.commerce.currency.service.CommerceCurrencyLocalService;
import com.liferay.commerce.discount.CommerceDiscountCalculation;
import com.liferay.commerce.discount.CommerceDiscountValue;
import com.liferay.commerce.discount.application.strategy.CommerceDiscountApplicationStrategy;
import com.liferay.commerce.internal.util.CommercePriceConverterUtil;
import com.liferay.commerce.price.CommerceProductPrice;
import com.liferay.commerce.price.CommerceProductPriceCalculation;
import com.liferay.commerce.price.CommerceProductPriceImpl;
import com.liferay.commerce.price.CommerceProductPriceRequest;
import com.liferay.commerce.price.list.constants.CommercePriceListConstants;
import com.liferay.commerce.price.list.discovery.CommercePriceListDiscovery;
import com.liferay.commerce.price.list.model.CommercePriceEntry;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.model.CommerceTierPriceEntry;
import com.liferay.commerce.price.list.service.CommercePriceEntryLocalService;
import com.liferay.commerce.price.list.service.CommercePriceListLocalService;
import com.liferay.commerce.price.list.service.CommerceTierPriceEntryLocalService;
import com.liferay.commerce.pricing.configuration.CommercePricingConfiguration;
import com.liferay.commerce.pricing.constants.CommercePricingConstants;
import com.liferay.commerce.pricing.exception.CommerceUndefinedBasePriceListException;
import com.liferay.commerce.pricing.modifier.CommercePriceModifierHelper;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.service.CommerceChannelLocalService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Riccardo Alberti
 */
@Component(
	property = "commerce.price.calculation.key=v2.0",
	service = CommerceProductPriceCalculation.class
)
public class CommerceProductPriceCalculationV2Impl
	extends BaseCommerceProductPriceCalculation {

	@Override
	public CommerceProductPrice getCommerceProductPrice(
			CommerceProductPriceRequest commerceProductPriceRequest)
		throws PortalException {

		long cpInstanceId = commerceProductPriceRequest.getCpInstanceId();
		int quantity = commerceProductPriceRequest.getQuantity();

		CommerceContext commerceContext =
			commerceProductPriceRequest.getCommerceContext();

		long commercePriceListId = _getCommercePriceListId(
			cpInstanceId, commerceContext);

		CommerceMoney unitPriceMoney = _getUnitPrice(
			commercePriceListId, cpInstanceId, quantity, commerceContext);

		BigDecimal finalPrice = unitPriceMoney.getPrice();

		long commercePromoPriceListId = _getCommercePromoPriceListId(
			cpInstanceId, commerceContext);

		CommerceMoney promoPriceMoney = _getPromoPrice(
			commercePromoPriceListId, cpInstanceId, quantity, commerceContext);

		BigDecimal unitPrice = unitPriceMoney.getPrice();
		BigDecimal promoPrice = promoPriceMoney.getPrice();

		if ((promoPrice != null) &&
			(promoPrice.compareTo(BigDecimal.ZERO) > 0) &&
			(promoPrice.compareTo(unitPrice) <= 0)) {

			finalPrice = promoPriceMoney.getPrice();

			commercePriceListId = commercePromoPriceListId;
		}

		BigDecimal[] updatedPrices = getUpdatedPrices(
			unitPrice, promoPrice, finalPrice, commerceContext,
			commerceProductPriceRequest.getCommerceOptionValues());

		finalPrice = updatedPrices[2];

		CommerceDiscountValue commerceDiscountValue;

		BigDecimal finalPriceWithTaxAmount = getConvertedPrice(
			cpInstanceId, finalPrice, false, commerceContext);

		boolean discountsTargetNetPrice = true;

		CommerceChannel commerceChannel =
			_commerceChannelLocalService.fetchCommerceChannel(
				commerceContext.getCommerceChannelId());

		if (commerceChannel != null) {
			discountsTargetNetPrice =
				commerceChannel.isDiscountsTargetNetPrice();
		}

		if (discountsTargetNetPrice) {
			commerceDiscountValue = _getCommerceDiscountValue(
				cpInstanceId, commercePriceListId, quantity, finalPrice,
				commerceContext);

			finalPrice = finalPrice.multiply(BigDecimal.valueOf(quantity));

			if (commerceDiscountValue != null) {
				CommerceMoney discountAmountMoney =
					commerceDiscountValue.getDiscountAmount();

				finalPrice = finalPrice.subtract(
					discountAmountMoney.getPrice());
			}

			finalPriceWithTaxAmount = getConvertedPrice(
				cpInstanceId, finalPrice, false, commerceContext);
		}
		else {
			commerceDiscountValue = _getCommerceDiscountValue(
				cpInstanceId, commercePriceListId, quantity,
				finalPriceWithTaxAmount, commerceContext);

			finalPriceWithTaxAmount = finalPriceWithTaxAmount.multiply(
				BigDecimal.valueOf(quantity));

			if (commerceDiscountValue != null) {
				CommerceMoney discountAmountMoney =
					commerceDiscountValue.getDiscountAmount();

				finalPriceWithTaxAmount = finalPriceWithTaxAmount.subtract(
					discountAmountMoney.getPrice());
			}

			finalPrice = getConvertedPrice(
				cpInstanceId, finalPriceWithTaxAmount, true, commerceContext);
		}

		// fill data

		CommerceProductPriceImpl commerceProductPriceImpl =
			new CommerceProductPriceImpl();

		commerceProductPriceImpl.setCommercePriceListId(commercePriceListId);
		commerceProductPriceImpl.setUnitPrice(
			commerceMoneyFactory.create(
				commerceContext.getCommerceCurrency(), updatedPrices[0]));
		commerceProductPriceImpl.setUnitPromoPrice(
			commerceMoneyFactory.create(
				commerceContext.getCommerceCurrency(), updatedPrices[1]));
		commerceProductPriceImpl.setQuantity(quantity);

		if (discountsTargetNetPrice) {
			commerceProductPriceImpl.setCommerceDiscountValue(
				commerceDiscountValue);
		}
		else {
			CommerceCurrency commerceCurrency =
				commerceContext.getCommerceCurrency();

			commerceProductPriceImpl.setCommerceDiscountValue(
				CommercePriceConverterUtil.getConvertedCommerceDiscountValue(
					commerceDiscountValue,
					updatedPrices[2].multiply(BigDecimal.valueOf(quantity)),
					finalPrice, _commerceMoneyFactory,
					RoundingMode.valueOf(commerceCurrency.getRoundingMode())));
		}

		commerceProductPriceImpl.setFinalPrice(
			commerceMoneyFactory.create(
				commerceContext.getCommerceCurrency(), finalPrice));

		if (commerceProductPriceRequest.isCalculateTax()) {
			setCommerceProductPriceWithTaxAmount(
				cpInstanceId, finalPriceWithTaxAmount, commerceProductPriceImpl,
				commerceContext, commerceDiscountValue,
				discountsTargetNetPrice);
		}

		return commerceProductPriceImpl;
	}

	@Override
	public CommerceProductPrice getCommerceProductPrice(
			long cpInstanceId, int quantity, boolean secure,
			CommerceContext commerceContext)
		throws PortalException {

		boolean calculateTax = false;

		CommerceChannel commerceChannel =
			_commerceChannelLocalService.fetchCommerceChannel(
				commerceContext.getCommerceChannelId());

		if (commerceChannel != null) {
			calculateTax = Objects.equals(
				commerceChannel.getPriceDisplayType(),
				CommercePricingConstants.TAX_INCLUDED_IN_PRICE);
		}

		long commercePriceListId = _getCommercePriceListId(
			cpInstanceId, commerceContext);

		CommercePriceList commercePriceList =
			_commercePriceListLocalService.getCommercePriceList(
				commercePriceListId);

		calculateTax = calculateTax || !commercePriceList.isNetPrice();

		long commercePromoPriceListId = _getCommercePromoPriceListId(
			cpInstanceId, commerceContext);

		if (commercePromoPriceListId > 0) {
			CommercePriceList commercePromotion =
				_commercePriceListLocalService.getCommercePriceList(
					commercePromoPriceListId);

			calculateTax = calculateTax || !commercePromotion.isNetPrice();
		}

		CommerceProductPriceRequest commerceProductPriceRequest =
			new CommerceProductPriceRequest();

		commerceProductPriceRequest.setCpInstanceId(cpInstanceId);
		commerceProductPriceRequest.setQuantity(quantity);
		commerceProductPriceRequest.setSecure(secure);
		commerceProductPriceRequest.setCommerceContext(commerceContext);
		commerceProductPriceRequest.setCommerceOptionValues(null);
		commerceProductPriceRequest.setCalculateTax(calculateTax);

		return getCommerceProductPrice(commerceProductPriceRequest);
	}

	@Override
	public CommerceProductPrice getCommerceProductPrice(
			long cpInstanceId, int quantity, CommerceContext commerceContext)
		throws PortalException {

		return getCommerceProductPrice(
			cpInstanceId, quantity, true, commerceContext);
	}

	@Override
	public CommerceMoney getFinalPrice(
			long cpInstanceId, int quantity, boolean secure,
			CommerceContext commerceContext)
		throws PortalException {

		CommerceProductPrice commerceProductPrice = getCommerceProductPrice(
			cpInstanceId, quantity, commerceContext);

		if (commerceProductPrice == null) {
			return null;
		}

		return commerceProductPrice.getFinalPrice();
	}

	@Override
	public CommerceMoney getFinalPrice(
			long cpInstanceId, int quantity, CommerceContext commerceContext)
		throws PortalException {

		return getFinalPrice(cpInstanceId, quantity, true, commerceContext);
	}

	@Override
	public CommerceMoney getPromoPrice(
			long cpInstanceId, int quantity, CommerceCurrency commerceCurrency,
			boolean secure, CommerceContext commerceContext)
		throws PortalException {

		long commercePromoPriceListId = _getCommercePromoPriceListId(
			cpInstanceId, commerceContext);

		return _getPromoPrice(
			commercePromoPriceListId, cpInstanceId, quantity, commerceContext);
	}

	@Override
	public CommerceMoney getUnitMaxPrice(
			long cpDefinitionId, int quantity, boolean secure,
			CommerceContext commerceContext)
		throws PortalException {

		CommerceMoney commerceMoney = null;
		BigDecimal maxPrice = BigDecimal.ZERO;

		List<CPInstance> cpInstances =
			cpInstanceLocalService.getCPDefinitionInstances(
				cpDefinitionId, WorkflowConstants.STATUS_APPROVED,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		for (CPInstance cpInstance : cpInstances) {
			CommerceMoney cpInstanceCommerceMoney = getUnitPrice(
				cpInstance.getCPInstanceId(), quantity,
				commerceContext.getCommerceCurrency(), secure, commerceContext);

			if (maxPrice.compareTo(cpInstanceCommerceMoney.getPrice()) < 0) {
				commerceMoney = cpInstanceCommerceMoney;

				maxPrice = commerceMoney.getPrice();
			}
		}

		return commerceMoney;
	}

	@Override
	public CommerceMoney getUnitMaxPrice(
			long cpDefinitionId, int quantity, CommerceContext commerceContext)
		throws PortalException {

		return getUnitMaxPrice(cpDefinitionId, quantity, true, commerceContext);
	}

	@Override
	public CommerceMoney getUnitMinPrice(
			long cpDefinitionId, int quantity, boolean secure,
			CommerceContext commerceContext)
		throws PortalException {

		CommerceMoney commerceMoney = null;
		BigDecimal minPrice = BigDecimal.ZERO;

		List<CPInstance> cpInstances =
			cpInstanceLocalService.getCPDefinitionInstances(
				cpDefinitionId, WorkflowConstants.STATUS_APPROVED,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		for (CPInstance cpInstance : cpInstances) {
			CommerceMoney cpInstanceCommerceMoney = getUnitPrice(
				cpInstance.getCPInstanceId(), quantity,
				commerceContext.getCommerceCurrency(), secure, commerceContext);

			if ((commerceMoney == null) ||
				(minPrice.compareTo(cpInstanceCommerceMoney.getPrice()) > 0)) {

				commerceMoney = cpInstanceCommerceMoney;

				minPrice = commerceMoney.getPrice();
			}
		}

		return commerceMoney;
	}

	@Override
	public CommerceMoney getUnitMinPrice(
			long cpDefinitionId, int quantity, CommerceContext commerceContext)
		throws PortalException {

		return getUnitMinPrice(cpDefinitionId, quantity, true, commerceContext);
	}

	@Override
	public CommerceMoney getUnitPrice(
			long cpInstanceId, int quantity, CommerceCurrency commerceCurrency,
			boolean secure, CommerceContext commerceContext)
		throws PortalException {

		long commercePriceListId = _getCommercePriceListId(
			cpInstanceId, commerceContext);

		return _getUnitPrice(
			commercePriceListId, cpInstanceId, quantity, commerceContext);
	}

	public void unsetCommerceDiscountApplicationStrategy(
		CommerceDiscountApplicationStrategy commerceDiscountApplicationStrategy,
		Map<String, Object> properties) {

		String commerceDiscountApplicationStrategyKey = GetterUtil.getString(
			properties.get("commerce.discount.application.strategy.key"));

		_commerceDiscountApplicationStrategyMap.remove(
			commerceDiscountApplicationStrategyKey);
	}

	public void unsetCommercePriceListDiscovery(
		CommercePriceListDiscovery commercePriceListDiscovery,
		Map<String, Object> properties) {

		String commercePriceListDiscoveryKey = GetterUtil.getString(
			properties.get("commerce.price.list.discovery.key"));

		_commercePriceListDiscoveryMap.remove(commercePriceListDiscoveryKey);
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	protected void setCommerceDiscountApplicationStrategy(
		CommerceDiscountApplicationStrategy commerceDiscountApplicationStrategy,
		Map<String, Object> properties) {

		String commerceDiscountApplicationStrategyKey = GetterUtil.getString(
			properties.get("commerce.discount.application.strategy.key"));

		_commerceDiscountApplicationStrategyMap.put(
			commerceDiscountApplicationStrategyKey,
			commerceDiscountApplicationStrategy);
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	protected void setCommercePriceListDiscovery(
		CommercePriceListDiscovery commercePriceListDiscovery,
		Map<String, Object> properties) {

		String commercePriceListDiscoveryKey = GetterUtil.getString(
			properties.get("commerce.price.list.discovery.key"));

		_commercePriceListDiscoveryMap.put(
			commercePriceListDiscoveryKey, commercePriceListDiscovery);
	}

	private CommerceDiscountValue _calculateCommerceDiscountValue(
			BigDecimal[] values, int quantity, BigDecimal finalPrice,
			CommerceContext commerceContext)
		throws PortalException {

		CommerceCurrency commerceCurrency =
			commerceContext.getCommerceCurrency();

		RoundingMode roundingMode = RoundingMode.valueOf(
			commerceCurrency.getRoundingMode());

		CommerceDiscountApplicationStrategy
			commerceDiscountApplicationStrategy =
				_getCommerceDiscountApplicationStrategy();

		BigDecimal discountedAmount =
			commerceDiscountApplicationStrategy.applyCommerceDiscounts(
				finalPrice, values);

		BigDecimal currentDiscountAmount = finalPrice.subtract(
			discountedAmount);

		currentDiscountAmount = currentDiscountAmount.setScale(
			_SCALE, roundingMode);

		CommerceMoney discountAmount = commerceMoneyFactory.create(
			commerceCurrency,
			currentDiscountAmount.multiply(new BigDecimal(quantity)));

		return new CommerceDiscountValue(
			0, discountAmount,
			_getDiscountPercentage(discountedAmount, finalPrice, roundingMode),
			values);
	}

	private long _getBasePriceListId(CPInstance cpInstance)
		throws PortalException {

		CommerceCatalog commerceCatalog = cpInstance.getCommerceCatalog();

		CommercePriceList basePriceList =
			_commercePriceListLocalService.getCommerceCatalogBasePriceList(
				commerceCatalog.getGroupId());

		if (basePriceList != null) {
			return basePriceList.getCommercePriceListId();
		}

		throw new CommerceUndefinedBasePriceListException();
	}

	private CommerceDiscountApplicationStrategy
			_getCommerceDiscountApplicationStrategy()
		throws ConfigurationException {

		CommercePricingConfiguration commercePricingConfiguration =
			_configurationProvider.getSystemConfiguration(
				CommercePricingConfiguration.class);

		String commerceDiscountApplicationStrategy =
			commercePricingConfiguration.commerceDiscountApplicationStrategy();

		if (!_commerceDiscountApplicationStrategyMap.containsKey(
				commerceDiscountApplicationStrategy)) {

			if (_log.isWarnEnabled()) {
				_log.warn(
					"No commerce discount application strategy specified for " +
						commerceDiscountApplicationStrategy);
			}

			return null;
		}

		return _commerceDiscountApplicationStrategyMap.get(
			commerceDiscountApplicationStrategy);
	}

	private CommerceDiscountValue _getCommerceDiscountValue(
			long cpInstanceId, long commercePriceListId, int quantity,
			BigDecimal finalPrice, CommerceContext commerceContext)
		throws PortalException {

		CPInstance cpInstance = cpInstanceLocalService.getCPInstance(
			cpInstanceId);

		CommercePriceEntry commercePriceEntry =
			_commercePriceEntryLocalService.fetchCommercePriceEntry(
				commercePriceListId, cpInstance.getCPInstanceUuid(), false);

		BigDecimal[] values = new BigDecimal[4];

		if ((commercePriceEntry != null) &&
			!commercePriceEntry.isDiscountDiscovery()) {

			if (!commercePriceEntry.isHasTierPrice()) {
				values[0] = commercePriceEntry.getDiscountLevel1();
				values[1] = commercePriceEntry.getDiscountLevel2();
				values[2] = commercePriceEntry.getDiscountLevel3();
				values[3] = commercePriceEntry.getDiscountLevel4();

				return _calculateCommerceDiscountValue(
					values, quantity, finalPrice, commerceContext);
			}
			else if (commercePriceEntry.isHasTierPrice() &&
					 commercePriceEntry.isBulkPricing()) {

				CommerceTierPriceEntry commerceTierPriceEntry =
					_commerceTierPriceEntryLocalService.
						findClosestCommerceTierPriceEntry(
							commercePriceEntry.getCommercePriceEntryId(),
							quantity);

				if (commerceTierPriceEntry != null) {
					values[0] = commerceTierPriceEntry.getDiscountLevel1();
					values[1] = commerceTierPriceEntry.getDiscountLevel2();
					values[2] = commerceTierPriceEntry.getDiscountLevel3();
					values[3] = commerceTierPriceEntry.getDiscountLevel4();

					return _calculateCommerceDiscountValue(
						values, quantity, finalPrice, commerceContext);
				}
			}
		}

		return _commerceDiscountCalculation.getProductCommerceDiscountValue(
			cpInstanceId, quantity, finalPrice, commerceContext);
	}

	private CommerceMoney _getCommerceMoney(
			long commercePriceListId, CommerceCurrency commerceCurrency,
			BigDecimal price)
		throws PortalException {

		CommercePriceList commercePriceList =
			_commercePriceListLocalService.getCommercePriceList(
				commercePriceListId);

		CommerceCurrency priceListCurrency =
			_commerceCurrencyLocalService.getCommerceCurrency(
				commercePriceList.getCommerceCurrencyId());

		if (priceListCurrency.getCommerceCurrencyId() !=
				commerceCurrency.getCommerceCurrencyId()) {

			price = price.divide(
				priceListCurrency.getRate(),
				RoundingMode.valueOf(priceListCurrency.getRoundingMode()));

			price = price.multiply(commerceCurrency.getRate());
		}

		if (price != null) {
			return commerceMoneyFactory.create(commerceCurrency, price);
		}

		return null;
	}

	private BigDecimal _getCommercePrice(
			long commercePriceListId, CommercePriceEntry commercePriceEntry,
			int quantity)
		throws PortalException {

		if (commercePriceEntry == null) {
			return null;
		}

		BigDecimal commercePrice = commercePriceEntry.getPrice();

		CommercePriceList commercePriceList =
			_commercePriceListLocalService.getCommercePriceList(
				commercePriceEntry.getCommercePriceListId());

		CommercePriceList modifierCommercePriceList =
			_commercePriceListLocalService.getCommercePriceList(
				commercePriceListId);

		CPInstance cpInstance = commercePriceEntry.getCPInstance();

		CommerceCurrency commerceCurrency =
			_commerceCurrencyLocalService.getCommerceCurrency(
				commercePriceList.getCommerceCurrencyId());

		if (!commercePriceEntry.isHasTierPrice()) {
			if ((commercePriceEntry.getCommercePriceListId() !=
					commercePriceListId) &&
				(commercePriceList.isNetPrice() ==
					modifierCommercePriceList.isNetPrice())) {

				commercePrice =
					_commercePriceModifierHelper.applyCommercePriceModifier(
						commercePriceListId, cpInstance.getCPDefinitionId(),
						commercePriceEntry.getPriceMoney(
							commerceCurrency.getCommerceCurrencyId()));
			}

			return commercePrice;
		}

		if (commercePriceEntry.isBulkPricing()) {
			commercePrice = commercePriceEntry.getPrice();

			CommerceTierPriceEntry commerceTierPriceEntry =
				_commerceTierPriceEntryLocalService.
					findClosestCommerceTierPriceEntry(
						commercePriceEntry.getCommercePriceEntryId(), quantity);

			if (commerceTierPriceEntry != null) {
				commercePrice = commerceTierPriceEntry.getPrice();
			}

			if ((commercePriceEntry.getCommercePriceListId() !=
					commercePriceListId) &&
				(commercePriceList.isNetPrice() ==
					modifierCommercePriceList.isNetPrice())) {

				commercePrice =
					_commercePriceModifierHelper.applyCommercePriceModifier(
						commercePriceListId, cpInstance.getCPDefinitionId(),
						commerceTierPriceEntry.getPriceMoney(
							commerceCurrency.getCommerceCurrencyId()));
			}

			return commercePrice;
		}

		int totalTierCounter = 0;

		List<CommerceTierPriceEntry> commerceTierPriceEntries =
			_commerceTierPriceEntryLocalService.findCommerceTierPriceEntries(
				commercePriceEntry.getCommercePriceEntryId(), quantity);

		if ((commerceTierPriceEntries != null) &&
			!commerceTierPriceEntries.isEmpty()) {

			commercePrice = BigDecimal.ZERO;

			CommerceTierPriceEntry commerceTierPriceEntry0 =
				commerceTierPriceEntries.get(0);

			int tierCounter =
				commerceTierPriceEntry0.getMinQuantity() - totalTierCounter - 1;

			BigDecimal currentPrice = commercePriceEntry.getPrice();

			currentPrice = currentPrice.multiply(
				BigDecimal.valueOf(tierCounter));

			commercePrice = commercePrice.add(currentPrice);

			totalTierCounter += tierCounter;

			for (int i = 0; i < (commerceTierPriceEntries.size() - 1); i++) {
				CommerceTierPriceEntry commerceTierPriceEntry1 =
					commerceTierPriceEntries.get(i);

				CommerceTierPriceEntry commerceTierPriceEntry2 =
					commerceTierPriceEntries.get(i + 1);

				tierCounter =
					commerceTierPriceEntry2.getMinQuantity() -
						totalTierCounter - 1;

				currentPrice = commerceTierPriceEntry1.getPrice();

				currentPrice = currentPrice.multiply(
					BigDecimal.valueOf(tierCounter));

				commercePrice = commercePrice.add(currentPrice);

				totalTierCounter += tierCounter;
			}

			totalTierCounter = quantity - totalTierCounter;

			CommerceTierPriceEntry commerceTierPriceEntry =
				commerceTierPriceEntries.get(
					commerceTierPriceEntries.size() - 1);

			currentPrice = commerceTierPriceEntry.getPrice();

			currentPrice = currentPrice.multiply(
				BigDecimal.valueOf(totalTierCounter));

			commercePrice = commercePrice.add(currentPrice);

			RoundingMode roundingMode = RoundingMode.valueOf(
				commerceCurrency.getRoundingMode());

			commercePrice = commercePrice.divide(
				BigDecimal.valueOf(quantity), _SCALE, roundingMode);
		}

		if ((commercePriceEntry.getCommercePriceListId() !=
				commercePriceListId) &&
			(commercePriceList.isNetPrice() ==
				modifierCommercePriceList.isNetPrice())) {

			commercePrice =
				_commercePriceModifierHelper.applyCommercePriceModifier(
					commercePriceListId, cpInstance.getCPDefinitionId(),
					commerceMoneyFactory.create(
						commerceCurrency, commercePrice));
		}

		return commercePrice;
	}

	private BigDecimal _getCommercePrice(
			long cpInstanceId, long commercePriceListId,
			CommerceMoney unitPriceMoney)
		throws PortalException {

		CommercePriceList commercePriceList =
			_commercePriceListLocalService.getCommercePriceList(
				commercePriceListId);

		BigDecimal commercePrice = null;

		if (commercePriceList != null) {
			CPInstance cpInstance = cpInstanceLocalService.getCPInstance(
				cpInstanceId);

			commercePrice =
				_commercePriceModifierHelper.applyCommercePriceModifier(
					commercePriceListId, cpInstance.getCPDefinitionId(),
					unitPriceMoney);
		}

		return commercePrice;
	}

	private CommercePriceList _getCommercePriceList(
			long cpInstanceId, CommerceContext commerceContext,
			String commercePriceListType)
		throws PortalException {

		CPInstance cpInstance = cpInstanceLocalService.getCPInstance(
			cpInstanceId);

		CommerceAccount commerceAccount = commerceContext.getCommerceAccount();

		long commerceAccountId = 0;

		if (commerceAccount != null) {
			commerceAccountId = commerceAccount.getCommerceAccountId();
		}

		CommercePriceListDiscovery commercePriceListDiscovery =
			_getCommercePriceListDiscovery(commercePriceListType);

		if (commercePriceListDiscovery == null) {
			return null;
		}

		return commercePriceListDiscovery.getCommercePriceList(
			cpInstance.getGroupId(), commerceAccountId,
			commerceContext.getCommerceChannelId(),
			cpInstance.getCPInstanceUuid(), commercePriceListType);
	}

	private CommercePriceListDiscovery _getCommercePriceListDiscovery(
			String commercePriceListType)
		throws PortalException {

		CommercePricingConfiguration commercePricingConfiguration =
			_configurationProvider.getSystemConfiguration(
				CommercePricingConfiguration.class);

		String discoveryMethod = CommercePricingConstants.ORDER_BY_HIERARCHY;

		if (commercePriceListType.equals(
				CommercePriceListConstants.TYPE_PRICE_LIST)) {

			discoveryMethod =
				commercePricingConfiguration.commercePriceListDiscovery();
		}
		else if (commercePriceListType.equals(
					CommercePriceListConstants.TYPE_PROMOTION)) {

			discoveryMethod =
				commercePricingConfiguration.commercePromotionDiscovery();
		}

		if (!_commercePriceListDiscoveryMap.containsKey(discoveryMethod)) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"No commerce price list discovery specified for " +
						discoveryMethod);
			}

			return null;
		}

		return _commercePriceListDiscoveryMap.get(discoveryMethod);
	}

	private long _getCommercePriceListId(
			long cpInstanceId, CommerceContext commerceContext)
		throws PortalException {

		CommercePriceList commercePriceList = _getCommercePriceList(
			cpInstanceId, commerceContext,
			CommercePriceListConstants.TYPE_PRICE_LIST);

		long commercePriceListId = 0;

		if (commercePriceList != null) {
			commercePriceListId = commercePriceList.getCommercePriceListId();
		}

		CPInstance cpInstance = cpInstanceLocalService.getCPInstance(
			cpInstanceId);

		CommercePriceEntry commercePriceEntry =
			_commercePriceEntryLocalService.fetchCommercePriceEntry(
				commercePriceListId, cpInstance.getCPInstanceUuid(), true);

		if (commercePriceEntry != null) {
			return commercePriceEntry.getCommercePriceListId();
		}

		boolean hasCommercePriceModifiers =
			_commercePriceModifierHelper.hasCommercePriceModifiers(
				commercePriceListId, cpInstance.getCPDefinitionId());

		if (hasCommercePriceModifiers) {
			return commercePriceListId;
		}

		return _getBasePriceListId(cpInstance);
	}

	private long _getCommercePromoPriceListId(
			long cpInstanceId, CommerceContext commerceContext)
		throws PortalException {

		CommercePriceList commercePriceList = _getCommercePriceList(
			cpInstanceId, commerceContext,
			CommercePriceListConstants.TYPE_PROMOTION);

		if (commercePriceList != null) {
			return commercePriceList.getCommercePriceListId();
		}

		return 0;
	}

	private BigDecimal _getDiscountPercentage(
		BigDecimal discountedAmount, BigDecimal amount,
		RoundingMode roundingMode) {

		double actualPrice = discountedAmount.doubleValue();
		double originalPrice = amount.doubleValue();

		double percentage = actualPrice / originalPrice;

		BigDecimal discountPercentage = new BigDecimal(percentage);

		discountPercentage = discountPercentage.multiply(_ONE_HUNDRED);

		MathContext mathContext = new MathContext(
			discountPercentage.precision(), roundingMode);

		return _ONE_HUNDRED.subtract(discountPercentage, mathContext);
	}

	private CommerceMoney _getPromoPrice(
			long commercePriceListId, long cpInstanceId, int quantity,
			CommerceContext commerceContext)
		throws PortalException {

		if (commercePriceListId > 0) {
			CPInstance cpInstance = cpInstanceLocalService.getCPInstance(
				cpInstanceId);

			CommercePriceList commercePriceList =
				_commercePriceListLocalService.getCommercePriceList(
					commercePriceListId);

			CommercePriceEntry commercePriceEntry =
				_commercePriceEntryLocalService.fetchCommercePriceEntry(
					commercePriceListId, cpInstance.getCPInstanceUuid(), true);

			if (commercePriceEntry != null) {
				BigDecimal promoPrice = _getCommercePrice(
					commercePriceListId, commercePriceEntry, quantity);

				if (!commercePriceList.isNetPrice()) {
					promoPrice = getConvertedPrice(
						cpInstance.getCPInstanceId(), promoPrice, true,
						commerceContext);
				}

				return _getCommerceMoney(
					commercePriceListId, commerceContext.getCommerceCurrency(),
					promoPrice);
			}

			CommerceMoney unitPrice = getUnitPrice(
				cpInstanceId, quantity, commerceContext.getCommerceCurrency(),
				false, commerceContext);

			BigDecimal promoPrice = _getCommercePrice(
				cpInstanceId, commercePriceListId, unitPrice);

			if (!commercePriceList.isNetPrice()) {
				promoPrice = getConvertedPrice(
					cpInstance.getCPInstanceId(), promoPrice, true,
					commerceContext);
			}

			return _getCommerceMoney(
				commercePriceListId, commerceContext.getCommerceCurrency(),
				promoPrice);
		}

		return commerceMoneyFactory.create(
			commerceContext.getCommerceCurrency(), BigDecimal.ZERO);
	}

	private CommerceMoney _getUnitPrice(
			long commercePriceListId, long cpInstanceId, int quantity,
			CommerceContext commerceContext)
		throws PortalException {

		CommercePriceList commercePriceList =
			_commercePriceListLocalService.getCommercePriceList(
				commercePriceListId);

		CPInstance cpInstance = cpInstanceLocalService.getCPInstance(
			cpInstanceId);

		CommercePriceEntry commercePriceEntry =
			_commercePriceEntryLocalService.fetchCommercePriceEntry(
				commercePriceListId, cpInstance.getCPInstanceUuid(), false);

		if (commercePriceEntry == null) {
			CommercePriceEntry commerceBasePriceEntry =
				_commercePriceEntryLocalService.fetchCommercePriceEntry(
					_getBasePriceListId(cpInstance),
					cpInstance.getCPInstanceUuid(), false);

			BigDecimal unitPrice = _getCommercePrice(
				commercePriceList.getCommercePriceListId(),
				commerceBasePriceEntry, quantity);

			if (!commercePriceList.isNetPrice()) {
				unitPrice = getConvertedPrice(
					cpInstance.getCPInstanceId(), unitPrice, true,
					commerceContext);
			}

			return _getCommerceMoney(
				commercePriceListId, commerceContext.getCommerceCurrency(),
				unitPrice);
		}

		BigDecimal unitPrice = _getCommercePrice(
			commercePriceList.getCommercePriceListId(), commercePriceEntry,
			quantity);

		if (!commercePriceList.isNetPrice()) {
			unitPrice = getConvertedPrice(
				cpInstance.getCPInstanceId(), unitPrice, true, commerceContext);
		}

		return _getCommerceMoney(
			commercePriceListId, commerceContext.getCommerceCurrency(),
			unitPrice);
	}

	private static final BigDecimal _ONE_HUNDRED = BigDecimal.valueOf(100);

	private static final int _SCALE = 10;

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceProductPriceCalculationV2Impl.class);

	@Reference
	private CommerceChannelLocalService _commerceChannelLocalService;

	@Reference
	private CommerceCurrencyLocalService _commerceCurrencyLocalService;

	private final Map<String, CommerceDiscountApplicationStrategy>
		_commerceDiscountApplicationStrategyMap = new ConcurrentHashMap<>();

	@Reference(target = "(commerce.discount.calculation.key=v2.0)")
	private CommerceDiscountCalculation _commerceDiscountCalculation;

	@Reference
	private CommerceMoneyFactory _commerceMoneyFactory;

	@Reference
	private CommercePriceEntryLocalService _commercePriceEntryLocalService;

	private final Map<String, CommercePriceListDiscovery>
		_commercePriceListDiscoveryMap = new ConcurrentHashMap<>();

	@Reference
	private CommercePriceListLocalService _commercePriceListLocalService;

	@Reference
	private CommercePriceModifierHelper _commercePriceModifierHelper;

	@Reference
	private CommerceTierPriceEntryLocalService
		_commerceTierPriceEntryLocalService;

	@Reference
	private ConfigurationProvider _configurationProvider;

}