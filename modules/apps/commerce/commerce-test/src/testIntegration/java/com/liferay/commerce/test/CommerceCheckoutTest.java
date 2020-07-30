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

package com.liferay.commerce.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.commerce.account.constants.CommerceAccountConstants;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.service.CommerceAccountLocalService;
import com.liferay.commerce.account.service.CommerceAccountUserRelLocalServiceUtil;
import com.liferay.commerce.constants.CommerceConstants;
import com.liferay.commerce.constants.CommerceOrderActionKeys;
import com.liferay.commerce.constants.CommerceOrderConstants;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.test.util.CommerceCurrencyTestUtil;
import com.liferay.commerce.exception.CommerceOrderGuestCheckoutException;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.order.engine.CommerceOrderEngine;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.service.CommerceOrderLocalServiceUtil;
import com.liferay.commerce.test.util.CommerceTestUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.settings.GroupServiceSettingsLocator;
import com.liferay.portal.kernel.settings.ModifiableSettings;
import com.liferay.portal.kernel.settings.Settings;
import com.liferay.portal.kernel.settings.SettingsFactory;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.CompanyTestUtil;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.RoleTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;

import java.math.BigDecimal;

import java.util.List;

import org.frutilla.FrutillaRule;

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
public class CommerceCheckoutTest {

	@ClassRule
	@Rule
	public static AggregateTestRule aggregateTestRule = new AggregateTestRule(
		new LiferayIntegrationTestRule(), PermissionCheckerMethodTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_company = CompanyTestUtil.addCompany();

		_user = UserTestUtil.addUser(_company);

		_group = GroupTestUtil.addGroup(
			_company.getCompanyId(), _user.getUserId(), 0);

		_commerceCurrency = CommerceCurrencyTestUtil.addCommerceCurrency(
			_group.getCompanyId());

		_commerceChannel = CommerceTestUtil.addCommerceChannel(
			_group.getGroupId(), _commerceCurrency.getCode());

		Settings settings = _settingsFactory.getSettings(
			new GroupServiceSettingsLocator(
				_commerceChannel.getGroupId(),
				CommerceConstants.ORDER_SERVICE_NAME));

		ModifiableSettings modifiableSettings =
			settings.getModifiableSettings();

		modifiableSettings.setValue(
			"guestCheckoutEnabled", Boolean.TRUE.toString());

		modifiableSettings.store();
	}

	@Test
	public void testGuestUserCheckout() throws Exception {
		frutillaRule.scenario(
			"When a guest creates an order and the channel has guest " +
				"checkout enabled, the guest should be able to checkout the " +
					"order"
		).given(
			"An order created by an un-authenticated user"
		).and(
			"Guest Checkout has been enabled on the channel"
		).when(
			"The guest tries to checkout the order"
		).then(
			"The guest should be able to place the order"
		);

		User user = _company.getDefaultUser();

		CommerceAccount commerceAccount =
			_commerceAccountLocalService.getGuestCommerceAccount(
				_company.getCompanyId());

		CommerceOrder commerceOrder =
			CommerceOrderLocalServiceUtil.addCommerceOrder(
				user.getUserId(), _commerceChannel.getGroupId(),
				commerceAccount.getCommerceAccountId(),
				_commerceCurrency.getCommerceCurrencyId());

		CommerceTestUtil.addCheckoutDetailsToUserOrder(
			commerceOrder, commerceOrder.getUserId(), false);

		commerceOrder = _commerceOrderEngine.checkoutCommerceOrder(
			commerceOrder, user.getUserId());

		Assert.assertEquals(
			commerceOrder.getOrderStatus(),
			CommerceOrderConstants.ORDER_STATUS_PENDING);
		Assert.assertTrue(commerceOrder.isGuestOrder());
	}

	@Test(expected = PrincipalException.MustHavePermission.class)
	public void testGuestUserCheckoutFromAnotherGuestUser() throws Exception {
		frutillaRule.scenario(
			"When a guest creates an order other guests should not be able " +
				"to see that order"
		).given(
			"An order created by an un-authenticated user"
		).and(
			"Guest Checkout has been enabled on the channel"
		).when(
			"Another guest user tries to checkout that order"
		).then(
			"A Permission exception should be thrown"
		);

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_company.getCompanyId(), _group.getGroupId(),
				_user.getUserId());

		User user = UserTestUtil.addUser(_company);

		serviceContext.setUserId(user.getUserId());

		PermissionChecker permissionChecker =
			PermissionCheckerFactoryUtil.create(user);

		PrincipalThreadLocal.setName(user.getUserId());

		PermissionThreadLocal.setPermissionChecker(permissionChecker);

		CommerceAccount commerceAccount =
			_commerceAccountLocalService.addCommerceAccount(
				RandomTestUtil.randomString(),
				CommerceAccountConstants.DEFAULT_PARENT_ACCOUNT_ID,
				user.getEmailAddress(), StringPool.BLANK,
				CommerceAccountConstants.ACCOUNT_TYPE_GUEST, true, null,
				serviceContext);

		Role role = RoleTestUtil.addRole(
			CommerceAccountConstants.ROLE_NAME_ACCOUNT_ADMINISTRATOR,
			RoleConstants.TYPE_SITE, "com.liferay.commerce.order",
			ResourceConstants.SCOPE_GROUP_TEMPLATE,
			String.valueOf(GroupConstants.DEFAULT_PARENT_GROUP_ID),
			CommerceOrderActionKeys.CHECKOUT_OPEN_COMMERCE_ORDERS);

