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

package com.liferay.dynamic.data.mapping.uad.exporter;

import com.liferay.dynamic.data.mapping.model.DDMFormInstanceRecord;
import com.liferay.dynamic.data.mapping.service.DDMFormInstanceRecordLocalService;
import com.liferay.dynamic.data.mapping.uad.constants.DDMUADConstants;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.user.associated.data.exporter.DynamicQueryUADExporter;

import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the ddm form instance record UAD exporter.
 *
 * <p>
 * This implementation exists only as a container for the default methods
 * generated by ServiceBuilder. All custom service methods should be put in
 * {@link DDMFormInstanceRecordUADExporter}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class BaseDDMFormInstanceRecordUADExporter
	extends DynamicQueryUADExporter<DDMFormInstanceRecord> {

	@Override
	public Class<DDMFormInstanceRecord> getTypeClass() {
		return DDMFormInstanceRecord.class;
	}

	@Override
	protected ActionableDynamicQuery doGetActionableDynamicQuery() {
		return ddmFormInstanceRecordLocalService.getActionableDynamicQuery();
	}

	@Override
	protected String[] doGetUserIdFieldNames() {
		return DDMUADConstants.USER_ID_FIELD_NAMES_DDM_FORM_INSTANCE_RECORD;
	}

	@Override
	protected String toXmlString(DDMFormInstanceRecord ddmFormInstanceRecord) {
		StringBundler sb = new StringBundler(22);

		sb.append("<model><model-name>");
		sb.append(
			"com.liferay.dynamic.data.mapping.model.DDMFormInstanceRecord");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>formInstanceRecordId</column-name><column-value><![CDATA[");
		sb.append(ddmFormInstanceRecord.getFormInstanceRecordId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>versionUserId</column-name><column-value><![CDATA[");
		sb.append(ddmFormInstanceRecord.getVersionUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>versionUserName</column-name><column-value><![CDATA[");
		sb.append(ddmFormInstanceRecord.getVersionUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(ddmFormInstanceRecord.getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(ddmFormInstanceRecord.getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>version</column-name><column-value><![CDATA[");
		sb.append(ddmFormInstanceRecord.getVersion());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	@Reference
	protected DDMFormInstanceRecordLocalService
		ddmFormInstanceRecordLocalService;

}