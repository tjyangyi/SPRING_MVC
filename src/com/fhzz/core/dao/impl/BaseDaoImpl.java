package com.fhzz.core.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;

import com.fhzz.core.dao.BaseDao;
import com.fhzz.core.dao.impl.support.HibernateTemplateSupport;
import com.fhzz.core.dao.impl.support.JdbcTemplageSupport;
import com.fhzz.core.hbase.dao.HBaseTemplateSupport;

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
	
	@Resource(name = "jdbcTemplate2")
	private JdbcTemplageSupport jdbcTemplate2;// 注入MYSQL的JDBC模板

	@Resource(name = "hibernateTemplate2")
	private HibernateTemplateSupport hibernateTemplate2;// 注入MYSQL的hibernate模板
	
	@Resource(name = "hbaseTemplate")
	private HBaseTemplateSupport hbaseTemplate;//注入HBASE模板

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
	public void saveToMysql(T entity) {
		hibernateTemplate2.save(entity);
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
	
	@Override
	public void batchSave(final List<T> entityList) {
		getHibernateTemplate().execute(new HibernateCallback<T>() {
			@Override
			public T doInHibernate(Session session) throws HibernateException {
				for (int i = 0; i < entityList.size(); i++) {
					T t = entityList.get(i);
					session.save(t);
					if (i % 100 == 0) {// 将数据刷入数据库并清空session
						session.flush();
						session.clear();
					}
				}
				return null;
			}
		});
	}
	

	public JdbcTemplageSupport getJdbcTemplate() {
		return jdbcTemplate;
	}

	public HibernateTemplateSupport getHibernateTemplate() {
		return hibernateTemplate;
	}

	public HBaseTemplateSupport getHbaseTemplate() {
		return hbaseTemplate;
	}
	
}
