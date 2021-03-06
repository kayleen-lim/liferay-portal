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

package com.liferay.message.boards.model.impl;

import com.liferay.message.boards.constants.MBCategoryConstants;
import com.liferay.message.boards.model.MBCategory;
import com.liferay.message.boards.service.MBCategoryLocalServiceUtil;
import com.liferay.message.boards.service.MBMessageLocalServiceUtil;
import com.liferay.message.boards.service.MBThreadLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class MBCategoryImpl extends MBCategoryBaseImpl {

	@Override
	public List<Long> getAncestorCategoryIds() throws PortalException {
		List<Long> ancestorCategoryIds = new ArrayList<>();

		MBCategory category = this;

		while (!category.isRoot()) {
			category = MBCategoryLocalServiceUtil.getCategory(
				category.getParentCategoryId());

			ancestorCategoryIds.add(category.getCategoryId());
		}

		return ancestorCategoryIds;
	}

	@Override
	public List<MBCategory> getAncestors() throws PortalException {
		List<MBCategory> ancestors = new ArrayList<>();

		MBCategory category = this;

		while (!category.isRoot()) {
			category = category.getParentCategory();

			ancestors.add(category);
		}

		return ancestors;
	}

	@Override
	public int getMessageCount() {
		return MBMessageLocalServiceUtil.getCategoryMessagesCount(
			getGroupId(), getCategoryId(), WorkflowConstants.STATUS_APPROVED);
	}

	@Override
	public MBCategory getParentCategory() throws PortalException {
		long parentCategoryId = getParentCategoryId();

		if ((parentCategoryId ==
				MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID) ||
			(parentCategoryId == MBCategoryConstants.DISCUSSION_CATEGORY_ID)) {

			return null;
		}

		return MBCategoryLocalServiceUtil.getCategory(getParentCategoryId());
	}

	@Override
	public int getThreadCount() {
		return MBThreadLocalServiceUtil.getThreadsCount(
			getGroupId(), getCategoryId(), WorkflowConstants.STATUS_APPROVED);
	}

	@Override
	public boolean isRoot() {
		if (getParentCategoryId() ==
				MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID) {

			return true;
		}

		return false;
	}

}