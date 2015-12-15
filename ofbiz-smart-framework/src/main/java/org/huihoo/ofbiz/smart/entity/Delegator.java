package org.huihoo.ofbiz.smart.entity;

import org.huihoo.ofbiz.smart.base.cache.Cache;

import java.sql.Connection;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;



/**
 * <p>
 * 数据库委托代理访问接口
 * </p>
 * 
 * @author huangbaihua
 * @since  1.0
 */
public interface Delegator {
  /**
   *<p>
   *     获取原始的数据库连接
   *</p>
   * @return
   */
  Connection getConnection() throws GenericEntityException;
  /**
   * <p>
   *  使用指定的数据源,实现方应该可以通过指定的名称，切换到该数据源下。以支持多数据源特性。
   * </p>
   * @param name 要使用的数据源名称
   * @return <code>{@link Delegator}</code>本身，返回自己的目的是可以实现接口的链式访问。
   */
  Delegator useDataSource(String name);

  /**
   *<p>
   *     获取当前接口实例的<code>{@link Cache}</code>。<br/>
   *     获取持有的缓存实例，主要是为了能对它进行一些常用操作，比如清空缓存或是查看相关统计数据，如缓存命中率等。<br/>
   *     实现方应该提供一个{@link Cache}的实现，以满足需要缓存数据的接口需求。
   *</p>
   *
   * @return
   */
  Cache getCache();
  //======================================================================================
  //                                 Transaction Operation
  // =====================================================================================

  /**
   * <p>
   *     开启一个事务
   * </p>
   */
  void beginTransaction();

  /**
   * <p>
   *     回滚一个事务
   * </p>
   */
  void rollback();

  /**
   * <p>
   *     提交一个事务
   * </p>
   */
  void commitTransaction();

  /**
   * <p>
   *     结束一个事务
   * </p>
   */
  void endTransaction();

  /**
   *
   * @param txRunnable
   */
  void executeWithInTx(TxRunnable txRunnable);

  /**
   *
   * @param txCallable
   * @return
   */
  Object executeWithInTx(TxCallable txCallable);
  //======================================================================================
  //                                 Entity Save,Update,Remove
  // =====================================================================================
  void save(Collection<?> entities) throws GenericEntityException;  
  void save(Object entity) throws GenericEntityException;
  void update(Collection<?> entities) throws GenericEntityException; 
  void update(Object entity) throws GenericEntityException;
  void remove(Collection<?> entities) throws GenericEntityException; 
  void remove(Object entity) throws GenericEntityException;
  void removeById(Class<?> entityClazz,Object id) throws GenericEntityException;
  
  // =====================================================================================
  //                                 Entity Find Method
  // =====================================================================================
  Object findById(Class<?> entityClazz, Object id) throws GenericEntityException;
  Object findById(Class<?> entityClazz, Object id, boolean useCache) throws GenericEntityException;
  
  List<Object> findIdsByAnd(Class<?> entityClazz, Map<String, Object> andMap) throws GenericEntityException;
  List<Object> findIdsByCond(Class<?> entityClazz, String cond) throws GenericEntityException;
    
  Object findUniqueByAnd(Class<?> entityClazz, Map<String, Object> andMap) throws GenericEntityException;
  Object findUniqueByAnd(Class<?> entityClazz, Map<String, Object> andMap, Set<String> fieldsToSelect,boolean useCache) throws GenericEntityException;

  List<?> findList(Class<?> entityClazz, String cond) throws GenericEntityException;
  List<?> findList(Class<?> entityClazz, String cond,Set<String> fieldsToSelect,List<String> orderBy) throws GenericEntityException;
  List<?> findList(Class<?> entityClazz, String cond,Set<String> fieldsToSelect,List<String> orderBy,boolean useCache) throws GenericEntityException;
  
  Map<String,Object> findPageByAnd(Class<?> entityClazz, Map<String, Object> andMap,int pageNo,int pageSize) throws GenericEntityException;
  Map<String,Object> findPageByAnd(Class<?> entityClazz, Map<String, Object> andMap,int pageNo,int pageSize,Set<String> fieldsToSelect,List<String> orderBy) throws GenericEntityException;
  Map<String,Object> findPageByAnd(Class<?> entityClazz, Map<String, Object> andMap,int pageNo,int pageSize,Set<String> fieldsToSelect,List<String> orderBy,boolean useCache) throws GenericEntityException;

  Map<String,Object> findPageByCond(Class<?> entityClazz, String cond,int pageNo,int pageSize) throws GenericEntityException;
  Map<String,Object> findPageByCond(Class<?> entityClazz, String cond,int pageNo,int pageSize,Set<String> fieldsToSelect,List<String> orderBy) throws GenericEntityException;
  Map<String,Object> findPageByCond(Class<?> entityClazz, String cond,int pageNo,int pageSize,Set<String> fieldsToSelect,List<String> orderBy,boolean useCache) throws GenericEntityException;
    
  int countByAnd(Class<?> entityClazz, Map<String, Object> andMap) throws GenericEntityException;
  int countByCond(Class<?> entityClazz, String cond) throws GenericEntityException;
  
  //======================================================================================
  //                                 Raw Query And Operation
  // =====================================================================================
  List<Map<String, Object>> findListByRawQuery(String query, List<?> params) throws GenericEntityException;
  List<Map<String, Object>> findListByRawQuery(String query, List<?> params,boolean useCache) throws GenericEntityException;
  int executeByRawSql(String sql) throws GenericEntityException;
  int executeByRawSql(String sql,List<?> params) throws GenericEntityException;
  int countByRawQuery(String query,String countAlias) throws GenericEntityException;
  int countByRawQuery(String query,String countAlias,List<?> params) throws GenericEntityException;
}
