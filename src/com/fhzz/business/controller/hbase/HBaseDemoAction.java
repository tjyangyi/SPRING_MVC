/**
 * 
 */
package com.fhzz.business.controller.hbase;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fhzz.business.controller.db.DatabaseDemoAction;
import com.fhzz.business.entity.DemoTable;
import com.fhzz.core.controller.BaseAction;

/**
 * @author YangYi
 * 
 */
@Controller
public class HBaseDemoAction extends BaseAction {
	Log logger = LogFactory.getLog(DatabaseDemoAction.class);

	@Autowired
	private HbaseTemplate hbaseTemplate;

	@RequestMapping("toHbaseDemo")
	public String hbaseTest(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		System.setProperty("hadoop.home.dir", "D:\\hadoop-2.8.3"); // 必备条件之一
//		find("DEMO_TABLE3","49217_20161013000000","49217_20181013000000");
		return "hbase/hbaseDemo";
	}

	@RequestMapping("scanWithStartAndStop")
	@ResponseBody
	public Map<String, Object> scanWithStartAndStop(@RequestParam("startRow") String startRow,
			@RequestParam("stopRow") String stopRow) throws IOException, SchedulerException {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		List<Map<String, Object>> list = scan( "DEMO_TABLE3", startRow, stopRow) ;
		result.put("total", list.size());
		result.put("rows", list);
		return result;
	}
	
	/**
	 * 通过表名，开始行键和结束行键获取数据
	 * 
	 * @param tableName
	 * @param startRow
	 * @param stopRow
	 * @return
	 */
	public List<Map<String, Object>> scan(String tableName, String startRow,
			String stopRow) {
		Scan scan = new Scan();
		if (startRow == null) {
			startRow = "";
		}
		if (stopRow == null) {
			stopRow = "";
		}
		scan.setStartRow(Bytes.toBytes(startRow));
		scan.setStopRow(Bytes.toBytes(stopRow));

		BeanPropertyRowMapper<DemoTable> rowMapper = BeanPropertyRowMapper.newInstance(DemoTable.class);
		/*
		 * PageFilter filter = new PageFilter(5); scan.setFilter(filter);
		 */
		return hbaseTemplate.find(tableName, scan,
				new RowMapper<Map<String, Object>>() {
					public Map<String, Object> mapRow(Result result, int rowNum)
							throws Exception {

						List<Cell> ceList = result.listCells();
						Map<String, Object> map = new HashMap<String, Object>();
						String row = "";
						if (ceList != null && ceList.size() > 0) {
							for (Cell cell : ceList) {
								row = Bytes.toString(cell.getRowArray(),
										cell.getRowOffset(),
										cell.getRowLength());
								String value = Bytes.toString(
										cell.getValueArray(),
										cell.getValueOffset(),
										cell.getValueLength());
								String family = Bytes.toString(
										cell.getFamilyArray(),
										cell.getFamilyOffset(),
										cell.getFamilyLength());
								String quali = Bytes.toString(
										cell.getQualifierArray(),
										cell.getQualifierOffset(),
										cell.getQualifierLength());
								map.put(family + "_" + quali, value);
							}
							map.put("row", row);
						}
						System.out.println(map);
						return map;
					}
				});
	}
}
