package com.fhzz.core.quartz.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.Trigger.TriggerState;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.fhzz.core.quartz.vo.CronJobInfo;

@Service
public class QuartzService {

	@Autowired
	private Scheduler quartzScheduler;

	@SuppressWarnings("unchecked")
	public CronJobInfo getCronJobInfo(String jobGroup, String jobName)
			throws SchedulerException {
		JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
		List<CronTrigger> triggers = (List<CronTrigger>) quartzScheduler
				.getTriggersOfJob(jobKey);
		TriggerKey triggerKey = triggers.get(0).getKey();
		return this.buildCronJobInfo(triggerKey);
	}

	public List<CronJobInfo> getAllCronJobInfos() throws SchedulerException {
		List<CronJobInfo> cronJobInfos = new ArrayList<CronJobInfo>();
		List<String> triggerGroupNames = quartzScheduler.getTriggerGroupNames();// 获取所有TriggerGroup
		for (String triggerGroupName : triggerGroupNames) {
			Set<TriggerKey> triggerKeys = quartzScheduler
					.getTriggerKeys(GroupMatcher
							.triggerGroupEquals(triggerGroupName));// 根据triggerGroup获取该group下的所有TriggerKey
			for (TriggerKey triggerKey : triggerKeys) {
				cronJobInfos.add(this.buildCronJobInfo(triggerKey));
			}
		}
		return cronJobInfos;
	}

	private CronJobInfo buildCronJobInfo(TriggerKey triggerKey)
			throws SchedulerException {
		CronJobInfo cronJobInfo = new CronJobInfo();
		// trigger key相关属性
		cronJobInfo.setTriggerName(triggerKey.getName());
		cronJobInfo.setTriggerGroupName(triggerKey.getGroup());
		// trigger相关属性
		Trigger trigger = quartzScheduler.getTrigger(triggerKey);
		cronJobInfo.setPrevFireTime(trigger.getPreviousFireTime());
		cronJobInfo.setNextFireTime(trigger.getNextFireTime());
		cronJobInfo.setPriority(trigger.getPriority());
		cronJobInfo.setStartTime(trigger.getStartTime());
		cronJobInfo.setEndTime(trigger.getEndTime());
		TriggerState triggerState = quartzScheduler.getTriggerState(trigger
				.getKey());
		cronJobInfo.setTriggerState(triggerState.name());
		if (trigger instanceof CronTrigger) {
			cronJobInfo.setCronExpression(((CronTrigger) trigger)
					.getCronExpression());
		}
		// jobKey相关属性
		JobKey jobKey = trigger.getJobKey();
		cronJobInfo.setJobName(jobKey.getName());
		cronJobInfo.setJobGroup(jobKey.getGroup());
		// jobDetial相关属性
		JobDetail jobDetail = quartzScheduler.getJobDetail(jobKey);
		JobDataMap map = jobDetail.getJobDataMap();
		cronJobInfo.setJobClass(jobDetail.getJobClass().getName());
		cronJobInfo.setJobDataMapJson(JSONObject.toJSONString(map));
		return cronJobInfo;
	}

	@SuppressWarnings("unchecked")
	public void addJob(CronJobInfo cronJobInfo) throws ClassNotFoundException, SchedulerException {
		Class<Job> jobClass = (Class<Job>) Class.forName(cronJobInfo
				.getJobClass());
		JobDetail job = JobBuilder
				.newJob(jobClass)
				.withIdentity(cronJobInfo.getJobName(),
						cronJobInfo.getJobGroup()).build();// 创建一项作业
		CronTrigger trigger = TriggerBuilder
				.newTrigger()
				.withIdentity(cronJobInfo.getTriggerName(),
						cronJobInfo.getTriggerGroupName())
				.withSchedule(
						CronScheduleBuilder.cronSchedule(cronJobInfo
								.getCronExpression())).build();// 创建一个触发器
		quartzScheduler.scheduleJob(job, trigger);// 告诉调度器使用该触发器来安排作业
		if (!quartzScheduler.isShutdown()) {
			quartzScheduler.start();// 启动
		}
	}

	/**
	 * 添加一项任务
	 */
	public void addJob(String jobName, String jobGroupName, String triggerName,
			String triggerGroupName, Class<? extends Job> cls, String cron)
			throws SchedulerException {
		JobDetail job = JobBuilder.newJob(cls)
				.withIdentity(jobName, jobGroupName).build();// 创建一项作业
		CronTrigger trigger = TriggerBuilder.newTrigger()
				.withIdentity(triggerName, triggerGroupName)
				.withSchedule(CronScheduleBuilder.cronSchedule(cron)).build();// 创建一个触发器
		quartzScheduler.scheduleJob(job, trigger);// 告诉调度器使用该触发器来安排作业
		if (!quartzScheduler.isShutdown()) {
			quartzScheduler.start();// 启动
		}
	}

	/**
	 * 修改定时器任务信息
	 */
	public boolean modifyJobTime(String oldjobName, String oldjobGroup,
			String oldtriggerName, String oldtriggerGroup, String jobName,
			String jobGroup, String triggerName, String triggerGroup,
			String cron) throws SchedulerException {
		JobKey jobKey = JobKey.jobKey(oldjobName, oldjobGroup);
		JobDetail job = quartzScheduler.getJobDetail(jobKey);
		Class<? extends Job> jobClass = job.getJobClass();
		this.removeJob(oldjobName, oldjobGroup, oldtriggerName, oldtriggerGroup);
		this.addJob(jobName, jobGroup, triggerName, triggerGroup, jobClass,
				cron);
		return true;
	}

	/**
	 * 修改定时器任务信息
	 */
	public void modifyJobTime(String triggerName, String triggerGroupName,
			String time) throws SchedulerException {
		CronTrigger ct = (CronTrigger) quartzScheduler.getTrigger(TriggerKey
				.triggerKey(triggerName, triggerGroupName));
		ct.getTriggerBuilder()
				.withSchedule(CronScheduleBuilder.cronSchedule(time)).build();// 修改时间
		quartzScheduler.resumeTrigger(TriggerKey.triggerKey(triggerName,
				triggerGroupName));// 重启触发器
	}

	/**
	 * 删除任务
	 */
	public void removeJob(String jobName, String jobGroupName,
			String triggerName, String triggerGroupName)
			throws SchedulerException {
		quartzScheduler.pauseTrigger(TriggerKey.triggerKey(triggerName,
				triggerGroupName));// 停止触发器
		quartzScheduler.unscheduleJob(TriggerKey.triggerKey(triggerName,
				triggerGroupName));// 移除触发器
		quartzScheduler.deleteJob(JobKey.jobKey(jobName, jobGroupName));// 删除任务
	}

	public void startSchedule() throws SchedulerException {
		quartzScheduler.start();
	}

	public void shutdownSchedule() throws SchedulerException {
		if (!quartzScheduler.isShutdown()) {
			quartzScheduler.shutdown();
		}
	}

	public void pauseJob(String jobName, String jobGroupName)
			throws SchedulerException {
		quartzScheduler.pauseJob(JobKey.jobKey(jobName, jobGroupName));
	}

	public void resumeJob(String jobName, String jobGroupName)
			throws SchedulerException {
		quartzScheduler.resumeJob(JobKey.jobKey(jobName, jobGroupName));
	}

}
