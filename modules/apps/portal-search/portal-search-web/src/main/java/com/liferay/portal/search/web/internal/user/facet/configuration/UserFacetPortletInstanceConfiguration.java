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

package com.liferay.portal.search.web.internal.user.facet.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Kevin Tan
 */
@ExtendedObjectClassDefinition(
	category = "search",
	scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE
)
@Meta.OCD(
	id = "com.liferay.portal.search.web.internal.user.facet.configuration.UserFacetPortletInstanceConfiguration",
	localization = "content/Language",
	name = "user-facet-portlet-instance-configuration-name"
)
public interface UserFacetPortletInstanceConfiguration {

	@Meta.AD(deflt = "0", name = "display-style-group-id", required = false)
	public long displayStyleGroupId();

	@Meta.AD(name = "display-style", required = false)
	public String displayStyle();

}