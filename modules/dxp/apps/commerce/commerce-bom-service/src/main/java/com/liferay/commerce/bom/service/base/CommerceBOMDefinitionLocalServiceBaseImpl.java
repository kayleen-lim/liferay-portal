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

package com.liferay.commerce.bom.service.base;

import com.liferay.commerce.bom.model.CommerceBOMDefinition;
import com.liferay.commerce.bom.service.CommerceBOMDefinitionLocalService;
import com.liferay.commerce.bom.service.persistence.CommerceBOMDefinitionPersistence;
import com.liferay.commerce.bom.service.persistence.CommerceBOMEntryPersistence;
import com.liferay.commerce.bom.service.persistence.CommerceBOMFolderApplicationRelPersistence;
import com.liferay.commerce.bom.service.persistence.CommerceBOMFolderPersistence;
import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the commerce bom definition local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.commerce.bom.service.impl.CommerceBOMDefinitionLocalServiceImpl}.
 * </p>
 *
 * @author Luca Pellizzon
 * @see com.liferay.commerce.bom.service.impl.CommerceBOMDefinitionLocalServiceImpl
 * @generated
 */
public abstract class CommerceBOMDefinitionLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements CommerceBOMDefinitionLocalService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>CommerceBOMDefinitionLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.commerce.bom.service.CommerceBOMDefinitionLocalServiceUtil</code>.
	 */

	/**
	 * Adds the commerce bom definition to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceBOMDefinitionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceBOMDefinition the commerce bom definition
	 * @return the commerce bom definition that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceBOMDefinition addCommerceBOMDefinition(
		CommerceBOMDefinition commerceBOMDefinition) {

		commerceBOMDefinition.setNew(true);

		return commerceBOMDefinitionPersistence.update(commerceBOMDefinition);
	}

	/**
	 * Creates a new commerce bom definition with the primary key. Does not add the commerce bom definition to the database.
	 *
	 * @param commerceBOMDefinitionId the primary key for the new commerce bom definition
	 * @return the new commerce bom definition
	 */
	@Override
	@Transactional(enabled = false)
	public CommerceBOMDefinition createCommerceBOMDefinition(
		long commerceBOMDefinitionId) {

		return commerceBOMDefinitionPersistence.create(commerceBOMDefinitionId);
	}

	/**
	 * Deletes the commerce bom definition with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceBOMDefinitionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceBOMDefinitionId the primary key of the commerce bom definition
	 * @return the commerce bom definition that was removed
	 * @throws PortalException if a commerce bom definition with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CommerceBOMDefinition deleteCommerceBOMDefinition(
			long commerceBOMDefinitionId)
		throws PortalException {

		return commerceBOMDefinitionPersistence.remove(commerceBOMDefinitionId);
	}

	/**
	 * Deletes the commerce bom definition from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceBOMDefinitionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceBOMDefinition the commerce bom definition
	 * @return the commerce bom definition that was removed
	 * @throws PortalException
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CommerceBOMDefinition deleteCommerceBOMDefinition(
			CommerceBOMDefinition commerceBOMDefinition)
		throws PortalException {

		return commerceBOMDefinitionPersistence.remove(commerceBOMDefinition);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return commerceBOMDefinitionPersistence.dslQuery(dslQuery);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			CommerceBOMDefinition.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return commerceBOMDefinitionPersistence.findWithDynamicQuery(
			dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.bom.model.impl.CommerceBOMDefinitionModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return commerceBOMDefinitionPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.bom.model.impl.CommerceBOMDefinitionModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return commerceBOMDefinitionPersistence.findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return commerceBOMDefinitionPersistence.countWithDynamicQuery(
			dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection) {

		return commerceBOMDefinitionPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public CommerceBOMDefinition fetchCommerceBOMDefinition(
		long commerceBOMDefinitionId) {

		return commerceBOMDefinitionPersistence.fetchByPrimaryKey(
			commerceBOMDefinitionId);
	}

	/**
	 * Returns the commerce bom definition with the primary key.
	 *
	 * @param commerceBOMDefinitionId the primary key of the commerce bom definition
	 * @return the commerce bom definition
	 * @throws PortalException if a commerce bom definition with the primary key could not be found
	 */
	@Override
	public CommerceBOMDefinition getCommerceBOMDefinition(
			long commerceBOMDefinitionId)
		throws PortalException {

		return commerceBOMDefinitionPersistence.findByPrimaryKey(
			commerceBOMDefinitionId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			commerceBOMDefinitionLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CommerceBOMDefinition.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"commerceBOMDefinitionId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			commerceBOMDefinitionLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(
			CommerceBOMDefinition.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"commerceBOMDefinitionId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			commerceBOMDefinitionLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CommerceBOMDefinition.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"commerceBOMDefinitionId");
	}

	/**
	 * @throws PortalException
	 */
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return commerceBOMDefinitionPersistence.create(
			((Long)primaryKeyObj).longValue());
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return commerceBOMDefinitionLocalService.deleteCommerceBOMDefinition(
			(CommerceBOMDefinition)persistedModel);
	}

	public BasePersistence<CommerceBOMDefinition> getBasePersistence() {
		return commerceBOMDefinitionPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return commerceBOMDefinitionPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the commerce bom definitions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.bom.model.impl.CommerceBOMDefinitionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce bom definitions
	 * @param end the upper bound of the range of commerce bom definitions (not inclusive)
	 * @return the range of commerce bom definitions
	 */
	@Override
	public List<CommerceBOMDefinition> getCommerceBOMDefinitions(
		int start, int end) {

		return commerceBOMDefinitionPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of commerce bom definitions.
	 *
	 * @return the number of commerce bom definitions
	 */
	@Override
	public int getCommerceBOMDefinitionsCount() {
		return commerceBOMDefinitionPersistence.countAll();
	}

	/**
	 * Updates the commerce bom definition in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceBOMDefinitionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceBOMDefinition the commerce bom definition
	 * @return the commerce bom definition that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceBOMDefinition updateCommerceBOMDefinition(
		CommerceBOMDefinition commerceBOMDefinition) {

		return commerceBOMDefinitionPersistence.update(commerceBOMDefinition);
	}

	/**
	 * Returns the commerce bom definition local service.
	 *
	 * @return the commerce bom definition local service
	 */
	public CommerceBOMDefinitionLocalService
		getCommerceBOMDefinitionLocalService() {

		return commerceBOMDefinitionLocalService;
	}

	/**
	 * Sets the commerce bom definition local service.
	 *
	 * @param commerceBOMDefinitionLocalService the commerce bom definition local service
	 */
	public void setCommerceBOMDefinitionLocalService(
		CommerceBOMDefinitionLocalService commerceBOMDefinitionLocalService) {

		this.commerceBOMDefinitionLocalService =
			commerceBOMDefinitionLocalService;
	}

	/**
	 * Returns the commerce bom definition persistence.
	 *
	 * @return the commerce bom definition persistence
	 */
	public CommerceBOMDefinitionPersistence
		getCommerceBOMDefinitionPersistence() {

		return commerceBOMDefinitionPersistence;
	}

	/**
	 * Sets the commerce bom definition persistence.
	 *
	 * @param commerceBOMDefinitionPersistence the commerce bom definition persistence
	 */
	public void setCommerceBOMDefinitionPersistence(
		CommerceBOMDefinitionPersistence commerceBOMDefinitionPersistence) {

		this.commerceBOMDefinitionPersistence =
			commerceBOMDefinitionPersistence;
	}

	/**
	 * Returns the commerce bom entry local service.
	 *
	 * @return the commerce bom entry local service
	 */
	public com.liferay.commerce.bom.service.CommerceBOMEntryLocalService
		getCommerceBOMEntryLocalService() {

		return commerceBOMEntryLocalService;
	}

	/**
	 * Sets the commerce bom entry local service.
	 *
	 * @param commerceBOMEntryLocalService the commerce bom entry local service
	 */
	public void setCommerceBOMEntryLocalService(
		com.liferay.commerce.bom.service.CommerceBOMEntryLocalService
			commerceBOMEntryLocalService) {

		this.commerceBOMEntryLocalService = commerceBOMEntryLocalService;
	}

	/**
	 * Returns the commerce bom entry persistence.
	 *
	 * @return the commerce bom entry persistence
	 */
	public CommerceBOMEntryPersistence getCommerceBOMEntryPersistence() {
		return commerceBOMEntryPersistence;
	}

	/**
	 * Sets the commerce bom entry persistence.
	 *
	 * @param commerceBOMEntryPersistence the commerce bom entry persistence
	 */
	public void setCommerceBOMEntryPersistence(
		CommerceBOMEntryPersistence commerceBOMEntryPersistence) {

		this.commerceBOMEntryPersistence = commerceBOMEntryPersistence;
	}

	/**
	 * Returns the commerce bom folder local service.
	 *
	 * @return the commerce bom folder local service
	 */
	public com.liferay.commerce.bom.service.CommerceBOMFolderLocalService
		getCommerceBOMFolderLocalService() {

		return commerceBOMFolderLocalService;
	}

	/**
	 * Sets the commerce bom folder local service.
	 *
	 * @param commerceBOMFolderLocalService the commerce bom folder local service
	 */
	public void setCommerceBOMFolderLocalService(
		com.liferay.commerce.bom.service.CommerceBOMFolderLocalService
			commerceBOMFolderLocalService) {

		this.commerceBOMFolderLocalService = commerceBOMFolderLocalService;
	}

	/**
	 * Returns the commerce bom folder persistence.
	 *
	 * @return the commerce bom folder persistence
	 */
	public CommerceBOMFolderPersistence getCommerceBOMFolderPersistence() {
		return commerceBOMFolderPersistence;
	}

	/**
	 * Sets the commerce bom folder persistence.
	 *
	 * @param commerceBOMFolderPersistence the commerce bom folder persistence
	 */
	public void setCommerceBOMFolderPersistence(
		CommerceBOMFolderPersistence commerceBOMFolderPersistence) {

		this.commerceBOMFolderPersistence = commerceBOMFolderPersistence;
	}

	/**
	 * Returns the commerce bom folder application rel local service.
	 *
	 * @return the commerce bom folder application rel local service
	 */
	public
		com.liferay.commerce.bom.service.
			CommerceBOMFolderApplicationRelLocalService
				getCommerceBOMFolderApplicationRelLocalService() {

		return commerceBOMFolderApplicationRelLocalService;
	}

	/**
	 * Sets the commerce bom folder application rel local service.
	 *
	 * @param commerceBOMFolderApplicationRelLocalService the commerce bom folder application rel local service
	 */
	public void setCommerceBOMFolderApplicationRelLocalService(
		com.liferay.commerce.bom.service.
			CommerceBOMFolderApplicationRelLocalService
				commerceBOMFolderApplicationRelLocalService) {

		this.commerceBOMFolderApplicationRelLocalService =
			commerceBOMFolderApplicationRelLocalService;
	}

	/**
	 * Returns the commerce bom folder application rel persistence.
	 *
	 * @return the commerce bom folder application rel persistence
	 */
	public CommerceBOMFolderApplicationRelPersistence
		getCommerceBOMFolderApplicationRelPersistence() {

		return commerceBOMFolderApplicationRelPersistence;
	}

	/**
	 * Sets the commerce bom folder application rel persistence.
	 *
	 * @param commerceBOMFolderApplicationRelPersistence the commerce bom folder application rel persistence
	 */
	public void setCommerceBOMFolderApplicationRelPersistence(
		CommerceBOMFolderApplicationRelPersistence
			commerceBOMFolderApplicationRelPersistence) {

		this.commerceBOMFolderApplicationRelPersistence =
			commerceBOMFolderApplicationRelPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService
		getCounterLocalService() {

		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService
			counterLocalService) {

		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the class name local service.
	 *
	 * @return the class name local service
	 */
	public com.liferay.portal.kernel.service.ClassNameLocalService
		getClassNameLocalService() {

		return classNameLocalService;
	}

	/**
	 * Sets the class name local service.
	 *
	 * @param classNameLocalService the class name local service
	 */
	public void setClassNameLocalService(
		com.liferay.portal.kernel.service.ClassNameLocalService
			classNameLocalService) {

		this.classNameLocalService = classNameLocalService;
	}

	/**
	 * Returns the class name persistence.
	 *
	 * @return the class name persistence
	 */
	public ClassNamePersistence getClassNamePersistence() {
		return classNamePersistence;
	}

	/**
	 * Sets the class name persistence.
	 *
	 * @param classNamePersistence the class name persistence
	 */
	public void setClassNamePersistence(
		ClassNamePersistence classNamePersistence) {

		this.classNamePersistence = classNamePersistence;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.kernel.service.ResourceLocalService
		getResourceLocalService() {

		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.kernel.service.ResourceLocalService
			resourceLocalService) {

		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.kernel.service.UserLocalService
		getUserLocalService() {

		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.kernel.service.UserLocalService userLocalService) {

		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register(
			"com.liferay.commerce.bom.model.CommerceBOMDefinition",
			commerceBOMDefinitionLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.commerce.bom.model.CommerceBOMDefinition");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return CommerceBOMDefinitionLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return CommerceBOMDefinition.class;
	}

	protected String getModelClassName() {
		return CommerceBOMDefinition.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				commerceBOMDefinitionPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(
				dataSource, sql);

			sqlUpdate.update();
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
	}

	@BeanReference(type = CommerceBOMDefinitionLocalService.class)
	protected CommerceBOMDefinitionLocalService
		commerceBOMDefinitionLocalService;

	@BeanReference(type = CommerceBOMDefinitionPersistence.class)
	protected CommerceBOMDefinitionPersistence commerceBOMDefinitionPersistence;

	@BeanReference(
		type = com.liferay.commerce.bom.service.CommerceBOMEntryLocalService.class
	)
	protected com.liferay.commerce.bom.service.CommerceBOMEntryLocalService
		commerceBOMEntryLocalService;

	@BeanReference(type = CommerceBOMEntryPersistence.class)
	protected CommerceBOMEntryPersistence commerceBOMEntryPersistence;

	@BeanReference(
		type = com.liferay.commerce.bom.service.CommerceBOMFolderLocalService.class
	)
	protected com.liferay.commerce.bom.service.CommerceBOMFolderLocalService
		commerceBOMFolderLocalService;

	@BeanReference(type = CommerceBOMFolderPersistence.class)
	protected CommerceBOMFolderPersistence commerceBOMFolderPersistence;

	@BeanReference(
		type = com.liferay.commerce.bom.service.CommerceBOMFolderApplicationRelLocalService.class
	)
	protected
		com.liferay.commerce.bom.service.
			CommerceBOMFolderApplicationRelLocalService
				commerceBOMFolderApplicationRelLocalService;

	@BeanReference(type = CommerceBOMFolderApplicationRelPersistence.class)
	protected CommerceBOMFolderApplicationRelPersistence
		commerceBOMFolderApplicationRelPersistence;

	@ServiceReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.ClassNameLocalService.class
	)
	protected com.liferay.portal.kernel.service.ClassNameLocalService
		classNameLocalService;

	@ServiceReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.ResourceLocalService.class
	)
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.UserLocalService.class
	)
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;

	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry
		persistedModelLocalServiceRegistry;

}