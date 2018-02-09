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
 * SysRolesMoudles entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SYS_ROLES_MODULES")
public class SysRolesModules implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = -1196967133324934358L;
	private String id;
	private String roleId;
	private String moduleId;

	private SysRoles sysRoles;
	private SysModules sysModules;

	// Constructors

	/** default constructor */
	public SysRolesModules() {
	}

	/** full constructor */
	public SysRolesModules(String id, SysRoles sysRoles, SysModules sysModules) {
		this.id = id;
		this.sysRoles = sysRoles;
		this.sysModules = sysModules;
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

	@Column(name = "ROLE_ID", insertable = false, updatable = false)
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Column(name = "MODULE_ID", insertable = false, updatable = false)
	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLE_ID", nullable = false)
	public SysRoles getSysRoles() {
		return this.sysRoles;
	}

	public void setSysRoles(SysRoles sysRoles) {
		this.sysRoles = sysRoles;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MODULE_ID", nullable = false)
	public SysModules getSysModules() {
		return this.sysModules;
	}

	public void setSysModules(SysModules sysModules) {
		this.sysModules = sysModules;
	}

}