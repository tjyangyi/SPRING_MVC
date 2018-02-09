package com.fhzz.core.sercurity.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * SysUsersRoles entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SYS_USERS_ROLES")
public class SysUsersRoles implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 7287274614364353155L;
	private String id;
	private String userId;
	private String roleId;
	
	private SysUsers sysUsers;
	private SysRoles sysRoles;

	// Constructors

	/** default constructor */
	public SysUsersRoles() {
	}

	/** full constructor */
	public SysUsersRoles(String id, SysUsers sysUsers, SysRoles sysRoles) {
		this.id = id;
		this.sysUsers = sysUsers;
		this.sysRoles = sysRoles;
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
	@Column(name = "USER_ID", nullable = false,insertable=false,updatable=false)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Column(name = "ROLE_ID", nullable = false,insertable=false,updatable=false)
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", nullable = false)
	public SysUsers getSysUsers() {
		return this.sysUsers;
	}

	public void setSysUsers(SysUsers sysUsers) {
		this.sysUsers = sysUsers;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLE_ID", nullable = false)
	public SysRoles getSysRoles() {
		return this.sysRoles;
	}

	public void setSysRoles(SysRoles sysRoles) {
		this.sysRoles = sysRoles;
	}

}