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

package com.liferay.commerce.discount.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.service.CommerceAccountLocalService;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.model.CommerceMoney;
import com.liferay.commerce.currency.test.util.CommerceCurrencyTestUtil;
import com.liferay.commerce.discount.constants.CommerceDiscountConstants;
import com.liferay.commerce.discount.model.CommerceDiscount;
import com.liferay.commerce.discount.service.CommerceDiscountLocalService;
import com.liferay.commerce.discount.test.util.CommerceDiscountTestUtil;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouse;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.price.CommerceOrderPriceCalculation;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.service.CPInstanceLocalService;
import com.liferay.commerce.product.test.util.CPTestUtil;
import com.liferay.commerce.service.CommerceOrderLocalService;
import com.liferay.commerce.test.util.CommerceInventoryTestUtil;
import com.liferay.commerce.test.util.CommerceTestUtil;
import com.liferay.commerce.test.util.TestCommerceContext;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;

import org.frutilla.FrutillaRule;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Luca Pellizzon
 */
@RunWith(Arquillian.class)
public class CommerceOrderDiscountTest {

	@ClassRule
	@Rule
	public static AggregateTestRule aggregateTestRule = new AggregateTestRule(
		new LiferayIntegrationTestRule(), PermissionCheckerMethodTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();

		_user = UserTestUtil.addUser();

		_commerceAccount =
			_commerceAccountLocalService.getPersonalCommerceAccount(
				_user.getUserId());

		_commerceCurrency = CommerceCurrencyTestUtil.addCommerceCurrency(
			_group.getCompanyId());

		_commerceOrders = new ArrayList<>();
	}

	@After
	public void tearDown() throws Exception {
		for (CommerceOrder commerceOrder : _commerceOrders) {
			_commerceOrderLocalService.deleteCommerceOrder(commerceOrder);
		}

		_commerceAccountLocalService.deleteCommerceAccount(_commerceAccount);
		GroupTestUtil.deleteGroup(_group);
		_userLocalService.deleteUser(_user);

		_commerceDiscountLocalService.deleteCommerceDiscounts(
			_user.getCompanyId());
	}

	@Test
	public void testDiscountWithCouponGreaterThanCartValue() throws Exception {
		frutillaRule.scenario(
			"Discount on total is applied to the order only if consistent"
		).given(
			"An order with some order items"
		).and(
			"A discount coupon on the total price"
		).when(
			"I try to get the final price of the order"
		).then(
			"The final price will be calculated with the discount"
		);

		CommerceChannel commerceChannel = CommerceTestUtil.addCommerceChannel(
			_commerceCurrency.getCode());

		CommerceOrder commerceOrder = CommerceTestUtil.addB2CCommerceOrder(
			_user.getUserId(), commerceChannel.getGroupId(), _commerceCurrency);

		_commerceOrders.add(commerceOrder);

		commerceOrder.setCommerceCurrencyId(
			_commerceCurrency.getCommerceCurrencyId());

		_commerceOrderLocalService.updateCommerceOrder(commerceOrder);

		CPInstance cpInstanceDiscount = CPTestUtil.addCPInstanceWithRandomSku(
			_group.getGroupId());

		cpInstanceDiscount.setPrice(BigDecimal.valueOf(0.9));

		_cpInstanceLocalService.updateCPInstance(cpInstanceDiscount);

		CommerceInventoryWarehouse commerceInventoryWarehouse =
			CommerceInventoryTestUtil.addCommerceInventoryWarehouse();

		CommerceTestUtil.addWarehouseCommerceChannelRel(
			commerceInventoryWarehouse.getCommerceInventoryWarehouseId(),
			commerceChannel.getCommerceChannelId());

		int quantity = 10;
		int orderedQuantity = 1;

		CommerceInventoryTestUtil.addCommerceInventoryWarehouseItem(
			_user.getUserId(), commerceInventoryWarehouse,
			cpInstanceDiscount.getSku(), quantity);

		String couponCode = StringUtil.randomString();

		CommerceDiscountTestUtil.addCouponDiscount(
			_group.getGroupId(), 1, couponCode,
			CommerceDiscountConstants.TARGET_TOTAL, null);

		CommerceContext commerceContext = new TestCommerceContext(
			_commerceCurrency, null, _user, _group, _commerceAccount,
			commerceOrder);

		CommerceTestUtil.addCommerceOrderItem(
			commerceOrder.getCommerceOrderId(),
			cpInstanceDiscount.getCPInstanceId(), orderedQuantity,
			commerceContext);

		commerceOrder = _commerceOrderLocalService.applyCouponCode(
			commerceOrder.getCommerceOrderId(), couponCode, commerceContext);

		CommerceMoney total = _commerceOrderPriceCalculation.getTotal(
			commerceOrder, commerceContext);

		BigDecimal price = BigDecimal.ZERO;
		BigDecimal totalPrice = total.getPrice();

		Assert.assertEquals(
			price.stripTrailingZeros(), totalPrice.stripTrailingZeros());
	}

