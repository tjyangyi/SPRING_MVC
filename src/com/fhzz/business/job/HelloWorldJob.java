/**
 * 
 */
package com.fhzz.business.job;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import com.fhzz.core.quartz.job.AbstractJob;
import com.fhzz.core.sercurity.controller.LoginAction;

/**
 * @author YangYi
 * 
 */
@Component
public class HelloWorldJob extends AbstractJob {
	Log logger = LogFactory.getLog(LoginAction.class);

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		logger.info("This is a first spring combine quartz !");
		logger.info("Welcome to Spring_Quartz World!" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		logger.info("Let's begin ! \n \n");
	}

}
