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

package com.liferay.headless.commerce.admin.pricing.internal.resource.v2_0;

import com.liferay.commerce.discount.exception.NoSuchDiscountException;
import com.liferay.commerce.discount.model.CommerceDiscount;
import com.liferay.commerce.discount.model.CommerceDiscountRel;
import com.liferay.commerce.discount.service.CommerceDiscountRelService;
import com.liferay.commerce.discount.service.CommerceDiscountService;
import com.liferay.commerce.pricing.model.CommercePricingClass;
import com.liferay.commerce.pricing.service.CommercePricingClassService;
import com.liferay.headless.commerce.admin.pricing.dto.v2_0.DiscountProductGroup;
import com.liferay.headless.commerce.admin.pricing.internal.dto.v2_0.converter.DiscountProductGroupDTOConverter;
import com.liferay.headless.commerce.admin.pricing.internal.util.v2_0.DiscountProductGroupUtil;
import com.liferay.headless.commerce.admin.pricing.resource.v2_0.DiscountProductGroupResource;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;
import com.liferay.portal.vulcan.dto.converter.DefaultDTOConverterContext;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Riccardo Alberti
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v2_0/discount-product-group.properties",
	scope = ServiceScope.PROTOTYPE, service = DiscountProductGroupResource.class
)
public class DiscountProductGroupResourceImpl
	extends BaseDiscountProductGroupResourceImpl {

	@Override
	public void deleteDiscountProductGroup(Long id) throws Exception {
		_commerceDiscountRelService.deleteCommerceDiscountRel(id);
	}

	@Override
	public Page<DiscountProductGroup>
			getDiscountByExternalReferenceCodeDiscountProductGroupsPage(
				String externalReferenceCode, Pagination pagination)
		throws Exception {

		CommerceDiscount commerceDiscount =
			_commerceDiscountService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (commerceDiscount == null) {
			throw new NoSuchDiscountException(
				"Unable to find Discount with externalReferenceCode: " +
					externalReferenceCode);
		}

		List<CommerceDiscountRel> commerceDiscountRels =
			_commerceDiscountRelService.getCommerceDiscountRels(
				commerceDiscount.getCommerceDiscountId(),
				CommercePricingClass.class.getName(),
				pagination.getStartPosition(), pagination.getEndPosition(),
				null);

		int totalItems =
			_commerceDiscountRelService.getCommerceDiscountRelsCount(
				commerceDiscount.getCommerceDiscountId(),
				CommercePricingClass.class.getName());

		return Page.of(
			_toDiscountProductGroups(commerceDiscountRels), pagination,
			totalItems);
	}

	@Override
	public Page<DiscountProductGroup> getDiscountIdDiscountProductGroupsPage(
			Long id, Pagination pagination)
		throws Exception {

		List<CommerceDiscountRel> commerceDiscountRels =
			_commerceDiscountRelService.getCommerceDiscountRels(
				id, CommercePricingClass.class.getName(),
				pagination.getStartPosition(), pagination.getEndPosition(),
				null);

		int totalItems =
			_commerceDiscountRelService.getCommerceDiscountRelsCount(
				id, CommercePricingClass.class.getName());

		return Page.of(
			_toDiscountProductGroups(commerceDiscountRels), pagination,
			totalItems);
	}

	@Override
	public DiscountProductGroup
			postDiscountByExternalReferenceCodeDiscountProductGroup(
				String externalReferenceCode,
				DiscountProductGroup discountProductGroup)
		throws Exception {

		CommerceDiscount commerceDiscount =
			_commerceDiscountService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (commerceDiscount == null) {
			throw new NoSuchDiscountException(
				"Unable to find Discount with externalReferenceCode: " +
					externalReferenceCode);
		}

		CommerceDiscountRel commerceDiscountRel =
			DiscountProductGroupUtil.addCommerceDiscountRel(
				_commercePricingClassService, _commerceDiscountRelService,
				discountProductGroup, commerceDiscount, _serviceContextHelper);

		return _toDiscountProductGroup(
			commerceDiscountRel.getCommerceDiscountRelId());
	}

	@Override
	public DiscountProductGroup postDiscountIdDiscountProductGroup(
			Long id, DiscountProductGroup discountProductGroup)
		throws Exception {

		CommerceDiscountRel commerceDiscountRel =
			DiscountProductGroupUtil.addCommerceDiscountRel(
				_commercePricingClassService, _commerceDiscountRelService,
				discountProductGroup,
				_commerceDiscountService.getCommerceDiscount(id),
				_serviceContextHelper);

		return _toDiscountProductGroup(
			commerceDiscountRel.getCommerceDiscountRelId());
	}

	private DiscountProductGroup _toDiscountProductGroup(
			Long commerceDiscountRelId)
		throws Exception {

		return _discountProductGroupDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				commerceDiscountRelId,
				contextAcceptLanguage.getPreferredLocale()));
	}

	private List<DiscountProductGroup> _toDiscountProductGroups(
			List<CommerceDiscountRel> commerceDiscountRels)
		throws Exception {

		List<DiscountProductGroup> discountProductGroups = new ArrayList<>();

		for (CommerceDiscountRel commerceDiscountRel : commerceDiscountRels) {
			discountProductGroups.add(
				_toDiscountProductGroup(
					commerceDiscountRel.getCommerceDiscountRelId()));
		}

		return discountProductGroups;
	}

	@Reference
	private CommerceDiscountRelService _commerceDiscountRelService;

	@Reference
	private CommerceDiscountService _commerceDiscountService;

	@Reference
	private CommercePricingClassService _commercePricingClassService;

	@Reference
	private DiscountProductGroupDTOConverter _discountProductGroupDTOConverter;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}