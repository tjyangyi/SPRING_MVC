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

import com.fhzz.business.entity.DemoTable;
import com.fhzz.business.service.db.JdbcDemoService;
import com.fhzz.business.vo.datagrid.DatagridDemoParam;
import com.fhzz.core.controller.BaseAction;
import com.fhzz.core.log.anotation.OperationLog;
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
	private JdbcDemoService jdbcDemoService;

	@RequestMapping("toDatagridDemo")
	public String welcomeDatagrid(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		return "datagrid/datagridDemo";
	}

	/**
	 * 最简单的，只分页
	 * 
	 * @param pageParam
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("getDatagridJson1")
	@ResponseBody
	@OperationLog
	public PageResult<DemoTable> getDatagridJson(@ModelAttribute PageParam pageParam) throws IOException {
		PageResult<DemoTable> page = jdbcDemoService.pagedQuery(pageParam);
		return page;
	}

	/**
	 * 使用param对象传入参数
	 * 
	 * @param pageParam
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("getDatagridJson2")
	@ResponseBody
	@OperationLog
	public PageResult<DemoTable> getDatagridJson2(@ModelAttribute DatagridDemoParam datadgridDemoParam)
			throws IOException {
		PageResult<DemoTable> page = jdbcDemoService.pagedQuery(datadgridDemoParam);
		return page;
	}
}
