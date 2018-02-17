package com.fhzz.core.quartz.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fhzz.core.controller.BaseAction;
import com.fhzz.core.quartz.service.QuartzJobRegister;
import com.fhzz.core.quartz.service.QuartzService;
import com.fhzz.core.quartz.vo.CronJobInfo;

@Controller
public class QuartzController extends BaseAction {
	Log logger = LogFactory.getLog(QuartzController.class);

	@Autowired
	private QuartzService quartzService;

	@Autowired
	private QuartzJobRegister quartzJobRegister;

	/**
	 * 定时列表页
	 */
	@RequestMapping(value = "quartzListJob")
	public String listJob() {
		return "quartz/listjob";
	}

	/**
	 * 获取所有使用Cron表达式的JOB
	 * 
	 * @throws SchedulerException
	 */
	@RequestMapping("getAllCronJobInfos")
	@ResponseBody
	public Map<String, Object> getAllCronJobInfos() throws SchedulerException {
		Map<String, Object> result = new HashMap<String, Object>();
		List<CronJobInfo> jobInfos = quartzService.getAllCronJobInfos();
		result.put("total", jobInfos.size());
		result.put("rows", jobInfos);
		return result;
	}

	/**
	 * 跳转到新增界面
	 */
	@RequestMapping("quartzToAddJob")
	public ModelAndView quartzToAddJob(ModelAndView modelAndView) {
		List<String> jobNameList = quartzJobRegister.getAllJobsName();
		modelAndView.addObject("jobNameList", jobNameList);
		modelAndView.setViewName("quartz/addjob");
		return modelAndView;
	}

	/**
	 * 新增job
	 * 
	 * @throws SchedulerException
	 * @throws ClassNotFoundException
	 */
	@RequestMapping(value = "quartzAddJob", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> quartzAddJob(@ModelAttribute CronJobInfo cronJobInfo, ModelAndView modelAndView)
			throws ClassNotFoundException, SchedulerException {
		Map<String, Object> result = new HashMap<String, Object>();
		quartzService.addJob(cronJobInfo);
		result.put("success", true);
		return result;
	}

	/**
	 * 运行任务
	 * 
	 * @throws SchedulerException
	 */
	@RequestMapping(value = "triggerJob", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> triggerJob(@ModelAttribute CronJobInfo cronJobInfo) throws SchedulerException {
		Map<String, Object> result = new HashMap<String, Object>();
		quartzService.triggerJob(cronJobInfo);
		result.put("success", true);
		return result;
	}

	/**
	 * 跳转到编辑
	 * 
	 * @throws SchedulerException
	 */
	@RequestMapping(value = "quartzToEdit")
	public ModelAndView quartzToEdit(@RequestParam("jobName") String jobName,
			@RequestParam("jobGroup") String jobGroup, ModelAndView modelAndView) throws SchedulerException {
		CronJobInfo cronJobInfo = quartzService.getCronJobInfo(jobGroup, jobName);
		modelAndView.addObject("cronJobInfo", cronJobInfo);
		modelAndView.setViewName("quartz/editjob");
		return modelAndView;
	}

	/**
	 * 修改触发时间
	 * 
	 * @throws SchedulerException
	 */
	@RequestMapping(value = "quartzEditJob", method = RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object> quartzEditJob(@ModelAttribute CronJobInfo cronJobInfo, ModelAndView modelAndView)
			throws SchedulerException {
		Map<String, Object> result = new HashMap<String, Object>();		
		quartzService.modifyJobTime(cronJobInfo);
		result.put("success", true);
		return result;
	}

	/**
	 * 暂停任务
	 */
	@RequestMapping(value = "/quartzPauseJob", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> quartzPauseJob(@RequestParam("jobName") String jobName,
			@RequestParam("jobGroup") String jobGroup) throws SchedulerException {
		Map<String, Object> result = new HashMap<String, Object>();
		quartzService.pauseJob(jobName, jobGroup);
		result.put("success", true);
		return result;
	}

	/**
	 * 恢复任务
	 */
	@RequestMapping(value = "/quartzResumeJob", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> quartzResumeJob(@RequestParam("jobName") String jobName,
			@RequestParam("jobGroup") String jobGroup) throws SchedulerException {
		Map<String, Object> result = new HashMap<String, Object>();
		quartzService.resumeJob(jobName, jobGroup);
		result.put("success", true);
		return result;
	}

	/**
	 * 删除任务
	 */
	@RequestMapping(value = "/quartzDeleteJob", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> quartzDeleteJob(@ModelAttribute CronJobInfo cronJobInfo) throws SchedulerException {
		Map<String, Object> result = new HashMap<String, Object>();
		quartzService.removeJob(cronJobInfo);
		result.put("success", true);
		return result;
	}
}
