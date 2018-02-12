/**
 * 
 */
package com.fhzz.core.sercurity.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.fhzz.core.controller.BaseAction;
import com.fhzz.core.utils.HTTPUtils;

/**
 * @author YangYi
 * 
 */
@Controller
public class LoginAction extends BaseAction {
	Log logger = LogFactory.getLog(LoginAction.class);

	@RequestMapping("expired")
	public String expired() {
		return "login/login";
	}

	@RequestMapping("toLogin")
	public ModelAndView toLogin(@RequestParam(value = "message", required = false) String message,
			ModelAndView modelAndView) {
		modelAndView.addObject("message", message);
		modelAndView.setViewName("login/login");
		return modelAndView;
	}

	@RequestMapping("sessionInvalid")
	@ResponseBody
	public ModelAndView sessionInvalid(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "message", required = false) String message) throws IOException {
		logger.error("session失效,重新登录");// 异常日志记录
		// 判断是否AJAX请求
		if (!HTTPUtils.isAjaxRequest(request)) {// 如果不是AJAX请求,VM格式返回
			ModelAndView modelAndView = new ModelAndView("login/login"); // 跳转到登录页面
			modelAndView.addObject("message", message);
			return modelAndView;
		} else {// 如果是AJAX请求,JSON格式返回
			ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
			modelAndView.addObject("sessionInvalid", true);
			modelAndView.addObject("message", message);
			return modelAndView;
		}
	}

}
