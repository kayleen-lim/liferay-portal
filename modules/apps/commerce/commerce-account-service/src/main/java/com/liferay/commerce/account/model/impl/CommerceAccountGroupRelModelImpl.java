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

package com.liferay.commerce.account.model.impl;

import com.liferay.commerce.account.model.CommerceAccountGroupRel;
import com.liferay.commerce.account.model.CommerceAccountGroupRelModel;
import com.liferay.commerce.account.model.CommerceAccountGroupRelSoap;
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
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.Validator;

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
 * The base model implementation for the CommerceAccountGroupRel service. Represents a row in the &quot;CommerceAccountGroupRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CommerceAccountGroupRelModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceAccountGroupRelImpl}.
 * </p>
 *
 * @author Marco Leo
 * @see CommerceAccountGroupRelImpl
 * @generated
 */
@JSON(strict = true)
public class CommerceAccountGroupRelModelImpl
	extends BaseModelImpl<CommerceAccountGroupRel>
	implements CommerceAccountGroupRelModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce account group rel model instance should use the <code>CommerceAccountGroupRel</code> interface instead.
	 */
	public static final String TABLE_NAME = "CommerceAccountGroupRel";

	public static final Object[][] TABLE_COLUMNS = {
		{"commerceAccountGroupRelId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}, {"classNameId", Types.BIGINT},
		{"classPK", Types.BIGINT}, {"commerceAccountGroupId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("commerceAccountGroupRelId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("commerceAccountGroupId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CommerceAccountGroupRel (commerceAccountGroupRelId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,classNameId LONG,classPK LONG,commerceAccountGroupId LONG)";

	public static final String TABLE_SQL_DROP =
		"drop table CommerceAccountGroupRel";

	public static final String ORDER_BY_JPQL =
		" ORDER BY commerceAccountGroupRel.createDate DESC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CommerceAccountGroupRel.createDate DESC";

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

	public static final long CLASSNAMEID_COLUMN_BITMASK = 1L;

	public static final long CLASSPK_COLUMN_BITMASK = 2L;

	public static final long COMMERCEACCOUNTGROUPID_COLUMN_BITMASK = 4L;

	public static final long CREATEDATE_COLUMN_BITMASK = 8L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static CommerceAccountGroupRel toModel(
		CommerceAccountGroupRelSoap soapModel) {

		if (soapModel == null) {
			return null;
		}

		CommerceAccountGroupRel model = new CommerceAccountGroupRelImpl();

		model.setCommerceAccountGroupRelId(
			soapModel.getCommerceAccountGroupRelId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setClassNameId(soapModel.getClassNameId());
		model.setClassPK(soapModel.getClassPK());
		model.setCommerceAccountGroupId(soapModel.getCommerceAccountGroupId());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<CommerceAccountGroupRel> toModels(
		CommerceAccountGroupRelSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<CommerceAccountGroupRel> models =
			new ArrayList<CommerceAccountGroupRel>(soapModels.length);

		for (CommerceAccountGroupRelSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.commerce.account.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.commerce.account.model.CommerceAccountGroupRel"));

	public CommerceAccountGroupRelModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _commerceAccountGroupRelId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCommerceAccountGroupRelId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceAccountGroupRelId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceAccountGroupRel.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceAccountGroupRel.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CommerceAccountGroupRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CommerceAccountGroupRel, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceAccountGroupRel, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((CommerceAccountGroupRel)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CommerceAccountGroupRel, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CommerceAccountGroupRel, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CommerceAccountGroupRel)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CommerceAccountGroupRel, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CommerceAccountGroupRel, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, CommerceAccountGroupRel>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			CommerceAccountGroupRel.class.getClassLoader(),
			CommerceAccountGroupRel.class, ModelWrapper.class);

		try {
			Constructor<CommerceAccountGroupRel> constructor =
				(Constructor<CommerceAccountGroupRel>)proxyClass.getConstructor(
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

	private static final Map<String, Function<CommerceAccountGroupRel, Object>>
		_attributeGetterFunctions;
	private static final Map
		<String, BiConsumer<CommerceAccountGroupRel, Object>>
			_attributeSetterBiConsumers;

	static {
		Map<String, Function<CommerceAccountGroupRel, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<CommerceAccountGroupRel, Object>>();
		Map<String, BiConsumer<CommerceAccountGroupRel, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<CommerceAccountGroupRel, ?>>();

		attributeGetterFunctions.put(
			"commerceAccountGroupRelId",
			CommerceAccountGroupRel::getCommerceAccountGroupRelId);
		attributeSetterBiConsumers.put(
			"commerceAccountGroupRelId",
			(BiConsumer<CommerceAccountGroupRel, Long>)
				CommerceAccountGroupRel::setCommerceAccountGroupRelId);
		attributeGetterFunctions.put(
			"companyId", CommerceAccountGroupRel::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<CommerceAccountGroupRel, Long>)
				CommerceAccountGroupRel::setCompanyId);
		attributeGetterFunctions.put(
			"userId", CommerceAccountGroupRel::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<CommerceAccountGroupRel, Long>)
				CommerceAccountGroupRel::setUserId);
		attributeGetterFunctions.put(
			"userName", CommerceAccountGroupRel::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<CommerceAccountGroupRel, String>)
				CommerceAccountGroupRel::setUserName);
		attributeGetterFunctions.put(
			"createDate", CommerceAccountGroupRel::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<CommerceAccountGroupRel, Date>)
				CommerceAccountGroupRel::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", CommerceAccountGroupRel::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<CommerceAccountGroupRel, Date>)
				CommerceAccountGroupRel::setModifiedDate);
		attributeGetterFunctions.put(
			"classNameId", CommerceAccountGroupRel::getClassNameId);
		attributeSetterBiConsumers.put(
			"classNameId",
			(BiConsumer<CommerceAccountGroupRel, Long>)
				CommerceAccountGroupRel::setClassNameId);
		attributeGetterFunctions.put(
			"classPK", CommerceAccountGroupRel::getClassPK);
		attributeSetterBiConsumers.put(
			"classPK",
			(BiConsumer<CommerceAccountGroupRel, Long>)
				CommerceAccountGroupRel::setClassPK);
		attributeGetterFunctions.put(
			"commerceAccountGroupId",
			CommerceAccountGroupRel::getCommerceAccountGroupId);
		attributeSetterBiConsumers.put(
			"commerceAccountGroupId",
			(BiConsumer<CommerceAccountGroupRel, Long>)
				CommerceAccountGroupRel::setCommerceAccountGroupId);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getCommerceAccountGroupRelId() {
		return _commerceAccountGroupRelId;
	}

	@Override
	public void setCommerceAccountGroupRelId(long commerceAccountGroupRelId) {
		_commerceAccountGroupRelId = commerceAccountGroupRelId;
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

	@Override
	public String getClassName() {
		if (getClassNameId() <= 0) {
			return "";
		}

		return PortalUtil.getClassName(getClassNameId());
	}

	@Override
	public void setClassName(String className) {
		long classNameId = 0;

		if (Validator.isNotNull(className)) {
			classNameId = PortalUtil.getClassNameId(className);
		}

		setClassNameId(classNameId);
	}

	@JSON
	@Override
	public long getClassNameId() {
		return _classNameId;
	}

	@Override
	public void setClassNameId(long classNameId) {
		_columnBitmask |= CLASSNAMEID_COLUMN_BITMASK;

		if (!_setOriginalClassNameId) {
			_setOriginalClassNameId = true;

			_originalClassNameId = _classNameId;
		}

		_classNameId = classNameId;
	}

	public long getOriginalClassNameId() {
		return _originalClassNameId;
	}

	@JSON
	@Override
	public long getClassPK() {
		return _classPK;
	}

	@Override
	public void setClassPK(long classPK) {
		_columnBitmask |= CLASSPK_COLUMN_BITMASK;

		if (!_setOriginalClassPK) {
			_setOriginalClassPK = true;

			_originalClassPK = _classPK;
		}

		_classPK = classPK;
	}

	public long getOriginalClassPK() {
		return _originalClassPK;
	}

	@JSON
	@Override
	public long getCommerceAccountGroupId() {
		return _commerceAccountGroupId;
	}

	@Override
	public void setCommerceAccountGroupId(long commerceAccountGroupId) {
		_columnBitmask |= COMMERCEACCOUNTGROUPID_COLUMN_BITMASK;

		if (!_setOriginalCommerceAccountGroupId) {
			_setOriginalCommerceAccountGroupId = true;

			_originalCommerceAccountGroupId = _commerceAccountGroupId;
		}

		_commerceAccountGroupId = commerceAccountGroupId;
	}

	public long getOriginalCommerceAccountGroupId() {
		return _originalCommerceAccountGroupId;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), CommerceAccountGroupRel.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CommerceAccountGroupRel toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CommerceAccountGroupRel>
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
		CommerceAccountGroupRelImpl commerceAccountGroupRelImpl =
			new CommerceAccountGroupRelImpl();

		commerceAccountGroupRelImpl.setCommerceAccountGroupRelId(
			getCommerceAccountGroupRelId());
		commerceAccountGroupRelImpl.setCompanyId(getCompanyId());
		commerceAccountGroupRelImpl.setUserId(getUserId());
		commerceAccountGroupRelImpl.setUserName(getUserName());
		commerceAccountGroupRelImpl.setCreateDate(getCreateDate());
		commerceAccountGroupRelImpl.setModifiedDate(getModifiedDate());
		commerceAccountGroupRelImpl.setClassNameId(getClassNameId());
		commerceAccountGroupRelImpl.setClassPK(getClassPK());
		commerceAccountGroupRelImpl.setCommerceAccountGroupId(
			getCommerceAccountGroupId());

		commerceAccountGroupRelImpl.resetOriginalValues();

		return commerceAccountGroupRelImpl;
	}

	@Override
	public int compareTo(CommerceAccountGroupRel commerceAccountGroupRel) {
		int value = 0;

		value = DateUtil.compareTo(
			getCreateDate(), commerceAccountGroupRel.getCreateDate());

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

		if (!(object instanceof CommerceAccountGroupRel)) {
			return false;
		}

		CommerceAccountGroupRel commerceAccountGroupRel =
			(CommerceAccountGroupRel)object;

		long primaryKey = commerceAccountGroupRel.getPrimaryKey();

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
		CommerceAccountGroupRelModelImpl commerceAccountGroupRelModelImpl =
			this;

		commerceAccountGroupRelModelImpl._setModifiedDate = false;

		commerceAccountGroupRelModelImpl._originalClassNameId =
			commerceAccountGroupRelModelImpl._classNameId;

		commerceAccountGroupRelModelImpl._setOriginalClassNameId = false;

		commerceAccountGroupRelModelImpl._originalClassPK =
			commerceAccountGroupRelModelImpl._classPK;

		commerceAccountGroupRelModelImpl._setOriginalClassPK = false;

		commerceAccountGroupRelModelImpl._originalCommerceAccountGroupId =
			commerceAccountGroupRelModelImpl._commerceAccountGroupId;

		commerceAccountGroupRelModelImpl._setOriginalCommerceAccountGroupId =
			false;

		commerceAccountGroupRelModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<CommerceAccountGroupRel> toCacheModel() {
		CommerceAccountGroupRelCacheModel commerceAccountGroupRelCacheModel =
			new CommerceAccountGroupRelCacheModel();

		commerceAccountGroupRelCacheModel.commerceAccountGroupRelId =
			getCommerceAccountGroupRelId();

		commerceAccountGroupRelCacheModel.companyId = getCompanyId();

		commerceAccountGroupRelCacheModel.userId = getUserId();

		commerceAccountGroupRelCacheModel.userName = getUserName();

		String userName = commerceAccountGroupRelCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commerceAccountGroupRelCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commerceAccountGroupRelCacheModel.createDate = createDate.getTime();
		}
		else {
			commerceAccountGroupRelCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commerceAccountGroupRelCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			commerceAccountGroupRelCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		commerceAccountGroupRelCacheModel.classNameId = getClassNameId();

		commerceAccountGroupRelCacheModel.classPK = getClassPK();

		commerceAccountGroupRelCacheModel.commerceAccountGroupId =
			getCommerceAccountGroupId();

		return commerceAccountGroupRelCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CommerceAccountGroupRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CommerceAccountGroupRel, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceAccountGroupRel, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(
				attributeGetterFunction.apply((CommerceAccountGroupRel)this));
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
		Map<String, Function<CommerceAccountGroupRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<CommerceAccountGroupRel, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceAccountGroupRel, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply((CommerceAccountGroupRel)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function
			<InvocationHandler, CommerceAccountGroupRel>
				_escapedModelProxyProviderFunction =
					_getProxyProviderFunction();

	}

	private long _commerceAccountGroupRelId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _classNameId;
	private long _originalClassNameId;
	private boolean _setOriginalClassNameId;
	private long _classPK;
	private long _originalClassPK;
	private boolean _setOriginalClassPK;
	private long _commerceAccountGroupId;
	private long _originalCommerceAccountGroupId;
	private boolean _setOriginalCommerceAccountGroupId;
	private long _columnBitmask;
	private CommerceAccountGroupRel _escapedModel;

}