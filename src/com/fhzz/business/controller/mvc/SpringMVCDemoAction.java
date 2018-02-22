/**
 * 
 */
package com.fhzz.business.controller.mvc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fhzz.business.vo.mvc.RequestParamDemo;
import com.fhzz.core.controller.BaseAction;

/**
 * @author YangYi
 * @desc 本类用于展示SPRING_MVC前后台交互的几种方式
 */
@Controller
public class SpringMVCDemoAction extends BaseAction {
	Log logger = LogFactory.getLog(SpringMVCDemoAction.class);

	/**
	 * 简单的界面跳转
	 */
	@RequestMapping("toSpringMVCDemo")
	public String toSpringMVCDemo() {
		return "demo/springMVCDemo";
	}

	/**
	 * 带数据返回的页面跳转
	 */
	@RequestMapping(value = "toSpringMVCDemoWithReturn", method = RequestMethod.POST)
	public ModelAndView toSpringMVCDemoWithReturn(ModelAndView modelAndView) {
		modelAndView.addObject("name", "姓名");
		modelAndView.addObject("sex", "性别");
		modelAndView.addObject("message", "返回界面的任意信息");
		modelAndView.setViewName("demo/springMVCDemoWithReturn");
		return modelAndView;
	}

	/**
	 * 使用POST请求JSON数据展示
	 */
	@RequestMapping("springMVCReturnJSON")
	@ResponseBody
	public Map<String, Object> springMVCReturnJSON(@RequestParam("param1") String param1,
			@RequestParam("param2") String param2) throws IOException, SchedulerException {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		List<String> list = new ArrayList<String>();// 模拟列表返回
		list.add("a");
		list.add("b");
		list.add("c");
		result.put("total", list.size());
		result.put("rows", list);
		return result;
	}

	/**
	 * 使用@RequestParam向后台传递参数
	 */
	@RequestMapping("springMVCRequestParam1")
	@ResponseBody
	public Map<String, Object> springMVCRequestParam1(@RequestParam("param1") String param1,
			@RequestParam(value = "param2", required = false) String param2) {
		logger.info(param1);
		logger.info(param2);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		return result;
	}

	/**
	 * 使用@ModelAttribute 以对象的方式向后台传递参数
	 */
	@RequestMapping("springMVCRequestParam2")
	@ResponseBody
	public Map<String, Object> springMVCRequestParam2(@ModelAttribute RequestParamDemo requestParamDemo) {
		logger.info(requestParamDemo.getParam1());
		logger.info(requestParamDemo.getParam2());
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		return result;
	}

	/**
	 * 直接使用HttpServletRequest向后台传递参数
	 */
	@RequestMapping("springMVCRequestParam3")
	@ResponseBody
	public Map<String, Object> springMVCRequestParam3(HttpServletRequest request, HttpServletResponse response) {
		String param1 = request.getParameter("param1");
		String param2 = request.getParameter("param2");
		logger.info(param1);
		logger.info(param2);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		return result;
	}

	/**
	 * 模拟界面跳转时有Exception抛出
	 */
	@RequestMapping("/exceptionForPageJump")
	public ModelAndView exceptionForPageJump(HttpServletRequest request) throws Exception {
		throw new Exception("exceptionForPageJump");
	}

	/**
	 * 模拟AJAX请求时有Exception抛出
	 */
	@RequestMapping(value = "/exceptionForAJAX", method = RequestMethod.POST)
	@ResponseBody
	public String exceptionForAJAX(HttpServletRequest request) throws Exception {
		throw new Exception("exceptionForAJAX");
	}
}
