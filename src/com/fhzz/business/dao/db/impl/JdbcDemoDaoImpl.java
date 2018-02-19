/**
 * 
 */
package com.fhzz.business.dao.db.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.fhzz.business.dao.db.JdbcDemoDao;
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
public class JdbcDemoDaoImpl extends BaseDaoImpl<DemoTable> implements JdbcDemoDao {

	@Override
	public PageResult<DemoTable> pagedQuery(PageParam pageParam) {
		String sql = "SELECT * FROM DEMO_TABLE WHERE NAME = ?";
		PageResult<DemoTable> page = this.getJdbcTemplate().pagedQuery(sql, DemoTable.class, pageParam, "name");
		return page;
	}

	@Override
	public PageResult<DemoTable> pagedQuery(DatagridDemoParam datagridDemoParam) {
		List<Object> sqlArgs = new ArrayList<Object>();
		StringBuffer sqlSb = new StringBuffer();
		sqlSb.append("SELECT * FROM DEMO_TABLE WHERE 1=1 ");
		if (datagridDemoParam.getStartTime() != null) {
			sqlSb.append("AND CUSTOM_TIME > ? ");
			sqlArgs.add(datagridDemoParam.getStartTime());
		}
		if (datagridDemoParam.getEndTime() != null) {
			sqlSb.append("AND CUSTOM_TIME < ? ");
			sqlArgs.add(datagridDemoParam.getEndTime());
		}
		if (!StringUtils.isEmpty(datagridDemoParam.getName())) {
			sqlSb.append("AND NAME LIKE ? ");
			sqlArgs.add("%" + datagridDemoParam.getName() + "%");
		}
		if (datagridDemoParam.getCountNum() != null) {
			sqlSb.append("AND COUNT_NUM = ? ");
			sqlArgs.add(datagridDemoParam.getCountNum());
		}
		sqlSb.append("ORDER BY UPDATE_TIME DESC");
		PageResult<DemoTable> page = this.getJdbcTemplate().pagedQuery(sqlSb.toString(), sqlArgs.toArray(),
				DemoTable.class, datagridDemoParam);
		return page;
	}
}
