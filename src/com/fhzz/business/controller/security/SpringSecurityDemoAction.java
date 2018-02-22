/**
 * 
 */
package com.fhzz.business.controller.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fhzz.core.controller.BaseAction;

/**
 * @author YangYi
 * @desc 本类为SpringSecurity权限DEMO
 */
@Controller
public class SpringSecurityDemoAction extends BaseAction {
	Log logger = LogFactory.getLog(SpringSecurityDemoAction.class);

	/**
	 * 测试权限不足的跳转
	 */
	@RequestMapping("pageJumpWithoutAuth")
	public String pageJumpWithoutAuth() {
		return "demo/springMVCDemo";
	}

	/**
	 * 测试权限不足的AJAX请求
	 */
	@RequestMapping("ajaxWithoutAuth")
	@ResponseBody
	public Map<String, Object> ajaxWithoutAuth() throws IOException,
			SchedulerException {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		return result;
	}
}
