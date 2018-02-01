package com.fhzz.business.entity.sys;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * SysPermission entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SYS_PERMISSION", schema = "PSOPUSER")
public class SysPermission implements java.io.Serializable {

	// Fields

	private Long permissionId;
	private SysPermission sysPermission;
	private String permissionName;
	private Long permissionLevel;
	private Timestamp createTime;
	private Timestamp updateTime;
	private Set<SysPermission> sysPermissions = new HashSet<SysPermission>(0);

	// Constructors

	/** default constructor */
	public SysPermission() {
	}

	/** minimal constructor */
	public SysPermission(Long permissionId) {
		this.permissionId = permissionId;
	}

	/** full constructor */
	public SysPermission(Long permissionId, SysPermission sysPermission,
			String permissionName, Long permissionLevel, Timestamp createTime,
			Timestamp updateTime, Set<SysPermission> sysPermissions) {
		this.permissionId = permissionId;
		this.sysPermission = sysPermission;
		this.permissionName = permissionName;
		this.permissionLevel = permissionLevel;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.sysPermissions = sysPermissions;
	}

	// Property accessors
	@Id
	@Column(name = "PERMISSION_ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getPermissionId() {
		return this.permissionId;
	}

	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_PERMISSION_ID")
	public SysPermission getSysPermission() {
		return this.sysPermission;
	}

	public void setSysPermission(SysPermission sysPermission) {
		this.sysPermission = sysPermission;
	}

	@Column(name = "PERMISSION_NAME")
	public String getPermissionName() {
		return this.permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	@Column(name = "PERMISSION_LEVEL", precision = 16, scale = 0)
	public Long getPermissionLevel() {
		return this.permissionLevel;
	}

	public void setPermissionLevel(Long permissionLevel) {
		this.permissionLevel = permissionLevel;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysPermission")
	public Set<SysPermission> getSysPermissions() {
		return this.sysPermissions;
	}

	public void setSysPermissions(Set<SysPermission> sysPermissions) {
		this.sysPermissions = sysPermissions;
	}

}