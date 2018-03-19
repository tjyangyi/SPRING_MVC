/**
 * 
 */
package com.fhzz.business.controller.hbase;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fhzz.business.controller.db.DatabaseDemoAction;
import com.fhzz.business.entity.hbase.TWbSwryxx;
import com.fhzz.business.service.hbase.HBaseDemoService;
import com.fhzz.core.controller.BaseAction;

/**
 * @author YangYi
 * 
 */
@Controller
public class HBaseDemoAction extends BaseAction {
	Log logger = LogFactory.getLog(DatabaseDemoAction.class);

	@Autowired
	private HBaseDemoService hbBaseDemoService;

	@RequestMapping("toHbaseDemo")
	public String hbaseTest(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		return "hbase/hbaseDemo";
	}

	@RequestMapping("scanWithStartAndStop")
	@ResponseBody
	public Map<String, Object> scanWithStartAndStop() throws IOException, SchedulerException, ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<String> swcsdmList = new ArrayList<String>();
		Date swsjStart = sdf.parse("2015-12-16 11:49:15");
		Date swsjEnd = sdf.parse("2015-12-16 12:21:28");
		swcsdmList.add("41022510200047");
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		List<TWbSwryxx> list = hbBaseDemoService.getSwryxx(swcsdmList, swsjStart, swsjEnd);
		result.put("total", list.size());
		result.put("rows", list);
		return result;
	}

}
