/**
 * 
 */
package com.fhzz.business.dao.atomikos.impl;

import org.springframework.stereotype.Repository;

import com.fhzz.business.dao.atomikos.AtomikosMysqlDao;
import com.fhzz.business.entity.mysql.MysqlTableTest;
import com.fhzz.core.dao.impl.BaseDaoImpl;

/**
 * @author YangYi
 * 
 */
@Repository
public class AtomikosMysqlDaoImpl extends BaseDaoImpl<MysqlTableTest> implements
		AtomikosMysqlDao {

}
