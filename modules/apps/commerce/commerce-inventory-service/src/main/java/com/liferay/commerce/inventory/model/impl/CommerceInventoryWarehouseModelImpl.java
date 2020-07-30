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

package com.liferay.commerce.inventory.model.impl;

import com.liferay.commerce.inventory.model.CommerceInventoryWarehouse;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouseModel;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouseSoap;
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
 * The base model implementation for the CommerceInventoryWarehouse service. Represents a row in the &quot;CIWarehouse&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CommerceInventoryWarehouseModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceInventoryWarehouseImpl}.
 * </p>
 *
 * @author Luca Pellizzon
 * @see CommerceInventoryWarehouseImpl
 * @generated
 */
@JSON(strict = true)
public class CommerceInventoryWarehouseModelImpl
	extends BaseModelImpl<CommerceInventoryWarehouse>
	implements CommerceInventoryWarehouseModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce inventory warehouse model instance should use the <code>CommerceInventoryWarehouse</code> interface instead.
	 */
	public static final String TABLE_NAME = "CIWarehouse";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"externalReferenceCode", Types.VARCHAR},
		{"CIWarehouseId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"name", Types.VARCHAR}, {"description", Types.VARCHAR},
		{"active_", Types.BOOLEAN}, {"street1", Types.VARCHAR},
		{"street2", Types.VARCHAR}, {"street3", Types.VARCHAR},
		{"city", Types.VARCHAR}, {"zip", Types.VARCHAR},
		{"commerceRegionCode", Types.VARCHAR},
		{"countryTwoLettersISOCode", Types.VARCHAR}, {"latitude", Types.DOUBLE},
		{"longitude", Types.DOUBLE}, {"type_", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("externalReferenceCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("CIWarehouseId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("active_", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("street1", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("street2", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("street3", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("city", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("zip", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("commerceRegionCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("countryTwoLettersISOCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("latitude", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("longitude", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("type_", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CIWarehouse (mvccVersion LONG default 0 not null,externalReferenceCode VARCHAR(75) null,CIWarehouseId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,name VARCHAR(75) null,description VARCHAR(75) null,active_ BOOLEAN,street1 VARCHAR(75) null,street2 VARCHAR(75) null,street3 VARCHAR(75) null,city VARCHAR(75) null,zip VARCHAR(75) null,commerceRegionCode VARCHAR(75) null,countryTwoLettersISOCode VARCHAR(75) null,latitude DOUBLE,longitude DOUBLE,type_ VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP = "drop table CIWarehouse";

	public static final String ORDER_BY_JPQL =
		" ORDER BY commerceInventoryWarehouse.name ASC";

	public static final String ORDER_BY_SQL = " ORDER BY CIWarehouse.name ASC";

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

	public static final long ACTIVE_COLUMN_BITMASK = 1L;

	public static final long COMPANYID_COLUMN_BITMASK = 2L;

	public static final long COUNTRYTWOLETTERSISOCODE_COLUMN_BITMASK = 4L;

	public static final long EXTERNALREFERENCECODE_COLUMN_BITMASK = 8L;

	public static final long NAME_COLUMN_BITMASK = 16L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static CommerceInventoryWarehouse toModel(
		CommerceInventoryWarehouseSoap soapModel) {

		if (soapModel == null) {
			return null;
		}

		CommerceInventoryWarehouse model = new CommerceInventoryWarehouseImpl();

		model.setMvccVersion(soapModel.getMvccVersion());
		model.setExternalReferenceCode(soapModel.getExternalReferenceCode());
		model.setCommerceInventoryWarehouseId(
			soapModel.getCommerceInventoryWarehouseId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setName(soapModel.getName());
		model.setDescription(soapModel.getDescription());
		model.setActive(soapModel.isActive());
		model.setStreet1(soapModel.getStreet1());
		model.setStreet2(soapModel.getStreet2());
		model.setStreet3(soapModel.getStreet3());
		model.setCity(soapModel.getCity());
		model.setZip(soapModel.getZip());
		model.setCommerceRegionCode(soapModel.getCommerceRegionCode());
		model.setCountryTwoLettersISOCode(
			soapModel.getCountryTwoLettersISOCode());
		model.setLatitude(soapModel.getLatitude());
		model.setLongitude(soapModel.getLongitude());
		model.setType(soapModel.getType());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<CommerceInventoryWarehouse> toModels(
		CommerceInventoryWarehouseSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<CommerceInventoryWarehouse> models =
			new ArrayList<CommerceInventoryWarehouse>(soapModels.length);

		for (CommerceInventoryWarehouseSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.commerce.inventory.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.commerce.inventory.model.CommerceInventoryWarehouse"));

	public CommerceInventoryWarehouseModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _commerceInventoryWarehouseId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCommerceInventoryWarehouseId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceInventoryWarehouseId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceInventoryWarehouse.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceInventoryWarehouse.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CommerceInventoryWarehouse, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CommerceInventoryWarehouse, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceInventoryWarehouse, Object>
				attributeGetterFunction = entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply(
					(CommerceInventoryWarehouse)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CommerceInventoryWarehouse, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CommerceInventoryWarehouse, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CommerceInventoryWarehouse)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CommerceInventoryWarehouse, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CommerceInventoryWarehouse, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, CommerceInventoryWarehouse>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			CommerceInventoryWarehouse.class.getClassLoader(),
			CommerceInventoryWarehouse.class, ModelWrapper.class);

		try {
			Constructor<CommerceInventoryWarehouse> constructor =
				(Constructor<CommerceInventoryWarehouse>)
					proxyClass.getConstructor(InvocationHandler.class);

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

	private static final Map
		<String, Function<CommerceInventoryWarehouse, Object>>
			_attributeGetterFunctions;
	private static final Map
		<String, BiConsumer<CommerceInventoryWarehouse, Object>>
			_attributeSetterBiConsumers;

	static {
		Map<String, Function<CommerceInventoryWarehouse, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<CommerceInventoryWarehouse, Object>>();
		Map<String, BiConsumer<CommerceInventoryWarehouse, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<CommerceInventoryWarehouse, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", CommerceInventoryWarehouse::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<CommerceInventoryWarehouse, Long>)
				CommerceInventoryWarehouse::setMvccVersion);
		attributeGetterFunctions.put(
			"externalReferenceCode",
			CommerceInventoryWarehouse::getExternalReferenceCode);
		attributeSetterBiConsumers.put(
			"externalReferenceCode",
			(BiConsumer<CommerceInventoryWarehouse, String>)
				CommerceInventoryWarehouse::setExternalReferenceCode);
		attributeGetterFunctions.put(
			"commerceInventoryWarehouseId",
			CommerceInventoryWarehouse::getCommerceInventoryWarehouseId);
		attributeSetterBiConsumers.put(
			"commerceInventoryWarehouseId",
			(BiConsumer<CommerceInventoryWarehouse, Long>)
				CommerceInventoryWarehouse::setCommerceInventoryWarehouseId);
		attributeGetterFunctions.put(
			"companyId", CommerceInventoryWarehouse::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<CommerceInventoryWarehouse, Long>)
				CommerceInventoryWarehouse::setCompanyId);
		attributeGetterFunctions.put(
			"userId", CommerceInventoryWarehouse::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<CommerceInventoryWarehouse, Long>)
				CommerceInventoryWarehouse::setUserId);
		attributeGetterFunctions.put(
			"userName", CommerceInventoryWarehouse::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<CommerceInventoryWarehouse, String>)
				CommerceInventoryWarehouse::setUserName);
		attributeGetterFunctions.put(
			"createDate", CommerceInventoryWarehouse::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<CommerceInventoryWarehouse, Date>)
				CommerceInventoryWarehouse::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", CommerceInventoryWarehouse::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<CommerceInventoryWarehouse, Date>)
				CommerceInventoryWarehouse::setModifiedDate);
		attributeGetterFunctions.put(
			"name", CommerceInventoryWarehouse::getName);
		attributeSetterBiConsumers.put(
			"name",
			(BiConsumer<CommerceInventoryWarehouse, String>)
				CommerceInventoryWarehouse::setName);
		attributeGetterFunctions.put(
			"description", CommerceInventoryWarehouse::getDescription);
		attributeSetterBiConsumers.put(
			"description",
			(BiConsumer<CommerceInventoryWarehouse, String>)
				CommerceInventoryWarehouse::setDescription);
		attributeGetterFunctions.put(
			"active", CommerceInventoryWarehouse::getActive);
		attributeSetterBiConsumers.put(
			"active",
			(BiConsumer<CommerceInventoryWarehouse, Boolean>)
				CommerceInventoryWarehouse::setActive);
		attributeGetterFunctions.put(
			"street1", CommerceInventoryWarehouse::getStreet1);
		attributeSetterBiConsumers.put(
			"street1",
			(BiConsumer<CommerceInventoryWarehouse, String>)
				CommerceInventoryWarehouse::setStreet1);
		attributeGetterFunctions.put(
			"street2", CommerceInventoryWarehouse::getStreet2);
		attributeSetterBiConsumers.put(
			"street2",
			(BiConsumer<CommerceInventoryWarehouse, String>)
				CommerceInventoryWarehouse::setStreet2);
		attributeGetterFunctions.put(
			"street3", CommerceInventoryWarehouse::getStreet3);
		attributeSetterBiConsumers.put(
			"street3",
			(BiConsumer<CommerceInventoryWarehouse, String>)
				CommerceInventoryWarehouse::setStreet3);
		attributeGetterFunctions.put(
			"city", CommerceInventoryWarehouse::getCity);
		attributeSetterBiConsumers.put(
			"city",
			(BiConsumer<CommerceInventoryWarehouse, String>)
				CommerceInventoryWarehouse::setCity);
		attributeGetterFunctions.put("zip", CommerceInventoryWarehouse::getZip);
		attributeSetterBiConsumers.put(
			"zip",
			(BiConsumer<CommerceInventoryWarehouse, String>)
				CommerceInventoryWarehouse::setZip);
		attributeGetterFunctions.put(
			"commerceRegionCode",
			CommerceInventoryWarehouse::getCommerceRegionCode);
		attributeSetterBiConsumers.put(
			"commerceRegionCode",
			(BiConsumer<CommerceInventoryWarehouse, String>)
				CommerceInventoryWarehouse::setCommerceRegionCode);
		attributeGetterFunctions.put(
			"countryTwoLettersISOCode",
			CommerceInventoryWarehouse::getCountryTwoLettersISOCode);
		attributeSetterBiConsumers.put(
			"countryTwoLettersISOCode",
			(BiConsumer<CommerceInventoryWarehouse, String>)
				CommerceInventoryWarehouse::setCountryTwoLettersISOCode);
		attributeGetterFunctions.put(
			"latitude", CommerceInventoryWarehouse::getLatitude);
		attributeSetterBiConsumers.put(
			"latitude",
			(BiConsumer<CommerceInventoryWarehouse, Double>)
				CommerceInventoryWarehouse::setLatitude);
		attributeGetterFunctions.put(
			"longitude", CommerceInventoryWarehouse::getLongitude);
		attributeSetterBiConsumers.put(
			"longitude",
			(BiConsumer<CommerceInventoryWarehouse, Double>)
				CommerceInventoryWarehouse::setLongitude);
		attributeGetterFunctions.put(
			"type", CommerceInventoryWarehouse::getType);
		attributeSetterBiConsumers.put(
			"type",
			(BiConsumer<CommerceInventoryWarehouse, String>)
				CommerceInventoryWarehouse::setType);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getMvccVersion() {
		return _mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	@JSON
	@Override
	public String getExternalReferenceCode() {
		if (_externalReferenceCode == null) {
			return "";
		}
		else {
			return _externalReferenceCode;
		}
	}

	@Override
	public void setExternalReferenceCode(String externalReferenceCode) {
		_columnBitmask |= EXTERNALREFERENCECODE_COLUMN_BITMASK;

		if (_originalExternalReferenceCode == null) {
			_originalExternalReferenceCode = _externalReferenceCode;
		}

		_externalReferenceCode = externalReferenceCode;
	}

	public String getOriginalExternalReferenceCode() {
		return GetterUtil.getString(_originalExternalReferenceCode);
	}

	@JSON
	@Override
	public long getCommerceInventoryWarehouseId() {
		return _commerceInventoryWarehouseId;
	}

	@Override
	public void setCommerceInventoryWarehouseId(
		long commerceInventoryWarehouseId) {

		_commerceInventoryWarehouseId = commerceInventoryWarehouseId;
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
	public String getName() {
		if (_name == null) {
			return "";
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		_columnBitmask = -1L;

		_name = name;
	}

	@JSON
	@Override
	public String getDescription() {
		if (_description == null) {
			return "";
		}
		else {
			return _description;
		}
	}

	@Override
	public void setDescription(String description) {
		_description = description;
	}

	@JSON
	@Override
	public boolean getActive() {
		return _active;
	}

	@JSON
	@Override
	public boolean isActive() {
		return _active;
	}

	@Override
	public void setActive(boolean active) {
		_columnBitmask |= ACTIVE_COLUMN_BITMASK;

		if (!_setOriginalActive) {
			_setOriginalActive = true;

			_originalActive = _active;
		}

		_active = active;
	}

	public boolean getOriginalActive() {
		return _originalActive;
	}

	@JSON
	@Override
	public String getStreet1() {
		if (_street1 == null) {
			return "";
		}
		else {
			return _street1;
		}
	}

	@Override
	public void setStreet1(String street1) {
		_street1 = street1;
	}

	@JSON
	@Override
	public String getStreet2() {
		if (_street2 == null) {
			return "";
		}
		else {
			return _street2;
		}
	}

	@Override
	public void setStreet2(String street2) {
		_street2 = street2;
	}

	@JSON
	@Override
	public String getStreet3() {
		if (_street3 == null) {
			return "";
		}
		else {
			return _street3;
		}
	}

	@Override
	public void setStreet3(String street3) {
		_street3 = street3;
	}

	@JSON
	@Override
	public String getCity() {
		if (_city == null) {
			return "";
		}
		else {
			return _city;
		}
	}

	@Override
	public void setCity(String city) {
		_city = city;
	}

	@JSON
	@Override
	public String getZip() {
		if (_zip == null) {
			return "";
		}
		else {
			return _zip;
		}
	}

	@Override
	public void setZip(String zip) {
		_zip = zip;
	}

	@JSON
	@Override
	public String getCommerceRegionCode() {
		if (_commerceRegionCode == null) {
			return "";
		}
		else {
			return _commerceRegionCode;
		}
	}

	@Override
	public void setCommerceRegionCode(String commerceRegionCode) {
		_commerceRegionCode = commerceRegionCode;
	}

	@JSON
	@Override
	public String getCountryTwoLettersISOCode() {
		if (_countryTwoLettersISOCode == null) {
			return "";
		}
		else {
			return _countryTwoLettersISOCode;
		}
	}

	@Override
	public void setCountryTwoLettersISOCode(String countryTwoLettersISOCode) {
		_columnBitmask |= COUNTRYTWOLETTERSISOCODE_COLUMN_BITMASK;

		if (_originalCountryTwoLettersISOCode == null) {
			_originalCountryTwoLettersISOCode = _countryTwoLettersISOCode;
		}

		_countryTwoLettersISOCode = countryTwoLettersISOCode;
	}

	public String getOriginalCountryTwoLettersISOCode() {
		return GetterUtil.getString(_originalCountryTwoLettersISOCode);
	}

	@JSON
	@Override
	public double getLatitude() {
		return _latitude;
	}

	@Override
	public void setLatitude(double latitude) {
		_latitude = latitude;
	}

	@JSON
	@Override
	public double getLongitude() {
		return _longitude;
	}

	@Override
	public void setLongitude(double longitude) {
		_longitude = longitude;
	}

	@JSON
	@Override
	public String getType() {
		if (_type == null) {
			return "";
		}
		else {
			return _type;
		}
	}

	@Override
	public void setType(String type) {
		_type = type;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), CommerceInventoryWarehouse.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CommerceInventoryWarehouse toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CommerceInventoryWarehouse>
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
		CommerceInventoryWarehouseImpl commerceInventoryWarehouseImpl =
			new CommerceInventoryWarehouseImpl();

		commerceInventoryWarehouseImpl.setMvccVersion(getMvccVersion());
		commerceInventoryWarehouseImpl.setExternalReferenceCode(
			getExternalReferenceCode());
		commerceInventoryWarehouseImpl.setCommerceInventoryWarehouseId(
			getCommerceInventoryWarehouseId());
		commerceInventoryWarehouseImpl.setCompanyId(getCompanyId());
		commerceInventoryWarehouseImpl.setUserId(getUserId());
		commerceInventoryWarehouseImpl.setUserName(getUserName());
		commerceInventoryWarehouseImpl.setCreateDate(getCreateDate());
		commerceInventoryWarehouseImpl.setModifiedDate(getModifiedDate());
		commerceInventoryWarehouseImpl.setName(getName());
		commerceInventoryWarehouseImpl.setDescription(getDescription());
		commerceInventoryWarehouseImpl.setActive(isActive());
		commerceInventoryWarehouseImpl.setStreet1(getStreet1());
		commerceInventoryWarehouseImpl.setStreet2(getStreet2());
		commerceInventoryWarehouseImpl.setStreet3(getStreet3());
		commerceInventoryWarehouseImpl.setCity(getCity());
		commerceInventoryWarehouseImpl.setZip(getZip());
		commerceInventoryWarehouseImpl.setCommerceRegionCode(
			getCommerceRegionCode());
		commerceInventoryWarehouseImpl.setCountryTwoLettersISOCode(
			getCountryTwoLettersISOCode());
		commerceInventoryWarehouseImpl.setLatitude(getLatitude());
		commerceInventoryWarehouseImpl.setLongitude(getLongitude());
		commerceInventoryWarehouseImpl.setType(getType());

		commerceInventoryWarehouseImpl.resetOriginalValues();

		return commerceInventoryWarehouseImpl;
	}

	@Override
	public int compareTo(
		CommerceInventoryWarehouse commerceInventoryWarehouse) {

		int value = 0;

		value = getName().compareTo(commerceInventoryWarehouse.getName());

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

		if (!(object instanceof CommerceInventoryWarehouse)) {
			return false;
		}

		CommerceInventoryWarehouse commerceInventoryWarehouse =
			(CommerceInventoryWarehouse)object;

		long primaryKey = commerceInventoryWarehouse.getPrimaryKey();

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
		CommerceInventoryWarehouseModelImpl
			commerceInventoryWarehouseModelImpl = this;

		commerceInventoryWarehouseModelImpl._originalExternalReferenceCode =
			commerceInventoryWarehouseModelImpl._externalReferenceCode;

		commerceInventoryWarehouseModelImpl._originalCompanyId =
			commerceInventoryWarehouseModelImpl._companyId;

		commerceInventoryWarehouseModelImpl._setOriginalCompanyId = false;

		commerceInventoryWarehouseModelImpl._setModifiedDate = false;

		commerceInventoryWarehouseModelImpl._originalActive =
			commerceInventoryWarehouseModelImpl._active;

		commerceInventoryWarehouseModelImpl._setOriginalActive = false;

		commerceInventoryWarehouseModelImpl._originalCountryTwoLettersISOCode =
			commerceInventoryWarehouseModelImpl._countryTwoLettersISOCode;

		commerceInventoryWarehouseModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<CommerceInventoryWarehouse> toCacheModel() {
		CommerceInventoryWarehouseCacheModel
			commerceInventoryWarehouseCacheModel =
				new CommerceInventoryWarehouseCacheModel();

		commerceInventoryWarehouseCacheModel.mvccVersion = getMvccVersion();

		commerceInventoryWarehouseCacheModel.externalReferenceCode =
			getExternalReferenceCode();

		String externalReferenceCode =
			commerceInventoryWarehouseCacheModel.externalReferenceCode;

		if ((externalReferenceCode != null) &&
			(externalReferenceCode.length() == 0)) {

			commerceInventoryWarehouseCacheModel.externalReferenceCode = null;
		}

		commerceInventoryWarehouseCacheModel.commerceInventoryWarehouseId =
			getCommerceInventoryWarehouseId();

		commerceInventoryWarehouseCacheModel.companyId = getCompanyId();

		commerceInventoryWarehouseCacheModel.userId = getUserId();

		commerceInventoryWarehouseCacheModel.userName = getUserName();

		String userName = commerceInventoryWarehouseCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commerceInventoryWarehouseCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commerceInventoryWarehouseCacheModel.createDate =
				createDate.getTime();
		}
		else {
			commerceInventoryWarehouseCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commerceInventoryWarehouseCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			commerceInventoryWarehouseCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		commerceInventoryWarehouseCacheModel.name = getName();

		String name = commerceInventoryWarehouseCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			commerceInventoryWarehouseCacheModel.name = null;
		}

		commerceInventoryWarehouseCacheModel.description = getDescription();

		String description = commerceInventoryWarehouseCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			commerceInventoryWarehouseCacheModel.description = null;
		}

		commerceInventoryWarehouseCacheModel.active = isActive();

		commerceInventoryWarehouseCacheModel.street1 = getStreet1();

		String street1 = commerceInventoryWarehouseCacheModel.street1;

		if ((street1 != null) && (street1.length() == 0)) {
			commerceInventoryWarehouseCacheModel.street1 = null;
		}

		commerceInventoryWarehouseCacheModel.street2 = getStreet2();

		String street2 = commerceInventoryWarehouseCacheModel.street2;

		if ((street2 != null) && (street2.length() == 0)) {
			commerceInventoryWarehouseCacheModel.street2 = null;
		}

		commerceInventoryWarehouseCacheModel.street3 = getStreet3();

		String street3 = commerceInventoryWarehouseCacheModel.street3;

		if ((street3 != null) && (street3.length() == 0)) {
			commerceInventoryWarehouseCacheModel.street3 = null;
		}

		commerceInventoryWarehouseCacheModel.city = getCity();

		String city = commerceInventoryWarehouseCacheModel.city;

		if ((city != null) && (city.length() == 0)) {
			commerceInventoryWarehouseCacheModel.city = null;
		}

		commerceInventoryWarehouseCacheModel.zip = getZip();

		String zip = commerceInventoryWarehouseCacheModel.zip;

		if ((zip != null) && (zip.length() == 0)) {
			commerceInventoryWarehouseCacheModel.zip = null;
		}

		commerceInventoryWarehouseCacheModel.commerceRegionCode =
			getCommerceRegionCode();

		String commerceRegionCode =
			commerceInventoryWarehouseCacheModel.commerceRegionCode;

		if ((commerceRegionCode != null) &&
			(commerceRegionCode.length() == 0)) {

			commerceInventoryWarehouseCacheModel.commerceRegionCode = null;
		}

		commerceInventoryWarehouseCacheModel.countryTwoLettersISOCode =
			getCountryTwoLettersISOCode();

		String countryTwoLettersISOCode =
			commerceInventoryWarehouseCacheModel.countryTwoLettersISOCode;

		if ((countryTwoLettersISOCode != null) &&
			(countryTwoLettersISOCode.length() == 0)) {

			commerceInventoryWarehouseCacheModel.countryTwoLettersISOCode =
				null;
		}

		commerceInventoryWarehouseCacheModel.latitude = getLatitude();

		commerceInventoryWarehouseCacheModel.longitude = getLongitude();

		commerceInventoryWarehouseCacheModel.type = getType();

		String type = commerceInventoryWarehouseCacheModel.type;

		if ((type != null) && (type.length() == 0)) {
			commerceInventoryWarehouseCacheModel.type = null;
		}

		return commerceInventoryWarehouseCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CommerceInventoryWarehouse, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CommerceInventoryWarehouse, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceInventoryWarehouse, Object>
				attributeGetterFunction = entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(
				attributeGetterFunction.apply(
					(CommerceInventoryWarehouse)this));
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
		Map<String, Function<CommerceInventoryWarehouse, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<CommerceInventoryWarehouse, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceInventoryWarehouse, Object>
				attributeGetterFunction = entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply(
					(CommerceInventoryWarehouse)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function
			<InvocationHandler, CommerceInventoryWarehouse>
				_escapedModelProxyProviderFunction =
					_getProxyProviderFunction();

	}

	private long _mvccVersion;
	private String _externalReferenceCode;
	private String _originalExternalReferenceCode;
	private long _commerceInventoryWarehouseId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _name;
	private String _description;
	private boolean _active;
	private boolean _originalActive;
	private boolean _setOriginalActive;
	private String _street1;
	private String _street2;
	private String _street3;
	private String _city;
	private String _zip;
	private String _commerceRegionCode;
	private String _countryTwoLettersISOCode;
	private String _originalCountryTwoLettersISOCode;
	private double _latitude;
	private double _longitude;
	private String _type;
	private long _columnBitmask;
	private CommerceInventoryWarehouse _escapedModel;

}