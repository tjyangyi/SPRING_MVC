/**
 * 
 */
package com.fhzz.business.service.atomikos.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fhzz.business.dao.atomikos.AtomikosDao;
import com.fhzz.business.dao.atomikos.AtomikosMysqlDao;
import com.fhzz.business.entity.Welcome;
import com.fhzz.business.entity.mysql.MysqlTableTest;
import com.fhzz.business.service.atomikos.AtomikosService;
import com.fhzz.core.utils.IDUtils;

/**
 * @author YangYi
 *
 */
@Service
public class AtomikosServiceImpl implements AtomikosService{
	@Autowired
	private AtomikosDao atomikosDao;
	
	@Autowired
	private AtomikosMysqlDao atomikosMysqlDao;
	
	public void saveOracle(){
		Welcome welcome = new Welcome(IDUtils.getUUID(), "123", "123");
		atomikosDao.save(welcome);
	}

	@Override
	public void saveMysql() {
		MysqlTableTest t = new MysqlTableTest(IDUtils.getUUID(),"name1");
		atomikosMysqlDao.saveToMysql(t);
	}

	@Override
	public void saveBoth() {
		Welcome welcome = new Welcome(IDUtils.getUUID(), "123", "123");
		atomikosDao.save(welcome);
		MysqlTableTest t = new MysqlTableTest(IDUtils.getUUID(),"name2");
		atomikosMysqlDao.saveToMysql(t);
	}

	@Override
	public void saveBothWithOracleException() {
		MysqlTableTest t = new MysqlTableTest(IDUtils.getUUID(),"name3");
		atomikosMysqlDao.saveToMysql(t);
		Welcome welcome = new Welcome(null, "123", "123");
		atomikosDao.save(welcome);
	}

	@Override
	public void saveBothWithMysqlException() {
		Welcome welcome = new Welcome(IDUtils.getUUID(), "123", "123");
		atomikosDao.save(welcome);
		MysqlTableTest t = new MysqlTableTest(null,"name3");
		atomikosMysqlDao.saveToMysql(t);
	}
	
	
}
