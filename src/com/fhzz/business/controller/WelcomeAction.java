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
		logger.debug("welcome");
		logger.info("welcome");
		logger.warn("welcome");
		logger.error("welcome");
		Welcome welcome = new Welcome("1", "001", "yy");
		welcomeService.saveWelcome(welcome);
		return "welcome1";
	}
	
	@RequestMapping("welcome2")
	public String welcome2(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		logger.debug("welcome2");
		logger.info("welcome2");
		logger.warn("welcome2");
		logger.error("welcome2");
		Welcome welcome = new Welcome("2", "002", "yy");
		welcomeService.saveWelcome(welcome);
		return "welcome2";
	}
}
