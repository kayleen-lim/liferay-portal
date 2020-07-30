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

package com.liferay.commerce.service.persistence.impl;

import com.liferay.commerce.model.CommerceShipment;
import com.liferay.commerce.model.impl.CommerceShipmentImpl;
import com.liferay.commerce.service.persistence.CommerceShipmentFinder;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.Iterator;
import java.util.List;

/**
 * @author Alec Sloan
 */
public class CommerceShipmentFinderImpl
	extends CommerceShipmentFinderBaseImpl implements CommerceShipmentFinder {

	public static final String COUNT_BY_COMMERCE_ORDER_ID =
		CommerceShipmentFinder.class.getName() + ".countByCommerceOrderId";

	public static final String FIND_BY_COMMERCE_ORDER_ID =
		CommerceShipmentFinder.class.getName() + ".findByCommerceOrderId";

	public static final String
		FIND_COMMERCE_SHIPMENT_STATUSES_BY_COMMERCE_ORDER_ID =
			CommerceShipmentFinder.class.getName() +
				".findCommerceShipmentStatusesByCommerceOrderId";

	@Override
	public int countByCommerceOrderId(long commerceOrderId) {
		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(getClass(), COUNT_BY_COMMERCE_ORDER_ID);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(commerceOrderId);

			Iterator<Long> itr = q.iterate();

			if (itr.hasNext()) {
				Long count = itr.next();

				if (count != null) {
					return count.intValue();
				}
			}

			return 0;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	public List<CommerceShipment> findByCommerceOrderId(
		long commerceOrderId, int start, int end) {

		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(getClass(), FIND_BY_COMMERCE_ORDER_ID);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("CommerceShipment", CommerceShipmentImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(commerceOrderId);

			return (List<CommerceShipment>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	public int[] findCommerceShipmentStatusesByCommerceOrderId(
		long commerceOrderId) {

		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(
				getClass(),
				FIND_COMMERCE_SHIPMENT_STATUSES_BY_COMMERCE_ORDER_ID);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(commerceOrderId);

			List<Integer> commerceShipmentStatuses =
				(List<Integer>)QueryUtil.list(
					q, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			return ArrayUtil.toIntArray(commerceShipmentStatuses);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@ServiceReference(type = CustomSQL.class)
	private CustomSQL _customSQL;

}