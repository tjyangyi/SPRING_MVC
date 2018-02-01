/**
 * 
 */
package com.fhzz.business.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fhzz.business.entity.Welcome;
import com.fhzz.business.service.IWelcomeService;
import com.fhzz.core.controller.BaseAction;

/**
 * @author YangYi
 * 
 */
@Controller
public class WelcomeAction extends BaseAction {
	Log logger = LogFactory.getLog(WelcomeAction.class);

	@Autowired
	private IWelcomeService welcomeService;

	@RequestMapping("welcome")
	public String welcome(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		logger.debug("debug");
		logger.info("info");
		logger.warn("warn");
		logger.error("error");
		Welcome welcome = new Welcome("1", "002", "yy");
		welcomeService.saveWelcome(welcome);

		return "welcome";
	}
}
