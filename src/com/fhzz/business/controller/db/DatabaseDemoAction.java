/**
 * 
 */
package com.fhzz.business.controller.db;

import java.io.IOException;
import java.util.Map;

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

import com.fhzz.business.entity.DemoTable;
import com.fhzz.business.service.db.HibernateDemoService;
import com.fhzz.business.service.db.JdbcDemoService;
import com.fhzz.business.vo.datagrid.DatagridDemoParam;
import com.fhzz.core.controller.BaseAction;
import com.fhzz.core.log.anotation.OperationLog;
import com.fhzz.core.vo.PageResult;

/**
 * @author YangYi
 * 
 */
@Controller
public class DatabaseDemoAction extends BaseAction {
	Log logger = LogFactory.getLog(DatabaseDemoAction.class);

	@Autowired
	private HibernateDemoService hibernateDemoService;
	@Autowired
	private JdbcDemoService jdbcDemoService;

	@RequestMapping("toDatabaseDemo")
	public String welcomeDatagrid(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		return "db/databaseDemo";
	}
	
	@RequestMapping("baseDaoSave")
	@ResponseBody
	@OperationLog
	public Map<String,Object> baseDaoSave(@ModelAttribute DemoTable demoTable)
			throws IOException {
		jdbcDemoService.saveDemoTable(demoTable);
		return successResult();
	}
	
	@RequestMapping("baseDaoUpdate")
	@ResponseBody
	@OperationLog
	public Map<String,Object> baseDaoUpdate(@ModelAttribute DemoTable demoTable)
			throws IOException {
		jdbcDemoService.updateDemoTable(demoTable);
		return successResult();
	}
	

	@RequestMapping("getDatabaseDemoPageQuery1")
	@ResponseBody
	@OperationLog
	public PageResult<DemoTable> getDatabaseDemoPageQuery1(@ModelAttribute DatagridDemoParam datagridDemoParam)
			throws IOException {
		PageResult<DemoTable> page = jdbcDemoService.pagedQuery(datagridDemoParam);
		return page;
	}

	@RequestMapping("getDatabaseDemoPageQuery2")
	@ResponseBody
	@OperationLog
	public PageResult<DemoTable> getDatabaseDemoPageQuery2(@ModelAttribute DatagridDemoParam datagridDemoParam)
			throws IOException {
		PageResult<DemoTable> page = hibernateDemoService.pagedQuery(datagridDemoParam);
		return page;
	}

}
