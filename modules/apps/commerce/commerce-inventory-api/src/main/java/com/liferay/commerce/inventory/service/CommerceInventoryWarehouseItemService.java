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

package com.liferay.commerce.inventory.service;

import com.liferay.commerce.inventory.model.CommerceInventoryWarehouseItem;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import java.util.Date;
import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the remote service interface for CommerceInventoryWarehouseItem. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Luca Pellizzon
 * @see CommerceInventoryWarehouseItemServiceUtil
 * @generated
 */
@AccessControlled
@JSONWebService
@OSGiBeanProperties(
	property = {
		"json.web.service.context.name=commerce",
		"json.web.service.context.path=CommerceInventoryWarehouseItem"
	},
	service = CommerceInventoryWarehouseItemService.class
)
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface CommerceInventoryWarehouseItemService extends BaseService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceInventoryWarehouseItemServiceUtil} to access the commerce inventory warehouse item remote service. Add custom service methods to <code>com.liferay.commerce.inventory.service.impl.CommerceInventoryWarehouseItemServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public CommerceInventoryWarehouseItem addCommerceInventoryWarehouseItem(
			long userId, long commerceInventoryWarehouseId, String sku,
			int quantity)
		throws PortalException;

	public CommerceInventoryWarehouseItem addCommerceInventoryWarehouseItem(
			long userId, long commerceInventoryWarehouseId,
			String externalReferenceCode, String sku, int quantity)
		throws PortalException;

	public void deleteCommerceInventoryWarehouseItem(
			long commerceInventoryWarehouseItemId)
		throws PortalException;

	public void deleteCommerceInventoryWarehouseItems(
			long companyId, String sku)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CommerceInventoryWarehouseItem fetchCommerceInventoryWarehouseItem(
			long commerceInventoryWarehouseId, String sku)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CommerceInventoryWarehouseItem
			fetchCommerceInventoryWarehouseItemByReferenceCode(
				long companyId, String externalReferenceCode)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CommerceInventoryWarehouseItem getCommerceInventoryWarehouseItem(
			long commerceInventoryWarehouseItemId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CommerceInventoryWarehouseItem
			getCommerceInventoryWarehouseItemByReferenceCode(
				long companyId, String externalReferenceCode)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CommerceInventoryWarehouseItem>
			getCommerceInventoryWarehouseItems(
				long commerceInventoryWarehouseId, int start, int end)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CommerceInventoryWarehouseItem>
			getCommerceInventoryWarehouseItems(
				long companyId, String sku, int start, int end)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CommerceInventoryWarehouseItem>
			getCommerceInventoryWarehouseItemsByCompanyId(
				long companyId, int start, int end)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CommerceInventoryWarehouseItem>
			getCommerceInventoryWarehouseItemsByCompanyIdAndSku(
				long companyId, String sku, int start, int end)
		throws PrincipalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCommerceInventoryWarehouseItemsCount(
			long commerceInventoryWarehouseId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCommerceInventoryWarehouseItemsCount(
			long companyId, String sku)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCommerceInventoryWarehouseItemsCountByCompanyId(
			long companyId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCommerceInventoryWarehouseItemsCountByModifiedDate(
			long companyId, Date startDate, Date endDate)
		throws PrincipalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CommerceInventoryWarehouseItem>
			getCommerceInventoryWarehouseItemsCountByModifiedDate(
				long companyId, Date startDate, Date endDate, int start,
				int end)
		throws PrincipalException;

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	public CommerceInventoryWarehouseItem
			increaseCommerceInventoryWarehouseItemQuantity(
				long commerceInventoryWarehouseItemId, int quantity)
		throws PortalException;

	public void moveQuantitiesBetweenWarehouses(
			long fromCommerceInventoryWarehouseId,
			long toCommerceInventoryWarehouseId, String sku, int quantity)
		throws PortalException;

	public CommerceInventoryWarehouseItem updateCommerceInventoryWarehouseItem(
			long commerceInventoryWarehouseItemId, int quantity,
			int reservedQuantity, long mvccVersion)
		throws PortalException;

	public CommerceInventoryWarehouseItem updateCommerceInventoryWarehouseItem(
			long commerceInventoryWarehouseItemId, int quantity,
			long mvccVersion)
		throws PortalException;

	public CommerceInventoryWarehouseItem upsertCommerceInventoryWarehouseItem(
			long companyId, long userId, long commerceInventoryWarehouseId,
			String externalReferenceCode, String sku, int quantity)
		throws PortalException;

	public CommerceInventoryWarehouseItem upsertCommerceInventoryWarehouseItem(
			long userId, long commerceInventoryWarehouseId, String sku,
			int quantity)
		throws PortalException;

}