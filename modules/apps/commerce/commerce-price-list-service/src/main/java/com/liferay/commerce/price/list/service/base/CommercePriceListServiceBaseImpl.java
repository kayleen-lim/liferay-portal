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

package com.liferay.commerce.price.list.service.base;

import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.service.CommercePriceListService;
import com.liferay.commerce.price.list.service.persistence.CommercePriceEntryPersistence;
import com.liferay.commerce.price.list.service.persistence.CommercePriceListAccountRelPersistence;
import com.liferay.commerce.price.list.service.persistence.CommercePriceListChannelRelPersistence;
import com.liferay.commerce.price.list.service.persistence.CommercePriceListCommerceAccountGroupRelPersistence;
import com.liferay.commerce.price.list.service.persistence.CommercePriceListDiscountRelPersistence;
import com.liferay.commerce.price.list.service.persistence.CommercePriceListFinder;
import com.liferay.commerce.price.list.service.persistence.CommercePriceListPersistence;
import com.liferay.commerce.price.list.service.persistence.CommerceTierPriceEntryPersistence;
import com.liferay.expando.kernel.service.persistence.ExpandoRowPersistence;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseServiceImpl;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.CompanyPersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.service.persistence.WorkflowInstanceLinkPersistence;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the commerce price list remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.commerce.price.list.service.impl.CommercePriceListServiceImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see com.liferay.commerce.price.list.service.impl.CommercePriceListServiceImpl
 * @generated
 */
