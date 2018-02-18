package com.fhzz.core.dao;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import com.fhzz.core.vo.PageParam;
import com.fhzz.core.vo.PageResult;

/**
 * @FileName : (HibernateTemplateSupport.java)
 * 
 * @description : 这里用一句话描述这个类的作用
 * @author : xx
 * @version : Version No.1
 * @create : 2015-6-1 下午01:27:32
 * @modify : 2015-6-1 下午01:27:32
 * @copyright : FiberHome FHZ Telecommunication Technologies Co.Ltd.
 */
public class HibernateTemplateSupport extends HibernateTemplate {

	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/*------------------------------------------HQL方式------------------------------------------*/
	/**
	 * 默认建议使用分页，带Count方式
	 * 
	 * @Title: pagedQuery
	 * @Description: 分页查询函数，使用hql.
	 * @param hql
	 * @param pageNo
	 *            页号,从1开始.
	 * @param pageSize
	 *            每页大小
	 * @param values
	 *            HQL传入参数值
	 * @return: Page
	 * @throws
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageResult pagedQuery(String hql, int pageNo, int pageSize,
			Object... values) {
		PageParam pageParam = new PageParam(pageNo, pageSize);
		String countQueryString = " select count(*) "
				+ removeSelect(removeOrders(hql));
		List<?> countlist = super.find(countQueryString, values);
		super.findByValueBean(queryString, valueBean);
		super.findByNamedQueryAndNamedParam(queryName, paramNames, values)
		if (countlist != null && countlist.size() > 0) {
			long totalCount = (Long) countlist.get(0);
			if (totalCount < 1)
				return new PageResult();
			int startIndex = pageParam.getStartIndex();
			List<?> list = list(hql, startIndex, pageSize, values);
			return new PageResult(totalCount, list);
		} else {
			return new PageResult();
		}
	}

	/**
	 * 
	 * @Description: 分页条件查询 带参数
	 * @param: @param hql
	 * @param: @param pn 数据记录起止位置
	 * @param: @param pageSize 每页大小
	 * @param: @param values HQL中传入参数值
	 * @param: @return
	 * @return: List<?>
	 * @throws
	 */
	public <T> List<T> list(final String hql, final int pn, final int pageSize,
			final Object... values) {
		return list(hql, pn, pageSize, false, values);
	}

	/**
	 * 分页条件查询 对于需要first,max,fetchsize,cache,cacheRegion等诸多设置的函数,可以在返回Query后自行设置.
	 */
	@SuppressWarnings("unchecked")
	private <T> List<T> list(final String hql, final int page, final int rows,
			boolean cacheAble, final Object... values) {
		Assert.hasText(hql);
		Query query = getSession().createQuery(hql);
		setParameters(query, values);
		if (cacheAble)
			query.setCacheable(true);
		if (rows > 0) {
			query.setFirstResult(page).setMaxResults(rows);
		}
		return query.list();
	}

	/**
	 * @Description : 执行HQL语句： insert, update, delete
	 * @param hql
	 * @param paramlist
	 * @return
	 */
	public int execute(final String hql, final Object... values) {
		Query query = getSession().createQuery(hql);
		setParameters(query, values);
		Object result = query.executeUpdate();
		return result == null ? 0 : ((Integer) result).intValue();
	}

	/*------------------------------------------Criteria方式------------------------------------------*/
	/**
	 * 根据属性名和属性值查询对象.
	 * 
	 * @return 符合条件的对象列表
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findBy(Class<T> entityClass, String propertyName,
			Object value) {
		Assert.hasText(propertyName);
		return createCriteria(entityClass, Restrictions.eq(propertyName, value))
				.list();
	}

	/**
	 * 根据属性名和属性值查询对象,带排序参数.
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findBy(Class<T> entityClass, String propertyName,
			Object value, String orderBy, boolean isAsc) {
		Assert.hasText(propertyName);
		Assert.hasText(orderBy);
		return createCriteria(entityClass, orderBy, isAsc,
				Restrictions.eq(propertyName, value)).list();
	}

	/**
	 * 根据实体某项属性名及其取值数组查询<br>
	 * 例如：根据ID数组获取<b>勾选</b>对象
	 * 
	 * @param entityClass
	 *            实体class
	 * @param propertyName
	 *            属性名
	 * @param values
	 *            属性取值
	 * @return
	 */
	public List<?> findByValues(Class<?> entityClass, String propertyName,
			Object[] values) {
		Assert.hasText(propertyName);
		Assert.notEmpty(values);
		DetachedCriteria dthCriteria = DetachedCriteria.forClass(entityClass);
		// 构建条件数组
		Criterion[] criterions = new Criterion[values.length];
		for (int i = 0; i < values.length; i++) {
			criterions[i] = Restrictions.eq(propertyName, values[i]);
		}
		// 添加OR条件
		dthCriteria.add(Restrictions.or(criterions));
		return super.findByCriteria(dthCriteria);
	}

