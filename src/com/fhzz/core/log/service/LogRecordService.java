package com.fhzz.core.log.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fhzz.core.log.dao.LogRecordDao;
import com.fhzz.core.log.entity.LogRecord;

@Service
public class LogRecordService {
	Log logger = LogFactory.getLog(LogRecordService.class);

	@Autowired
	private LogRecordDao logRecordDao;

	@Transactional
	public void saveLogRecord(LogRecord logRecord) {
		logRecordDao.saveLogRecord(logRecord);
	}
}
