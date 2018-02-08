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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fhzz.business.entity.Welcome;
import com.fhzz.business.service.welcome.WelcomeService;
import com.fhzz.core.controller.BaseAction;
import com.fhzz.core.log.anotation.OperationLog;
import com.fhzz.core.log.interceptor.OperationTypeEnum;

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
	@OperationLog(operationType = OperationTypeEnum.Query, operationDesc = "访问welcome方法")
	public String welcome(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		Welcome welcome = new Welcome("1", "001", "yy");
		welcomeService.saveWelcome(welcome);
		return "welcome/welcome";
	}

	@RequestMapping("queryAllWelcome")
	public String queryAllWelcome(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		logger.info("queryAllWelcome");
		welcomeService.queryAllWelcome();
		return "welcome/welcome";
	}

	@RequestMapping("welcomeAjaxRequest")
	public void welcomeAjaxRequest(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		JSONObject json = new JSONObject();
		json.put("result", "success");
		json.put("object", "object");
		sendAjax(response, json);
	}

	@RequestMapping("welcomeDatagrid")
	public String welcomeDatagrid(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		logger.info("welcomeDatagrid");
		return "welcome/welcomeDatagrid";
	}
	
	@RequestMapping("getWelcomeDatagridJson")
	public void getWelcomeDatagridJson(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		JSONArray rows = new JSONArray();
		JSONObject j = new  JSONObject();
		j.put("productid", "FI-SW-01");
		rows.add(j);
		
		JSONObject json = new JSONObject();
		json.put("total", "1");
		json.put("rows", rows);
		sendAjax(response, json);
	}
}
