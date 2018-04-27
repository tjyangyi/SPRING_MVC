/**
 * 
 */
package com.fhzz.business.controller.atomikos;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fhzz.business.controller.datagrid.DatagridDemoAction;
import com.fhzz.business.service.atomikos.AtomikosService;
import com.fhzz.core.controller.BaseAction;

/**
 * @author YangYi
 * 
 */
@Controller
public class AtomikosAction extends BaseAction {
	Log logger = LogFactory.getLog(DatagridDemoAction.class);
	
	@Autowired
	private AtomikosService atomikosService;
	
	@RequestMapping("toAtomikos")
	public String toAtomikos(){
		return "atomikos/atomikos";
	}
	
	@RequestMapping("saveOracle")
	@ResponseBody
	public Map<String, Object> save1() throws Exception{
		atomikosService.saveOracle();
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		return result;
	}
	
	@RequestMapping("saveMysql")
	@ResponseBody
	public Map<String, Object> saveMysql() throws Exception{
		atomikosService.saveMysql();
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		return result;
	}
	
	@RequestMapping("saveBoth")
	@ResponseBody
	public Map<String, Object> saveBoth() throws Exception{
		atomikosService.saveBoth();
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		return result;
	}
	
	@RequestMapping("saveBothWithOracleException")
	@ResponseBody
	public Map<String, Object> saveBothWithOracleException() throws Exception{
		atomikosService.saveBothWithOracleException();
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		return result;
	}
	
	@RequestMapping("saveBothWithMysqlException")
	@ResponseBody
	public Map<String, Object> saveBothWithMysqlException() throws Exception{
		atomikosService.saveBothWithMysqlException();
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		return result;
	}
	
	
}
