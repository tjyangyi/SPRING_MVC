package com.fhzz.core.hbase.mapper;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.hadoop.hbase.RowMapper;

import com.fhzz.core.hbase.entity.HBaseEntity;

public class BeanPropertyRowMapper<T extends HBaseEntity> implements RowMapper<T> {

	/** Logger available to subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	/** The class we are mapping to */
	private Class<T> mappedClass;

	/** Map of the fields we provide mapping for */
	private Map<String, PropertyDescriptor> mappedFields;


	public BeanPropertyRowMapper(Class<T> mappedClass) {
		initialize(mappedClass);
	}

	protected void initialize(Class<T> mappedClass) {
		this.mappedClass = mappedClass;
		this.mappedFields = new HashMap<>();
		PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(mappedClass);
		for (PropertyDescriptor pd : pds) {
			if (pd.getWriteMethod() != null && pd.getReadMethod() != null) {
				Method getMethod = pd.getReadMethod();// 获得get方法
				if(getMethod.isAnnotationPresent(Column.class)){
					Column column = getMethod.getAnnotation(Column.class);
					this.mappedFields.put(column.name(), pd);
				}
			}
		}
	}

	@Override
	public T mapRow(Result result, int rowNum) throws Exception {
		T mappedObject = BeanUtils.instantiateClass(this.mappedClass);
		BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(mappedObject);
		List<Cell> cellList = result.listCells();
		String row = null;
		for (Cell cell : cellList) {
			row = Bytes.toString(cell.getRowArray(), cell.getRowOffset(), cell.getRowLength());
			String qualifier = Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(),
					cell.getQualifierLength());
			String value = Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
			PropertyDescriptor pd = (this.mappedFields != null ? this.mappedFields.get(qualifier) : null);
			System.out.println("qualifier = " + qualifier +  "  value = "+ value);
			if (pd != null) {
				try {
					System.out.println("pd.getName() = " + pd.getName());
					bw.setPropertyValue(pd.getName(), value);
				} catch (Exception ex) {
					throw new DataRetrievalFailureException("Unable to map qualifier '" + qualifier + "' to property '"
							+ pd.getName() + "'", ex);
				}
			} else {
				if (rowNum == 0 && logger.isDebugEnabled()) {
					logger.debug("No property found for qualifier '" + qualifier);
				}
			}
		}
		mappedObject.setRow(row);
		return mappedObject;
	}

	public static <T extends HBaseEntity> BeanPropertyRowMapper<T> newInstance(Class<T> mappedClass) {
		return new BeanPropertyRowMapper<>(mappedClass);
	}

	public final Class<T> getMappedClass() {
		return this.mappedClass;
	}

}
