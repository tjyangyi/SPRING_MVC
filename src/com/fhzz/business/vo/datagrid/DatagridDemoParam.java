/**
 * 
 */
package com.fhzz.business.vo.datagrid;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fhzz.core.vo.PageParam;

/**
 * @author YangYi
 * 
 */
public class DatagridDemoParam extends PageParam {
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;

	private String name;

	private Integer countNum;

	@Override
	public String toString() {
		return "DatagridDemoParam [startTime=" + startTime + ", endTime=" + endTime + ", name=" + name + ", countNum="
				+ countNum + ", getStartIndex()=" + getStartIndex() + ", getEndIndex()=" + getEndIndex()
				+ ", getPage()=" + getPage() + ", getRows()=" + getRows() + "]";
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCountNum() {
		return countNum;
	}

	public void setCountNum(Integer countNum) {
		this.countNum = countNum;
	}

}
