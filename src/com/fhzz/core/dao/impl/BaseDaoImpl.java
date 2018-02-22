package com.fhzz.core.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.annotation.Resource;

import com.fhzz.core.dao.BaseDao;
import com.fhzz.core.dao.impl.support.HibernateTemplateSupport;
import com.fhzz.core.dao.impl.support.JdbcTemplageSupport;

/**
 * 
 * @author: YangYi
 * @CreateTime: 2018年1月29日 下午5:17:45
 * @Copyright: FHZZ
 */
public class BaseDaoImpl<T> implements BaseDao<T> {

	private Class<?> entityClass;// DAO的泛型类,即子类所指定的T所对应的Entity

	@Resource(name = "jdbcTemplate")
	private JdbcTemplageSupport jdbcTemplate;// 注入JDBC模板

	@Resource(name = "hibernateTemplate")
	private HibernateTemplateSupport hibernateTemplate;// 注入hibernate模板

	public BaseDaoImpl() {
		Type t = this.getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		Type actualType = pt.getActualTypeArguments()[0];
		entityClass = (Class<?>) actualType;
	}

	@Override
	public void save(T entity) {
		hibernateTemplate.save(entity);
	}

	@Override
	public void update(T entity) {
		hibernateTemplate.update(entity);
	}

	@Override
	public void saveOrUpdate(T entity) {
		hibernateTemplate.saveOrUpdate(entity);
	}

	@Override
	@SuppressWarnings("unchecked")
	public T get(Serializable id) {
		return (T) hibernateTemplate.get(entityClass, id);
	}

	public JdbcTemplageSupport getJdbcTemplate() {
		return jdbcTemplate;
	}

	public HibernateTemplateSupport getHibernateTemplate() {
		return hibernateTemplate;
	}
}
