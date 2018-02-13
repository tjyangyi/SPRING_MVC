/**
 * 
 */
package com.fhzz.business.controller.welcome;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String welcome(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		Welcome welcome = new Welcome("1", "001", "yy");
		welcomeService.saveWelcome(welcome);
		return "welcome/welcome";
	}
}
