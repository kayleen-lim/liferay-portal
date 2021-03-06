/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.portal.reports.engine.console.internal.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.SubscriptionSender;

/**
 * @author Brian I. Kim
 */
public class ReportsEngineConsoleSubscriptionSender extends SubscriptionSender {

	@Override
	protected void sendNotification(User user) throws Exception {
		sendEmailNotification(user);

		if (currentUserId == user.getUserId()) {
			if (_log.isDebugEnabled()) {
				_log.debug("Skip notification for user " + currentUserId);
			}

			return;
		}

		sendUserNotification(user);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ReportsEngineConsoleSubscriptionSender.class);

}