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

package com.liferay.portal.tools.bundle.support.ant;

import com.liferay.portal.tools.bundle.support.commands.CleanCommand;

import org.apache.tools.ant.BuildException;

/**
 * @author David Truong
 */
public class CleanTask extends CleanCommand {

	@Override
	public void execute() {
		try {
			super.execute();
		}
		catch (Exception exception) {
			throw new BuildException(exception);
		}
	}

}