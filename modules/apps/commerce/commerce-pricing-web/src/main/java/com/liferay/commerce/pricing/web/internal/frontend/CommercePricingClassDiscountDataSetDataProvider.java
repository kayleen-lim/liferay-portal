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

package com.liferay.commerce.pricing.web.internal.frontend;

import com.liferay.commerce.discount.model.CommerceDiscount;
import com.liferay.commerce.discount.service.CommerceDiscountService;
import com.liferay.commerce.frontend.CommerceDataSetDataProvider;
import com.liferay.commerce.frontend.Filter;
import com.liferay.commerce.frontend.Pagination;
import com.liferay.commerce.frontend.model.LabelField;
import com.liferay.commerce.pricing.web.internal.frontend.constants.CommercePricingClassDataSetConstants;
import com.liferay.commerce.pricing.web.internal.model.PricingClassDiscount;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(
	immediate = true,
	property = "commerce.data.provider.key=" + CommercePricingClassDataSetConstants.COMMERCE_DATA_SET_KEY_PRICING_CLASS_DISCOUNTS,
	service = CommerceDataSetDataProvider.class
)
public class CommercePricingClassDiscountDataSetDataProvider
	implements CommerceDataSetDataProvider<PricingClassDiscount> {

	@Override
	public int countItems(HttpServletRequest httpServletRequest, Filter filter)
		throws PortalException {

		long commercePricingClassId = ParamUtil.getLong(
			httpServletRequest, "commercePricingClassId");

		return _commerceDiscountService.
			getCommerceDiscountsCountByPricingClassId(
				commercePricingClassId, filter.getKeywords());
	}

	@Override
	public List<PricingClassDiscount> getItems(
			HttpServletRequest httpServletRequest, Filter filter,
			Pagination pagination, Sort sort)
		throws PortalException {

		List<PricingClassDiscount> priceClassDiscounts = new ArrayList<>();

		long commercePricingClassId = ParamUtil.getLong(
			httpServletRequest, "commercePricingClassId");

		List<CommerceDiscount> commerceDiscounts =
			_commerceDiscountService.searchByCommercePricingClassId(
				commercePricingClassId, filter.getKeywords(),
				pagination.getStartPosition(), pagination.getEndPosition());

		for (CommerceDiscount commerceDiscount : commerceDiscounts) {
			String statusDisplayStyle = StringPool.BLANK;

			if (commerceDiscount.getStatus() ==
					WorkflowConstants.STATUS_APPROVED) {

				statusDisplayStyle = "success";
			}
			else if (commerceDiscount.getStatus() ==
						WorkflowConstants.STATUS_DRAFT) {

				statusDisplayStyle = "secondary";
			}
			else if (commerceDiscount.getStatus() ==
						WorkflowConstants.STATUS_EXPIRED) {

				statusDisplayStyle = "warning";
			}

			priceClassDiscounts.add(
				new PricingClassDiscount(
					commerceDiscount.getCommerceDiscountId(),
					commerceDiscount.getTitle(), "Product Group",
					_getDiscountType(commerceDiscount.isUsePercentage()),
					new LabelField(
						statusDisplayStyle,
						LanguageUtil.get(
							httpServletRequest,
							WorkflowConstants.getStatusLabel(
								commerceDiscount.getStatus())))));
		}

		return priceClassDiscounts;
	}

	private String _getDiscountType(boolean isUsePercentage) {
		if (isUsePercentage) {
			return "Percentage";
		}

		return "Absolute";
	}

	@Reference
	private CommerceDiscountService _commerceDiscountService;

}