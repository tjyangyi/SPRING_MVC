/**
 * 
 */
package com.fhzz.core.quartz.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fhzz.business.controller.welcome.WelcomeAction;
import com.fhzz.core.quartz.dao.QuartzDao;
import com.fhzz.core.quartz.entity.QrtzCronTriggers;
import com.fhzz.core.quartz.entity.QrtzJobDetails;
import com.fhzz.core.quartz.entity.QrtzTriggers;

/**
 * @author YangYi
 * 
 */
@Service
public class QuartzJobManager {
	Log logger = LogFactory.getLog(QuartzJobManager.class);

	@Autowired
	private QuartzDao quartzDao;
	@Autowired
	private Scheduler quartzScheduler;

	@PostConstruct
	public void init() throws SchedulerException {
		try {
			this.cleanNotExistedJob();
		} catch (SchedulerException e) {
			logger.error(e.getMessage(), e);
		}
		quartzScheduler.start();
	}

	private void cleanNotExistedJob() throws SchedulerException {
		List<QrtzJobDetails> jobDetails = quartzDao.getAllJobDetails();
		System.out.println(jobDetails);
		for (QrtzJobDetails jobDetail : jobDetails) {
			try {
				Class.forName(jobDetail.getJobClassName());
			} catch (Exception e) {
				logger.info("Quartz Job类未找到,即将删除与之相关的任务,类名="
						+ jobDetail.getJobClassName());
				List<QrtzTriggers> triggers = quartzDao.getQrtzTriggersByJob(
						jobDetail.getId().getJobName(), jobDetail.getId()
								.getJobGroup());
				logger.info("与之绑定的QRTZ_TRIGGER有:" + triggers);
				List<QrtzCronTriggers> cronTriggers = quartzDao
						.getCronTriggersByTriggers(triggers);
				logger.info("与之绑定的QRTZ_CRON_TRIGGER有:" + cronTriggers);
				quartzDao
						.deleteNotExistedJob(cronTriggers, triggers, jobDetail);
				logger.info("无效任务删除成功:" + jobDetail.getJobClassName());
			}
		}
	}

}
