/**
 * 
 */
package com.fhzz.business.dao.demo.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.fhzz.business.dao.demo.DatabaseOperationExampleDao;
import com.fhzz.business.entity.DemoTable;
import com.fhzz.business.vo.datagrid.DatagridDemoParam;
import com.fhzz.core.dao.BaseDaoImpl;
import com.fhzz.core.vo.PageParam;
import com.fhzz.core.vo.PageResult;

/**
 * @author: YangYi
 * @CreateTime: 2018年2月11日 下午1:36:35
 * @Copyright: FHZZ
 */
@Repository
public class DatabaseOperationExampleDaoImpl extends BaseDaoImpl<DemoTable>
		implements DatabaseOperationExampleDao {

	@Override
	public PageResult<DemoTable> queryDemoTable(PageParam pageParam) {
		String sql = "SELECT * FROM DEMO_TABLE WHERE NAME = ?";
		PageResult<DemoTable> page = this.getJdbcTemplate().pagedQuery(sql,
				DemoTable.class, pageParam, "name");
		return page;
	}

	@Override
	public PageResult<DemoTable> queryDemoTable(
			DatagridDemoParam datagridDemoParam) {
		List<Object> sqlArgs = new ArrayList<Object>();
		StringBuffer sqlSb = new StringBuffer();
		sqlSb.append("SELECT * FROM DEMO_TABLE WHERE 1=1 ");
		if (datagridDemoParam.getStartTime() != null) {
			sqlSb.append("AND CREATE_TIME > ? ");
			sqlArgs.add(datagridDemoParam.getStartTime());
		}
		if (datagridDemoParam.getEndTime() != null) {
			sqlSb.append("AND CREATE_TIME < ? ");
			sqlArgs.add(datagridDemoParam.getEndTime());
		}
		if (!StringUtils.isEmpty(datagridDemoParam.getName())) {
			sqlSb.append("AND NAME LIKE ? ");
			sqlArgs.add("%" + datagridDemoParam.getName() + "%");
		}
		if (datagridDemoParam.getCount() != null) {
			sqlSb.append("AND COUNT = ? ");
			sqlArgs.add(datagridDemoParam.getCount());
		}
		sqlSb.append("ORDER BY CREATE_TIME DESC");
		PageResult<DemoTable> page = this.getJdbcTemplate().pagedQuery(
				sqlSb.toString(), sqlArgs.toArray(), DemoTable.class,
				datagridDemoParam);
		return page;
	}
}
