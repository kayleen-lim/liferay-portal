<%--
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
--%>

<%@ include file="/document_library/ct_display/init.jsp" %>

<%
DLFolder dlFolder = (DLFolder)request.getAttribute(WebKeys.DOCUMENT_LIBRARY_FOLDER);
%>

<p>
	<b><liferay-ui:message key="id" /></b>: <%= dlFolder.getFolderId() %>
</p>

<p>
	<b><liferay-ui:message key="name" /></b>: <%= HtmlUtil.escape(dlFolder.getName()) %>
</p>

<p>
	<b><liferay-ui:message key="description" /></b>: <%= HtmlUtil.escape(dlFolder.getDescription()) %>
</p>

<p>
	<b><liferay-ui:message key="folders" /></b>: <%= DLAppServiceUtil.getFoldersCount(dlFolder.getRepositoryId(), dlFolder.getFolderId()) %>
</p>

<%
int status = WorkflowConstants.STATUS_APPROVED;

if (permissionChecker.isContentReviewer(user.getCompanyId(), scopeGroupId)) {
	status = WorkflowConstants.STATUS_ANY;
}

int fileEntriesCount = DLAppServiceUtil.getFileEntriesAndFileShortcutsCount(dlFolder.getRepositoryId(), dlFolder.getFolderId(), status);
%>

<p>
	<b><liferay-ui:message key="documents" /></b>: <%= fileEntriesCount %>
</p>