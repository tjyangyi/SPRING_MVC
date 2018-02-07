/**
 * 
 */
package com.fhzz.core.dao.log;

import org.springframework.stereotype.Repository;

import com.fhzz.core.dao.DaoTemplate;
import com.fhzz.core.entity.LogRecord;
import com.fhzz.core.utils.Result;

/**
 * @author YangYi
 * 
 */
@Repository
public class LogRecordDao extends DaoTemplate {

	public Result saveLogRecord(LogRecord logRecord) {
		getHibernateTemplate().save(logRecord);
		return Result.getSuccessInstance();
	}

}
