package com.fhzz.core.dao.impl.support;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.HibernateTemplate;

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

	@SuppressWarnings("unchecked")
	public <T> PageResult<T> pagedQuery(String hql, Object[] values, PageParam pageParam, Boolean... cacheable) {
		String countQueryString = " select count(*) " + removeSelect(removeOrders(hql));
		Long total = (Long) super.find(countQueryString, values).get(0);
		Query query = getSession().createQuery(hql);
		if (cacheable.length > 0 && cacheable[0] != null) {
			query.setCacheable(cacheable[0]);
		}
		setParameters(query, values);
		query.setFirstResult(pageParam.getStartIndex()).setMaxResults(pageParam.getRows());
		List<T> list = query.list();
		return new PageResult<T>(total, list);
	}

	/*------------------------------------------Criteria方式------------------------------------------*/
	/**
	 * 根据属性名和属性值查询对象.
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findBy(Class<T> entityClass, String propertyName, Object value) {
		return createCriteria(entityClass, Restrictions.eq(propertyName, value)).list();
	}

	/**
	 * 根据属性名和属性值查询对象,带排序参数.
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findBy(Class<T> entityClass, String propertyName, Object value, String orderBy, boolean isAsc) {
		return createCriteria(entityClass, orderBy, isAsc, Restrictions.eq(propertyName, value)).list();
	}

	/**
	 * 根据实体某项属性名及其取值数组查询<br>
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findByValues(Class<T> entityClass, String propertyName, Object[] values) {
		DetachedCriteria dthCriteria = DetachedCriteria.forClass(entityClass);
		Criterion[] criterions = new Criterion[values.length];// 构建条件数组
		for (int i = 0; i < values.length; i++) {
			criterions[i] = Restrictions.eq(propertyName, values[i]);
		}
		dthCriteria.add(Restrictions.or(criterions));// 添加OR条件
		return (List<T>) super.findByCriteria(dthCriteria);
	}

	/**
	 * 根据属性名和属性值查询唯一对象.
	 */
	@SuppressWarnings("unchecked")
	public <T> T findUniqueBy(Class<T> entityClass, String propertyName, Object value) {
		return (T) createCriteria(entityClass, Restrictions.eq(propertyName, value)).uniqueResult();
	}

	/*------------------------------------------内部辅助方法------------------------------------------*/

	/**
	 * 创建Criteria对象.
	 */
	private Criteria createCriteria(Class<?> entityClass, Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}

	/**
	 * 创建Criteria对象，带排序字段与升降序字段.
	 */
	private Criteria createCriteria(Class<?> entityClass, String orderBy, boolean isAsc, Criterion... criterions) {
		Criteria criteria = createCriteria(entityClass, criterions);
		if (isAsc)
			criteria.addOrder(Order.asc(orderBy));
		else
			criteria.addOrder(Order.desc(orderBy));
		return criteria;
	}

	/**
	 * 设置HQL语句中的参数
	 */
	private void setParameters(Query query, Object[] values) {
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
	 */
	private String removeSelect(String hql) {
		int beginPosition = hql.toLowerCase().indexOf("from");
		return hql.substring(beginPosition);
	}

	/**
	 * 去除hql的orderby 子句，用于pagedQuery.
	 */
	private String removeOrders(String hql) {
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(hql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
