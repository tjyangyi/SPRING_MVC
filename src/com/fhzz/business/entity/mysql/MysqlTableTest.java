package com.fhzz.business.entity.mysql;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MysqlTableTest entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "mysql_table_test", catalog = "medb")
public class MysqlTableTest implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 3349767525210359222L;
	private String id;
	private String name;

	// Constructors

	/** default constructor */
	public MysqlTableTest() {
	}

	/** full constructor */
	public MysqlTableTest(String id, String name) {
		this.id = id;
		this.name = name;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 64)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "NAME", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}