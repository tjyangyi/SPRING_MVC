/**
 * 
 */
package com.fhzz.business.controller.welcome;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.fhzz.business.entity.Welcome;
import com.fhzz.business.service.WelcomeService;
import com.fhzz.core.controller.BaseAction;

/**
 * @author YangYi
 * 
 */
@Controller
public class WelcomeAction extends BaseAction {
	Log logger = LogFactory.getLog(WelcomeAction.class);

	@Autowired
	private WelcomeService welcomeService;

	@RequestMapping("welcome")
	public String welcome(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		logger.debug("welcome");
		logger.info("welcome");
		logger.warn("welcome");
		logger.error("welcome");
		Welcome welcome = new Welcome("1", "001", "yy");
		welcomeService.saveWelcome(welcome);
		return "welcome";
	}

	@RequestMapping("queryAllWelcome")
	public String queryAllWelcome(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		logger.info("queryAllWelcome");
		welcomeService.queryAllWelcome();
		return "welcome";
	}

	@RequestMapping("welcomeAjaxRequest")
	public void welcomeAjaxRequest(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		JSONObject json = new JSONObject();
		json.put("result", "success");
		json.put("object", "object");
		sendAjax(response, json);
	}

}
