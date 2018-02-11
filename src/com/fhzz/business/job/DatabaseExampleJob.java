/**
 * 
 */
package com.fhzz.business.job;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.fhzz.business.entity.DemoTable;
import com.fhzz.business.service.demo.DatabaseOperationExampleService;
import com.fhzz.core.quartz.job.AbstractJob;

/**
 * @author: YangYi
 * @CreateTime: 2018年2月11日 下午1:32:32
 * @Copyright: FHZZ
 */
public class DatabaseExampleJob extends AbstractJob {
	Log logger = LogFactory.getLog(DatabaseExampleJob.class);

	@Autowired
	private DatabaseOperationExampleService databaseOperationExampleService;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		DemoTable demoTable = new DemoTable(null, "name", 1, new Date());
		databaseOperationExampleService.saveDemoTable(demoTable);

		DemoTable demoTable2 = new DemoTable(null, "name2", 2, new Date());
		databaseOperationExampleService.updateDemoTable(demoTable2);

		DemoTable demoTable3 = databaseOperationExampleService.getDemoTable("62e31883-9abd-42ec-b039-4eb90f38fa21");
		logger.info(demoTable3);
	}

}
