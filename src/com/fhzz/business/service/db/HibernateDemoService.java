/**
 * 
 */
package com.fhzz.business.service.db;

import java.util.List;

import com.fhzz.business.entity.DemoTable;
import com.fhzz.business.vo.datagrid.DatagridDemoParam;
import com.fhzz.core.vo.PageResult;
import com.fhzz.core.vo.PageParam;

/**
 * @author: YangYi
 * @CreateTime: 2018年2月11日 下午2:21:33
 * @Copyright: FHZZ
 */
public interface HibernateDemoService {
	void saveDemoTable(DemoTable demoTable);

	void updateDemoTable(DemoTable demoTable);
	
	void saveOrUpdateDemoTable(DemoTable demoTable);
	
	void batchSaveDemoTable(List<DemoTable> demoTableList);

	DemoTable getDemoTable(String id);

	PageResult<DemoTable> pagedQuery(PageParam pageParam);

	PageResult<DemoTable> pagedQuery(DatagridDemoParam datagridDemoParam);

	List<DemoTable> findBy(String propertyName, Object value);

	List<DemoTable> findBy(String propertyName, Object value,String orderBy,boolean isAsc);

	List<DemoTable> findByValues(String propertyName, Object[] values);
	
	
}