		CommerceAccountUserRelLocalServiceUtil.addCommerceAccountUserRel(
			commerceAccount.getCommerceAccountId(), user.getUserId(),
			new long[] {role.getRoleId()}, serviceContext);

		CommerceOrder commerceOrder =
			CommerceOrderLocalServiceUtil.addCommerceOrder(
				user.getUserId(), _commerceChannel.getGroupId(),
				commerceAccount.getCommerceAccountId(),
				_commerceCurrency.getCommerceCurrencyId());

		CommerceTestUtil.addCheckoutDetailsToUserOrder(
			commerceOrder, commerceOrder.getUserId(), false);

		User user2 = UserTestUtil.addUser(_company);

		permissionChecker = PermissionCheckerFactoryUtil.create(user2);

		PrincipalThreadLocal.setName(user2.getUserId());

		PermissionThreadLocal.setPermissionChecker(permissionChecker);

		_commerceOrderEngine.checkoutCommerceOrder(
			commerceOrder, user2.getUserId());
	}

	@Test(expected = CommerceOrderGuestCheckoutException.class)
	public void testGuestUserCheckoutWithGuestCheckoutDisabled()
		throws Exception {

		frutillaRule.scenario(
			"When a guest creates an order and the channel does not have " +
				"guest checkout enabled, the guest cart should not be " +
					"allowed to be checked out"
		).given(
			"An order created by an un-authenticated user"
		).and(
			"Guest Checkout has not been enabled on the channel"
		).when(
			"The guest tries to checkout the order"
		).then(
			"The guest should be able to place the order"
		);

		Settings settings = _settingsFactory.getSettings(
			new GroupServiceSettingsLocator(
				_commerceChannel.getGroupId(),
				CommerceConstants.ORDER_SERVICE_NAME));

		ModifiableSettings modifiableSettings =
			settings.getModifiableSettings();

		modifiableSettings.setValue(
			"guestCheckoutEnabled", Boolean.FALSE.toString());

		modifiableSettings.store();

		User user = _company.getDefaultUser();

		CommerceAccount commerceAccount =
			_commerceAccountLocalService.getGuestCommerceAccount(
				_company.getCompanyId());

		CommerceOrder commerceOrder =
			CommerceOrderLocalServiceUtil.addCommerceOrder(
				user.getUserId(), _commerceChannel.getGroupId(),
				commerceAccount.getCommerceAccountId(),
				_commerceCurrency.getCommerceCurrencyId());

		CommerceTestUtil.addCheckoutDetailsToUserOrder(
			commerceOrder, commerceOrder.getUserId(), false);

		_commerceOrderEngine.checkoutCommerceOrder(
			commerceOrder, user.getUserId());

		Assert.assertNotEquals(
			commerceOrder.getOrderStatus(),
			CommerceOrderConstants.ORDER_STATUS_PENDING);
		Assert.assertTrue(commerceOrder.isGuestOrder());
	}

	@Test
	public void testUserCheckout() throws Exception {
		frutillaRule.scenario(
			"When multiple price lists are available, check that only the " +
				"matching one is retrieved"
		).given(
			"I add some price lists with different priorities"
		).when(
			"I try to get the best matching price list"
		).then(
			"The price list with the highest priority should be retrieved"
		);

		CommerceOrder commerceOrder = CommerceTestUtil.addB2CCommerceOrder(
			_user.getUserId(), _commerceChannel.getGroupId(),
			_commerceCurrency.getCommerceCurrencyId());

		CommerceTestUtil.addCheckoutDetailsToUserOrder(
			commerceOrder, commerceOrder.getUserId(), false);

		commerceOrder = _commerceOrderEngine.checkoutCommerceOrder(
			commerceOrder, _user.getUserId());

		Assert.assertEquals(
			WorkflowConstants.STATUS_APPROVED, commerceOrder.getStatus());

		List<CommerceOrderItem> commerceOrderItems =
			commerceOrder.getCommerceOrderItems();

		BigDecimal expectedSubTotal = BigDecimal.ZERO;

		for (CommerceOrderItem commerceOrderItem : commerceOrderItems) {
			CPInstance cpInstance = commerceOrderItem.fetchCPInstance();

			if (cpInstance == null) {
				continue;
			}

			BigDecimal price = cpInstance.getPrice();

			int quantity = commerceOrderItem.getQuantity();

			BigDecimal totalItemPrice = price.multiply(
				BigDecimal.valueOf(quantity));

			expectedSubTotal = expectedSubTotal.add(totalItemPrice);
		}

		BigDecimal actualSubTotal = commerceOrder.getSubtotal();

		Assert.assertEquals(
			expectedSubTotal.doubleValue(), actualSubTotal.doubleValue(),
			0.0001);

		BigDecimal expectedTotal = expectedSubTotal.add(
			commerceOrder.getShippingAmount());

		BigDecimal actualTotal = commerceOrder.getTotal();

		Assert.assertEquals(
			expectedTotal.doubleValue(), actualTotal.doubleValue(), 0.0001);
	}

	@Rule
	public FrutillaRule frutillaRule = new FrutillaRule();

	@Inject
	private CommerceAccountLocalService _commerceAccountLocalService;

	@DeleteAfterTestRun
	private CommerceChannel _commerceChannel;

	@DeleteAfterTestRun
	private CommerceCurrency _commerceCurrency;

	@Inject
	private CommerceOrderEngine _commerceOrderEngine;

	@DeleteAfterTestRun
	private Company _company;

	@DeleteAfterTestRun
	private Group _group;

	@Inject
	private SettingsFactory _settingsFactory;

	@DeleteAfterTestRun
	private User _user;

}