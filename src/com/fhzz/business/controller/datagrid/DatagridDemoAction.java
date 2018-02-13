/**
 * 
 */
package com.fhzz.business.controller.datagrid;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fhzz.business.service.demo.DatabaseOperationExampleService;
import com.fhzz.core.controller.BaseAction;
import com.fhzz.core.vo.PageParam;
import com.fhzz.core.vo.PageResult;

/**
 * @author YangYi
 * 
 */
@Controller
public class DatagridDemoAction extends BaseAction {
	Log logger = LogFactory.getLog(DatagridDemoAction.class);

	@Autowired
	private DatabaseOperationExampleService databaseOperationExampleService;

	@RequestMapping("toDatagridDemo")
	public String welcomeDatagrid(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		return "datagrid/datagridDemo";
	}
	
	@RequestMapping("getDatagridJson")
	@ResponseBody
	public PageResult getDatagridJson(@ModelAttribute PageParam pageParam) throws IOException {
		PageResult page = databaseOperationExampleService.queryDemoTable(pageParam);
		System.out.println(page);
		return page;
	}
}
