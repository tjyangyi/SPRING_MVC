/**
 * 
 */
package com.fhzz.core.hbase.entity;

/**
 * @author YangYi
 * 
 */
public abstract class HBaseEntity {
	protected String row;

	public String getRow() {
		return row;
	}

	public void setRow(String row) {
		this.row = row;
	}

}
