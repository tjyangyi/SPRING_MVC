package com.fhzz.business.service.db.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fhzz.business.dao.db.HibernateDemoDao;
import com.fhzz.business.entity.DemoTable;
import com.fhzz.business.service.db.HibernateDemoService;
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
public class HibernateDemoServiceImpl implements HibernateDemoService {
	@Autowired
	private HibernateDemoDao hibernateDemoDao;

	@Override
	public void saveDemoTable(DemoTable demoTable) {
		// 这里可以进行一些业务处理
		hibernateDemoDao.save(demoTable);
	}

	@Override
	public void updateDemoTable(DemoTable demoTable) {
		DemoTable existDemoTable = hibernateDemoDao
				.get("b580544f-a89a-4544-9f6e-81266f0f29ae");
		existDemoTable.setName(demoTable.getName());
		existDemoTable.setCountNum(demoTable.getCountNum());
		existDemoTable.setCreateTime(demoTable.getCreateTime());
		hibernateDemoDao.update(existDemoTable);
	}

	@Override
	public DemoTable getDemoTable(String id) {
		return hibernateDemoDao.get(id);
	}

	@Override
	public PageResult<DemoTable> pagedQuery(PageParam pageParam) {
		return hibernateDemoDao.pagedQuery(pageParam);
	}

	@Override
	public PageResult<DemoTable> pagedQuery(
			DatagridDemoParam datagridDemoParam) {
		return hibernateDemoDao.pagedQuery(datagridDemoParam);
	}


}
