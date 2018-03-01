/**
 * 
 */
package com.fhzz.business.dao.db;

import java.util.List;

import com.fhzz.business.entity.DemoTable;
import com.fhzz.business.vo.datagrid.DatagridDemoParam;
import com.fhzz.core.dao.BaseDao;
import com.fhzz.core.vo.PageResult;
import com.fhzz.core.vo.PageParam;

/**
 * @author: YangYi
 * @CreateTime: 2018年2月11日 下午1:36:15
 * @Copyright: FHZZ
 */
public interface JdbcDemoDao extends BaseDao<DemoTable> {
	void batchSaveDemoTable(final List<DemoTable> eneityList);

	PageResult<DemoTable> pagedQuery(PageParam pageParam);

	PageResult<DemoTable> pagedQuery(DatagridDemoParam datagridDemoParam);

}
