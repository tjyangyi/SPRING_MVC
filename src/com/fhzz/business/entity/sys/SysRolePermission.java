package com.fhzz.business.entity.sys;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SysRolePermission entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SYS_ROLE_PERMISSION", schema = "PSOPUSER")
public class SysRolePermission implements java.io.Serializable {

	// Fields

	private String id;
	private String roleId;
	private Long permissionId;
	private Timestamp createTime;
	private Timestamp updateTime;

	// Constructors

	/** default constructor */
	public SysRolePermission() {
	}

	/** minimal constructor */
	public SysRolePermission(String id) {
		this.id = id;
	}

	/** full constructor */
	public SysRolePermission(String id, String roleId, Long permissionId,
			Timestamp createTime, Timestamp updateTime) {
		this.id = id;
		this.roleId = roleId;
		this.permissionId = permissionId;
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

	@Column(name = "ROLE_ID", length = 64)
	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Column(name = "PERMISSION_ID", precision = 16, scale = 0)
	public Long getPermissionId() {
		return this.permissionId;
	}

	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
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