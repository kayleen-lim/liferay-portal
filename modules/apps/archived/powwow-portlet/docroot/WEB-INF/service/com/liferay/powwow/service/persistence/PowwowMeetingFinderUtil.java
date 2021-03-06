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

package com.liferay.powwow.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;

/**
 * @author Shinn Lok
 * @generated
 */
public class PowwowMeetingFinderUtil {

	public static int countByU_S(long userId, int[] statuses) {
		return getFinder().countByU_S(userId, statuses);
	}

	public static java.util.List<com.liferay.powwow.model.PowwowMeeting>
		findByU_S(
			long userId, int[] statuses, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.powwow.model.PowwowMeeting> orderByComparator) {

		return getFinder().findByU_S(
			userId, statuses, start, end, orderByComparator);
	}

	public static PowwowMeetingFinder getFinder() {
		if (_finder == null) {
			_finder = (PowwowMeetingFinder)PortletBeanLocatorUtil.locate(
				com.liferay.powwow.service.ServletContextUtil.
					getServletContextName(),
				PowwowMeetingFinder.class.getName());
		}

		return _finder;
	}

	public void setFinder(PowwowMeetingFinder finder) {
		_finder = finder;
	}

	private static PowwowMeetingFinder _finder;

}