/**
 * 
 */
package com.fhzz.business.dao.demo.impl;

import org.springframework.stereotype.Repository;

import com.fhzz.business.dao.demo.DatabaseOperationExampleDao;
import com.fhzz.business.entity.DemoTable;
import com.fhzz.business.vo.datagrid.DatagridDemoParam;
import com.fhzz.core.dao.BaseDaoImpl;
import com.fhzz.core.vo.PageResult;
import com.fhzz.core.vo.PageParam;

/**
 * @author: YangYi
 * @CreateTime: 2018年2月11日 下午1:36:35
 * @Copyright: FHZZ
 */
@Repository
public class DatabaseOperationExampleDaoImpl extends BaseDaoImpl<DemoTable> implements DatabaseOperationExampleDao {

	@Override
	public PageResult<DemoTable> queryDemoTable(PageParam pageParam) {
		String sql = "SELECT * FROM DEMO_TABLE WHERE NAME = ?";
		PageResult<DemoTable> page = this.getJdbcTemplate().pagedQuery(sql, DemoTable.class, pageParam, "name");
		return page;
	}

	@Override
	public PageResult<DemoTable> queryDemoTable(DatagridDemoParam datagridDemoParam) {
		StringBuffer sqlSb = new StringBuffer();
		sqlSb.append("SELECT * FROM DEMO_TABLE WHERE 1=1 ");
		if (datagridDemoParam.getStartTime() != null) {
			sqlSb.append("AND CREATE_TIME > ?");
		}
		if (datagridDemoParam.getEndTime() != null) {
			sqlSb.append("AND CREATE_TIME < ?");
		}
		PageResult<DemoTable> page = this.getJdbcTemplate().pagedQuery(sqlSb.toString(), DemoTable.class,
				datagridDemoParam, datagridDemoParam.getStartTime(), datagridDemoParam.getEndTime());
		return page;
	}
}
