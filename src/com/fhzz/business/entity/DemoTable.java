package com.fhzz.business.entity;
// default package

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DemoTable entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DEMO_TABLE")
public class DemoTable implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private BigDecimal count;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public DemoTable() {
	}

	/** minimal constructor */
	public DemoTable(String id) {
		this.id = id;
	}

	/** full constructor */
	public DemoTable(String id, String name, BigDecimal count,
			Timestamp createTime) {
		this.id = id;
		this.name = name;
		this.count = count;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
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
	public BigDecimal getCount() {
		return this.count;
	}

	public void setCount(BigDecimal count) {
		this.count = count;
	}

	@Column(name = "CREATE_TIME", length = 7)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}