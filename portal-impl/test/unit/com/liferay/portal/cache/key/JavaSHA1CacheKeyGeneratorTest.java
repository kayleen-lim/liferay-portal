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

package com.liferay.portal.cache.key;

import com.liferay.portal.kernel.util.Digester;

import org.junit.Before;

/**
 * @author Vilmos Papp
 */
public class JavaSHA1CacheKeyGeneratorTest
	extends BaseCacheKeyGeneratorTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		cacheKeyGenerator = new MessageDigestCacheKeyGenerator(Digester.SHA_1);
	}

}