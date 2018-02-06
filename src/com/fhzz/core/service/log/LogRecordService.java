package com.fhzz.core.service.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fhzz.core.dao.log.LogRecordDao;
import com.fhzz.core.entity.LogRecord;
import com.fhzz.core.utils.Result;

@Service
public class LogRecordService {
	@Autowired
	private LogRecordDao logRecordDao;

	@Transactional
	public Result saveOrUpdateLogRecord(LogRecord logRecord) {
		return logRecordDao.saveOrUpdateLogRecord(logRecord);
	}
}
