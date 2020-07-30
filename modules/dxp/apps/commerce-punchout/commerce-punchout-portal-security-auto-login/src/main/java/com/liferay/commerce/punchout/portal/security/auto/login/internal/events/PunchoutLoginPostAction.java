/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.commerce.punchout.portal.security.auto.login.internal.events;

import com.liferay.commerce.constants.CommerceWebKeys;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.context.CommerceContextFactory;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.service.CommerceCurrencyLocalService;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.order.CommerceOrderHttpHelper;
import com.liferay.commerce.product.service.CommerceChannelLocalService;
import com.liferay.commerce.punchout.oauth2.provider.model.PunchoutAccessToken;
import com.liferay.commerce.service.CommerceOrderLocalService;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.CookieKeys;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jaclyn Ong
 */
@Component(
	immediate = true, property = "key=login.events.post",
	service = LifecycleAction.class
)
public class PunchoutLoginPostAction extends Action {

	@Override
	public void run(
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse) {

		try {
			Object punchoutAccessTokenObject = httpServletRequest.getAttribute(
				"punchoutAccessToken");

			Object punchoutUserIdObject = httpServletRequest.getAttribute(
				"punchoutUserId");

			if ((punchoutAccessTokenObject == null) ||
				(punchoutUserIdObject == null)) {

				return;
			}

			PunchoutAccessToken punchoutAccessToken =
				(PunchoutAccessToken)httpServletRequest.getAttribute(
					"punchoutAccessToken");

			long punchoutUserId = (long)httpServletRequest.getAttribute(
				"punchoutUserId");

			long companyId = _portal.getCompanyId(httpServletRequest);

			_startNewPunchoutSession(
				companyId, punchoutAccessToken.getGroupId(),
				punchoutAccessToken.getCommerceAccountId(),
				punchoutAccessToken.getCurrencyCode(), punchoutAccessToken,
				punchoutUserId, httpServletRequest, httpServletResponse);

			httpServletRequest.removeAttribute("punchoutAccessToken");

			httpServletRequest.removeAttribute("punchoutUserId");
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private ThemeDisplay _getThemeDisplay() {
		return new ThemeDisplay() {
			{
				setSignedIn(true);
			}
		};
	}

	private void _startNewPunchoutSession(
			long companyId, long groupId, long commerceAccountId,
			String currencyCode, PunchoutAccessToken punchoutAccessToken,
			long punchoutUserId, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws PortalException {

		long commerceCurrencyId = 0;

		CommerceCurrency commerceCurrency =
			_commerceCurrencyLocalService.getCommerceCurrency(
				companyId, currencyCode);

		if (commerceCurrency != null) {
			commerceCurrencyId = commerceCurrency.getCommerceCurrencyId();
		}

		long commerceChannelGroupId =
			_commerceChannelLocalService.getCommerceChannelGroupIdBySiteGroupId(
				groupId);

		String commerceOrderUuid = punchoutAccessToken.getCommerceOrderUuid();

		CommerceOrder commerceOrder;

		if (!Validator.isBlank(commerceOrderUuid)) {
			commerceOrder =
				_commerceOrderLocalService.fetchCommerceOrderByUuidAndGroupId(
					commerceOrderUuid, commerceChannelGroupId);
		}
		else {
			commerceOrder = _commerceOrderLocalService.addCommerceOrder(
				punchoutUserId, commerceChannelGroupId, commerceAccountId,
				commerceCurrencyId);

			ServiceContext serviceContext = new ServiceContext();

			commerceOrder = _commerceOrderLocalService.updateStatus(
				punchoutUserId, commerceOrder.getCommerceOrderId(),
				WorkflowConstants.STATUS_APPROVED, serviceContext,
				Collections.emptyMap());
		}

		CommerceContext commerceContext = _commerceContextFactory.create(
			companyId, commerceChannelGroupId, punchoutUserId,
			commerceOrder.getCommerceOrderId(), commerceAccountId);

		httpServletRequest.setAttribute(
			CommerceWebKeys.COMMERCE_CONTEXT, commerceContext);

		ThemeDisplay themeDisplay = _getThemeDisplay();

		httpServletRequest.setAttribute(WebKeys.THEME_DISPLAY, themeDisplay);

		String cookieName = _commerceOrderHttpHelper.getCookieName(
			commerceOrder.getGroupId());

		Cookie cookie = new Cookie(cookieName, commerceOrder.getUuid());

		cookie.setMaxAge(-1);
		cookie.setPath(StringPool.SLASH);

		CookieKeys.addCookie(httpServletRequest, httpServletResponse, cookie);

		HttpSession httpSession = httpServletRequest.getSession();

		HashMap<String, Object> punchoutSessionAttributes =
			punchoutAccessToken.getPunchoutSessionAttributes();

		for (Map.Entry mapElement : punchoutSessionAttributes.entrySet()) {
			String key = (String)mapElement.getKey();

			if (_log.isDebugEnabled()) {
				StringBundler sb = new StringBundler();

				sb.append("Adding attribute to session (key=");
				sb.append(key);
				sb.append(StringPool.COMMA);
				sb.append(StringPool.SPACE);
				sb.append("value=");
				sb.append(mapElement.getValue());
				sb.append(StringPool.CLOSE_PARENTHESIS);

				_log.debug(sb.toString());
			}

			httpSession.setAttribute(key, mapElement.getValue());
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PunchoutLoginPostAction.class);

	@Reference
	private CommerceChannelLocalService _commerceChannelLocalService;

	@Reference
	private CommerceContextFactory _commerceContextFactory;

	@Reference
	private CommerceCurrencyLocalService _commerceCurrencyLocalService;

	@Reference
	private CommerceOrderHttpHelper _commerceOrderHttpHelper;

	@Reference
	private CommerceOrderLocalService _commerceOrderLocalService;

	@Reference
	private Portal _portal;

}