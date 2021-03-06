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

package com.liferay.layout.content.page.editor.web.internal.util;

import com.liferay.document.library.kernel.model.DLFileEntryConstants;
import com.liferay.info.field.InfoField;
import com.liferay.info.form.InfoForm;
import com.liferay.info.item.InfoItemServiceTracker;
import com.liferay.info.item.provider.InfoItemFormProvider;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Objects;

import javax.portlet.ResourceRequest;

/**
 * @author Eudaldo Alonso
 */
public class MappingContentUtil {

	public static JSONArray getMappingFieldsJSONArray(
			String formVariationKey,
			InfoItemServiceTracker infoItemServiceTracker, String itemClassName,
			ResourceRequest resourceRequest)
		throws Exception {

		// LPS-111037

		if (Objects.equals(
				DLFileEntryConstants.getClassName(), itemClassName)) {

			itemClassName = FileEntry.class.getName();
		}

		InfoItemFormProvider<?> infoItemFormProvider =
			infoItemServiceTracker.getFirstInfoItemService(
				InfoItemFormProvider.class, itemClassName);

		if (infoItemFormProvider == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to get info item form provider for class " +
						itemClassName);
			}

			return JSONFactoryUtil.createJSONArray();
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		InfoForm infoForm = infoItemFormProvider.getInfoForm(formVariationKey);

		for (InfoField infoField : infoForm.getAllInfoFields()) {
			jsonArray.put(
				JSONUtil.put(
					"key", infoField.getName()
				).put(
					"label", infoField.getLabel(themeDisplay.getLocale())
				).put(
					"type",
					infoField.getInfoFieldType(
					).getName()
				));
		}

		return jsonArray;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		MappingContentUtil.class);

}