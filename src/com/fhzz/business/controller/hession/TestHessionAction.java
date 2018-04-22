package com.fhzz.business.controller.hession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fhzz.business.service.hession.TestHessionService;
import com.fhzz.core.controller.BaseAction;

@Controller
public class TestHessionAction extends BaseAction {
	Log logger = LogFactory.getLog(TestHessionAction.class);

	@Autowired
	private TestHessionService testHessionService;

	@RequestMapping("testHession")
	@ResponseBody
	public Map<String, Object> testHession() throws IOException {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		result.put("data1", 1234);
		testHessionService.testHession();
		return result;
	}
	
	@RequestMapping("testNoHession")
	@ResponseBody
	public Map<String, Object> testNoHession() throws IOException {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		result.put("data1", 1234);
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("testHessionDatabase")
	@ResponseBody
	public Map<String, Object> testHessionDatabase() throws IOException {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		result.put("data1", 1234);
		testHessionService.testHessionDatabase();
		return result;
	}
	
	
}
