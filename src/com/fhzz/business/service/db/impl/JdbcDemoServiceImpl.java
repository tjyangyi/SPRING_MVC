package com.fhzz.business.service.db.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fhzz.business.dao.db.JdbcDemoDao;
import com.fhzz.business.entity.DemoTable;
import com.fhzz.business.service.db.JdbcDemoService;
import com.fhzz.business.vo.datagrid.DatagridDemoParam;
import com.fhzz.core.vo.PageParam;
import com.fhzz.core.vo.PageResult;

/**
 * 
 * @author: YangYi
 * @CreateTime: 2018年2月11日 下午2:22:38
 * @Copyright: FHZZ
 */
@Service
public class JdbcDemoServiceImpl implements JdbcDemoService {
	@Autowired
	private JdbcDemoDao jdbcDemoDao;

	@Override
	public void saveDemoTable(DemoTable demoTable) {
		// 这里可以进行一些业务处理
		jdbcDemoDao.save(demoTable);
	}

	@Override
	public void updateDemoTable(DemoTable demoTable) {
		jdbcDemoDao.update(demoTable);
	}

	@Override
	public DemoTable getDemoTable(String id) {
		return jdbcDemoDao.get(id);
	}

	@Override
	public PageResult<DemoTable> pagedQuery(PageParam pageParam) {
		return jdbcDemoDao.pagedQuery(pageParam);
	}

	@Override
	public PageResult<DemoTable> pagedQuery(DatagridDemoParam datagridDemoParam) {
		return jdbcDemoDao.pagedQuery(datagridDemoParam);
	}

}
