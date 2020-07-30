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

package com.liferay.commerce.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.math.BigDecimal;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link CommerceOrderItem}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommerceOrderItem
 * @generated
 */
public class CommerceOrderItemWrapper
	extends BaseModelWrapper<CommerceOrderItem>
	implements CommerceOrderItem, ModelWrapper<CommerceOrderItem> {

	public CommerceOrderItemWrapper(CommerceOrderItem commerceOrderItem) {
		super(commerceOrderItem);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("externalReferenceCode", getExternalReferenceCode());
		attributes.put("commerceOrderItemId", getCommerceOrderItemId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("commerceOrderId", getCommerceOrderId());
		attributes.put("commercePriceListId", getCommercePriceListId());
		attributes.put("CProductId", getCProductId());
		attributes.put("CPInstanceId", getCPInstanceId());
		attributes.put(
			"parentCommerceOrderItemId", getParentCommerceOrderItemId());
		attributes.put("quantity", getQuantity());
		attributes.put("shippedQuantity", getShippedQuantity());
		attributes.put("json", getJson());
		attributes.put("name", getName());
		attributes.put("sku", getSku());
		attributes.put("unitPrice", getUnitPrice());
		attributes.put("promoPrice", getPromoPrice());
		attributes.put("discountAmount", getDiscountAmount());
		attributes.put("finalPrice", getFinalPrice());
		attributes.put(
			"discountPercentageLevel1", getDiscountPercentageLevel1());
		attributes.put(
			"discountPercentageLevel2", getDiscountPercentageLevel2());
		attributes.put(
			"discountPercentageLevel3", getDiscountPercentageLevel3());
		attributes.put(
			"discountPercentageLevel4", getDiscountPercentageLevel4());
		attributes.put("unitPriceWithTaxAmount", getUnitPriceWithTaxAmount());
		attributes.put("promoPriceWithTaxAmount", getPromoPriceWithTaxAmount());
		attributes.put("discountWithTaxAmount", getDiscountWithTaxAmount());
		attributes.put("finalPriceWithTaxAmount", getFinalPriceWithTaxAmount());
		attributes.put(
			"discountPercentageLevel1WithTaxAmount",
			getDiscountPercentageLevel1WithTaxAmount());
		attributes.put(
			"discountPercentageLevel2WithTaxAmount",
			getDiscountPercentageLevel2WithTaxAmount());
		attributes.put(
			"discountPercentageLevel3WithTaxAmount",
			getDiscountPercentageLevel3WithTaxAmount());
		attributes.put(
			"discountPercentageLevel4WithTaxAmount",
			getDiscountPercentageLevel4WithTaxAmount());
		attributes.put("subscription", isSubscription());
		attributes.put("deliveryGroup", getDeliveryGroup());
		attributes.put("shippingAddressId", getShippingAddressId());
		attributes.put("printedNote", getPrintedNote());
		attributes.put("requestedDeliveryDate", getRequestedDeliveryDate());
		attributes.put("bookedQuantityId", getBookedQuantityId());
		attributes.put("manuallyAdjusted", isManuallyAdjusted());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String externalReferenceCode = (String)attributes.get(
			"externalReferenceCode");

		if (externalReferenceCode != null) {
			setExternalReferenceCode(externalReferenceCode);
		}

		Long commerceOrderItemId = (Long)attributes.get("commerceOrderItemId");

		if (commerceOrderItemId != null) {
			setCommerceOrderItemId(commerceOrderItemId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long commerceOrderId = (Long)attributes.get("commerceOrderId");

		if (commerceOrderId != null) {
			setCommerceOrderId(commerceOrderId);
		}

		Long commercePriceListId = (Long)attributes.get("commercePriceListId");

		if (commercePriceListId != null) {
			setCommercePriceListId(commercePriceListId);
		}

		Long CProductId = (Long)attributes.get("CProductId");

		if (CProductId != null) {
			setCProductId(CProductId);
		}

		Long CPInstanceId = (Long)attributes.get("CPInstanceId");

		if (CPInstanceId != null) {
			setCPInstanceId(CPInstanceId);
		}

		Long parentCommerceOrderItemId = (Long)attributes.get(
			"parentCommerceOrderItemId");

		if (parentCommerceOrderItemId != null) {
			setParentCommerceOrderItemId(parentCommerceOrderItemId);
		}

		Integer quantity = (Integer)attributes.get("quantity");

		if (quantity != null) {
			setQuantity(quantity);
		}

		Integer shippedQuantity = (Integer)attributes.get("shippedQuantity");

		if (shippedQuantity != null) {
			setShippedQuantity(shippedQuantity);
		}

		String json = (String)attributes.get("json");

		if (json != null) {
			setJson(json);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String sku = (String)attributes.get("sku");

		if (sku != null) {
			setSku(sku);
		}

		BigDecimal unitPrice = (BigDecimal)attributes.get("unitPrice");

		if (unitPrice != null) {
			setUnitPrice(unitPrice);
		}

		BigDecimal promoPrice = (BigDecimal)attributes.get("promoPrice");

		if (promoPrice != null) {
			setPromoPrice(promoPrice);
		}

		BigDecimal discountAmount = (BigDecimal)attributes.get(
			"discountAmount");

		if (discountAmount != null) {
			setDiscountAmount(discountAmount);
		}

		BigDecimal finalPrice = (BigDecimal)attributes.get("finalPrice");

		if (finalPrice != null) {
			setFinalPrice(finalPrice);
		}

		BigDecimal discountPercentageLevel1 = (BigDecimal)attributes.get(
			"discountPercentageLevel1");

		if (discountPercentageLevel1 != null) {
			setDiscountPercentageLevel1(discountPercentageLevel1);
		}

		BigDecimal discountPercentageLevel2 = (BigDecimal)attributes.get(
			"discountPercentageLevel2");

		if (discountPercentageLevel2 != null) {
			setDiscountPercentageLevel2(discountPercentageLevel2);
		}

		BigDecimal discountPercentageLevel3 = (BigDecimal)attributes.get(
			"discountPercentageLevel3");

		if (discountPercentageLevel3 != null) {
			setDiscountPercentageLevel3(discountPercentageLevel3);
		}

		BigDecimal discountPercentageLevel4 = (BigDecimal)attributes.get(
			"discountPercentageLevel4");

		if (discountPercentageLevel4 != null) {
			setDiscountPercentageLevel4(discountPercentageLevel4);
		}

		BigDecimal unitPriceWithTaxAmount = (BigDecimal)attributes.get(
			"unitPriceWithTaxAmount");

		if (unitPriceWithTaxAmount != null) {
			setUnitPriceWithTaxAmount(unitPriceWithTaxAmount);
		}

		BigDecimal promoPriceWithTaxAmount = (BigDecimal)attributes.get(
			"promoPriceWithTaxAmount");

		if (promoPriceWithTaxAmount != null) {
			setPromoPriceWithTaxAmount(promoPriceWithTaxAmount);
		}

		BigDecimal discountWithTaxAmount = (BigDecimal)attributes.get(
			"discountWithTaxAmount");

		if (discountWithTaxAmount != null) {
			setDiscountWithTaxAmount(discountWithTaxAmount);
		}

		BigDecimal finalPriceWithTaxAmount = (BigDecimal)attributes.get(
			"finalPriceWithTaxAmount");

		if (finalPriceWithTaxAmount != null) {
			setFinalPriceWithTaxAmount(finalPriceWithTaxAmount);
		}

		BigDecimal discountPercentageLevel1WithTaxAmount =
			(BigDecimal)attributes.get("discountPercentageLevel1WithTaxAmount");

		if (discountPercentageLevel1WithTaxAmount != null) {
			setDiscountPercentageLevel1WithTaxAmount(
				discountPercentageLevel1WithTaxAmount);
		}

		BigDecimal discountPercentageLevel2WithTaxAmount =
			(BigDecimal)attributes.get("discountPercentageLevel2WithTaxAmount");

		if (discountPercentageLevel2WithTaxAmount != null) {
			setDiscountPercentageLevel2WithTaxAmount(
				discountPercentageLevel2WithTaxAmount);
		}

		BigDecimal discountPercentageLevel3WithTaxAmount =
			(BigDecimal)attributes.get("discountPercentageLevel3WithTaxAmount");

		if (discountPercentageLevel3WithTaxAmount != null) {
			setDiscountPercentageLevel3WithTaxAmount(
				discountPercentageLevel3WithTaxAmount);
		}

		BigDecimal discountPercentageLevel4WithTaxAmount =
			(BigDecimal)attributes.get("discountPercentageLevel4WithTaxAmount");

		if (discountPercentageLevel4WithTaxAmount != null) {
			setDiscountPercentageLevel4WithTaxAmount(
				discountPercentageLevel4WithTaxAmount);
		}

		Boolean subscription = (Boolean)attributes.get("subscription");

		if (subscription != null) {
			setSubscription(subscription);
		}

		String deliveryGroup = (String)attributes.get("deliveryGroup");

		if (deliveryGroup != null) {
			setDeliveryGroup(deliveryGroup);
		}

		Long shippingAddressId = (Long)attributes.get("shippingAddressId");

		if (shippingAddressId != null) {
			setShippingAddressId(shippingAddressId);
		}

		String printedNote = (String)attributes.get("printedNote");

		if (printedNote != null) {
			setPrintedNote(printedNote);
		}

		Date requestedDeliveryDate = (Date)attributes.get(
			"requestedDeliveryDate");

		if (requestedDeliveryDate != null) {
			setRequestedDeliveryDate(requestedDeliveryDate);
		}

		Long bookedQuantityId = (Long)attributes.get("bookedQuantityId");

		if (bookedQuantityId != null) {
			setBookedQuantityId(bookedQuantityId);
		}

		Boolean manuallyAdjusted = (Boolean)attributes.get("manuallyAdjusted");

		if (manuallyAdjusted != null) {
			setManuallyAdjusted(manuallyAdjusted);
		}
	}

	@Override
	public com.liferay.commerce.product.model.CPInstance fetchCPInstance() {
		return model.fetchCPInstance();
	}

	@Override
	public com.liferay.commerce.product.model.CProduct fetchCProduct() {
		return model.fetchCProduct();
	}

	@Override
	public String[] getAvailableLanguageIds() {
		return model.getAvailableLanguageIds();
	}

	/**
	 * Returns the booked quantity ID of this commerce order item.
	 *
	 * @return the booked quantity ID of this commerce order item
	 */
	@Override
	public long getBookedQuantityId() {
		return model.getBookedQuantityId();
	}

	@Override
	public CommerceOrder getCommerceOrder()
		throws com.liferay.portal.kernel.exception.PortalException {

		return model.getCommerceOrder();
	}

	/**
	 * Returns the commerce order ID of this commerce order item.
	 *
	 * @return the commerce order ID of this commerce order item
	 */
	@Override
	public long getCommerceOrderId() {
		return model.getCommerceOrderId();
	}

	/**
	 * Returns the commerce order item ID of this commerce order item.
	 *
	 * @return the commerce order item ID of this commerce order item
	 */
	@Override
	public long getCommerceOrderItemId() {
		return model.getCommerceOrderItemId();
	}

	/**
	 * Returns the commerce price list ID of this commerce order item.
	 *
	 * @return the commerce price list ID of this commerce order item
	 */
	@Override
	public long getCommercePriceListId() {
		return model.getCommercePriceListId();
	}

	/**
	 * Returns the company ID of this commerce order item.
	 *
	 * @return the company ID of this commerce order item
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	@Override
	public com.liferay.commerce.product.model.CPDefinition getCPDefinition()
		throws com.liferay.portal.kernel.exception.PortalException {

		return model.getCPDefinition();
	}

	@Override
	public long getCPDefinitionId() {
		return model.getCPDefinitionId();
	}

	/**
	 * @deprecated As of Athanasius (7.3.x)
	 */
	@Deprecated
	@Override
	public com.liferay.commerce.product.model.CPInstance getCPInstance()
		throws com.liferay.portal.kernel.exception.PortalException {

		return model.getCPInstance();
	}

	/**
	 * Returns the cp instance ID of this commerce order item.
	 *
	 * @return the cp instance ID of this commerce order item
	 */
	@Override
	public long getCPInstanceId() {
		return model.getCPInstanceId();
	}

	/**
	 * @deprecated As of Athanasius (7.3.x)
	 */
	@Deprecated
	@Override
	public com.liferay.commerce.product.model.CProduct getCProduct()
		throws com.liferay.portal.kernel.exception.PortalException {

		return model.getCProduct();
	}

	/**
	 * Returns the c product ID of this commerce order item.
	 *
	 * @return the c product ID of this commerce order item
	 */
	@Override
	public long getCProductId() {
		return model.getCProductId();
	}

	/**
	 * Returns the create date of this commerce order item.
	 *
	 * @return the create date of this commerce order item
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	@Override
	public String getDefaultLanguageId() {
		return model.getDefaultLanguageId();
	}

	/**
	 * Returns the delivery group of this commerce order item.
	 *
	 * @return the delivery group of this commerce order item
	 */
	@Override
	public String getDeliveryGroup() {
		return model.getDeliveryGroup();
	}

	/**
	 * Returns the discount amount of this commerce order item.
	 *
	 * @return the discount amount of this commerce order item
	 */
	@Override
	public BigDecimal getDiscountAmount() {
		return model.getDiscountAmount();
	}

	@Override
	public com.liferay.commerce.currency.model.CommerceMoney
			getDiscountAmountMoney()
		throws com.liferay.portal.kernel.exception.PortalException {

		return model.getDiscountAmountMoney();
	}

	/**
	 * Returns the discount percentage level1 of this commerce order item.
	 *
	 * @return the discount percentage level1 of this commerce order item
	 */
	@Override
	public BigDecimal getDiscountPercentageLevel1() {
		return model.getDiscountPercentageLevel1();
	}

	/**
	 * Returns the discount percentage level1 with tax amount of this commerce order item.
	 *
	 * @return the discount percentage level1 with tax amount of this commerce order item
	 */
	@Override
	public BigDecimal getDiscountPercentageLevel1WithTaxAmount() {
		return model.getDiscountPercentageLevel1WithTaxAmount();
	}

	/**
	 * Returns the discount percentage level2 of this commerce order item.
	 *
	 * @return the discount percentage level2 of this commerce order item
	 */
	@Override
	public BigDecimal getDiscountPercentageLevel2() {
		return model.getDiscountPercentageLevel2();
	}

	/**
	 * Returns the discount percentage level2 with tax amount of this commerce order item.
	 *
	 * @return the discount percentage level2 with tax amount of this commerce order item
	 */
	@Override
	public BigDecimal getDiscountPercentageLevel2WithTaxAmount() {
		return model.getDiscountPercentageLevel2WithTaxAmount();
	}

	/**
	 * Returns the discount percentage level3 of this commerce order item.
	 *
	 * @return the discount percentage level3 of this commerce order item
	 */
	@Override
	public BigDecimal getDiscountPercentageLevel3() {
		return model.getDiscountPercentageLevel3();
	}

	/**
	 * Returns the discount percentage level3 with tax amount of this commerce order item.
	 *
	 * @return the discount percentage level3 with tax amount of this commerce order item
	 */
	@Override
	public BigDecimal getDiscountPercentageLevel3WithTaxAmount() {
		return model.getDiscountPercentageLevel3WithTaxAmount();
	}

	/**
	 * Returns the discount percentage level4 of this commerce order item.
	 *
	 * @return the discount percentage level4 of this commerce order item
	 */
	@Override
	public BigDecimal getDiscountPercentageLevel4() {
		return model.getDiscountPercentageLevel4();
	}

	/**
	 * Returns the discount percentage level4 with tax amount of this commerce order item.
	 *
	 * @return the discount percentage level4 with tax amount of this commerce order item
	 */
	@Override
	public BigDecimal getDiscountPercentageLevel4WithTaxAmount() {
		return model.getDiscountPercentageLevel4WithTaxAmount();
	}

	/**
	 * Returns the discount with tax amount of this commerce order item.
	 *
	 * @return the discount with tax amount of this commerce order item
	 */
	@Override
	public BigDecimal getDiscountWithTaxAmount() {
		return model.getDiscountWithTaxAmount();
	}

	@Override
	public com.liferay.commerce.currency.model.CommerceMoney
			getDiscountWithTaxAmountMoney()
		throws com.liferay.portal.kernel.exception.PortalException {

		return model.getDiscountWithTaxAmountMoney();
	}

	/**
	 * Returns the external reference code of this commerce order item.
	 *
	 * @return the external reference code of this commerce order item
	 */
	@Override
	public String getExternalReferenceCode() {
		return model.getExternalReferenceCode();
	}

	/**
	 * Returns the final price of this commerce order item.
	 *
	 * @return the final price of this commerce order item
	 */
	@Override
	public BigDecimal getFinalPrice() {
		return model.getFinalPrice();
	}

	@Override
	public com.liferay.commerce.currency.model.CommerceMoney
			getFinalPriceMoney()
		throws com.liferay.portal.kernel.exception.PortalException {

		return model.getFinalPriceMoney();
	}

	/**
	 * Returns the final price with tax amount of this commerce order item.
	 *
	 * @return the final price with tax amount of this commerce order item
	 */
	@Override
	public BigDecimal getFinalPriceWithTaxAmount() {
		return model.getFinalPriceWithTaxAmount();
	}

	@Override
	public com.liferay.commerce.currency.model.CommerceMoney
			getFinalPriceWithTaxAmountMoney()
		throws com.liferay.portal.kernel.exception.PortalException {

		return model.getFinalPriceWithTaxAmountMoney();
	}

	/**
	 * Returns the group ID of this commerce order item.
	 *
	 * @return the group ID of this commerce order item
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the json of this commerce order item.
	 *
	 * @return the json of this commerce order item
	 */
	@Override
	public String getJson() {
		return model.getJson();
	}

	/**
	 * Returns the manually adjusted of this commerce order item.
	 *
	 * @return the manually adjusted of this commerce order item
	 */
	@Override
	public boolean getManuallyAdjusted() {
		return model.getManuallyAdjusted();
	}

	/**
	 * Returns the modified date of this commerce order item.
	 *
	 * @return the modified date of this commerce order item
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the name of this commerce order item.
	 *
	 * @return the name of this commerce order item
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the localized name of this commerce order item in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized name of this commerce order item
	 */
	@Override
	public String getName(java.util.Locale locale) {
		return model.getName(locale);
	}

	/**
	 * Returns the localized name of this commerce order item in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized name of this commerce order item. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getName(java.util.Locale locale, boolean useDefault) {
		return model.getName(locale, useDefault);
	}

	/**
	 * Returns the localized name of this commerce order item in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized name of this commerce order item
	 */
	@Override
	public String getName(String languageId) {
		return model.getName(languageId);
	}

	/**
	 * Returns the localized name of this commerce order item in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized name of this commerce order item
	 */
	@Override
	public String getName(String languageId, boolean useDefault) {
		return model.getName(languageId, useDefault);
	}

	@Override
	public String getNameCurrentLanguageId() {
		return model.getNameCurrentLanguageId();
	}

	@Override
	public String getNameCurrentValue() {
		return model.getNameCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized names of this commerce order item.
	 *
	 * @return the locales and localized names of this commerce order item
	 */
	@Override
	public Map<java.util.Locale, String> getNameMap() {
		return model.getNameMap();
	}

	@Override
	public long getParentCommerceOrderItemCPDefinitionId() {
		return model.getParentCommerceOrderItemCPDefinitionId();
	}

	/**
	 * Returns the parent commerce order item ID of this commerce order item.
	 *
	 * @return the parent commerce order item ID of this commerce order item
	 */
	@Override
	public long getParentCommerceOrderItemId() {
		return model.getParentCommerceOrderItemId();
	}

	/**
	 * Returns the primary key of this commerce order item.
	 *
	 * @return the primary key of this commerce order item
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the printed note of this commerce order item.
	 *
	 * @return the printed note of this commerce order item
	 */
	@Override
	public String getPrintedNote() {
		return model.getPrintedNote();
	}

	/**
	 * Returns the promo price of this commerce order item.
	 *
	 * @return the promo price of this commerce order item
	 */
	@Override
	public BigDecimal getPromoPrice() {
		return model.getPromoPrice();
	}

	@Override
	public com.liferay.commerce.currency.model.CommerceMoney
			getPromoPriceMoney()
		throws com.liferay.portal.kernel.exception.PortalException {

		return model.getPromoPriceMoney();
	}

	/**
	 * Returns the promo price with tax amount of this commerce order item.
	 *
	 * @return the promo price with tax amount of this commerce order item
	 */
	@Override
	public BigDecimal getPromoPriceWithTaxAmount() {
		return model.getPromoPriceWithTaxAmount();
	}

	@Override
	public com.liferay.commerce.currency.model.CommerceMoney
			getPromoPriceWithTaxAmountMoney()
		throws com.liferay.portal.kernel.exception.PortalException {

		return model.getPromoPriceWithTaxAmountMoney();
	}

	/**
	 * Returns the quantity of this commerce order item.
	 *
	 * @return the quantity of this commerce order item
	 */
	@Override
	public int getQuantity() {
		return model.getQuantity();
	}

	/**
	 * Returns the requested delivery date of this commerce order item.
	 *
	 * @return the requested delivery date of this commerce order item
	 */
	@Override
	public Date getRequestedDeliveryDate() {
		return model.getRequestedDeliveryDate();
	}

	/**
	 * Returns the shipped quantity of this commerce order item.
	 *
	 * @return the shipped quantity of this commerce order item
	 */
	@Override
	public int getShippedQuantity() {
		return model.getShippedQuantity();
	}

	/**
	 * Returns the shipping address ID of this commerce order item.
	 *
	 * @return the shipping address ID of this commerce order item
	 */
	@Override
	public long getShippingAddressId() {
		return model.getShippingAddressId();
	}

	/**
	 * Returns the sku of this commerce order item.
	 *
	 * @return the sku of this commerce order item
	 */
	@Override
	public String getSku() {
		return model.getSku();
	}

	/**
	 * Returns the subscription of this commerce order item.
	 *
	 * @return the subscription of this commerce order item
	 */
	@Override
	public boolean getSubscription() {
		return model.getSubscription();
	}

	/**
	 * Returns the unit price of this commerce order item.
	 *
	 * @return the unit price of this commerce order item
	 */
	@Override
	public BigDecimal getUnitPrice() {
		return model.getUnitPrice();
	}

	@Override
	public com.liferay.commerce.currency.model.CommerceMoney getUnitPriceMoney()
		throws com.liferay.portal.kernel.exception.PortalException {

		return model.getUnitPriceMoney();
	}

	/**
	 * Returns the unit price with tax amount of this commerce order item.
	 *
	 * @return the unit price with tax amount of this commerce order item
	 */
	@Override
	public BigDecimal getUnitPriceWithTaxAmount() {
		return model.getUnitPriceWithTaxAmount();
	}

	@Override
	public com.liferay.commerce.currency.model.CommerceMoney
			getUnitPriceWithTaxAmountMoney()
		throws com.liferay.portal.kernel.exception.PortalException {

		return model.getUnitPriceWithTaxAmountMoney();
	}

	/**
	 * Returns the user ID of this commerce order item.
	 *
	 * @return the user ID of this commerce order item
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this commerce order item.
	 *
	 * @return the user name of this commerce order item
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this commerce order item.
	 *
	 * @return the user uuid of this commerce order item
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	@Override
	public boolean hasParentCommerceOrderItem() {
		return model.hasParentCommerceOrderItem();
	}

	/**
	 * Returns <code>true</code> if this commerce order item is manually adjusted.
	 *
	 * @return <code>true</code> if this commerce order item is manually adjusted; <code>false</code> otherwise
	 */
	@Override
	public boolean isManuallyAdjusted() {
		return model.isManuallyAdjusted();
	}

	/**
	 * Returns <code>true</code> if this commerce order item is subscription.
	 *
	 * @return <code>true</code> if this commerce order item is subscription; <code>false</code> otherwise
	 */
	@Override
	public boolean isSubscription() {
		return model.isSubscription();
	}

	@Override
	public void persist() {
		model.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {

		model.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
			java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {

		model.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	/**
	 * Sets the booked quantity ID of this commerce order item.
	 *
	 * @param bookedQuantityId the booked quantity ID of this commerce order item
	 */
	@Override
	public void setBookedQuantityId(long bookedQuantityId) {
		model.setBookedQuantityId(bookedQuantityId);
	}

	/**
	 * Sets the commerce order ID of this commerce order item.
	 *
	 * @param commerceOrderId the commerce order ID of this commerce order item
	 */
	@Override
	public void setCommerceOrderId(long commerceOrderId) {
		model.setCommerceOrderId(commerceOrderId);
	}

	/**
	 * Sets the commerce order item ID of this commerce order item.
	 *
	 * @param commerceOrderItemId the commerce order item ID of this commerce order item
	 */
	@Override
	public void setCommerceOrderItemId(long commerceOrderItemId) {
		model.setCommerceOrderItemId(commerceOrderItemId);
	}

	/**
	 * Sets the commerce price list ID of this commerce order item.
	 *
	 * @param commercePriceListId the commerce price list ID of this commerce order item
	 */
	@Override
	public void setCommercePriceListId(long commercePriceListId) {
		model.setCommercePriceListId(commercePriceListId);
	}

	/**
	 * Sets the company ID of this commerce order item.
	 *
	 * @param companyId the company ID of this commerce order item
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the cp instance ID of this commerce order item.
	 *
	 * @param CPInstanceId the cp instance ID of this commerce order item
	 */
	@Override
	public void setCPInstanceId(long CPInstanceId) {
		model.setCPInstanceId(CPInstanceId);
	}

	/**
	 * Sets the c product ID of this commerce order item.
	 *
	 * @param CProductId the c product ID of this commerce order item
	 */
	@Override
	public void setCProductId(long CProductId) {
		model.setCProductId(CProductId);
	}

	/**
	 * Sets the create date of this commerce order item.
	 *
	 * @param createDate the create date of this commerce order item
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the delivery group of this commerce order item.
	 *
	 * @param deliveryGroup the delivery group of this commerce order item
	 */
	@Override
	public void setDeliveryGroup(String deliveryGroup) {
		model.setDeliveryGroup(deliveryGroup);
	}

	/**
	 * Sets the discount amount of this commerce order item.
	 *
	 * @param discountAmount the discount amount of this commerce order item
	 */
	@Override
	public void setDiscountAmount(BigDecimal discountAmount) {
		model.setDiscountAmount(discountAmount);
	}

	/**
	 * Sets the discount percentage level1 of this commerce order item.
	 *
	 * @param discountPercentageLevel1 the discount percentage level1 of this commerce order item
	 */
	@Override
	public void setDiscountPercentageLevel1(
		BigDecimal discountPercentageLevel1) {

		model.setDiscountPercentageLevel1(discountPercentageLevel1);
	}

	/**
	 * Sets the discount percentage level1 with tax amount of this commerce order item.
	 *
	 * @param discountPercentageLevel1WithTaxAmount the discount percentage level1 with tax amount of this commerce order item
	 */
	@Override
	public void setDiscountPercentageLevel1WithTaxAmount(
		BigDecimal discountPercentageLevel1WithTaxAmount) {

		model.setDiscountPercentageLevel1WithTaxAmount(
			discountPercentageLevel1WithTaxAmount);
	}

	/**
	 * Sets the discount percentage level2 of this commerce order item.
	 *
	 * @param discountPercentageLevel2 the discount percentage level2 of this commerce order item
	 */
	@Override
	public void setDiscountPercentageLevel2(
		BigDecimal discountPercentageLevel2) {

		model.setDiscountPercentageLevel2(discountPercentageLevel2);
	}

	/**
	 * Sets the discount percentage level2 with tax amount of this commerce order item.
	 *
	 * @param discountPercentageLevel2WithTaxAmount the discount percentage level2 with tax amount of this commerce order item
	 */
	@Override
	public void setDiscountPercentageLevel2WithTaxAmount(
		BigDecimal discountPercentageLevel2WithTaxAmount) {

		model.setDiscountPercentageLevel2WithTaxAmount(
			discountPercentageLevel2WithTaxAmount);
	}

	/**
	 * Sets the discount percentage level3 of this commerce order item.
	 *
	 * @param discountPercentageLevel3 the discount percentage level3 of this commerce order item
	 */
	@Override
	public void setDiscountPercentageLevel3(
		BigDecimal discountPercentageLevel3) {

		model.setDiscountPercentageLevel3(discountPercentageLevel3);
	}

	/**
	 * Sets the discount percentage level3 with tax amount of this commerce order item.
	 *
	 * @param discountPercentageLevel3WithTaxAmount the discount percentage level3 with tax amount of this commerce order item
	 */
	@Override
	public void setDiscountPercentageLevel3WithTaxAmount(
		BigDecimal discountPercentageLevel3WithTaxAmount) {

		model.setDiscountPercentageLevel3WithTaxAmount(
			discountPercentageLevel3WithTaxAmount);
	}

	/**
	 * Sets the discount percentage level4 of this commerce order item.
	 *
	 * @param discountPercentageLevel4 the discount percentage level4 of this commerce order item
	 */
	@Override
	public void setDiscountPercentageLevel4(
		BigDecimal discountPercentageLevel4) {

		model.setDiscountPercentageLevel4(discountPercentageLevel4);
	}

	/**
	 * Sets the discount percentage level4 with tax amount of this commerce order item.
	 *
	 * @param discountPercentageLevel4WithTaxAmount the discount percentage level4 with tax amount of this commerce order item
	 */
	@Override
	public void setDiscountPercentageLevel4WithTaxAmount(
		BigDecimal discountPercentageLevel4WithTaxAmount) {

		model.setDiscountPercentageLevel4WithTaxAmount(
			discountPercentageLevel4WithTaxAmount);
	}

	/**
	 * Sets the discount with tax amount of this commerce order item.
	 *
	 * @param discountWithTaxAmount the discount with tax amount of this commerce order item
	 */
	@Override
	public void setDiscountWithTaxAmount(BigDecimal discountWithTaxAmount) {
		model.setDiscountWithTaxAmount(discountWithTaxAmount);
	}

	/**
	 * Sets the external reference code of this commerce order item.
	 *
	 * @param externalReferenceCode the external reference code of this commerce order item
	 */
	@Override
	public void setExternalReferenceCode(String externalReferenceCode) {
		model.setExternalReferenceCode(externalReferenceCode);
	}

	/**
	 * Sets the final price of this commerce order item.
	 *
	 * @param finalPrice the final price of this commerce order item
	 */
	@Override
	public void setFinalPrice(BigDecimal finalPrice) {
		model.setFinalPrice(finalPrice);
	}

	/**
	 * Sets the final price with tax amount of this commerce order item.
	 *
	 * @param finalPriceWithTaxAmount the final price with tax amount of this commerce order item
	 */
	@Override
	public void setFinalPriceWithTaxAmount(BigDecimal finalPriceWithTaxAmount) {
		model.setFinalPriceWithTaxAmount(finalPriceWithTaxAmount);
	}

	/**
	 * Sets the group ID of this commerce order item.
	 *
	 * @param groupId the group ID of this commerce order item
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the json of this commerce order item.
	 *
	 * @param json the json of this commerce order item
	 */
	@Override
	public void setJson(String json) {
		model.setJson(json);
	}

	/**
	 * Sets whether this commerce order item is manually adjusted.
	 *
	 * @param manuallyAdjusted the manually adjusted of this commerce order item
	 */
	@Override
	public void setManuallyAdjusted(boolean manuallyAdjusted) {
		model.setManuallyAdjusted(manuallyAdjusted);
	}

	/**
	 * Sets the modified date of this commerce order item.
	 *
	 * @param modifiedDate the modified date of this commerce order item
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the name of this commerce order item.
	 *
	 * @param name the name of this commerce order item
	 */
	@Override
	public void setName(String name) {
		model.setName(name);
	}

	/**
	 * Sets the localized name of this commerce order item in the language.
	 *
	 * @param name the localized name of this commerce order item
	 * @param locale the locale of the language
	 */
	@Override
	public void setName(String name, java.util.Locale locale) {
		model.setName(name, locale);
	}

	/**
	 * Sets the localized name of this commerce order item in the language, and sets the default locale.
	 *
	 * @param name the localized name of this commerce order item
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setName(
		String name, java.util.Locale locale, java.util.Locale defaultLocale) {

		model.setName(name, locale, defaultLocale);
	}

	@Override
	public void setNameCurrentLanguageId(String languageId) {
		model.setNameCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized names of this commerce order item from the map of locales and localized names.
	 *
	 * @param nameMap the locales and localized names of this commerce order item
	 */
	@Override
	public void setNameMap(Map<java.util.Locale, String> nameMap) {
		model.setNameMap(nameMap);
	}

	/**
	 * Sets the localized names of this commerce order item from the map of locales and localized names, and sets the default locale.
	 *
	 * @param nameMap the locales and localized names of this commerce order item
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setNameMap(
		Map<java.util.Locale, String> nameMap, java.util.Locale defaultLocale) {

		model.setNameMap(nameMap, defaultLocale);
	}

	/**
	 * Sets the parent commerce order item ID of this commerce order item.
	 *
	 * @param parentCommerceOrderItemId the parent commerce order item ID of this commerce order item
	 */
	@Override
	public void setParentCommerceOrderItemId(long parentCommerceOrderItemId) {
		model.setParentCommerceOrderItemId(parentCommerceOrderItemId);
	}

	/**
	 * Sets the primary key of this commerce order item.
	 *
	 * @param primaryKey the primary key of this commerce order item
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the printed note of this commerce order item.
	 *
	 * @param printedNote the printed note of this commerce order item
	 */
	@Override
	public void setPrintedNote(String printedNote) {
		model.setPrintedNote(printedNote);
	}

	/**
	 * Sets the promo price of this commerce order item.
	 *
	 * @param promoPrice the promo price of this commerce order item
	 */
	@Override
	public void setPromoPrice(BigDecimal promoPrice) {
		model.setPromoPrice(promoPrice);
	}

	/**
	 * Sets the promo price with tax amount of this commerce order item.
	 *
	 * @param promoPriceWithTaxAmount the promo price with tax amount of this commerce order item
	 */
	@Override
	public void setPromoPriceWithTaxAmount(BigDecimal promoPriceWithTaxAmount) {
		model.setPromoPriceWithTaxAmount(promoPriceWithTaxAmount);
	}

	/**
	 * Sets the quantity of this commerce order item.
	 *
	 * @param quantity the quantity of this commerce order item
	 */
	@Override
	public void setQuantity(int quantity) {
		model.setQuantity(quantity);
	}

	/**
	 * Sets the requested delivery date of this commerce order item.
	 *
	 * @param requestedDeliveryDate the requested delivery date of this commerce order item
	 */
	@Override
	public void setRequestedDeliveryDate(Date requestedDeliveryDate) {
		model.setRequestedDeliveryDate(requestedDeliveryDate);
	}

	/**
	 * Sets the shipped quantity of this commerce order item.
	 *
	 * @param shippedQuantity the shipped quantity of this commerce order item
	 */
	@Override
	public void setShippedQuantity(int shippedQuantity) {
		model.setShippedQuantity(shippedQuantity);
	}

	/**
	 * Sets the shipping address ID of this commerce order item.
	 *
	 * @param shippingAddressId the shipping address ID of this commerce order item
	 */
	@Override
	public void setShippingAddressId(long shippingAddressId) {
		model.setShippingAddressId(shippingAddressId);
	}

	/**
	 * Sets the sku of this commerce order item.
	 *
	 * @param sku the sku of this commerce order item
	 */
	@Override
	public void setSku(String sku) {
		model.setSku(sku);
	}

	/**
	 * Sets whether this commerce order item is subscription.
	 *
	 * @param subscription the subscription of this commerce order item
	 */
	@Override
	public void setSubscription(boolean subscription) {
		model.setSubscription(subscription);
	}

	/**
	 * Sets the unit price of this commerce order item.
	 *
	 * @param unitPrice the unit price of this commerce order item
	 */
	@Override
	public void setUnitPrice(BigDecimal unitPrice) {
		model.setUnitPrice(unitPrice);
	}

	/**
	 * Sets the unit price with tax amount of this commerce order item.
	 *
	 * @param unitPriceWithTaxAmount the unit price with tax amount of this commerce order item
	 */
	@Override
	public void setUnitPriceWithTaxAmount(BigDecimal unitPriceWithTaxAmount) {
		model.setUnitPriceWithTaxAmount(unitPriceWithTaxAmount);
	}

	/**
	 * Sets the user ID of this commerce order item.
	 *
	 * @param userId the user ID of this commerce order item
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this commerce order item.
	 *
	 * @param userName the user name of this commerce order item
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this commerce order item.
	 *
	 * @param userUuid the user uuid of this commerce order item
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	protected CommerceOrderItemWrapper wrap(
		CommerceOrderItem commerceOrderItem) {

		return new CommerceOrderItemWrapper(commerceOrderItem);
	}

}