package com.fhzz.core.dao;

import java.io.Serializable;
import java.util.List;

/**
 * @author: YangYi
 * @CreateTime: 2018年2月11日 下午2:45:23
 * @Copyright: FHZZ
 */
public interface BaseDao<T> {
	public void save(T entity);

	public void update(T entity);

	public void saveOrUpdate(T entity);

	/**
	 * 根据ID获取entity
	 */
	public T get(Serializable id);

	/**
	 *批量保存 
	 */
	public void batchSave(List<T> entityList);
	
	/**
	 * 保存到mysql数据库
	 */
	public void saveToMysql(T entity);
}
