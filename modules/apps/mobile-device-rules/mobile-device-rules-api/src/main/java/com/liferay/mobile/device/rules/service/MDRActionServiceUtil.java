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

package com.liferay.mobile.device.rules.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for MDRAction. This utility wraps
 * <code>com.liferay.mobile.device.rules.service.impl.MDRActionServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Edward C. Han
 * @see MDRActionService
 * @generated
 */
public class MDRActionServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.mobile.device.rules.service.impl.MDRActionServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.mobile.device.rules.model.MDRAction addAction(
			long ruleGroupInstanceId,
			java.util.Map<java.util.Locale, String> nameMap,
			java.util.Map<java.util.Locale, String> descriptionMap, String type,
			String typeSettings,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addAction(
			ruleGroupInstanceId, nameMap, descriptionMap, type, typeSettings,
			serviceContext);
	}

	public static com.liferay.mobile.device.rules.model.MDRAction addAction(
			long ruleGroupInstanceId,
			java.util.Map<java.util.Locale, String> nameMap,
			java.util.Map<java.util.Locale, String> descriptionMap, String type,
			com.liferay.portal.kernel.util.UnicodeProperties
				typeSettingsUnicodeProperties,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addAction(
			ruleGroupInstanceId, nameMap, descriptionMap, type,
			typeSettingsUnicodeProperties, serviceContext);
	}

	public static void deleteAction(long actionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deleteAction(actionId);
	}

	public static com.liferay.mobile.device.rules.model.MDRAction fetchAction(
			long actionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().fetchAction(actionId);
	}

	public static com.liferay.mobile.device.rules.model.MDRAction getAction(
			long actionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getAction(actionId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.mobile.device.rules.model.MDRAction updateAction(
			long actionId, java.util.Map<java.util.Locale, String> nameMap,
			java.util.Map<java.util.Locale, String> descriptionMap, String type,
			String typeSettings,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateAction(
			actionId, nameMap, descriptionMap, type, typeSettings,
			serviceContext);
	}

	public static com.liferay.mobile.device.rules.model.MDRAction updateAction(
			long actionId, java.util.Map<java.util.Locale, String> nameMap,
			java.util.Map<java.util.Locale, String> descriptionMap, String type,
			com.liferay.portal.kernel.util.UnicodeProperties
				typeSettingsUnicodeProperties,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateAction(
			actionId, nameMap, descriptionMap, type,
			typeSettingsUnicodeProperties, serviceContext);
	}

	public static MDRActionService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<MDRActionService, MDRActionService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(MDRActionService.class);

		ServiceTracker<MDRActionService, MDRActionService> serviceTracker =
			new ServiceTracker<MDRActionService, MDRActionService>(
				bundle.getBundleContext(), MDRActionService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}