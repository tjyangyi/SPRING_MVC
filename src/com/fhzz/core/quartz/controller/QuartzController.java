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
	public String listJob() throws SchedulerException {
		return "quartz/listjob";
	}

	/**
	 * 获取所有使用Cron表达式的JOB
	 */
	@RequestMapping("getAllCronJobInfos")
	@ResponseBody
	public Map<String, Object> getAllCronJobInfos() {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			List<CronJobInfo> jobInfos = quartzService.getAllCronJobInfos();
			result.put("total", jobInfos.size());
			result.put("rows", jobInfos);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return result;
	}

	/**
	 * 跳转到新增界面
	 */
	@RequestMapping("quartzToAddJob")
	public ModelAndView quartzToAddJob(ModelAndView modelAndView) {
		List<String> jobNameList = quartzJobRegister.getAllJobsName();
		modelAndView.addObject("jobNameList",jobNameList);
		modelAndView.setViewName("quartz/addjob");
		return modelAndView;
	}

	/**
	 * 新增job
	 */
	@RequestMapping(value = "quartzAddJob", method = RequestMethod.POST)
	public ModelAndView quartzAddJob(@ModelAttribute CronJobInfo cronJobInfo, ModelAndView modelAndView) {
		modelAndView.addObject("opName", "添加任务");
		try {
			quartzService.addJob(cronJobInfo);
			modelAndView.addObject("message", "添加任务成功!");
		} catch (Exception e) {
			modelAndView.addObject("message", "添加任务失败!");
			logger.error(e.getMessage(), e);
		}
		modelAndView.setViewName("quartz/message");
		return modelAndView;
	}

	/**
	 * 运行任务
	 */
	@RequestMapping(value = "triggerJob", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> triggerJob(@ModelAttribute CronJobInfo cronJobInfo) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			quartzService.triggerJob(cronJobInfo);
			result.put("success", true);
		} catch (Exception e) {
			result.put("success", false);
			result.put("errorMsg", e.getMessage());
		}
		return result;
	}

	/**
	 * 跳转到编辑
	 */
	@RequestMapping(value = "/quartzToEdit")
	public ModelAndView quartzToEdit(@RequestParam("jobName") String jobName,
			@RequestParam("jobGroup") String jobGroup, ModelAndView modelAndView) {
		try {
			CronJobInfo cronJobInfo = quartzService.getCronJobInfo(jobGroup, jobName);
			modelAndView.addObject("cronJobInfo", cronJobInfo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		modelAndView.setViewName("quartz/editjob");
		return modelAndView;
	}

	/**
	 * 修改触发时间
	 */
	@RequestMapping(value = "/quartzEdit", method = RequestMethod.POST)
	public ModelAndView edit(@ModelAttribute CronJobInfo cronJobInfo, ModelAndView modelAndView) {
		modelAndView.addObject("opName", "更新任务");
		try {
			quartzService.modifyJobTime(cronJobInfo);
			modelAndView.addObject("message", "修改任务成功!");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			modelAndView.addObject("message", "修改任务失败!");
		}
		modelAndView.setViewName("quartz/message");
		return modelAndView;
	}

	/**
	 * 暂停任务
	 */
	@RequestMapping(value = "/quartzPauseJob", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> quartzPauseJob(@RequestParam("jobName") String jobName,
			@RequestParam("jobGroup") String jobGroup) throws SchedulerException {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			quartzService.pauseJob(jobName, jobGroup);
			result.put("success", true);
		} catch (Exception e) {
			result.put("success", false);
			result.put("errorMsg", e.getMessage());
		}
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
		try {
			quartzService.resumeJob(jobName, jobGroup);
			result.put("success", true);
		} catch (Exception e) {
			result.put("success", false);
			result.put("errorMsg", e.getMessage());
		}
		return result;
	}

	/**
	 * 删除任务
	 */
	@RequestMapping(value = "/quartzDeleteJob", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> quartzDeleteJob(@ModelAttribute CronJobInfo cronJobInfo) throws SchedulerException {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			quartzService.removeJob(cronJobInfo);
			result.put("success", true);
		} catch (Exception e) {
			result.put("success", false);
			result.put("errorMsg", e.getMessage());
		}
		return result;
	}
}
