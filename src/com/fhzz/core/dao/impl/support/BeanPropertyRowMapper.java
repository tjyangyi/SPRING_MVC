package com.fhzz.core.dao.impl.support;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.NotWritablePropertyException;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

public class BeanPropertyRowMapper<T> implements RowMapper<T> {

	/** Logger available to subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	/** The class we are mapping to */
	private Class<T> mappedClass;

	/** Map of the fields we provide mapping for */
	private Map<String, PropertyDescriptor> mappedFields;

	/** Set of bean properties we provide mapping for */
	private Set<String> mappedProperties;

	public BeanPropertyRowMapper(Class<T> mappedClass) {
		initialize(mappedClass);
	}

	protected void initialize(Class<T> mappedClass) {
		this.mappedClass = mappedClass;
		this.mappedFields = new HashMap<>();
		this.mappedProperties = new HashSet<>();
		PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(mappedClass);
		for (PropertyDescriptor pd : pds) {
			if (pd.getWriteMethod() != null && pd.getReadMethod() != null) {
				Method getMethod = pd.getReadMethod();// 获得get方法
				Column column = getMethod.getAnnotation(Column.class);
				this.mappedFields.put(column.name(), pd);
				this.mappedProperties.add(column.name());
			}
		}
	}

	@Override
	public T mapRow(Result result, int rowNum) throws Exception {
		T mappedObject = BeanUtils.instantiateClass(this.mappedClass);
		BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(mappedObject);
		List<Cell> cellList = result.listCells();
		for (Cell cell : cellList) {
			String row = Bytes.toString(cell.getRowArray(), cell.getRowOffset(), cell.getRowLength());
			String qualifier = Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(),
					cell.getQualifierLength());
			String value = Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
			PropertyDescriptor pd = (this.mappedFields != null ? this.mappedFields.get(qualifier) : null);
			if (pd != null) {
				try {
					try {
						bw.setPropertyValue(pd.getName(), value);
					} catch (TypeMismatchException ex) {
					}
				} catch (NotWritablePropertyException ex) {
					throw new DataRetrievalFailureException("Unable to map qualifier '" + qualifier + "' to property '"
							+ pd.getName() + "'", ex);
				}
			} else {
				// No PropertyDescriptor found
				if (rowNum == 0 && logger.isDebugEnabled()) {
					logger.debug("No property found for qualifier '" + qualifier);
				}
			}
		}
		return mappedObject;
	}

	public T mapRow(ResultSet rs, int rowNumber) throws SQLException {
		T mappedObject = BeanUtils.instantiateClass(this.mappedClass);
		BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(mappedObject);

		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCount = rsmd.getColumnCount();
		// Set<String> populatedProperties = (isCheckFullyPopulated() ? new
		// HashSet<>() : null);
		// Set<String> populatedProperties =new HashSet<>() ;
		Set<String> populatedProperties = null;

		for (int index = 1; index <= columnCount; index++) {
			String column = JdbcUtils.lookupColumnName(rsmd, index);
			String field = lowerCaseName(column.replaceAll(" ", ""));
			PropertyDescriptor pd = (this.mappedFields != null ? this.mappedFields.get(field) : null);
			if (pd != null) {
				try {
					Object value = getColumnValue(rs, index, pd);
					try {
						bw.setPropertyValue(pd.getName(), value);
					} catch (TypeMismatchException ex) {
					}
					if (populatedProperties != null) {
						populatedProperties.add(pd.getName());
					}
				} catch (NotWritablePropertyException ex) {
					throw new DataRetrievalFailureException("Unable to map column '" + column + "' to property '"
							+ pd.getName() + "'", ex);
				}
			} else {
				// No PropertyDescriptor found
				if (rowNumber == 0 && logger.isDebugEnabled()) {
					logger.debug("No property found for column '" + column + "' mapped to field '" + field + "'");
				}
			}
		}
		return mappedObject;
	}

	protected String underscoreName(String name) {
		if (!StringUtils.hasLength(name)) {
			return "";
		}
		StringBuilder result = new StringBuilder();
		result.append(lowerCaseName(name.substring(0, 1)));
		for (int i = 1; i < name.length(); i++) {
			String s = name.substring(i, i + 1);
			String slc = lowerCaseName(s);
			if (!s.equals(slc)) {
				result.append("_").append(slc);
			} else {
				result.append(s);
			}
		}
		return result.toString();
	}

	protected String lowerCaseName(String name) {
		return name.toLowerCase(Locale.US);
	}

	protected Object getColumnValue(ResultSet rs, int index, PropertyDescriptor pd) throws SQLException {
		return JdbcUtils.getResultSetValue(rs, index, pd.getPropertyType());
	}

	public static <T> BeanPropertyRowMapper<T> newInstance(Class<T> mappedClass) {
		return new BeanPropertyRowMapper<>(mappedClass);
	}

	public final Class<T> getMappedClass() {
		return this.mappedClass;
	}

}
