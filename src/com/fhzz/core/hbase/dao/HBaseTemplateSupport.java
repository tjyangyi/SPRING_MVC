/**
 * 
 */
package com.fhzz.core.hbase.dao;

import java.util.List;

import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.data.hadoop.hbase.HbaseTemplate;

import com.fhzz.core.hbase.entity.HBaseEntity;
import com.fhzz.core.hbase.mapper.BeanPropertyRowMapper;

/**
 * @author: YangYi
 * @CreateTime: 2018年3月19日 下午2:46:28
 * @Copyright: FHZZ
 */
public class HBaseTemplateSupport extends HbaseTemplate {

	static {
		if ("Windows".equals(System.getProperty("os.name"))) {
			System.setProperty("hadoop.home.dir", "D:\\hadoop-2.8.3"); // 必备条件之一
		}
	}

	public <T extends HBaseEntity> List<T> scan(String tableName, Class<T> mappedClass, String startRow, String stopRow) {
		if (startRow == null ) {
			throw new IllegalArgumentException("startRow 不可为空");
		}
		if (stopRow == null) {
			throw new IllegalArgumentException("stopRow 不可为空");
		}
		Scan scan = new Scan(Bytes.toBytes(startRow), Bytes.toBytes(stopRow));
		return this.find(tableName, scan, new BeanPropertyRowMapper<T>(mappedClass));
	}
}
