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
import org.springframework.stereotype.Component;

import com.fhzz.business.entity.DemoTable;
import com.fhzz.business.service.db.HibernateDemoService;
import com.fhzz.core.quartz.job.AbstractJob;

/**
 * @author: YangYi
 * @CreateTime: 2018年2月11日 下午1:32:32
 * @Copyright: FHZZ
 */
@Component
public class DatabaseExampleJob extends AbstractJob {
	Log logger = LogFactory.getLog(DatabaseExampleJob.class);

	@Autowired
	private HibernateDemoService hibernateDemoService;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		DemoTable demoTable = new DemoTable(null, "name", 1, new Date(), new Date(), new Date());
		hibernateDemoService.saveDemoTable(demoTable);
	}

}
