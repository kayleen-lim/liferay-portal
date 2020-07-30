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

package com.liferay.commerce.pricing.web.internal.display.context;

import com.liferay.commerce.frontend.ClayCreationMenu;
import com.liferay.commerce.frontend.ClayCreationMenuActionItem;
import com.liferay.commerce.frontend.ClayMenuActionItem;
import com.liferay.commerce.frontend.model.HeaderActionModel;
import com.liferay.commerce.pricing.constants.CommercePricingClassActionKeys;
import com.liferay.commerce.pricing.constants.CommercePricingPorletKeys;
import com.liferay.commerce.pricing.model.CommercePricingClass;
import com.liferay.commerce.pricing.service.CommercePricingClassService;
import com.liferay.commerce.pricing.web.internal.display.context.util.CommercePricingClassRequestHelper;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.service.permission.PortalPermissionUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderResponse;
import javax.portlet.RenderURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Riccardo Alberti
 */
public class CommercePricingClassDisplayContext {

	public CommercePricingClassDisplayContext(
		HttpServletRequest httpServletRequest,
		ModelResourcePermission<CommercePricingClass>
			commercePricingClassModelResourcePermission,
		CommercePricingClassService commercePricingClassService,
		Portal portal) {

		_commercePricingClassModelResourcePermission =
			commercePricingClassModelResourcePermission;
		_commercePricingClassService = commercePricingClassService;
		_portal = portal;

		commercePricingClassRequestHelper =
			new CommercePricingClassRequestHelper(httpServletRequest);
	}

	public String getAddCommercePricingClassRenderURL() throws Exception {
		LiferayPortletResponse liferayPortletResponse =
			commercePricingClassRequestHelper.getLiferayPortletResponse();

		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		portletURL.setParameter(
			"mvcRenderCommandName", "addCommercePricingClass");
		portletURL.setWindowState(LiferayWindowState.POP_UP);

		return portletURL.toString();
	}

	public ClayCreationMenu getClayCreationMenu() throws Exception {
		ClayCreationMenu clayCreationMenu = new ClayCreationMenu();

		if (hasAddPermission()) {
			clayCreationMenu.addClayCreationMenuActionItem(
				new ClayCreationMenuActionItem(
					getAddCommercePricingClassRenderURL(),
					LanguageUtil.get(
						commercePricingClassRequestHelper.getRequest(),
						"add-product-group"),
					ClayMenuActionItem.CLAY_MENU_ACTION_ITEM_TARGET_MODAL));
		}

		return clayCreationMenu;
	}

	public CommercePricingClass getCommercePricingClass()
		throws PortalException {

		long commercePricingClassId = ParamUtil.getLong(
			commercePricingClassRequestHelper.getRequest(),
			"commercePricingClassId");

		if (commercePricingClassId == 0) {
			return null;
		}

		return _commercePricingClassService.fetchCommercePricingClass(
			commercePricingClassId);
	}

	public long getCommercePricingClassId() throws PortalException {
		CommercePricingClass commercePricingClass = getCommercePricingClass();

		if (commercePricingClass == null) {
			return 0;
		}

		return commercePricingClass.getCommercePricingClassId();
	}

	public String getEditCommercePricingClassActionURL() throws Exception {
		CommercePricingClass commercePricingClass = getCommercePricingClass();

		if (commercePricingClass == null) {
			return StringPool.BLANK;
		}

		PortletURL portletURL = _portal.getControlPanelPortletURL(
			commercePricingClassRequestHelper.getRequest(),
			CommercePricingPorletKeys.COMMERCE_PRICING_CLASSES,
			PortletRequest.ACTION_PHASE);

		portletURL.setParameter(
			ActionRequest.ACTION_NAME, "editCommercePricingClass");
		portletURL.setParameter(Constants.CMD, Constants.UPDATE);
		portletURL.setParameter(
			"commercePricingClassId",
			String.valueOf(commercePricingClass.getCommercePricingClassId()));
		portletURL.setWindowState(LiferayWindowState.POP_UP);

		return portletURL.toString();
	}

	public PortletURL getEditCommercePricingClassRenderURL() {
		PortletURL portletURL = _portal.getControlPanelPortletURL(
			commercePricingClassRequestHelper.getRequest(),
			CommercePricingPorletKeys.COMMERCE_PRICING_CLASSES,
			PortletRequest.RENDER_PHASE);

		portletURL.setParameter(
			"mvcRenderCommandName", "editCommercePricingClass");

		return portletURL;
	}

	public List<HeaderActionModel> getHeaderActionModels() throws Exception {
		List<HeaderActionModel> headerActionModels = new ArrayList<>();

		RenderResponse renderResponse =
			commercePricingClassRequestHelper.getRenderResponse();

		RenderURL cancelURL = renderResponse.createRenderURL();

		HeaderActionModel cancelHeaderActionModel = new HeaderActionModel(
			null, cancelURL.toString(), null, "cancel");

		headerActionModels.add(cancelHeaderActionModel);

		if (hasPermission(ActionKeys.UPDATE)) {
			headerActionModels.add(
				new HeaderActionModel(
					"btn-primary", renderResponse.getNamespace() + "fm",
					getEditCommercePricingClassActionURL(), null, "save"));
		}

		return headerActionModels;
	}

	public boolean hasAddPermission() throws PortalException {
		return PortalPermissionUtil.contains(
			commercePricingClassRequestHelper.getPermissionChecker(),
			CommercePricingClassActionKeys.ADD_COMMERCE_PRICING_CLASS);
	}

	public boolean hasPermission(String actionId) throws PortalException {
		return _commercePricingClassModelResourcePermission.contains(
			commercePricingClassRequestHelper.getPermissionChecker(),
			getCommercePricingClassId(), actionId);
	}

	protected final CommercePricingClassRequestHelper
		commercePricingClassRequestHelper;

	private final ModelResourcePermission<CommercePricingClass>
		_commercePricingClassModelResourcePermission;
	private final CommercePricingClassService _commercePricingClassService;
	private final Portal _portal;

}