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

import com.liferay.commerce.model.CPDefinitionInventory;
import com.liferay.commerce.model.CPDefinitionInventoryModel;
import com.liferay.commerce.model.CPDefinitionInventorySoap;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
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
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
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
 * The base model implementation for the CPDefinitionInventory service. Represents a row in the &quot;CPDefinitionInventory&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CPDefinitionInventoryModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CPDefinitionInventoryImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CPDefinitionInventoryImpl
 * @generated
 */
@JSON(strict = true)
public class CPDefinitionInventoryModelImpl
	extends BaseModelImpl<CPDefinitionInventory>
	implements CPDefinitionInventoryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a cp definition inventory model instance should use the <code>CPDefinitionInventory</code> interface instead.
	 */
	public static final String TABLE_NAME = "CPDefinitionInventory";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"CPDefinitionInventoryId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"CPDefinitionId", Types.BIGINT},
		{"CPDefinitionInventoryEngine", Types.VARCHAR},
		{"lowStockActivity", Types.VARCHAR},
		{"displayAvailability", Types.BOOLEAN},
		{"displayStockQuantity", Types.BOOLEAN},
		{"minStockQuantity", Types.INTEGER}, {"backOrders", Types.BOOLEAN},
		{"minOrderQuantity", Types.INTEGER},
		{"maxOrderQuantity", Types.INTEGER},
		{"allowedOrderQuantities", Types.VARCHAR},
		{"multipleOrderQuantity", Types.INTEGER}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("CPDefinitionInventoryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("CPDefinitionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("CPDefinitionInventoryEngine", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("lowStockActivity", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("displayAvailability", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("displayStockQuantity", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("minStockQuantity", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("backOrders", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("minOrderQuantity", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("maxOrderQuantity", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("allowedOrderQuantities", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("multipleOrderQuantity", Types.INTEGER);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CPDefinitionInventory (uuid_ VARCHAR(75) null,CPDefinitionInventoryId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,CPDefinitionId LONG,CPDefinitionInventoryEngine VARCHAR(75) null,lowStockActivity VARCHAR(75) null,displayAvailability BOOLEAN,displayStockQuantity BOOLEAN,minStockQuantity INTEGER,backOrders BOOLEAN,minOrderQuantity INTEGER,maxOrderQuantity INTEGER,allowedOrderQuantities VARCHAR(75) null,multipleOrderQuantity INTEGER)";

	public static final String TABLE_SQL_DROP =
		"drop table CPDefinitionInventory";

	public static final String ORDER_BY_JPQL =
		" ORDER BY cpDefinitionInventory.CPDefinitionInventoryId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CPDefinitionInventory.CPDefinitionInventoryId ASC";

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

	public static final long CPDEFINITIONID_COLUMN_BITMASK = 1L;

	public static final long COMPANYID_COLUMN_BITMASK = 2L;

	public static final long GROUPID_COLUMN_BITMASK = 4L;

	public static final long UUID_COLUMN_BITMASK = 8L;

	public static final long CPDEFINITIONINVENTORYID_COLUMN_BITMASK = 16L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static CPDefinitionInventory toModel(
		CPDefinitionInventorySoap soapModel) {

		if (soapModel == null) {
			return null;
		}

		CPDefinitionInventory model = new CPDefinitionInventoryImpl();

		model.setUuid(soapModel.getUuid());
		model.setCPDefinitionInventoryId(
			soapModel.getCPDefinitionInventoryId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setCPDefinitionId(soapModel.getCPDefinitionId());
		model.setCPDefinitionInventoryEngine(
			soapModel.getCPDefinitionInventoryEngine());
		model.setLowStockActivity(soapModel.getLowStockActivity());
		model.setDisplayAvailability(soapModel.isDisplayAvailability());
		model.setDisplayStockQuantity(soapModel.isDisplayStockQuantity());
		model.setMinStockQuantity(soapModel.getMinStockQuantity());
		model.setBackOrders(soapModel.isBackOrders());
		model.setMinOrderQuantity(soapModel.getMinOrderQuantity());
		model.setMaxOrderQuantity(soapModel.getMaxOrderQuantity());
		model.setAllowedOrderQuantities(soapModel.getAllowedOrderQuantities());
		model.setMultipleOrderQuantity(soapModel.getMultipleOrderQuantity());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<CPDefinitionInventory> toModels(
		CPDefinitionInventorySoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<CPDefinitionInventory> models =
			new ArrayList<CPDefinitionInventory>(soapModels.length);

		for (CPDefinitionInventorySoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.commerce.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.commerce.model.CPDefinitionInventory"));

	public CPDefinitionInventoryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _CPDefinitionInventoryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCPDefinitionInventoryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _CPDefinitionInventoryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CPDefinitionInventory.class;
	}

	@Override
	public String getModelClassName() {
		return CPDefinitionInventory.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CPDefinitionInventory, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CPDefinitionInventory, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CPDefinitionInventory, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((CPDefinitionInventory)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CPDefinitionInventory, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CPDefinitionInventory, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CPDefinitionInventory)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CPDefinitionInventory, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CPDefinitionInventory, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, CPDefinitionInventory>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			CPDefinitionInventory.class.getClassLoader(),
			CPDefinitionInventory.class, ModelWrapper.class);

		try {
			Constructor<CPDefinitionInventory> constructor =
				(Constructor<CPDefinitionInventory>)proxyClass.getConstructor(
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

	private static final Map<String, Function<CPDefinitionInventory, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<CPDefinitionInventory, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<CPDefinitionInventory, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<CPDefinitionInventory, Object>>();
		Map<String, BiConsumer<CPDefinitionInventory, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<CPDefinitionInventory, ?>>();

		attributeGetterFunctions.put("uuid", CPDefinitionInventory::getUuid);
		attributeSetterBiConsumers.put(
			"uuid",
			(BiConsumer<CPDefinitionInventory, String>)
				CPDefinitionInventory::setUuid);
		attributeGetterFunctions.put(
			"CPDefinitionInventoryId",
			CPDefinitionInventory::getCPDefinitionInventoryId);
		attributeSetterBiConsumers.put(
			"CPDefinitionInventoryId",
			(BiConsumer<CPDefinitionInventory, Long>)
				CPDefinitionInventory::setCPDefinitionInventoryId);
		attributeGetterFunctions.put(
			"groupId", CPDefinitionInventory::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId",
			(BiConsumer<CPDefinitionInventory, Long>)
				CPDefinitionInventory::setGroupId);
		attributeGetterFunctions.put(
			"companyId", CPDefinitionInventory::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<CPDefinitionInventory, Long>)
				CPDefinitionInventory::setCompanyId);
		attributeGetterFunctions.put(
			"userId", CPDefinitionInventory::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<CPDefinitionInventory, Long>)
				CPDefinitionInventory::setUserId);
		attributeGetterFunctions.put(
			"userName", CPDefinitionInventory::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<CPDefinitionInventory, String>)
				CPDefinitionInventory::setUserName);
		attributeGetterFunctions.put(
			"createDate", CPDefinitionInventory::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<CPDefinitionInventory, Date>)
				CPDefinitionInventory::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", CPDefinitionInventory::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<CPDefinitionInventory, Date>)
				CPDefinitionInventory::setModifiedDate);
		attributeGetterFunctions.put(
			"CPDefinitionId", CPDefinitionInventory::getCPDefinitionId);
		attributeSetterBiConsumers.put(
			"CPDefinitionId",
			(BiConsumer<CPDefinitionInventory, Long>)
				CPDefinitionInventory::setCPDefinitionId);
		attributeGetterFunctions.put(
			"CPDefinitionInventoryEngine",
			CPDefinitionInventory::getCPDefinitionInventoryEngine);
		attributeSetterBiConsumers.put(
			"CPDefinitionInventoryEngine",
			(BiConsumer<CPDefinitionInventory, String>)
				CPDefinitionInventory::setCPDefinitionInventoryEngine);
		attributeGetterFunctions.put(
			"lowStockActivity", CPDefinitionInventory::getLowStockActivity);
		attributeSetterBiConsumers.put(
			"lowStockActivity",
			(BiConsumer<CPDefinitionInventory, String>)
				CPDefinitionInventory::setLowStockActivity);
		attributeGetterFunctions.put(
			"displayAvailability",
			CPDefinitionInventory::getDisplayAvailability);
		attributeSetterBiConsumers.put(
			"displayAvailability",
			(BiConsumer<CPDefinitionInventory, Boolean>)
				CPDefinitionInventory::setDisplayAvailability);
		attributeGetterFunctions.put(
			"displayStockQuantity",
			CPDefinitionInventory::getDisplayStockQuantity);
		attributeSetterBiConsumers.put(
			"displayStockQuantity",
			(BiConsumer<CPDefinitionInventory, Boolean>)
				CPDefinitionInventory::setDisplayStockQuantity);
		attributeGetterFunctions.put(
			"minStockQuantity", CPDefinitionInventory::getMinStockQuantity);
		attributeSetterBiConsumers.put(
			"minStockQuantity",
			(BiConsumer<CPDefinitionInventory, Integer>)
				CPDefinitionInventory::setMinStockQuantity);
		attributeGetterFunctions.put(
			"backOrders", CPDefinitionInventory::getBackOrders);
		attributeSetterBiConsumers.put(
			"backOrders",
			(BiConsumer<CPDefinitionInventory, Boolean>)
				CPDefinitionInventory::setBackOrders);
		attributeGetterFunctions.put(
			"minOrderQuantity", CPDefinitionInventory::getMinOrderQuantity);
		attributeSetterBiConsumers.put(
			"minOrderQuantity",
			(BiConsumer<CPDefinitionInventory, Integer>)
				CPDefinitionInventory::setMinOrderQuantity);
		attributeGetterFunctions.put(
			"maxOrderQuantity", CPDefinitionInventory::getMaxOrderQuantity);
		attributeSetterBiConsumers.put(
			"maxOrderQuantity",
			(BiConsumer<CPDefinitionInventory, Integer>)
				CPDefinitionInventory::setMaxOrderQuantity);
		attributeGetterFunctions.put(
			"allowedOrderQuantities",
			CPDefinitionInventory::getAllowedOrderQuantities);
		attributeSetterBiConsumers.put(
			"allowedOrderQuantities",
			(BiConsumer<CPDefinitionInventory, String>)
				CPDefinitionInventory::setAllowedOrderQuantities);
		attributeGetterFunctions.put(
			"multipleOrderQuantity",
			CPDefinitionInventory::getMultipleOrderQuantity);
		attributeSetterBiConsumers.put(
			"multipleOrderQuantity",
			(BiConsumer<CPDefinitionInventory, Integer>)
				CPDefinitionInventory::setMultipleOrderQuantity);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public String getUuid() {
		if (_uuid == null) {
			return "";
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		_columnBitmask |= UUID_COLUMN_BITMASK;

		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@JSON
	@Override
	public long getCPDefinitionInventoryId() {
		return _CPDefinitionInventoryId;
	}

	@Override
	public void setCPDefinitionInventoryId(long CPDefinitionInventoryId) {
		_CPDefinitionInventoryId = CPDefinitionInventoryId;
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
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
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
	public long getCPDefinitionId() {
		return _CPDefinitionId;
	}

	@Override
	public void setCPDefinitionId(long CPDefinitionId) {
		_columnBitmask |= CPDEFINITIONID_COLUMN_BITMASK;

		if (!_setOriginalCPDefinitionId) {
			_setOriginalCPDefinitionId = true;

			_originalCPDefinitionId = _CPDefinitionId;
		}

		_CPDefinitionId = CPDefinitionId;
	}

	public long getOriginalCPDefinitionId() {
		return _originalCPDefinitionId;
	}

	@JSON
	@Override
	public String getCPDefinitionInventoryEngine() {
		if (_CPDefinitionInventoryEngine == null) {
			return "";
		}
		else {
			return _CPDefinitionInventoryEngine;
		}
	}

	@Override
	public void setCPDefinitionInventoryEngine(
		String CPDefinitionInventoryEngine) {

		_CPDefinitionInventoryEngine = CPDefinitionInventoryEngine;
	}

	@JSON
	@Override
	public String getLowStockActivity() {
		if (_lowStockActivity == null) {
			return "";
		}
		else {
			return _lowStockActivity;
		}
	}

	@Override
	public void setLowStockActivity(String lowStockActivity) {
		_lowStockActivity = lowStockActivity;
	}

	@JSON
	@Override
	public boolean getDisplayAvailability() {
		return _displayAvailability;
	}

	@JSON
	@Override
	public boolean isDisplayAvailability() {
		return _displayAvailability;
	}

	@Override
	public void setDisplayAvailability(boolean displayAvailability) {
		_displayAvailability = displayAvailability;
	}

	@JSON
	@Override
	public boolean getDisplayStockQuantity() {
		return _displayStockQuantity;
	}

	@JSON
	@Override
	public boolean isDisplayStockQuantity() {
		return _displayStockQuantity;
	}

	@Override
	public void setDisplayStockQuantity(boolean displayStockQuantity) {
		_displayStockQuantity = displayStockQuantity;
	}

	@JSON
	@Override
	public int getMinStockQuantity() {
		return _minStockQuantity;
	}

	@Override
	public void setMinStockQuantity(int minStockQuantity) {
		_minStockQuantity = minStockQuantity;
	}

	@JSON
	@Override
	public boolean getBackOrders() {
		return _backOrders;
	}

	@JSON
	@Override
	public boolean isBackOrders() {
		return _backOrders;
	}

	@Override
	public void setBackOrders(boolean backOrders) {
		_backOrders = backOrders;
	}

	@JSON
	@Override
	public int getMinOrderQuantity() {
		return _minOrderQuantity;
	}

	@Override
	public void setMinOrderQuantity(int minOrderQuantity) {
		_minOrderQuantity = minOrderQuantity;
	}

	@JSON
	@Override
	public int getMaxOrderQuantity() {
		return _maxOrderQuantity;
	}

	@Override
	public void setMaxOrderQuantity(int maxOrderQuantity) {
		_maxOrderQuantity = maxOrderQuantity;
	}

	@JSON
	@Override
	public String getAllowedOrderQuantities() {
		if (_allowedOrderQuantities == null) {
			return "";
		}
		else {
			return _allowedOrderQuantities;
		}
	}

	@Override
	public void setAllowedOrderQuantities(String allowedOrderQuantities) {
		_allowedOrderQuantities = allowedOrderQuantities;
	}

	@JSON
	@Override
	public int getMultipleOrderQuantity() {
		return _multipleOrderQuantity;
	}

	@Override
	public void setMultipleOrderQuantity(int multipleOrderQuantity) {
		_multipleOrderQuantity = multipleOrderQuantity;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(CPDefinitionInventory.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), CPDefinitionInventory.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CPDefinitionInventory toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CPDefinitionInventory>
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
		CPDefinitionInventoryImpl cpDefinitionInventoryImpl =
			new CPDefinitionInventoryImpl();

		cpDefinitionInventoryImpl.setUuid(getUuid());
		cpDefinitionInventoryImpl.setCPDefinitionInventoryId(
			getCPDefinitionInventoryId());
		cpDefinitionInventoryImpl.setGroupId(getGroupId());
		cpDefinitionInventoryImpl.setCompanyId(getCompanyId());
		cpDefinitionInventoryImpl.setUserId(getUserId());
		cpDefinitionInventoryImpl.setUserName(getUserName());
		cpDefinitionInventoryImpl.setCreateDate(getCreateDate());
		cpDefinitionInventoryImpl.setModifiedDate(getModifiedDate());
		cpDefinitionInventoryImpl.setCPDefinitionId(getCPDefinitionId());
		cpDefinitionInventoryImpl.setCPDefinitionInventoryEngine(
			getCPDefinitionInventoryEngine());
		cpDefinitionInventoryImpl.setLowStockActivity(getLowStockActivity());
		cpDefinitionInventoryImpl.setDisplayAvailability(
			isDisplayAvailability());
		cpDefinitionInventoryImpl.setDisplayStockQuantity(
			isDisplayStockQuantity());
		cpDefinitionInventoryImpl.setMinStockQuantity(getMinStockQuantity());
		cpDefinitionInventoryImpl.setBackOrders(isBackOrders());
		cpDefinitionInventoryImpl.setMinOrderQuantity(getMinOrderQuantity());
		cpDefinitionInventoryImpl.setMaxOrderQuantity(getMaxOrderQuantity());
		cpDefinitionInventoryImpl.setAllowedOrderQuantities(
			getAllowedOrderQuantities());
		cpDefinitionInventoryImpl.setMultipleOrderQuantity(
			getMultipleOrderQuantity());

		cpDefinitionInventoryImpl.resetOriginalValues();

		return cpDefinitionInventoryImpl;
	}

	@Override
	public int compareTo(CPDefinitionInventory cpDefinitionInventory) {
		long primaryKey = cpDefinitionInventory.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof CPDefinitionInventory)) {
			return false;
		}

		CPDefinitionInventory cpDefinitionInventory =
			(CPDefinitionInventory)object;

		long primaryKey = cpDefinitionInventory.getPrimaryKey();

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
		CPDefinitionInventoryModelImpl cpDefinitionInventoryModelImpl = this;

		cpDefinitionInventoryModelImpl._originalUuid =
			cpDefinitionInventoryModelImpl._uuid;

		cpDefinitionInventoryModelImpl._originalGroupId =
			cpDefinitionInventoryModelImpl._groupId;

		cpDefinitionInventoryModelImpl._setOriginalGroupId = false;

		cpDefinitionInventoryModelImpl._originalCompanyId =
			cpDefinitionInventoryModelImpl._companyId;

		cpDefinitionInventoryModelImpl._setOriginalCompanyId = false;

		cpDefinitionInventoryModelImpl._setModifiedDate = false;

		cpDefinitionInventoryModelImpl._originalCPDefinitionId =
			cpDefinitionInventoryModelImpl._CPDefinitionId;

		cpDefinitionInventoryModelImpl._setOriginalCPDefinitionId = false;

		cpDefinitionInventoryModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<CPDefinitionInventory> toCacheModel() {
		CPDefinitionInventoryCacheModel cpDefinitionInventoryCacheModel =
			new CPDefinitionInventoryCacheModel();

		cpDefinitionInventoryCacheModel.uuid = getUuid();

		String uuid = cpDefinitionInventoryCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			cpDefinitionInventoryCacheModel.uuid = null;
		}

		cpDefinitionInventoryCacheModel.CPDefinitionInventoryId =
			getCPDefinitionInventoryId();

		cpDefinitionInventoryCacheModel.groupId = getGroupId();

		cpDefinitionInventoryCacheModel.companyId = getCompanyId();

		cpDefinitionInventoryCacheModel.userId = getUserId();

		cpDefinitionInventoryCacheModel.userName = getUserName();

		String userName = cpDefinitionInventoryCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			cpDefinitionInventoryCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			cpDefinitionInventoryCacheModel.createDate = createDate.getTime();
		}
		else {
			cpDefinitionInventoryCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			cpDefinitionInventoryCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			cpDefinitionInventoryCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		cpDefinitionInventoryCacheModel.CPDefinitionId = getCPDefinitionId();

		cpDefinitionInventoryCacheModel.CPDefinitionInventoryEngine =
			getCPDefinitionInventoryEngine();

		String CPDefinitionInventoryEngine =
			cpDefinitionInventoryCacheModel.CPDefinitionInventoryEngine;

		if ((CPDefinitionInventoryEngine != null) &&
			(CPDefinitionInventoryEngine.length() == 0)) {

			cpDefinitionInventoryCacheModel.CPDefinitionInventoryEngine = null;
		}

		cpDefinitionInventoryCacheModel.lowStockActivity =
			getLowStockActivity();

		String lowStockActivity =
			cpDefinitionInventoryCacheModel.lowStockActivity;

		if ((lowStockActivity != null) && (lowStockActivity.length() == 0)) {
			cpDefinitionInventoryCacheModel.lowStockActivity = null;
		}

		cpDefinitionInventoryCacheModel.displayAvailability =
			isDisplayAvailability();

		cpDefinitionInventoryCacheModel.displayStockQuantity =
			isDisplayStockQuantity();

		cpDefinitionInventoryCacheModel.minStockQuantity =
			getMinStockQuantity();

		cpDefinitionInventoryCacheModel.backOrders = isBackOrders();

		cpDefinitionInventoryCacheModel.minOrderQuantity =
			getMinOrderQuantity();

		cpDefinitionInventoryCacheModel.maxOrderQuantity =
			getMaxOrderQuantity();

		cpDefinitionInventoryCacheModel.allowedOrderQuantities =
			getAllowedOrderQuantities();

		String allowedOrderQuantities =
			cpDefinitionInventoryCacheModel.allowedOrderQuantities;

		if ((allowedOrderQuantities != null) &&
			(allowedOrderQuantities.length() == 0)) {

			cpDefinitionInventoryCacheModel.allowedOrderQuantities = null;
		}

		cpDefinitionInventoryCacheModel.multipleOrderQuantity =
			getMultipleOrderQuantity();

		return cpDefinitionInventoryCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CPDefinitionInventory, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CPDefinitionInventory, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CPDefinitionInventory, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(
				attributeGetterFunction.apply((CPDefinitionInventory)this));
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
		Map<String, Function<CPDefinitionInventory, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<CPDefinitionInventory, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CPDefinitionInventory, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply((CPDefinitionInventory)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, CPDefinitionInventory>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private String _uuid;
	private String _originalUuid;
	private long _CPDefinitionInventoryId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _CPDefinitionId;
	private long _originalCPDefinitionId;
	private boolean _setOriginalCPDefinitionId;
	private String _CPDefinitionInventoryEngine;
	private String _lowStockActivity;
	private boolean _displayAvailability;
	private boolean _displayStockQuantity;
	private int _minStockQuantity;
	private boolean _backOrders;
	private int _minOrderQuantity;
	private int _maxOrderQuantity;
	private String _allowedOrderQuantities;
	private int _multipleOrderQuantity;
	private long _columnBitmask;
	private CPDefinitionInventory _escapedModel;

}