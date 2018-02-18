/**
 * 
 */
package com.fhzz.business.service.db;

import com.fhzz.business.entity.DemoTable;
import com.fhzz.business.vo.datagrid.DatagridDemoParam;
import com.fhzz.core.vo.PageResult;
import com.fhzz.core.vo.PageParam;

/**
 * @author: YangYi
 * @CreateTime: 2018年2月11日 下午2:21:33
 * @Copyright: FHZZ
 */
public interface JdbcDemoService {
	public void saveDemoTable(DemoTable demoTable);
	
	public void updateDemoTable(DemoTable demoTable);
	
	public DemoTable getDemoTable(String id);

	public PageResult<DemoTable> pagedQuery(PageParam pageParam);
	
	public PageResult<DemoTable> pagedQuery(DatagridDemoParam datagridDemoParam);
	
}
