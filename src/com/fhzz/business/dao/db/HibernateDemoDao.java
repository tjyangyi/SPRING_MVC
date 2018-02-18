/**
 * 
 */
package com.fhzz.business.dao.db;

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
	PageResult<DemoTable> queryDemoTable(PageParam pageParam);

	PageResult<DemoTable> queryDemoTable(DatagridDemoParam datagridDemoParam);
}
