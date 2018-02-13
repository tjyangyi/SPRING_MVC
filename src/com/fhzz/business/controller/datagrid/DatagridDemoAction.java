/**
 * 
 */
package com.fhzz.business.controller.datagrid;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fhzz.business.service.welcome.WelcomeService;
import com.fhzz.core.controller.BaseAction;

/**
 * @author YangYi
 * 
 */
@Controller
public class DatagridDemoAction extends BaseAction {
	Log logger = LogFactory.getLog(DatagridDemoAction.class);

	@Autowired
	private WelcomeService welcomeService;

	@RequestMapping("toDatagridDemo")
	public String welcomeDatagrid(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		return "datagrid/datagridDemo";
	}
	
	@RequestMapping("getDatagridJson")
	@ResponseBody
	public Map<String,Object> getDatagridJson() throws IOException {
		Map<String,Object> map = new HashMap<String,Object>();
		JSONArray rows = new JSONArray();
		JSONObject j = new  JSONObject();
		j.put("productid", "FI-SW-01");
		rows.add(j);
		map.put("total", "1");
		map.put("rows", rows);
		return map;
	}
}