	@Test
	public void testMultiTargetDiscounts() throws Exception {
		frutillaRule.scenario(
			"Discounts on multiple targets shall be applied on the order"
		).given(
			"An order with some order items"
		).and(
			"A discount on one product and a discount on the total price"
		).when(
			"I try to get the final price of the order"
		).then(
			"The final price will be calculated with the discounts"
		);

		CommerceChannel commerceChannel = CommerceTestUtil.addCommerceChannel(
			_commerceCurrency.getCode());

		CommerceOrder commerceOrder = CommerceTestUtil.addB2CCommerceOrder(
			_user.getUserId(), commerceChannel.getGroupId(), _commerceCurrency);

		_commerceOrders.add(commerceOrder);

		commerceOrder.setCommerceCurrencyId(
			_commerceCurrency.getCommerceCurrencyId());

		_commerceOrderLocalService.updateCommerceOrder(commerceOrder);

		CPInstance cpInstanceDiscount = CPTestUtil.addCPInstanceWithRandomSku(
			_group.getGroupId());

		CPInstance cpInstancePlain = CPTestUtil.addCPInstanceWithRandomSku(
			_group.getGroupId());

		cpInstanceDiscount.setPrice(BigDecimal.valueOf(25));
		cpInstancePlain.setPrice(BigDecimal.valueOf(10));

		_cpInstanceLocalService.updateCPInstance(cpInstanceDiscount);
		_cpInstanceLocalService.updateCPInstance(cpInstancePlain);

		CPDefinition cpDefinition = cpInstanceDiscount.getCPDefinition();

		CommerceInventoryWarehouse commerceInventoryWarehouse =
			CommerceInventoryTestUtil.addCommerceInventoryWarehouse();

		CommerceTestUtil.addWarehouseCommerceChannelRel(
			commerceInventoryWarehouse.getCommerceInventoryWarehouseId(),
			commerceChannel.getCommerceChannelId());

		int quantity = 10;
		int orderedQuantity = 1;

		CommerceInventoryTestUtil.addCommerceInventoryWarehouseItem(
			_user.getUserId(), commerceInventoryWarehouse,
			cpInstanceDiscount.getSku(), quantity);

		CommerceInventoryTestUtil.addCommerceInventoryWarehouseItem(
			_user.getUserId(), commerceInventoryWarehouse,
			cpInstancePlain.getSku(), quantity);

		CommerceDiscount commerceDiscount1 =
			CommerceDiscountTestUtil.addFixedCommerceDiscount(
				_group.getGroupId(), 10,
				CommerceDiscountConstants.TARGET_PRODUCT,
				cpDefinition.getCPDefinitionId());

		CommerceDiscount commerceDiscount2 =
			CommerceDiscountTestUtil.addFixedCommerceDiscount(
				_group.getGroupId(), 10, CommerceDiscountConstants.TARGET_TOTAL,
				null);

		CommerceTestUtil.addCommerceOrderItem(
			commerceOrder.getCommerceOrderId(),
			cpInstanceDiscount.getCPInstanceId(), orderedQuantity);

		CommerceTestUtil.addCommerceOrderItem(
			commerceOrder.getCommerceOrderId(),
			cpInstancePlain.getCPInstanceId(), orderedQuantity);

		CommerceContext commerceContext = new TestCommerceContext(
			_commerceCurrency, null, _user, _group, _commerceAccount,
			commerceOrder);

		CommerceMoney total = _commerceOrderPriceCalculation.getTotal(
			commerceOrder, commerceContext);
		CommerceMoney subtotal = _commerceOrderPriceCalculation.getSubtotal(
			commerceOrder, commerceContext);

		BigDecimal prod1Price = cpInstanceDiscount.getPrice();
		BigDecimal prod2Price = cpInstancePlain.getPrice();

		BigDecimal prod1TotalPrice = prod1Price.multiply(
			BigDecimal.valueOf(orderedQuantity));
		BigDecimal prod2TotalPrice = prod2Price.multiply(
			BigDecimal.valueOf(orderedQuantity));

		BigDecimal expectedCartValue = prod1TotalPrice.add(prod2TotalPrice);

		BigDecimal discount1Level1 = commerceDiscount1.getLevel1();
		BigDecimal discount2Level1 = commerceDiscount2.getLevel1();

		BigDecimal expectedSubtotal = expectedCartValue.subtract(
			discount1Level1);

		BigDecimal expectedTotal = expectedSubtotal.subtract(discount2Level1);

		BigDecimal subtotalPrice = subtotal.getPrice();
		BigDecimal totalPrice = total.getPrice();

		Assert.assertEquals(
			expectedSubtotal.stripTrailingZeros(),
			subtotalPrice.stripTrailingZeros());
		Assert.assertEquals(
			expectedTotal.stripTrailingZeros(),
			totalPrice.stripTrailingZeros());
	}

