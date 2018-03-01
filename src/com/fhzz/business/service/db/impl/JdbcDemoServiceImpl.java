package com.fhzz.business.service.db.impl;

import java.util.List;

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
	public void batchSaveDemoTable(List<DemoTable> eneityList) {
		jdbcDemoDao.batchSaveDemoTable(eneityList);
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
