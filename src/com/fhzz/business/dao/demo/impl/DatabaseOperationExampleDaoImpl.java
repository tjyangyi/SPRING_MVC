/**
 * 
 */
package com.fhzz.business.dao.demo.impl;

import org.springframework.stereotype.Repository;

import com.fhzz.business.dao.demo.DatabaseOperationExampleDao;
import com.fhzz.business.entity.DemoTable;
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
}
