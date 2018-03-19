package com.fhzz.business.entity;

// default package

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * DemoTable entity. @author MyEclipse Persistence Tools
 */
@Entity
@DynamicInsert
@DynamicUpdate
@SelectBeforeUpdate
@Table(name = "DEMO_TABLE")
public class DemoTable implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = -419597990685300959L;
	private String id;
	private String name;
	private Integer countNum;
	private Date createTime;
	private Date updateTime;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date customTime;
	
	private String customTimeStr;

	// Constructors

	@Override
	public String toString() {
		return "DemoTable [id=" + id + ", name=" + name + ", countNum="
				+ countNum + ", createTime=" + createTime + ", updateTime="
				+ updateTime + ", customTime=" + customTime + "]";
	}

	/** default constructor */
	public DemoTable() {
	}

	/** minimal constructor */
	public DemoTable(String id) {
		this.id = id;
	}

	/** full constructor */
	public DemoTable(String id, String name, Integer countNum, Date createTime,
			Date updateTime,Date customTime,String customTimeStr) {
		this.id = id;
		this.name = name;
		this.countNum = countNum;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.customTime = customTime;
		this.customTimeStr = customTimeStr;
	}

	// Property accessors
	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	@Column(name = "ID", unique = true, nullable = false, length = 100)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "NAME")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "COUNT_NUM", precision = 22, scale = 0)
	public Integer getCountNum() {
		return countNum;
	}

	public void setCountNum(Integer countNum) {
		this.countNum = countNum;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	@Column(name = "CREATE_TIME", length = 7, updatable = false)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	@Column(name = "UPDATE_TIME", length = 7)
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "CUSTOM_TIME", length = 7)
	public Date getCustomTime() {
		return customTime;
	}

	public void setCustomTime(Date customTime) {
		this.customTime = customTime;
	}

	@Column(name = "CUSTOM_TIME_STR", length = 7)
	public String getCustomTimeStr() {
		return customTimeStr;
	}

	public void setCustomTimeStr(String customTimeStr) {
		this.customTimeStr = customTimeStr;
	}

}