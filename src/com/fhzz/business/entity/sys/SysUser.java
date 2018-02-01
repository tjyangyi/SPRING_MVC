package com.fhzz.business.entity.sys;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * SysUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SYS_USER", schema = "PSOPUSER")
public class SysUser implements java.io.Serializable {

	// Fields

	private String userId;
	private String password;
	private String username;
	private String status;
	private Timestamp createTime;
	private Timestamp updateTime;
	private Set<SysUserRole> sysUserRoles = new HashSet<SysUserRole>(0);

	// Constructors

	/** default constructor */
	public SysUser() {
	}

	/** minimal constructor */
	public SysUser(String userId) {
		this.userId = userId;
	}

	/** full constructor */
	public SysUser(String userId, String password, String username,
			String status, Timestamp createTime, Timestamp updateTime,
			Set<SysUserRole> sysUserRoles) {
		this.userId = userId;
		this.password = password;
		this.username = username;
		this.status = status;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.sysUserRoles = sysUserRoles;
	}

	// Property accessors
	@Id
	@Column(name = "USER_ID", unique = true, nullable = false, length = 64)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "PASSWORD")
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "USERNAME", length = 64)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "STATUS")
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "CREATE_TIME", length = 7)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "UPDATE_TIME", length = 7)
	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysUser")
	public Set<SysUserRole> getSysUserRoles() {
		return this.sysUserRoles;
	}

	public void setSysUserRoles(Set<SysUserRole> sysUserRoles) {
		this.sysUserRoles = sysUserRoles;
	}

}