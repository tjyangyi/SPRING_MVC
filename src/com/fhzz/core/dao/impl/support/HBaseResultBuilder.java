/**
 * 
 */
package com.fhzz.core.dao.impl.support;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.util.StringUtils;

/**
 * @author: YangYi
 * @CreateTime: 2018年3月19日 下午3:03:46
 * @Copyright: FHZZ
 */
public class HBaseResultBuilder<T> {
	Log logger = LogFactory.getLog(HBaseResultBuilder.class);

	private Class<T> mappedClass;
	private Map<String, PropertyDescriptor> mappedFields;
	private Set<String> mappedProperties;
	private HashSet populatedProperties;
	private BeanWrapper beanWrapper;
	private Result result;
	private String columnFamilyName;
	private T t;

	// 接受一些列参数并实例化要返回的结果对象
	public HBaseResultBuilder(String columnFamilyName, Result result, Class<T> clazz) {
		this.columnFamilyName = columnFamilyName;
		this.result = result;
		this.mappedClass = clazz;
		this.mappedFields = new HashMap<>();
		this.mappedProperties = new HashSet<>();
		this.populatedProperties = new HashSet<>();
		this.t = BeanUtils.instantiate(clazz);
		PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(mappedClass);
		// PropertyDescriptor[] var3 = pds;
		int len = pds.length;
		for (int i = 0; i < len; ++i) {
			PropertyDescriptor pd = pds[i];
			if (pd.getWriteMethod() != null) {
				this.mappedFields.put(this.lowerCaseName(pd.getName()), pd);
				String underscoredName = this.underscoreName(pd.getName());
				if (!this.lowerCaseName(pd.getName()).equals(underscoredName)) {
					this.mappedFields.put(underscoredName, pd);
				}
				this.mappedProperties.add(pd.getName());
			}
		}
		beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(t);
	}

	private String underscoreName(String name) {
		if (!StringUtils.hasLength(name)) {
			return "";
		} else {
			StringBuilder result = new StringBuilder();
			result.append(this.lowerCaseName(name.substring(0, 1)));

			for (int i = 1; i < name.length(); ++i) {
				String s = name.substring(i, i + 1);
				String slc = this.lowerCaseName(s);
				if (!s.equals(slc)) {
					result.append("_").append(slc);
				} else {
					result.append(s);
				}
			}

			return result.toString();
		}
	}

	private String lowerCaseName(String name) {
		return name.toLowerCase(Locale.US);
	}

	// 使用时根据要解析的字段频繁调用此方法即可，仿造java8 流式操作
	public HBaseResultBuilder build(String columnName) {
		byte[] value = result.getValue(columnFamilyName.getBytes(), columnName.getBytes());
		if (value == null || value.length == 0) {
			return this;
		} else {
			String field = this.lowerCaseName(columnName.replaceAll(" ", ""));
			PropertyDescriptor pd = this.mappedFields.get(field);
			if (pd == null) {
				logger.error("HBaseResultBuilder error: can not find property: " + field);
			} else {
				beanWrapper.setPropertyValue(pd.getName(), Bytes.toString(value));
				populatedProperties.add(pd.getName());
			}
		}
		return this;
	}

	// 伪造Java8的即视感，“流最后的终端操作“。
	public T fetch() {
		// 只要有一个属性被解析出来就返回结果对象，毕竟hbase存的是稀疏数据，不一定全量
		if (CollectionUtils.isNotEmpty(populatedProperties)) {
			return this.t;
		} else {
			return null;
		}
	}

}