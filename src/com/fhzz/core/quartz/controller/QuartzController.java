package com.fhzz.core.quartz.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.fhzz.core.controller.BaseAction;
import com.fhzz.core.quartz.service.QuartzService;
import com.fhzz.core.quartz.vo.CronJobInfo;

@Controller
public class QuartzController extends BaseAction {

	@Autowired
	private QuartzService quartzService;

	/**
	 * 定时列表页
	 */
	@RequestMapping(value = "/quartzListJob")
	public String listJob(HttpServletRequest request,
			HttpServletResponse response) throws SchedulerException {
		return "quartz/listjob";
	}

	/**
	 * 获取所有使用Cron表达式的JOB
	 */
	@RequestMapping("/getAllCronJobInfos")
	public void getAllCronJobInfos(HttpServletRequest request,
			HttpServletResponse response) throws IOException,
			SchedulerException {
		List<CronJobInfo> jobInfos = quartzService.getAllCronJobInfos();
		JSONObject json = new JSONObject();
		json.put("total", jobInfos.size());
		json.put("rows", jobInfos);
		sendAjax(response, json);
	}

	/**
	 * 跳转到新增界面
	 */
	@RequestMapping(value = "/quartzToAddJob")
	public String quartzToAddJob(HttpServletRequest request,
			HttpServletResponse response) throws SchedulerException {
		return "quartz/addjob";
	}

	/**
	 * 新增job
	 */
	@RequestMapping(value = "/quartzAddJob", method = RequestMethod.POST)
	public ModelAndView quartzAddJob(@ModelAttribute CronJobInfo cronJobInfo,
			ModelAndView modelAndView) throws SchedulerException,
			ClassNotFoundException {
		quartzService.addJob(cronJobInfo);
		modelAndView.addObject("message", "添加任务成功!");
		modelAndView.addObject("opName", "添加任务!");
		modelAndView.setViewName("quartz/message");
		return modelAndView;
	}

	/**
	 * 跳转到编辑
	 */
	@RequestMapping(value = "/quartzToEdit")
	public ModelAndView quartzToEdit(@RequestParam("jobName") String jobName,
			@RequestParam("jobGroup") String jobGroup, ModelAndView modelAndView)
			throws SchedulerException {
		CronJobInfo cronJobInfo = quartzService.getCronJobInfo(jobGroup,
				jobName);
		modelAndView.addObject("cronJobInfo", cronJobInfo);
		modelAndView.setViewName("quartz/editjob");
		return modelAndView;
	}

	/**
	 * 编辑job
	 */
	@RequestMapping(value = "/quartzEdit", method = RequestMethod.POST)
	public String edit(HttpServletRequest request, HttpServletResponse response)
			throws SchedulerException, ClassNotFoundException {
		String jobName = request.getParameter("jobName");
		String jobGroupName = request.getParameter("jobGroupName");
		String triggerName = request.getParameter("triggerName");
		String triggerGroupName = request.getParameter("triggerGroupName");
		String cron = request.getParameter("cron");
		String oldjobName = request.getParameter("oldjobName");
		String oldjobGroup = request.getParameter("oldjobGroup");
		String oldtriggerName = request.getParameter("oldtriggerName");
		String oldtriggerGroup = request.getParameter("oldtriggerGroup");
		boolean result = quartzService.modifyJobTime(oldjobName, oldjobGroup,
				oldtriggerName, oldtriggerGroup, jobName, jobGroupName,
				triggerName, triggerGroupName, cron);
		if (result) {
			request.setAttribute("message", "修改任务成功!");
		} else {
			request.setAttribute("message", "修改任务失败!");
		}
		request.setAttribute("opName", "更新任务!");
		return "quartz/message";
	}

	/**
	 * 暂停任务
	 */
	@RequestMapping(value = "/quartzPauseJob", method = RequestMethod.POST)
	@ResponseBody
	public String pauseJob(@RequestParam("jobName") String jobName,
			@RequestParam("jobGroupName") String jobGroupName)
			throws SchedulerException {
		JSONObject json = new JSONObject();
		quartzService.pauseJob(jobName, jobGroupName);
		json.put("status", "success");
		return json.toString();
	}

	/**
	 * 恢复任务
	 */
	@RequestMapping(value = "/quartzResumeJob", method = RequestMethod.POST)
	@ResponseBody
	public String quartzResumeJob(@RequestParam("jobName") String jobName,
			@RequestParam("jobGroupName") String jobGroupName)
			throws SchedulerException {
		JSONObject json = new JSONObject();
		quartzService.resumeJob(jobName, jobGroupName);
		json.put("status", "success");
		return json.toString();
	}

	/**
	 * 删除任务
	 */
	@RequestMapping(value = "/quartzDeleteJob", method = RequestMethod.POST)
	@ResponseBody
	public String deleteJob(@RequestParam("jobName") String jobName,
			@RequestParam("jobGroupName") String jobGroupName,
			@RequestParam("triggerName") String triggerName,
			@RequestParam("triggerGroupName") String triggerGroupName)
			throws SchedulerException {
		JSONObject json = new JSONObject();
		quartzService.removeJob(jobName, jobGroupName, triggerName,
				triggerGroupName);
		json.put("status", "success");
		return json.toString();
	}
}
