/**
 * 
 */
package com.fhzz.core.dao.impl.support;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.BeanUtils;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;

/**
 * @author: YangYi
 * @CreateTime: 2018年3月19日 下午2:46:28
 * @Copyright: FHZZ
 */
public class HbaseTemplateSupport extends HbaseTemplate {

	static {
		if ("Windows".equals(System.getProperty("os.name"))) {
			System.setProperty("hadoop.home.dir", "D:\\hadoop-2.8.3"); // 必备条件之一
		}
	}
	
	Map<String,PropertyDescriptor> map = new HashMap<String,PropertyDescriptor> ();
	
	private void initMap(){
		Field[] fields = t.getClass().getDeclaredFields();// 获得属性
		for (Field field : fields) {
			PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), clazz);
			Method getMethod = propertyDescriptor.getReadMethod();// 获得get方法
		//	Method setMethod = propertyDescriptor.getWriteMethod();// 获得set方法
			Column column = getMethod.getAnnotation(Column.class);
			String key = column.name();
			map.put(key, propertyDescriptor);
		}
	}

	public <T> List<T> scan(String tableName, String startRow, String endRow, Class<T> clazz)
			throws IntrospectionException {
		T t = BeanUtils.instantiate(clazz);

		
		return null;
	}

	public List<Map<String, Object>> scan2(String tableName, String startRow, String stopRow) {
		Scan scan = new Scan();
		if (startRow == null) {
			startRow = "";
		}
		if (stopRow == null) {
			stopRow = "";
		}
		scan.setStartRow(Bytes.toBytes(startRow));
		scan.setStopRow(Bytes.toBytes(stopRow));
		/*
		 * PageFilter filter = new PageFilter(5); scan.setFilter(filter);
		 */
		return this.find(tableName, scan, new RowMapper<Map<String, Object>>() {
			public Map<String, Object> mapRow(Result result, int rowNum) throws Exception {
				List<Cell> ceList = result.listCells();
				Map<String, Object> map = new HashMap<String, Object>();
				String row = "";
				if (ceList != null && ceList.size() > 0) {
					for (Cell cell : ceList) {
						row = Bytes.toString(cell.getRowArray(), cell.getRowOffset(), cell.getRowLength());
						String value = Bytes.toString(cell.getValueArray(), cell.getValueOffset(),
								cell.getValueLength());
//						String family = Bytes.toString(cell.getFamilyArray(), cell.getFamilyOffset(),
//								cell.getFamilyLength());
						String quali = Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(),
								cell.getQualifierLength());
						map.put(quali, value);
					}
					map.put("row", row);
				}
				System.out.println(map);
				return map;
			}
		});
	}
}
