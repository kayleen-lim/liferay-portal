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

package com.liferay.commerce.frontend.clay.table.selectable;

import com.liferay.commerce.frontend.clay.data.set.ClayDataSetConstants;
import com.liferay.commerce.frontend.clay.data.set.ClayDataSetDisplayView;
import com.liferay.petra.string.StringPool;

import java.util.Locale;

/**
 * @author Alessio Antonio Rendina
 */
public abstract class ClaySelectableTableDataSetDisplayView
	implements ClayDataSetDisplayView {

	public String getContentRenderer() {
		return ClayDataSetConstants.
			CLAY_DATA_SET_CONTENT_RENDERER_SELECTABLE_TABLE;
	}

	public abstract String getFirstColumnLabel(Locale locale);

	public abstract String getFirstColumnName();

	public String getLabel() {
		return ClayDataSetConstants.
			CLAY_DATA_SET_CONTENT_RENDERER_SELECTABLE_TABLE;
	}

	public String getThumbnail() {
		return StringPool.BLANK;
	}

}