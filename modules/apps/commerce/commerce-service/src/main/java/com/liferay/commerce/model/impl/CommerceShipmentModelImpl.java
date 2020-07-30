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

package com.liferay.commerce.model.impl;

import com.liferay.commerce.model.CommerceShipment;
import com.liferay.commerce.model.CommerceShipmentModel;
import com.liferay.commerce.model.CommerceShipmentSoap;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the CommerceShipment service. Represents a row in the &quot;CommerceShipment&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CommerceShipmentModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceShipmentImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommerceShipmentImpl
 * @generated
 */
@JSON(strict = true)
public class CommerceShipmentModelImpl
	extends BaseModelImpl<CommerceShipment> implements CommerceShipmentModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce shipment model instance should use the <code>CommerceShipment</code> interface instead.
	 */
	public static final String TABLE_NAME = "CommerceShipment";

	public static final Object[][] TABLE_COLUMNS = {
		{"commerceShipmentId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}, {"commerceAccountId", Types.BIGINT},
		{"commerceAddressId", Types.BIGINT},
		{"commerceShippingMethodId", Types.BIGINT},
		{"shippingOptionName", Types.CLOB}, {"carrier", Types.VARCHAR},
		{"trackingNumber", Types.VARCHAR}, {"shippingDate", Types.TIMESTAMP},
		{"expectedDate", Types.TIMESTAMP}, {"status", Types.INTEGER}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("commerceShipmentId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("commerceAccountId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("commerceAddressId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("commerceShippingMethodId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("shippingOptionName", Types.CLOB);
		TABLE_COLUMNS_MAP.put("carrier", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("trackingNumber", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("shippingDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("expectedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("status", Types.INTEGER);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CommerceShipment (commerceShipmentId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,commerceAccountId LONG,commerceAddressId LONG,commerceShippingMethodId LONG,shippingOptionName TEXT null,carrier VARCHAR(75) null,trackingNumber VARCHAR(75) null,shippingDate DATE null,expectedDate DATE null,status INTEGER)";

	public static final String TABLE_SQL_DROP = "drop table CommerceShipment";

	public static final String ORDER_BY_JPQL =
		" ORDER BY commerceShipment.createDate DESC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CommerceShipment.createDate DESC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static final boolean ENTITY_CACHE_ENABLED = true;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static final boolean FINDER_CACHE_ENABLED = true;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static final boolean COLUMN_BITMASK_ENABLED = true;

	public static final long COMMERCEADDRESSID_COLUMN_BITMASK = 1L;

	public static final long GROUPID_COLUMN_BITMASK = 2L;

	public static final long STATUS_COLUMN_BITMASK = 4L;

	public static final long CREATEDATE_COLUMN_BITMASK = 8L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static CommerceShipment toModel(CommerceShipmentSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		CommerceShipment model = new CommerceShipmentImpl();

		model.setCommerceShipmentId(soapModel.getCommerceShipmentId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setCommerceAccountId(soapModel.getCommerceAccountId());
		model.setCommerceAddressId(soapModel.getCommerceAddressId());
		model.setCommerceShippingMethodId(
			soapModel.getCommerceShippingMethodId());
		model.setShippingOptionName(soapModel.getShippingOptionName());
		model.setCarrier(soapModel.getCarrier());
		model.setTrackingNumber(soapModel.getTrackingNumber());
		model.setShippingDate(soapModel.getShippingDate());
		model.setExpectedDate(soapModel.getExpectedDate());
		model.setStatus(soapModel.getStatus());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<CommerceShipment> toModels(
		CommerceShipmentSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<CommerceShipment> models = new ArrayList<CommerceShipment>(
			soapModels.length);

		for (CommerceShipmentSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.commerce.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.commerce.model.CommerceShipment"));

	public CommerceShipmentModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _commerceShipmentId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCommerceShipmentId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceShipmentId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceShipment.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceShipment.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CommerceShipment, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CommerceShipment, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceShipment, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((CommerceShipment)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CommerceShipment, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CommerceShipment, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CommerceShipment)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CommerceShipment, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CommerceShipment, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, CommerceShipment>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			CommerceShipment.class.getClassLoader(), CommerceShipment.class,
			ModelWrapper.class);

		try {
			Constructor<CommerceShipment> constructor =
				(Constructor<CommerceShipment>)proxyClass.getConstructor(
					InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException
							reflectiveOperationException) {

					throw new InternalError(reflectiveOperationException);
				}
			};
		}
		catch (NoSuchMethodException noSuchMethodException) {
			throw new InternalError(noSuchMethodException);
		}
	}

	private static final Map<String, Function<CommerceShipment, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<CommerceShipment, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<CommerceShipment, Object>>
			attributeGetterFunctions =
				new LinkedHashMap<String, Function<CommerceShipment, Object>>();
		Map<String, BiConsumer<CommerceShipment, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap<String, BiConsumer<CommerceShipment, ?>>();

		attributeGetterFunctions.put(
			"commerceShipmentId", CommerceShipment::getCommerceShipmentId);
		attributeSetterBiConsumers.put(
			"commerceShipmentId",
			(BiConsumer<CommerceShipment, Long>)
				CommerceShipment::setCommerceShipmentId);
		attributeGetterFunctions.put("groupId", CommerceShipment::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId",
			(BiConsumer<CommerceShipment, Long>)CommerceShipment::setGroupId);
		attributeGetterFunctions.put(
			"companyId", CommerceShipment::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<CommerceShipment, Long>)CommerceShipment::setCompanyId);
		attributeGetterFunctions.put("userId", CommerceShipment::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<CommerceShipment, Long>)CommerceShipment::setUserId);
		attributeGetterFunctions.put("userName", CommerceShipment::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<CommerceShipment, String>)
				CommerceShipment::setUserName);
		attributeGetterFunctions.put(
			"createDate", CommerceShipment::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<CommerceShipment, Date>)
				CommerceShipment::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", CommerceShipment::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<CommerceShipment, Date>)
				CommerceShipment::setModifiedDate);
		attributeGetterFunctions.put(
			"commerceAccountId", CommerceShipment::getCommerceAccountId);
		attributeSetterBiConsumers.put(
			"commerceAccountId",
			(BiConsumer<CommerceShipment, Long>)
				CommerceShipment::setCommerceAccountId);
		attributeGetterFunctions.put(
			"commerceAddressId", CommerceShipment::getCommerceAddressId);
		attributeSetterBiConsumers.put(
			"commerceAddressId",
			(BiConsumer<CommerceShipment, Long>)
				CommerceShipment::setCommerceAddressId);
		attributeGetterFunctions.put(
			"commerceShippingMethodId",
			CommerceShipment::getCommerceShippingMethodId);
		attributeSetterBiConsumers.put(
			"commerceShippingMethodId",
			(BiConsumer<CommerceShipment, Long>)
				CommerceShipment::setCommerceShippingMethodId);
		attributeGetterFunctions.put(
			"shippingOptionName", CommerceShipment::getShippingOptionName);
		attributeSetterBiConsumers.put(
			"shippingOptionName",
			(BiConsumer<CommerceShipment, String>)
				CommerceShipment::setShippingOptionName);
		attributeGetterFunctions.put("carrier", CommerceShipment::getCarrier);
		attributeSetterBiConsumers.put(
			"carrier",
			(BiConsumer<CommerceShipment, String>)CommerceShipment::setCarrier);
		attributeGetterFunctions.put(
			"trackingNumber", CommerceShipment::getTrackingNumber);
		attributeSetterBiConsumers.put(
			"trackingNumber",
			(BiConsumer<CommerceShipment, String>)
				CommerceShipment::setTrackingNumber);
		attributeGetterFunctions.put(
			"shippingDate", CommerceShipment::getShippingDate);
		attributeSetterBiConsumers.put(
			"shippingDate",
			(BiConsumer<CommerceShipment, Date>)
				CommerceShipment::setShippingDate);
		attributeGetterFunctions.put(
			"expectedDate", CommerceShipment::getExpectedDate);
		attributeSetterBiConsumers.put(
			"expectedDate",
			(BiConsumer<CommerceShipment, Date>)
				CommerceShipment::setExpectedDate);
		attributeGetterFunctions.put("status", CommerceShipment::getStatus);
		attributeSetterBiConsumers.put(
			"status",
			(BiConsumer<CommerceShipment, Integer>)CommerceShipment::setStatus);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getCommerceShipmentId() {
		return _commerceShipmentId;
	}

	@Override
	public void setCommerceShipmentId(long commerceShipmentId) {
		_commerceShipmentId = commerceShipmentId;
	}

	@JSON
	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_columnBitmask |= GROUPID_COLUMN_BITMASK;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = _groupId;
		}

		_groupId = groupId;
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException portalException) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_columnBitmask = -1L;

		_createDate = createDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public long getCommerceAccountId() {
		return _commerceAccountId;
	}

	@Override
	public void setCommerceAccountId(long commerceAccountId) {
		_commerceAccountId = commerceAccountId;
	}

	@JSON
	@Override
	public long getCommerceAddressId() {
		return _commerceAddressId;
	}

	@Override
	public void setCommerceAddressId(long commerceAddressId) {
		_columnBitmask |= COMMERCEADDRESSID_COLUMN_BITMASK;

		if (!_setOriginalCommerceAddressId) {
			_setOriginalCommerceAddressId = true;

			_originalCommerceAddressId = _commerceAddressId;
		}

		_commerceAddressId = commerceAddressId;
	}

	public long getOriginalCommerceAddressId() {
		return _originalCommerceAddressId;
	}

	@JSON
	@Override
	public long getCommerceShippingMethodId() {
		return _commerceShippingMethodId;
	}

	@Override
	public void setCommerceShippingMethodId(long commerceShippingMethodId) {
		_commerceShippingMethodId = commerceShippingMethodId;
	}

	@JSON
	@Override
	public String getShippingOptionName() {
		if (_shippingOptionName == null) {
			return "";
		}
		else {
			return _shippingOptionName;
		}
	}

	@Override
	public void setShippingOptionName(String shippingOptionName) {
		_shippingOptionName = shippingOptionName;
	}

	@JSON
	@Override
	public String getCarrier() {
		if (_carrier == null) {
			return "";
		}
		else {
			return _carrier;
		}
	}

	@Override
	public void setCarrier(String carrier) {
		_carrier = carrier;
	}

	@JSON
	@Override
	public String getTrackingNumber() {
		if (_trackingNumber == null) {
			return "";
		}
		else {
			return _trackingNumber;
		}
	}

	@Override
	public void setTrackingNumber(String trackingNumber) {
		_trackingNumber = trackingNumber;
	}

	@JSON
	@Override
	public Date getShippingDate() {
		return _shippingDate;
	}

	@Override
	public void setShippingDate(Date shippingDate) {
		_shippingDate = shippingDate;
	}

	@JSON
	@Override
	public Date getExpectedDate() {
		return _expectedDate;
	}

	@Override
	public void setExpectedDate(Date expectedDate) {
		_expectedDate = expectedDate;
	}

	@JSON
	@Override
	public int getStatus() {
		return _status;
	}

	@Override
	public void setStatus(int status) {
		_columnBitmask |= STATUS_COLUMN_BITMASK;

		if (!_setOriginalStatus) {
			_setOriginalStatus = true;

			_originalStatus = _status;
		}

		_status = status;
	}

	public int getOriginalStatus() {
		return _originalStatus;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), CommerceShipment.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CommerceShipment toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CommerceShipment>
				escapedModelProxyProviderFunction =
					EscapedModelProxyProviderFunctionHolder.
						_escapedModelProxyProviderFunction;

			_escapedModel = escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		CommerceShipmentImpl commerceShipmentImpl = new CommerceShipmentImpl();

		commerceShipmentImpl.setCommerceShipmentId(getCommerceShipmentId());
		commerceShipmentImpl.setGroupId(getGroupId());
		commerceShipmentImpl.setCompanyId(getCompanyId());
		commerceShipmentImpl.setUserId(getUserId());
		commerceShipmentImpl.setUserName(getUserName());
		commerceShipmentImpl.setCreateDate(getCreateDate());
		commerceShipmentImpl.setModifiedDate(getModifiedDate());
		commerceShipmentImpl.setCommerceAccountId(getCommerceAccountId());
		commerceShipmentImpl.setCommerceAddressId(getCommerceAddressId());
		commerceShipmentImpl.setCommerceShippingMethodId(
			getCommerceShippingMethodId());
		commerceShipmentImpl.setShippingOptionName(getShippingOptionName());
		commerceShipmentImpl.setCarrier(getCarrier());
		commerceShipmentImpl.setTrackingNumber(getTrackingNumber());
		commerceShipmentImpl.setShippingDate(getShippingDate());
		commerceShipmentImpl.setExpectedDate(getExpectedDate());
		commerceShipmentImpl.setStatus(getStatus());

		commerceShipmentImpl.resetOriginalValues();

		return commerceShipmentImpl;
	}

	@Override
	public int compareTo(CommerceShipment commerceShipment) {
		int value = 0;

		value = DateUtil.compareTo(
			getCreateDate(), commerceShipment.getCreateDate());

		value = value * -1;

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof CommerceShipment)) {
			return false;
		}

		CommerceShipment commerceShipment = (CommerceShipment)object;

		long primaryKey = commerceShipment.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		CommerceShipmentModelImpl commerceShipmentModelImpl = this;

		commerceShipmentModelImpl._originalGroupId =
			commerceShipmentModelImpl._groupId;

		commerceShipmentModelImpl._setOriginalGroupId = false;

		commerceShipmentModelImpl._setModifiedDate = false;

		commerceShipmentModelImpl._originalCommerceAddressId =
			commerceShipmentModelImpl._commerceAddressId;

		commerceShipmentModelImpl._setOriginalCommerceAddressId = false;

		commerceShipmentModelImpl._originalStatus =
			commerceShipmentModelImpl._status;

		commerceShipmentModelImpl._setOriginalStatus = false;

		commerceShipmentModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<CommerceShipment> toCacheModel() {
		CommerceShipmentCacheModel commerceShipmentCacheModel =
			new CommerceShipmentCacheModel();

		commerceShipmentCacheModel.commerceShipmentId = getCommerceShipmentId();

		commerceShipmentCacheModel.groupId = getGroupId();

		commerceShipmentCacheModel.companyId = getCompanyId();

		commerceShipmentCacheModel.userId = getUserId();

		commerceShipmentCacheModel.userName = getUserName();

		String userName = commerceShipmentCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commerceShipmentCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commerceShipmentCacheModel.createDate = createDate.getTime();
		}
		else {
			commerceShipmentCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commerceShipmentCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			commerceShipmentCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		commerceShipmentCacheModel.commerceAccountId = getCommerceAccountId();

		commerceShipmentCacheModel.commerceAddressId = getCommerceAddressId();

		commerceShipmentCacheModel.commerceShippingMethodId =
			getCommerceShippingMethodId();

		commerceShipmentCacheModel.shippingOptionName = getShippingOptionName();

		String shippingOptionName =
			commerceShipmentCacheModel.shippingOptionName;

		if ((shippingOptionName != null) &&
			(shippingOptionName.length() == 0)) {

			commerceShipmentCacheModel.shippingOptionName = null;
		}

		commerceShipmentCacheModel.carrier = getCarrier();

		String carrier = commerceShipmentCacheModel.carrier;

		if ((carrier != null) && (carrier.length() == 0)) {
			commerceShipmentCacheModel.carrier = null;
		}

		commerceShipmentCacheModel.trackingNumber = getTrackingNumber();

		String trackingNumber = commerceShipmentCacheModel.trackingNumber;

		if ((trackingNumber != null) && (trackingNumber.length() == 0)) {
			commerceShipmentCacheModel.trackingNumber = null;
		}

		Date shippingDate = getShippingDate();

		if (shippingDate != null) {
			commerceShipmentCacheModel.shippingDate = shippingDate.getTime();
		}
		else {
			commerceShipmentCacheModel.shippingDate = Long.MIN_VALUE;
		}

		Date expectedDate = getExpectedDate();

		if (expectedDate != null) {
			commerceShipmentCacheModel.expectedDate = expectedDate.getTime();
		}
		else {
			commerceShipmentCacheModel.expectedDate = Long.MIN_VALUE;
		}

		commerceShipmentCacheModel.status = getStatus();

		return commerceShipmentCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CommerceShipment, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CommerceShipment, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceShipment, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((CommerceShipment)this));
			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<CommerceShipment, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<CommerceShipment, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceShipment, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((CommerceShipment)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, CommerceShipment>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _commerceShipmentId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _commerceAccountId;
	private long _commerceAddressId;
	private long _originalCommerceAddressId;
	private boolean _setOriginalCommerceAddressId;
	private long _commerceShippingMethodId;
	private String _shippingOptionName;
	private String _carrier;
	private String _trackingNumber;
	private Date _shippingDate;
	private Date _expectedDate;
	private int _status;
	private int _originalStatus;
	private boolean _setOriginalStatus;
	private long _columnBitmask;
	private CommerceShipment _escapedModel;

}