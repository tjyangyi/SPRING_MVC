/**
 * 
 */
package com.fhzz.business.dao.db;

import java.util.List;

import com.fhzz.business.entity.DemoTable;
import com.fhzz.business.vo.datagrid.DatagridDemoParam;
import com.fhzz.core.dao.BaseDao;
import com.fhzz.core.vo.PageParam;
import com.fhzz.core.vo.PageResult;

/**
 * @author YangYi
 * 
 */
public interface HibernateDemoDao extends BaseDao<DemoTable> {
	PageResult<DemoTable> pagedQuery(PageParam pageParam);

	PageResult<DemoTable> pagedQuery(DatagridDemoParam datagridDemoParam);
	
	List<DemoTable> findBy(String propertyName, Object value);
	
	List<DemoTable> findBy(String propertyName, Object value,String orderBy,boolean isAsc);

	List<DemoTable> findByValues(String propertyName, Object[] values);
}