public abstract class CommercePriceListServiceBaseImpl
	extends BaseServiceImpl
	implements CommercePriceListService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>CommercePriceListService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.commerce.price.list.service.CommercePriceListServiceUtil</code>.
	 */

	/**
	 * Returns the commerce price entry local service.
	 *
	 * @return the commerce price entry local service
	 */
	public
		com.liferay.commerce.price.list.service.CommercePriceEntryLocalService
			getCommercePriceEntryLocalService() {

		return commercePriceEntryLocalService;
	}

	/**
	 * Sets the commerce price entry local service.
	 *
	 * @param commercePriceEntryLocalService the commerce price entry local service
	 */
	public void setCommercePriceEntryLocalService(
		com.liferay.commerce.price.list.service.CommercePriceEntryLocalService
			commercePriceEntryLocalService) {

		this.commercePriceEntryLocalService = commercePriceEntryLocalService;
	}

	/**
	 * Returns the commerce price entry remote service.
	 *
	 * @return the commerce price entry remote service
	 */
	public com.liferay.commerce.price.list.service.CommercePriceEntryService
		getCommercePriceEntryService() {

		return commercePriceEntryService;
	}

	/**
	 * Sets the commerce price entry remote service.
	 *
	 * @param commercePriceEntryService the commerce price entry remote service
	 */
	public void setCommercePriceEntryService(
		com.liferay.commerce.price.list.service.CommercePriceEntryService
			commercePriceEntryService) {

		this.commercePriceEntryService = commercePriceEntryService;
	}

	/**
	 * Returns the commerce price entry persistence.
	 *
	 * @return the commerce price entry persistence
	 */
	public CommercePriceEntryPersistence getCommercePriceEntryPersistence() {
		return commercePriceEntryPersistence;
	}

	/**
	 * Sets the commerce price entry persistence.
	 *
	 * @param commercePriceEntryPersistence the commerce price entry persistence
	 */
	public void setCommercePriceEntryPersistence(
		CommercePriceEntryPersistence commercePriceEntryPersistence) {

		this.commercePriceEntryPersistence = commercePriceEntryPersistence;
	}

	/**
	 * Returns the commerce price list local service.
	 *
	 * @return the commerce price list local service
	 */
	public com.liferay.commerce.price.list.service.CommercePriceListLocalService
		getCommercePriceListLocalService() {

		return commercePriceListLocalService;
	}

	/**
	 * Sets the commerce price list local service.
	 *
	 * @param commercePriceListLocalService the commerce price list local service
	 */
	public void setCommercePriceListLocalService(
		com.liferay.commerce.price.list.service.CommercePriceListLocalService
			commercePriceListLocalService) {

		this.commercePriceListLocalService = commercePriceListLocalService;
	}

	/**
	 * Returns the commerce price list remote service.
	 *
	 * @return the commerce price list remote service
	 */
	public CommercePriceListService getCommercePriceListService() {
		return commercePriceListService;
	}

	/**
	 * Sets the commerce price list remote service.
	 *
	 * @param commercePriceListService the commerce price list remote service
	 */
	public void setCommercePriceListService(
		CommercePriceListService commercePriceListService) {

		this.commercePriceListService = commercePriceListService;
	}

	/**
	 * Returns the commerce price list persistence.
	 *
	 * @return the commerce price list persistence
	 */
	public CommercePriceListPersistence getCommercePriceListPersistence() {
		return commercePriceListPersistence;
	}

	/**
	 * Sets the commerce price list persistence.
	 *
	 * @param commercePriceListPersistence the commerce price list persistence
	 */
	public void setCommercePriceListPersistence(
		CommercePriceListPersistence commercePriceListPersistence) {

		this.commercePriceListPersistence = commercePriceListPersistence;
	}

	/**
	 * Returns the commerce price list finder.
	 *
	 * @return the commerce price list finder
	 */
	public CommercePriceListFinder getCommercePriceListFinder() {
		return commercePriceListFinder;
	}

	/**
	 * Sets the commerce price list finder.
	 *
	 * @param commercePriceListFinder the commerce price list finder
	 */
	public void setCommercePriceListFinder(
		CommercePriceListFinder commercePriceListFinder) {

		this.commercePriceListFinder = commercePriceListFinder;
	}

	/**
	 * Returns the commerce price list account rel local service.
	 *
	 * @return the commerce price list account rel local service
	 */
	public com.liferay.commerce.price.list.service.
		CommercePriceListAccountRelLocalService
			getCommercePriceListAccountRelLocalService() {

		return commercePriceListAccountRelLocalService;
	}

	/**
	 * Sets the commerce price list account rel local service.
	 *
	 * @param commercePriceListAccountRelLocalService the commerce price list account rel local service
	 */
	public void setCommercePriceListAccountRelLocalService(
		com.liferay.commerce.price.list.service.
			CommercePriceListAccountRelLocalService
				commercePriceListAccountRelLocalService) {

		this.commercePriceListAccountRelLocalService =
			commercePriceListAccountRelLocalService;
	}

	/**
	 * Returns the commerce price list account rel remote service.
	 *
	 * @return the commerce price list account rel remote service
	 */
	public
		com.liferay.commerce.price.list.service.
			CommercePriceListAccountRelService
				getCommercePriceListAccountRelService() {

		return commercePriceListAccountRelService;
	}

	/**
	 * Sets the commerce price list account rel remote service.
	 *
	 * @param commercePriceListAccountRelService the commerce price list account rel remote service
	 */
	public void setCommercePriceListAccountRelService(
		com.liferay.commerce.price.list.service.
			CommercePriceListAccountRelService
				commercePriceListAccountRelService) {

		this.commercePriceListAccountRelService =
			commercePriceListAccountRelService;
	}

	/**
	 * Returns the commerce price list account rel persistence.
	 *
	 * @return the commerce price list account rel persistence
	 */
	public CommercePriceListAccountRelPersistence
		getCommercePriceListAccountRelPersistence() {

		return commercePriceListAccountRelPersistence;
	}

	/**
	 * Sets the commerce price list account rel persistence.
	 *
	 * @param commercePriceListAccountRelPersistence the commerce price list account rel persistence
	 */
	public void setCommercePriceListAccountRelPersistence(
		CommercePriceListAccountRelPersistence
			commercePriceListAccountRelPersistence) {

		this.commercePriceListAccountRelPersistence =
			commercePriceListAccountRelPersistence;
	}

	/**
	 * Returns the commerce price list channel rel local service.
	 *
	 * @return the commerce price list channel rel local service
	 */
	public com.liferay.commerce.price.list.service.
		CommercePriceListChannelRelLocalService
			getCommercePriceListChannelRelLocalService() {

		return commercePriceListChannelRelLocalService;
	}

	/**
	 * Sets the commerce price list channel rel local service.
	 *
	 * @param commercePriceListChannelRelLocalService the commerce price list channel rel local service
	 */
	public void setCommercePriceListChannelRelLocalService(
		com.liferay.commerce.price.list.service.
			CommercePriceListChannelRelLocalService
				commercePriceListChannelRelLocalService) {

		this.commercePriceListChannelRelLocalService =
			commercePriceListChannelRelLocalService;
	}

	/**
	 * Returns the commerce price list channel rel remote service.
	 *
	 * @return the commerce price list channel rel remote service
	 */
	public
		com.liferay.commerce.price.list.service.
			CommercePriceListChannelRelService
				getCommercePriceListChannelRelService() {

		return commercePriceListChannelRelService;
	}

	/**
	 * Sets the commerce price list channel rel remote service.
	 *
	 * @param commercePriceListChannelRelService the commerce price list channel rel remote service
	 */
	public void setCommercePriceListChannelRelService(
		com.liferay.commerce.price.list.service.
			CommercePriceListChannelRelService
				commercePriceListChannelRelService) {

		this.commercePriceListChannelRelService =
			commercePriceListChannelRelService;
	}

	/**
	 * Returns the commerce price list channel rel persistence.
	 *
	 * @return the commerce price list channel rel persistence
	 */
	public CommercePriceListChannelRelPersistence
		getCommercePriceListChannelRelPersistence() {

		return commercePriceListChannelRelPersistence;
	}

	/**
	 * Sets the commerce price list channel rel persistence.
	 *
	 * @param commercePriceListChannelRelPersistence the commerce price list channel rel persistence
	 */
	public void setCommercePriceListChannelRelPersistence(
		CommercePriceListChannelRelPersistence
			commercePriceListChannelRelPersistence) {

		this.commercePriceListChannelRelPersistence =
			commercePriceListChannelRelPersistence;
	}

	/**
	 * Returns the commerce price list commerce account group rel local service.
	 *
	 * @return the commerce price list commerce account group rel local service
	 */
	public com.liferay.commerce.price.list.service.
		CommercePriceListCommerceAccountGroupRelLocalService
			getCommercePriceListCommerceAccountGroupRelLocalService() {

		return commercePriceListCommerceAccountGroupRelLocalService;
	}

	/**
	 * Sets the commerce price list commerce account group rel local service.
	 *
	 * @param commercePriceListCommerceAccountGroupRelLocalService the commerce price list commerce account group rel local service
	 */
	public void setCommercePriceListCommerceAccountGroupRelLocalService(
		com.liferay.commerce.price.list.service.
			CommercePriceListCommerceAccountGroupRelLocalService
				commercePriceListCommerceAccountGroupRelLocalService) {

		this.commercePriceListCommerceAccountGroupRelLocalService =
			commercePriceListCommerceAccountGroupRelLocalService;
	}

	/**
	 * Returns the commerce price list commerce account group rel remote service.
	 *
	 * @return the commerce price list commerce account group rel remote service
	 */
	public com.liferay.commerce.price.list.service.
		CommercePriceListCommerceAccountGroupRelService
			getCommercePriceListCommerceAccountGroupRelService() {

		return commercePriceListCommerceAccountGroupRelService;
	}

	/**
	 * Sets the commerce price list commerce account group rel remote service.
	 *
	 * @param commercePriceListCommerceAccountGroupRelService the commerce price list commerce account group rel remote service
	 */
	public void setCommercePriceListCommerceAccountGroupRelService(
		com.liferay.commerce.price.list.service.
			CommercePriceListCommerceAccountGroupRelService
				commercePriceListCommerceAccountGroupRelService) {

		this.commercePriceListCommerceAccountGroupRelService =
			commercePriceListCommerceAccountGroupRelService;
	}

	/**
	 * Returns the commerce price list commerce account group rel persistence.
	 *
	 * @return the commerce price list commerce account group rel persistence
	 */
	public CommercePriceListCommerceAccountGroupRelPersistence
		getCommercePriceListCommerceAccountGroupRelPersistence() {

		return commercePriceListCommerceAccountGroupRelPersistence;
	}

	/**
	 * Sets the commerce price list commerce account group rel persistence.
	 *
	 * @param commercePriceListCommerceAccountGroupRelPersistence the commerce price list commerce account group rel persistence
	 */
	public void setCommercePriceListCommerceAccountGroupRelPersistence(
		CommercePriceListCommerceAccountGroupRelPersistence
			commercePriceListCommerceAccountGroupRelPersistence) {

		this.commercePriceListCommerceAccountGroupRelPersistence =
			commercePriceListCommerceAccountGroupRelPersistence;
	}

	/**
	 * Returns the commerce price list discount rel local service.
	 *
	 * @return the commerce price list discount rel local service
	 */
	public com.liferay.commerce.price.list.service.
		CommercePriceListDiscountRelLocalService
			getCommercePriceListDiscountRelLocalService() {

		return commercePriceListDiscountRelLocalService;
	}

	/**
	 * Sets the commerce price list discount rel local service.
	 *
	 * @param commercePriceListDiscountRelLocalService the commerce price list discount rel local service
	 */
	public void setCommercePriceListDiscountRelLocalService(
		com.liferay.commerce.price.list.service.
			CommercePriceListDiscountRelLocalService
				commercePriceListDiscountRelLocalService) {

		this.commercePriceListDiscountRelLocalService =
			commercePriceListDiscountRelLocalService;
	}

	/**
	 * Returns the commerce price list discount rel remote service.
	 *
	 * @return the commerce price list discount rel remote service
	 */
	public
		com.liferay.commerce.price.list.service.
			CommercePriceListDiscountRelService
				getCommercePriceListDiscountRelService() {

		return commercePriceListDiscountRelService;
	}

	/**
	 * Sets the commerce price list discount rel remote service.
	 *
	 * @param commercePriceListDiscountRelService the commerce price list discount rel remote service
	 */
	public void setCommercePriceListDiscountRelService(
		com.liferay.commerce.price.list.service.
			CommercePriceListDiscountRelService
				commercePriceListDiscountRelService) {

		this.commercePriceListDiscountRelService =
			commercePriceListDiscountRelService;
	}

	/**
	 * Returns the commerce price list discount rel persistence.
	 *
	 * @return the commerce price list discount rel persistence
	 */
	public CommercePriceListDiscountRelPersistence
		getCommercePriceListDiscountRelPersistence() {

		return commercePriceListDiscountRelPersistence;
	}

	/**
	 * Sets the commerce price list discount rel persistence.
	 *
	 * @param commercePriceListDiscountRelPersistence the commerce price list discount rel persistence
	 */
	public void setCommercePriceListDiscountRelPersistence(
		CommercePriceListDiscountRelPersistence
			commercePriceListDiscountRelPersistence) {

		this.commercePriceListDiscountRelPersistence =
			commercePriceListDiscountRelPersistence;
	}

	/**
	 * Returns the commerce tier price entry local service.
	 *
	 * @return the commerce tier price entry local service
	 */
	public
		com.liferay.commerce.price.list.service.
			CommerceTierPriceEntryLocalService
				getCommerceTierPriceEntryLocalService() {

		return commerceTierPriceEntryLocalService;
	}

	/**
	 * Sets the commerce tier price entry local service.
	 *
	 * @param commerceTierPriceEntryLocalService the commerce tier price entry local service
	 */
	public void setCommerceTierPriceEntryLocalService(
		com.liferay.commerce.price.list.service.
			CommerceTierPriceEntryLocalService
				commerceTierPriceEntryLocalService) {

		this.commerceTierPriceEntryLocalService =
			commerceTierPriceEntryLocalService;
	}

	/**
	 * Returns the commerce tier price entry remote service.
	 *
	 * @return the commerce tier price entry remote service
	 */
	public com.liferay.commerce.price.list.service.CommerceTierPriceEntryService
		getCommerceTierPriceEntryService() {

		return commerceTierPriceEntryService;
	}

	/**
	 * Sets the commerce tier price entry remote service.
	 *
	 * @param commerceTierPriceEntryService the commerce tier price entry remote service
	 */
	public void setCommerceTierPriceEntryService(
		com.liferay.commerce.price.list.service.CommerceTierPriceEntryService
			commerceTierPriceEntryService) {

		this.commerceTierPriceEntryService = commerceTierPriceEntryService;
	}

	/**
	 * Returns the commerce tier price entry persistence.
	 *
	 * @return the commerce tier price entry persistence
	 */
	public CommerceTierPriceEntryPersistence
		getCommerceTierPriceEntryPersistence() {

		return commerceTierPriceEntryPersistence;
	}

	/**
	 * Sets the commerce tier price entry persistence.
	 *
	 * @param commerceTierPriceEntryPersistence the commerce tier price entry persistence
	 */
	public void setCommerceTierPriceEntryPersistence(
		CommerceTierPriceEntryPersistence commerceTierPriceEntryPersistence) {

		this.commerceTierPriceEntryPersistence =
			commerceTierPriceEntryPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService
		getCounterLocalService() {

		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService
			counterLocalService) {

		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the class name local service.
	 *
	 * @return the class name local service
	 */
	public com.liferay.portal.kernel.service.ClassNameLocalService
		getClassNameLocalService() {

		return classNameLocalService;
	}

	/**
	 * Sets the class name local service.
	 *
	 * @param classNameLocalService the class name local service
	 */
	public void setClassNameLocalService(
		com.liferay.portal.kernel.service.ClassNameLocalService
			classNameLocalService) {

		this.classNameLocalService = classNameLocalService;
	}

	/**
	 * Returns the class name remote service.
	 *
	 * @return the class name remote service
	 */
	public com.liferay.portal.kernel.service.ClassNameService
		getClassNameService() {

		return classNameService;
	}

	/**
	 * Sets the class name remote service.
	 *
	 * @param classNameService the class name remote service
	 */
	public void setClassNameService(
		com.liferay.portal.kernel.service.ClassNameService classNameService) {

		this.classNameService = classNameService;
	}

	/**
	 * Returns the class name persistence.
	 *
	 * @return the class name persistence
	 */
	public ClassNamePersistence getClassNamePersistence() {
		return classNamePersistence;
	}

	/**
	 * Sets the class name persistence.
	 *
	 * @param classNamePersistence the class name persistence
	 */
	public void setClassNamePersistence(
		ClassNamePersistence classNamePersistence) {

		this.classNamePersistence = classNamePersistence;
	}

	/**
	 * Returns the company local service.
	 *
	 * @return the company local service
	 */
	public com.liferay.portal.kernel.service.CompanyLocalService
		getCompanyLocalService() {

		return companyLocalService;
	}

	/**
	 * Sets the company local service.
	 *
	 * @param companyLocalService the company local service
	 */
	public void setCompanyLocalService(
		com.liferay.portal.kernel.service.CompanyLocalService
			companyLocalService) {

		this.companyLocalService = companyLocalService;
	}

	/**
	 * Returns the company remote service.
	 *
	 * @return the company remote service
	 */
	public com.liferay.portal.kernel.service.CompanyService
		getCompanyService() {

		return companyService;
	}

	/**
	 * Sets the company remote service.
	 *
	 * @param companyService the company remote service
	 */
	public void setCompanyService(
		com.liferay.portal.kernel.service.CompanyService companyService) {

		this.companyService = companyService;
	}

	/**
	 * Returns the company persistence.
	 *
	 * @return the company persistence
	 */
	public CompanyPersistence getCompanyPersistence() {
		return companyPersistence;
	}

	/**
	 * Sets the company persistence.
	 *
	 * @param companyPersistence the company persistence
	 */
	public void setCompanyPersistence(CompanyPersistence companyPersistence) {
		this.companyPersistence = companyPersistence;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.kernel.service.ResourceLocalService
		getResourceLocalService() {

		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.kernel.service.ResourceLocalService
			resourceLocalService) {

		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.kernel.service.UserLocalService
		getUserLocalService() {

		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.kernel.service.UserLocalService userLocalService) {

		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public com.liferay.portal.kernel.service.UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(
		com.liferay.portal.kernel.service.UserService userService) {

		this.userService = userService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	/**
	 * Returns the workflow instance link local service.
	 *
	 * @return the workflow instance link local service
	 */
	public com.liferay.portal.kernel.service.WorkflowInstanceLinkLocalService
		getWorkflowInstanceLinkLocalService() {

		return workflowInstanceLinkLocalService;
	}

	/**
	 * Sets the workflow instance link local service.
	 *
	 * @param workflowInstanceLinkLocalService the workflow instance link local service
	 */
	public void setWorkflowInstanceLinkLocalService(
		com.liferay.portal.kernel.service.WorkflowInstanceLinkLocalService
			workflowInstanceLinkLocalService) {

		this.workflowInstanceLinkLocalService =
			workflowInstanceLinkLocalService;
	}

	/**
	 * Returns the workflow instance link persistence.
	 *
	 * @return the workflow instance link persistence
	 */
	public WorkflowInstanceLinkPersistence
		getWorkflowInstanceLinkPersistence() {

		return workflowInstanceLinkPersistence;
	}

	/**
	 * Sets the workflow instance link persistence.
	 *
	 * @param workflowInstanceLinkPersistence the workflow instance link persistence
	 */
	public void setWorkflowInstanceLinkPersistence(
		WorkflowInstanceLinkPersistence workflowInstanceLinkPersistence) {

		this.workflowInstanceLinkPersistence = workflowInstanceLinkPersistence;
	}

	/**
	 * Returns the expando row local service.
	 *
	 * @return the expando row local service
	 */
	public com.liferay.expando.kernel.service.ExpandoRowLocalService
		getExpandoRowLocalService() {

		return expandoRowLocalService;
	}

	/**
	 * Sets the expando row local service.
	 *
	 * @param expandoRowLocalService the expando row local service
	 */
	public void setExpandoRowLocalService(
		com.liferay.expando.kernel.service.ExpandoRowLocalService
			expandoRowLocalService) {

		this.expandoRowLocalService = expandoRowLocalService;
	}

	/**
	 * Returns the expando row persistence.
	 *
	 * @return the expando row persistence
	 */
	public ExpandoRowPersistence getExpandoRowPersistence() {
		return expandoRowPersistence;
	}

	/**
	 * Sets the expando row persistence.
	 *
	 * @param expandoRowPersistence the expando row persistence
	 */
	public void setExpandoRowPersistence(
		ExpandoRowPersistence expandoRowPersistence) {

		this.expandoRowPersistence = expandoRowPersistence;
	}

	public void afterPropertiesSet() {
	}

	public void destroy() {
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return CommercePriceListService.class.getName();
	}

	protected Class<?> getModelClass() {
		return CommercePriceList.class;
	}

	protected String getModelClassName() {
		return CommercePriceList.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				commercePriceListPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(
				dataSource, sql);

			sqlUpdate.update();
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
	}

	@BeanReference(
		type = com.liferay.commerce.price.list.service.CommercePriceEntryLocalService.class
	)
	protected
		com.liferay.commerce.price.list.service.CommercePriceEntryLocalService
			commercePriceEntryLocalService;

	@BeanReference(
		type = com.liferay.commerce.price.list.service.CommercePriceEntryService.class
	)
	protected com.liferay.commerce.price.list.service.CommercePriceEntryService
		commercePriceEntryService;

	@BeanReference(type = CommercePriceEntryPersistence.class)
	protected CommercePriceEntryPersistence commercePriceEntryPersistence;

	@BeanReference(
		type = com.liferay.commerce.price.list.service.CommercePriceListLocalService.class
	)
	protected
		com.liferay.commerce.price.list.service.CommercePriceListLocalService
			commercePriceListLocalService;

	@BeanReference(type = CommercePriceListService.class)
	protected CommercePriceListService commercePriceListService;

	@BeanReference(type = CommercePriceListPersistence.class)
	protected CommercePriceListPersistence commercePriceListPersistence;

	@BeanReference(type = CommercePriceListFinder.class)
	protected CommercePriceListFinder commercePriceListFinder;

	@BeanReference(
		type = com.liferay.commerce.price.list.service.CommercePriceListAccountRelLocalService.class
	)
	protected com.liferay.commerce.price.list.service.
		CommercePriceListAccountRelLocalService
			commercePriceListAccountRelLocalService;

	@BeanReference(
		type = com.liferay.commerce.price.list.service.CommercePriceListAccountRelService.class
	)
	protected
		com.liferay.commerce.price.list.service.
			CommercePriceListAccountRelService
				commercePriceListAccountRelService;

	@BeanReference(type = CommercePriceListAccountRelPersistence.class)
	protected CommercePriceListAccountRelPersistence
		commercePriceListAccountRelPersistence;

	@BeanReference(
		type = com.liferay.commerce.price.list.service.CommercePriceListChannelRelLocalService.class
	)
	protected com.liferay.commerce.price.list.service.
		CommercePriceListChannelRelLocalService
			commercePriceListChannelRelLocalService;

	@BeanReference(
		type = com.liferay.commerce.price.list.service.CommercePriceListChannelRelService.class
	)
	protected
		com.liferay.commerce.price.list.service.
			CommercePriceListChannelRelService
				commercePriceListChannelRelService;

	@BeanReference(type = CommercePriceListChannelRelPersistence.class)
	protected CommercePriceListChannelRelPersistence
		commercePriceListChannelRelPersistence;

	@BeanReference(
		type = com.liferay.commerce.price.list.service.CommercePriceListCommerceAccountGroupRelLocalService.class
	)
	protected com.liferay.commerce.price.list.service.
		CommercePriceListCommerceAccountGroupRelLocalService
			commercePriceListCommerceAccountGroupRelLocalService;

	@BeanReference(
		type = com.liferay.commerce.price.list.service.CommercePriceListCommerceAccountGroupRelService.class
	)
	protected com.liferay.commerce.price.list.service.
		CommercePriceListCommerceAccountGroupRelService
			commercePriceListCommerceAccountGroupRelService;

	@BeanReference(
		type = CommercePriceListCommerceAccountGroupRelPersistence.class
	)
	protected CommercePriceListCommerceAccountGroupRelPersistence
		commercePriceListCommerceAccountGroupRelPersistence;

	@BeanReference(
		type = com.liferay.commerce.price.list.service.CommercePriceListDiscountRelLocalService.class
	)
	protected com.liferay.commerce.price.list.service.
		CommercePriceListDiscountRelLocalService
			commercePriceListDiscountRelLocalService;

	@BeanReference(
		type = com.liferay.commerce.price.list.service.CommercePriceListDiscountRelService.class
	)
	protected
		com.liferay.commerce.price.list.service.
			CommercePriceListDiscountRelService
				commercePriceListDiscountRelService;

	@BeanReference(type = CommercePriceListDiscountRelPersistence.class)
	protected CommercePriceListDiscountRelPersistence
		commercePriceListDiscountRelPersistence;

	@BeanReference(
		type = com.liferay.commerce.price.list.service.CommerceTierPriceEntryLocalService.class
	)
	protected
		com.liferay.commerce.price.list.service.
			CommerceTierPriceEntryLocalService
				commerceTierPriceEntryLocalService;

	@BeanReference(
		type = com.liferay.commerce.price.list.service.CommerceTierPriceEntryService.class
	)
	protected
		com.liferay.commerce.price.list.service.CommerceTierPriceEntryService
			commerceTierPriceEntryService;

	@BeanReference(type = CommerceTierPriceEntryPersistence.class)
	protected CommerceTierPriceEntryPersistence
		commerceTierPriceEntryPersistence;

	@ServiceReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.ClassNameLocalService.class
	)
	protected com.liferay.portal.kernel.service.ClassNameLocalService
		classNameLocalService;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.ClassNameService.class
	)
	protected com.liferay.portal.kernel.service.ClassNameService
		classNameService;

	@ServiceReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.CompanyLocalService.class
	)
	protected com.liferay.portal.kernel.service.CompanyLocalService
		companyLocalService;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.CompanyService.class
	)
	protected com.liferay.portal.kernel.service.CompanyService companyService;

	@ServiceReference(type = CompanyPersistence.class)
	protected CompanyPersistence companyPersistence;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.ResourceLocalService.class
	)
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.UserLocalService.class
	)
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.UserService.class
	)
	protected com.liferay.portal.kernel.service.UserService userService;

	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.WorkflowInstanceLinkLocalService.class
	)
	protected com.liferay.portal.kernel.service.WorkflowInstanceLinkLocalService
		workflowInstanceLinkLocalService;

	@ServiceReference(type = WorkflowInstanceLinkPersistence.class)
	protected WorkflowInstanceLinkPersistence workflowInstanceLinkPersistence;

	@ServiceReference(
		type = com.liferay.expando.kernel.service.ExpandoRowLocalService.class
	)
	protected com.liferay.expando.kernel.service.ExpandoRowLocalService
		expandoRowLocalService;

	@ServiceReference(type = ExpandoRowPersistence.class)
	protected ExpandoRowPersistence expandoRowPersistence;

}