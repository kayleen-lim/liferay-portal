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

package com.liferay.commerce.pricing.model.impl;

import com.liferay.commerce.pricing.model.CommercePriceModifierRel;
import com.liferay.commerce.pricing.model.CommercePriceModifierRelModel;
import com.liferay.commerce.pricing.model.CommercePriceModifierRelSoap;
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
 * The base model implementation for the CommercePriceModifierRel service. Represents a row in the &quot;CommercePriceModifierRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CommercePriceModifierRelModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommercePriceModifierRelImpl}.
 * </p>
 *
 * @author Riccardo Alberti
 * @see CommercePriceModifierRelImpl
 * @generated
 */
@JSON(strict = true)
public class CommercePriceModifierRelModelImpl
	extends BaseModelImpl<CommercePriceModifierRel>
	implements CommercePriceModifierRelModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce price modifier rel model instance should use the <code>CommercePriceModifierRel</code> interface instead.
	 */
	public static final String TABLE_NAME = "CommercePriceModifierRel";

	public static final Object[][] TABLE_COLUMNS = {
		{"commercePriceModifierRelId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP},
		{"commercePriceModifierId", Types.BIGINT},
		{"classNameId", Types.BIGINT}, {"classPK", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("commercePriceModifierRelId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("commercePriceModifierId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CommercePriceModifierRel (commercePriceModifierRelId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,commercePriceModifierId LONG,classNameId LONG,classPK LONG)";

	public static final String TABLE_SQL_DROP =
		"drop table CommercePriceModifierRel";

	public static final String ORDER_BY_JPQL =
		" ORDER BY commercePriceModifierRel.createDate DESC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CommercePriceModifierRel.createDate DESC";

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

	public static final long COMMERCEPRICEMODIFIERID_COLUMN_BITMASK = 4L;

	public static final long CREATEDATE_COLUMN_BITMASK = 8L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static CommercePriceModifierRel toModel(
		CommercePriceModifierRelSoap soapModel) {

		if (soapModel == null) {
			return null;
		}

		CommercePriceModifierRel model = new CommercePriceModifierRelImpl();

		model.setCommercePriceModifierRelId(
			soapModel.getCommercePriceModifierRelId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setCommercePriceModifierId(
			soapModel.getCommercePriceModifierId());
		model.setClassNameId(soapModel.getClassNameId());
		model.setClassPK(soapModel.getClassPK());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<CommercePriceModifierRel> toModels(
		CommercePriceModifierRelSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<CommercePriceModifierRel> models =
			new ArrayList<CommercePriceModifierRel>(soapModels.length);

		for (CommercePriceModifierRelSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.commerce.pricing.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.commerce.pricing.model.CommercePriceModifierRel"));

	public CommercePriceModifierRelModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _commercePriceModifierRelId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCommercePriceModifierRelId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commercePriceModifierRelId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CommercePriceModifierRel.class;
	}

	@Override
	public String getModelClassName() {
		return CommercePriceModifierRel.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CommercePriceModifierRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CommercePriceModifierRel, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommercePriceModifierRel, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((CommercePriceModifierRel)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CommercePriceModifierRel, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CommercePriceModifierRel, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CommercePriceModifierRel)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CommercePriceModifierRel, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CommercePriceModifierRel, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, CommercePriceModifierRel>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			CommercePriceModifierRel.class.getClassLoader(),
			CommercePriceModifierRel.class, ModelWrapper.class);

		try {
			Constructor<CommercePriceModifierRel> constructor =
				(Constructor<CommercePriceModifierRel>)
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

	private static final Map<String, Function<CommercePriceModifierRel, Object>>
		_attributeGetterFunctions;
	private static final Map
		<String, BiConsumer<CommercePriceModifierRel, Object>>
			_attributeSetterBiConsumers;

	static {
		Map<String, Function<CommercePriceModifierRel, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<CommercePriceModifierRel, Object>>();
		Map<String, BiConsumer<CommercePriceModifierRel, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<CommercePriceModifierRel, ?>>();

		attributeGetterFunctions.put(
			"commercePriceModifierRelId",
			CommercePriceModifierRel::getCommercePriceModifierRelId);
		attributeSetterBiConsumers.put(
			"commercePriceModifierRelId",
			(BiConsumer<CommercePriceModifierRel, Long>)
				CommercePriceModifierRel::setCommercePriceModifierRelId);
		attributeGetterFunctions.put(
			"companyId", CommercePriceModifierRel::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<CommercePriceModifierRel, Long>)
				CommercePriceModifierRel::setCompanyId);
		attributeGetterFunctions.put(
			"userId", CommercePriceModifierRel::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<CommercePriceModifierRel, Long>)
				CommercePriceModifierRel::setUserId);
		attributeGetterFunctions.put(
			"userName", CommercePriceModifierRel::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<CommercePriceModifierRel, String>)
				CommercePriceModifierRel::setUserName);
		attributeGetterFunctions.put(
			"createDate", CommercePriceModifierRel::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<CommercePriceModifierRel, Date>)
				CommercePriceModifierRel::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", CommercePriceModifierRel::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<CommercePriceModifierRel, Date>)
				CommercePriceModifierRel::setModifiedDate);
		attributeGetterFunctions.put(
			"commercePriceModifierId",
			CommercePriceModifierRel::getCommercePriceModifierId);
		attributeSetterBiConsumers.put(
			"commercePriceModifierId",
			(BiConsumer<CommercePriceModifierRel, Long>)
				CommercePriceModifierRel::setCommercePriceModifierId);
		attributeGetterFunctions.put(
			"classNameId", CommercePriceModifierRel::getClassNameId);
		attributeSetterBiConsumers.put(
			"classNameId",
			(BiConsumer<CommercePriceModifierRel, Long>)
				CommercePriceModifierRel::setClassNameId);
		attributeGetterFunctions.put(
			"classPK", CommercePriceModifierRel::getClassPK);
		attributeSetterBiConsumers.put(
			"classPK",
			(BiConsumer<CommercePriceModifierRel, Long>)
				CommercePriceModifierRel::setClassPK);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getCommercePriceModifierRelId() {
		return _commercePriceModifierRelId;
	}

	@Override
	public void setCommercePriceModifierRelId(long commercePriceModifierRelId) {
		_commercePriceModifierRelId = commercePriceModifierRelId;
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
	public long getCommercePriceModifierId() {
		return _commercePriceModifierId;
	}

	@Override
	public void setCommercePriceModifierId(long commercePriceModifierId) {
		_columnBitmask |= COMMERCEPRICEMODIFIERID_COLUMN_BITMASK;

		if (!_setOriginalCommercePriceModifierId) {
			_setOriginalCommercePriceModifierId = true;

			_originalCommercePriceModifierId = _commercePriceModifierId;
		}

		_commercePriceModifierId = commercePriceModifierId;
	}

	public long getOriginalCommercePriceModifierId() {
		return _originalCommercePriceModifierId;
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

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), CommercePriceModifierRel.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CommercePriceModifierRel toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CommercePriceModifierRel>
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
		CommercePriceModifierRelImpl commercePriceModifierRelImpl =
			new CommercePriceModifierRelImpl();

		commercePriceModifierRelImpl.setCommercePriceModifierRelId(
			getCommercePriceModifierRelId());
		commercePriceModifierRelImpl.setCompanyId(getCompanyId());
		commercePriceModifierRelImpl.setUserId(getUserId());
		commercePriceModifierRelImpl.setUserName(getUserName());
		commercePriceModifierRelImpl.setCreateDate(getCreateDate());
		commercePriceModifierRelImpl.setModifiedDate(getModifiedDate());
		commercePriceModifierRelImpl.setCommercePriceModifierId(
			getCommercePriceModifierId());
		commercePriceModifierRelImpl.setClassNameId(getClassNameId());
		commercePriceModifierRelImpl.setClassPK(getClassPK());

		commercePriceModifierRelImpl.resetOriginalValues();

		return commercePriceModifierRelImpl;
	}

	@Override
	public int compareTo(CommercePriceModifierRel commercePriceModifierRel) {
		int value = 0;

		value = DateUtil.compareTo(
			getCreateDate(), commercePriceModifierRel.getCreateDate());

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

		if (!(object instanceof CommercePriceModifierRel)) {
			return false;
		}

		CommercePriceModifierRel commercePriceModifierRel =
			(CommercePriceModifierRel)object;

		long primaryKey = commercePriceModifierRel.getPrimaryKey();

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
		CommercePriceModifierRelModelImpl commercePriceModifierRelModelImpl =
			this;

		commercePriceModifierRelModelImpl._setModifiedDate = false;

		commercePriceModifierRelModelImpl._originalCommercePriceModifierId =
			commercePriceModifierRelModelImpl._commercePriceModifierId;

		commercePriceModifierRelModelImpl._setOriginalCommercePriceModifierId =
			false;

		commercePriceModifierRelModelImpl._originalClassNameId =
			commercePriceModifierRelModelImpl._classNameId;

		commercePriceModifierRelModelImpl._setOriginalClassNameId = false;

		commercePriceModifierRelModelImpl._originalClassPK =
			commercePriceModifierRelModelImpl._classPK;

		commercePriceModifierRelModelImpl._setOriginalClassPK = false;

		commercePriceModifierRelModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<CommercePriceModifierRel> toCacheModel() {
		CommercePriceModifierRelCacheModel commercePriceModifierRelCacheModel =
			new CommercePriceModifierRelCacheModel();

		commercePriceModifierRelCacheModel.commercePriceModifierRelId =
			getCommercePriceModifierRelId();

		commercePriceModifierRelCacheModel.companyId = getCompanyId();

		commercePriceModifierRelCacheModel.userId = getUserId();

		commercePriceModifierRelCacheModel.userName = getUserName();

		String userName = commercePriceModifierRelCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commercePriceModifierRelCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commercePriceModifierRelCacheModel.createDate =
				createDate.getTime();
		}
		else {
			commercePriceModifierRelCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commercePriceModifierRelCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			commercePriceModifierRelCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		commercePriceModifierRelCacheModel.commercePriceModifierId =
			getCommercePriceModifierId();

		commercePriceModifierRelCacheModel.classNameId = getClassNameId();

		commercePriceModifierRelCacheModel.classPK = getClassPK();

		return commercePriceModifierRelCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CommercePriceModifierRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CommercePriceModifierRel, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommercePriceModifierRel, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(
				attributeGetterFunction.apply((CommercePriceModifierRel)this));
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
		Map<String, Function<CommercePriceModifierRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<CommercePriceModifierRel, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommercePriceModifierRel, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply((CommercePriceModifierRel)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function
			<InvocationHandler, CommercePriceModifierRel>
				_escapedModelProxyProviderFunction =
					_getProxyProviderFunction();

	}

	private long _commercePriceModifierRelId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _commercePriceModifierId;
	private long _originalCommercePriceModifierId;
	private boolean _setOriginalCommercePriceModifierId;
	private long _classNameId;
	private long _originalClassNameId;
	private boolean _setOriginalClassNameId;
	private long _classPK;
	private long _originalClassPK;
	private boolean _setOriginalClassPK;
	private long _columnBitmask;
	private CommercePriceModifierRel _escapedModel;

}