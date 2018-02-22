/**
 * 
 */
package com.fhzz.business.controller.db;

import java.io.IOException;
import java.util.List;
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
	public Map<String, Object> baseDaoSave(@ModelAttribute DemoTable demoTable) throws IOException {
		hibernateDemoService.saveDemoTable(demoTable);
		return successResult();
	}

	@RequestMapping("baseDaoUpdate")
	@ResponseBody
	@OperationLog
	public Map<String, Object> baseDaoUpdate(@ModelAttribute DemoTable demoTable) throws IOException {
		hibernateDemoService.updateDemoTable(demoTable);
		return successResult();
	}

	@RequestMapping("baseDaoSaveOrUpdate")
	@ResponseBody
	@OperationLog
	public Map<String, Object> baseDaoSaveOrUpdate(@ModelAttribute DemoTable demoTable) throws IOException {
		if("".equals(demoTable.getId().trim())){
			demoTable.setId(null);
		}
		hibernateDemoService.saveOrUpdateDemoTable(demoTable);
		return successResult();
	}

	@RequestMapping("baseDaoGet")
	@ResponseBody
	@OperationLog
	public Map<String, Object> baseDaoGet(@ModelAttribute DemoTable demoTable) throws IOException {
		demoTable = hibernateDemoService.getDemoTable(demoTable.getId());
		Map<String, Object> map = successResult();
		map.put("demoTable", demoTable);
		return map;
	}

	@RequestMapping("jdbcPageQueryDemo")
	@ResponseBody
	@OperationLog
	public PageResult<DemoTable> jdbcPageQueryDemo(@ModelAttribute DatagridDemoParam datagridDemoParam)
			throws IOException {
		PageResult<DemoTable> page = jdbcDemoService.pagedQuery(datagridDemoParam);
		return page;
	}

	@RequestMapping("hibernatePageQueryDemo")
	@ResponseBody
	@OperationLog
	public PageResult<DemoTable> hibernatePageQueryDemo(@ModelAttribute DatagridDemoParam datagridDemoParam)
			throws IOException {
		PageResult<DemoTable> page = hibernateDemoService.pagedQuery(datagridDemoParam);
		return page;
	}
	
	@RequestMapping("findBy")
	@ResponseBody
	@OperationLog
	public PageResult<DemoTable> findBy(@ModelAttribute DatagridDemoParam datagridDemoParam)
			throws IOException {
		List<DemoTable> list = hibernateDemoService.findBy("name",datagridDemoParam.getName());
		PageResult<DemoTable> pageResult = new PageResult<DemoTable>(list.size(),list);
		return pageResult;
	} 
	
	@RequestMapping("findByAndOrderBy")
	@ResponseBody
	@OperationLog
	public PageResult<DemoTable> findByAndOrderBy(@ModelAttribute DatagridDemoParam datagridDemoParam)
			throws IOException {
		List<DemoTable> list = hibernateDemoService.findBy("name",datagridDemoParam.getName(),"customTime",true);
		PageResult<DemoTable> pageResult = new PageResult<DemoTable>(list.size(),list);
		return pageResult;
	} 
	
	@RequestMapping("findByValues")
	@ResponseBody
	@OperationLog
	public PageResult<DemoTable> findByValues(@ModelAttribute DatagridDemoParam datagridDemoParam)
			throws IOException {
		Object[] values = new Object[0];
		if(datagridDemoParam.getName() != null){
			values = datagridDemoParam.getName().split(",");
		}
		List<DemoTable> list = hibernateDemoService.findByValues("name",values);
		PageResult<DemoTable> pageResult = new PageResult<DemoTable>(list.size(),list);
		return pageResult;
	} 
	
}