	/**
	 * 根据属性名和属性值查询唯一对象.
	 * 
	 * @return 符合条件的唯一对象 or null if not found.
	 */
	@SuppressWarnings("unchecked")
	public <T> T findUniqueBy(Class<T> entityClass, String propertyName,
			Object value) {
		Assert.hasText(propertyName);
		return (T) createCriteria(entityClass,
				Restrictions.eq(propertyName, value)).uniqueResult();
	}

	/**
	 * 判断对象某些属性的值在数据库中是否唯一.
	 * 
	 * @param uniquePropertyNames
	 *            在POJO里不能重复的属性列表,以逗号分割 如"name,loginid,password"
	 */
	public <T> boolean isUnique(Class<T> entityClass, Object entity,
			String uniquePropertyNames) {
		Assert.hasText(uniquePropertyNames);
		Criteria criteria = createCriteria(entityClass).setProjection(
				Projections.rowCount());
		String[] nameList = uniquePropertyNames.split(",");
		try {
			for (String name : nameList) {
				criteria.add(Restrictions.eq(name,
						PropertyUtils.getProperty(entity, name)));
			}
			// 以下代码为了如果是update的情况,排除entity自身.
			String idName = getIdName(entityClass);
			// 取得entity的主键值
			Serializable id = getId(entityClass, entity);
			// 如果id!=null,说明对象已存在,该操作为update,加入排除自身的判断
			if (id != null)
				criteria.add(Restrictions.not(Restrictions.eq(idName, id)));
		} catch (Exception e) {
			ReflectionUtils.handleReflectionException(e);
		}
		// count结果通常为Long，非空
		return ((Number) criteria.uniqueResult()).intValue() == 0;
	}

	/**
	 * 获取全部对象,带排序字段与升降序参数.
	 */
	public List<?> getAll(Class<?> entityClass, String orderBy, boolean isAsc) {
		Assert.hasText(orderBy);
		if (isAsc)
			return super.findByCriteria(DetachedCriteria.forClass(entityClass)
					.addOrder(Order.asc(orderBy)));
		else
			return super.findByCriteria(DetachedCriteria.forClass(entityClass)
					.addOrder(Order.desc(orderBy)));
	}

	/*------------------------------------------内部辅助方法------------------------------------------*/

	/**
	 * 创建Criteria对象.
	 * 
	 * @param criterions
	 *            可变的Restrictions条件列表,见{@link #createQuery(String,Object...)}
	 */
	public Criteria createCriteria(Class<?> entityClass,
			Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}

	/**
	 * 创建Criteria对象，带排序字段与升降序字段.
	 * 
	 * @see #createCriteria(Class,Criterion[])
	 */
	private Criteria createCriteria(Class<?> entityClass, String orderBy,
			boolean isAsc, Criterion... criterions) {
		Assert.hasText(orderBy);
		Criteria criteria = createCriteria(entityClass, criterions);
		if (isAsc)
			criteria.addOrder(Order.asc(orderBy));
		else
			criteria.addOrder(Order.desc(orderBy));
		return criteria;
	}

	/**
	 * 设置HQL语句中的参数
	 * 
	 * @param query
	 * @param paramlist
	 */
	private void setParameters(Query query, Object... values) {
		if (null == values || values.length < 0)
			return;
		for (int i = 0; i < values.length; i++) {
			if (values[i] instanceof Date)
				query.setTimestamp(i, (Date) values[i]);
			else
				query.setParameter(i, values[i]);
		}
	}

	/**
	 * 去除hql的select 子句，未考虑union的情况,用于pagedQuery.
	 * 
	 * @see #pagedQuery(String,int,int,Object[])
	 */
	private static String removeSelect(String hql) {
		Assert.hasText(hql);
		int beginPos = hql.toLowerCase().indexOf("from");
		Assert.isTrue(beginPos != -1, " hql : " + hql
				+ " must has a keyword 'from'");
		return hql.substring(beginPos);
	}

	/**
	 * 去除hql的orderby 子句，用于pagedQuery.
	 * 
	 * @see #pagedQuery(String,int,int,Object[])
	 */
	private static String removeOrders(String hql) {
		Assert.hasText(hql);
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*",
				Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(hql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}

	/**
	 * 取得对象的主键名,辅助函数.
	 */
	private String getIdName(Class<?> clazz) {
		Assert.notNull(clazz);
		ClassMetadata meta = getSessionFactory().getClassMetadata(clazz);
		Assert.notNull(meta, "Class " + clazz
				+ " not define in hibernate session factory.");
		String idName = meta.getIdentifierPropertyName();
		Assert.hasText(idName, clazz.getSimpleName()
				+ " has no identifier property define.");
		return idName;
	}

	/**
	 * 取得对象的主键值,辅助函数.
	 */
	private Serializable getId(Class<?> entityClass, Object entity)
			throws NoSuchMethodException, IllegalAccessException,
			InvocationTargetException {
		Assert.notNull(entity);
		Assert.notNull(entityClass);
		return (Serializable) PropertyUtils.getProperty(entity,
				getIdName(entityClass));
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
