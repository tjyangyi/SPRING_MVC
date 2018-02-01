package com.fhzz.business.entity.sys;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * SysUserRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SYS_USER_ROLE", schema = "PSOPUSER")
public class SysUserRole implements java.io.Serializable {

	// Fields

	private String id;
	private SysUser sysUser;
	private String roleId;
	private Timestamp createTime;
	private Timestamp updateTime;

	// Constructors

	/** default constructor */
	public SysUserRole() {
	}

	/** minimal constructor */
	public SysUserRole(String id) {
		this.id = id;
	}

	/** full constructor */
	public SysUserRole(String id, SysUser sysUser, String roleId,
			Timestamp createTime, Timestamp updateTime) {
		this.id = id;
		this.sysUser = sysUser;
		this.roleId = roleId;
		this.createTime = createTime;
		this.updateTime = updateTime;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	public SysUser getSysUser() {
		return this.sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	@Column(name = "ROLE_ID", length = 64)
	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
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

}