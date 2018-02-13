package com.fhzz.core.log.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fhzz.core.log.dao.LogRecordDao;
import com.fhzz.core.log.entity.LogRecord;

@Service
public class LogRecordService {
	@Autowired
	private LogRecordDao logRecordDao;

	@Transactional
	public void saveLogRecord(LogRecord logRecord) {
		logRecordDao.saveLogRecord(logRecord);
	}
}