	@Test
	public void testMultiTargetDiscountsWithCoupon() throws Exception {
		frutillaRule.scenario(
			"Discounts on multiple targets shall be applied on the order"
		).given(
			"An order with some order items"
		).and(
			"A discount on one product and a discount coupon on the total price"
		).when(
			"I try to get the final price of the order"
		).then(
			"The final price will be calculated with the discounts"
		);
		CommerceChannel commerceChannel = CommerceTestUtil.addCommerceChannel(
			_commerceCurrency.getCode());

		CommerceOrder commerceOrder = CommerceTestUtil.addB2CCommerceOrder(
			_user.getUserId(), commerceChannel.getGroupId(), _commerceCurrency);

		_commerceOrders.add(commerceOrder);

		commerceOrder.setCommerceCurrencyId(
			_commerceCurrency.getCommerceCurrencyId());

		_commerceOrderLocalService.updateCommerceOrder(commerceOrder);

		CPInstance cpInstanceDiscount = CPTestUtil.addCPInstanceWithRandomSku(
			_group.getGroupId());

		CPInstance cpInstancePlain = CPTestUtil.addCPInstanceWithRandomSku(
			_group.getGroupId());

		cpInstanceDiscount.setPrice(BigDecimal.valueOf(25));
		cpInstancePlain.setPrice(BigDecimal.valueOf(10));

		_cpInstanceLocalService.updateCPInstance(cpInstanceDiscount);
		_cpInstanceLocalService.updateCPInstance(cpInstancePlain);

		CPDefinition cpDefinition = cpInstanceDiscount.getCPDefinition();

		CommerceInventoryWarehouse commerceInventoryWarehouse =
			CommerceInventoryTestUtil.addCommerceInventoryWarehouse();

		CommerceTestUtil.addWarehouseCommerceChannelRel(
			commerceInventoryWarehouse.getCommerceInventoryWarehouseId(),
			commerceChannel.getCommerceChannelId());

		int quantity = 10;
		int orderedQuantity = 1;

		CommerceInventoryTestUtil.addCommerceInventoryWarehouseItem(
			_user.getUserId(), commerceInventoryWarehouse,
			cpInstanceDiscount.getSku(), quantity);

		CommerceInventoryTestUtil.addCommerceInventoryWarehouseItem(
			_user.getUserId(), commerceInventoryWarehouse,
			cpInstancePlain.getSku(), quantity);

		CommerceDiscount commerceDiscount1 =
			CommerceDiscountTestUtil.addFixedCommerceDiscount(
				_group.getGroupId(), 10,
				CommerceDiscountConstants.TARGET_PRODUCT,
				cpDefinition.getCPDefinitionId());

		String couponCode = StringUtil.randomString();

		CommerceDiscount commerceDiscount2 =
			CommerceDiscountTestUtil.addCouponDiscount(
				_group.getGroupId(), 10, couponCode,
				CommerceDiscountConstants.TARGET_TOTAL, null);

		new TestCommerceContext(
			_commerceCurrency, null, _user, _group, _commerceAccount,
			commerceOrder);

		CommerceContext commerceContext = new TestCommerceContext(
			_commerceCurrency, null, _user, _group, _commerceAccount,
			commerceOrder);

		CommerceTestUtil.addCommerceOrderItem(
			commerceOrder.getCommerceOrderId(),
			cpInstanceDiscount.getCPInstanceId(), orderedQuantity,
			commerceContext);

		CommerceTestUtil.addCommerceOrderItem(
			commerceOrder.getCommerceOrderId(),
			cpInstancePlain.getCPInstanceId(), orderedQuantity,
			commerceContext);

		commerceOrder = _commerceOrderLocalService.applyCouponCode(
			commerceOrder.getCommerceOrderId(), couponCode, commerceContext);

		CommerceMoney total = _commerceOrderPriceCalculation.getTotal(
			commerceOrder, commerceContext);
		CommerceMoney subtotal = _commerceOrderPriceCalculation.getSubtotal(
			commerceOrder, commerceContext);

		BigDecimal prod1Price = cpInstanceDiscount.getPrice();
		BigDecimal prod2Price = cpInstancePlain.getPrice();

		BigDecimal prod1TotalPrice = prod1Price.multiply(
			BigDecimal.valueOf(orderedQuantity));
		BigDecimal prod2TotalPrice = prod2Price.multiply(
			BigDecimal.valueOf(orderedQuantity));

		BigDecimal expectedCartValue = prod1TotalPrice.add(prod2TotalPrice);

		BigDecimal discount1Level1 = commerceDiscount1.getLevel1();
		BigDecimal discount2Level1 = commerceDiscount2.getLevel1();

		BigDecimal expectedSubtotal = expectedCartValue.subtract(
			discount1Level1);

		BigDecimal expectedTotal = expectedSubtotal.subtract(discount2Level1);

		BigDecimal subtotalPrice = subtotal.getPrice();
		BigDecimal totalPrice = total.getPrice();

		Assert.assertEquals(
			expectedSubtotal.stripTrailingZeros(),
			subtotalPrice.stripTrailingZeros());
		Assert.assertEquals(
			expectedTotal.stripTrailingZeros(),
			totalPrice.stripTrailingZeros());
	}

	@Rule
	public FrutillaRule frutillaRule = new FrutillaRule();

	private CommerceAccount _commerceAccount;

	@Inject
	private CommerceAccountLocalService _commerceAccountLocalService;

	@DeleteAfterTestRun
	private CommerceCurrency _commerceCurrency;

	@Inject
	private CommerceDiscountLocalService _commerceDiscountLocalService;

	@Inject
	private CommerceOrderLocalService _commerceOrderLocalService;

	@Inject(filter = "commerce.price.calculation.key=v1.0")
	private CommerceOrderPriceCalculation _commerceOrderPriceCalculation;

	private List<CommerceOrder> _commerceOrders;

	@Inject
	private CPInstanceLocalService _cpInstanceLocalService;

	private Group _group;
	private User _user;

	@Inject
	private UserLocalService _userLocalService;

}