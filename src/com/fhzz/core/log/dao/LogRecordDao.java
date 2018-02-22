/**
 * 
 */
package com.fhzz.core.log.dao;

import org.springframework.stereotype.Repository;

import com.fhzz.core.dao.impl.BaseDaoImpl;
import com.fhzz.core.log.entity.LogRecord;

/**
 * @author YangYi
 * 
 */
@Repository
public class LogRecordDao extends BaseDaoImpl<LogRecord> {

	public void saveLogRecord(LogRecord logRecord) {
		getHibernateTemplate().save(logRecord);
	}

}
