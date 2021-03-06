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

package com.liferay.portal.kernel.language;

/**
 * @author Tina Tian
 */
public class LanguageBuilderUtil {

	public static final String AUTOMATIC_COPY = " (Automatic Copy)";

	public static final String AUTOMATIC_TRANSLATION =
		" (Automatic Translation)";

	public static String fixValue(String value) {
		if (value.endsWith(AUTOMATIC_COPY)) {
			value = value.substring(
				0, value.length() - AUTOMATIC_COPY.length());
		}

		if (value.endsWith(AUTOMATIC_TRANSLATION)) {
			value = value.substring(
				0, value.length() - AUTOMATIC_TRANSLATION.length());
		}

		return value;
	}

}