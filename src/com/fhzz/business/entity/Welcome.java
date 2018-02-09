package com.fhzz.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AssistFile entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WELCOME")
public class Welcome implements java.io.Serializable {
	// Fields
	private static final long serialVersionUID = -5923136026371408857L;
	private String id;
	private String userId;
	private String userName;

	// Constructors

	@Override
	public String toString() {
		return "Welcome [id=" + id + ", userId=" + userId + ", userName="
				+ userName + "]";
	}

	/** default constructor */
	public Welcome() {
	}

	/** minimal constructor */
	public Welcome(String id) {
		this.id = id;
	}

	/** full constructor */
	public Welcome(String id, String userId, String userName) {
		super();
		this.id = id;
		this.userId = userId;
		this.userName = userName;
	}

	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 64)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "USER_ID", length = 64)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "USER_NAME", length = 64)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}