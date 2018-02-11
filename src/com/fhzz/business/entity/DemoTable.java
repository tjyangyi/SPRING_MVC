package com.fhzz.business.entity;

// default package

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * DemoTable entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DEMO_TABLE")
public class DemoTable implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = -419597990685300959L;
	private String id;
	private String name;
	private Integer count;
	private Date createTime;

	// Constructors

	@Override
	public String toString() {
		return "DemoTable [id=" + id + ", name=" + name + ", count=" + count + ", createTime=" + createTime + "]";
	}

	/** default constructor */
	public DemoTable() {
	}

	/** minimal constructor */
	public DemoTable(String id) {
		this.id = id;
	}

	/** full constructor */
	public DemoTable(String id, String name, Integer count, Date createTime) {
		this.id = id;
		this.name = name;
		this.count = count;
		this.createTime = createTime;
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

	@Column(name = "COUNT", precision = 22, scale = 0)
	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Column(name = "CREATE_TIME", length = 7)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}