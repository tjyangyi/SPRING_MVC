/**
 * 
 */
package com.fhzz.business.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Service;

import com.fhzz.core.quartz.job.AbstractJob;

/**
 * @author: YangYi
 * @CreateTime: 2018年2月11日 下午1:32:32
 * @Copyright: FHZZ
 */
@Service
public class DatabaseExampleJob extends AbstractJob {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		
	}

}
