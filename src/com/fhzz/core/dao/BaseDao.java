/**
 * 
 */
package com.fhzz.core.dao;

import java.io.Serializable;

/**
 * @author: YangYi
 * @CreateTime: 2018年2月11日 下午2:45:23
 * @Copyright: FHZZ
 */
public interface BaseDao<T> {
	public void save(T entity);

	public void update(T entity);

	public void saveOrUpdate(T entity);

	public T get(Serializable id);
}
