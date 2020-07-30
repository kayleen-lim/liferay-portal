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

package com.liferay.commerce.frontend.internal.clay.data.set;

import com.liferay.commerce.frontend.clay.data.set.ClayDataSetFilterContextContributor;
import com.liferay.commerce.frontend.clay.data.set.ClayDataSetFilterContextContributorRegistry;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerCustomizerFactory;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerCustomizerFactory.ServiceWrapper;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Marco Leo
 */
@Component(
	immediate = true,
	service = ClayDataSetFilterContextContributorRegistry.class
)
public class ClayDataSetFilterContextContributorRegistryImpl
	implements ClayDataSetFilterContextContributorRegistry {

	@Override
	public List<ClayDataSetFilterContextContributor>
		getClayDataSetFilterContextContributors(String key) {

		List<ServiceWrapper<ClayDataSetFilterContextContributor>>
			clayDataSetFilterContextContributorServiceWrappers =
				_serviceTrackerMap.getService(key);

		if (clayDataSetFilterContextContributorServiceWrappers ==
				null) {

			if (_log.isDebugEnabled()) {
				_log.debug(
					"No ClayDataSetFilterContextContributor " +
						"registered with key " + key);
			}

			return Collections.emptyList();
		}

		List<ClayDataSetFilterContextContributor>
			clayDataSetFilterContextContributors = new ArrayList<>();

		for (ServiceWrapper<ClayDataSetFilterContextContributor>
				clayDataSetFilterContextContributorServiceWrapper :
					clayDataSetFilterContextContributorServiceWrappers) {

			clayDataSetFilterContextContributors.add(
				clayDataSetFilterContextContributorServiceWrapper.
					getService());
		}

		return clayDataSetFilterContextContributors;
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceTrackerMap = ServiceTrackerMapFactory.openMultiValueMap(
			bundleContext, ClayDataSetFilterContextContributor.class,
			"commerce.data.set.filter.type",
			ServiceTrackerCustomizerFactory.
				<ClayDataSetFilterContextContributor>serviceWrapper(
					bundleContext));
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerMap.close();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ClayDataSetFilterContextContributorRegistryImpl.class);

	private ServiceTrackerMap
		<String,
		 List<ServiceWrapper<ClayDataSetFilterContextContributor>>>
			_serviceTrackerMap;

}