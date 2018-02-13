package com.fhzz.business.service.demo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fhzz.business.dao.demo.DatabaseOperationExampleDao;
import com.fhzz.business.entity.DemoTable;
import com.fhzz.business.service.demo.DatabaseOperationExampleService;
import com.fhzz.core.vo.PageResult;
import com.fhzz.core.vo.PageParam;

/**
 * 
 * @author: YangYi
 * @CreateTime: 2018年2月11日 下午2:22:38
 * @Copyright: FHZZ
 */
@Service
public class DatabaseOperationExampleServiceImpl implements DatabaseOperationExampleService {
	@Autowired
	private DatabaseOperationExampleDao databaseOperationExampleDao;

	@Override
	public void saveDemoTable(DemoTable demoTable) {
		// 这里可以进行一些业务处理
		databaseOperationExampleDao.save(demoTable);
	}

	@Override
	public void updateDemoTable(DemoTable demoTable) {
		DemoTable existDemoTable = databaseOperationExampleDao.get("b580544f-a89a-4544-9f6e-81266f0f29ae");
		existDemoTable.setName(demoTable.getName());
		existDemoTable.setCount(demoTable.getCount());
		existDemoTable.setCreateTime(demoTable.getCreateTime());
		databaseOperationExampleDao.update(existDemoTable);
	}
	
	@Override
	public DemoTable getDemoTable(String id){
		return databaseOperationExampleDao.get(id);
	}

	@Override
	public PageResult<DemoTable> queryDemoTable(PageParam pageParam) {
		return databaseOperationExampleDao.queryDemoTable(pageParam);
	}

}
