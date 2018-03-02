package com.fhzz.business.service.db.impl;

import java.util.List;

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
		hibernateDemoDao.update(demoTable);
	}
	
	@Override
	public void saveOrUpdateDemoTable(DemoTable demoTable) {
		hibernateDemoDao.saveOrUpdate(demoTable);
	}
	
	@Override
	public void batchSaveDemoTable(List<DemoTable> demoTableList) {
		hibernateDemoDao.batchSave(demoTableList);
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

	@Override
	public List<DemoTable> findBy(String propertyName, Object value) {
		return hibernateDemoDao.findBy(propertyName, value);
	}
	
	@Override
	public List<DemoTable> findBy(String propertyName, Object value,String orderBy,boolean isAsc){
		return hibernateDemoDao.findBy( propertyName, value, orderBy, isAsc);
	}

	@Override
	public List<DemoTable> findByValues(String propertyName, Object[] values) {
		return hibernateDemoDao.findByValues(propertyName, values);
	}

}
