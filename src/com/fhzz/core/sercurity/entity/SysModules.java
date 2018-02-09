package com.fhzz.core.sercurity.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**
 * SysModules entity. @author MyEclipse Persistence Tools
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "SYS_MODULES")
public class SysModules implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 8055718517818553882L;
	private String moduleId;
	private String moduleName;
	private String moduleDesc;
	private String moduleType;
	private String parent;
	private String moduleUrl;
	private Integer ILevel;
	private Boolean leaf;
	private String application;
	private String controller;
	private Boolean enable;
	private Integer priority;
	private Set<SysResources> sysResourceses = new HashSet<SysResources>(0);
	private Set<SysRolesModules> sysRolesMoudleses = new HashSet<SysRolesModules>(
			0);

	// Constructors

	/** default constructor */
	public SysModules() {
	}

	/** minimal constructor */
	public SysModules(String moduleId, String moduleName) {
		this.moduleId = moduleId;
		this.moduleName = moduleName;
	}

	/** full constructor */
	public SysModules(String moduleId, String moduleName, String moduleDesc,
			String moduleType, String parent, String moduleUrl,
			Integer ILevel, Boolean leaf, String application,
			String controller, Boolean enable, Integer priority,
			Set<SysResources> sysResourceses,
			Set<SysRolesModules> sysRolesMoudleses) {
		this.moduleId = moduleId;
		this.moduleName = moduleName;
		this.moduleDesc = moduleDesc;
		this.moduleType = moduleType;
		this.parent = parent;
		this.moduleUrl = moduleUrl;
		this.ILevel = ILevel;
		this.leaf = leaf;
		this.application = application;
		this.controller = controller;
		this.enable = enable;
		this.priority = priority;
		this.sysResourceses = sysResourceses;
		this.sysRolesMoudleses = sysRolesMoudleses;
	}

	// Property accessors
	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	@Column(name = "MODULE_ID", unique = true, nullable = false, length = 100)
	public String getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	@Column(name = "MODULE_NAME", nullable = false, length = 100)
	public String getModuleName() {
		return this.moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	@Column(name = "MODULE_DESC", length = 200)
	public String getModuleDesc() {
		return this.moduleDesc;
	}

	public void setModuleDesc(String moduleDesc) {
		this.moduleDesc = moduleDesc;
	}

	@Column(name = "MODULE_TYPE", length = 100)
	public String getModuleType() {
		return this.moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}

	@Column(name = "PARENT", length = 100)
	public String getParent() {
		return this.parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	@Column(name = "MODULE_URL", length = 100)
	public String getModuleUrl() {
		return this.moduleUrl;
	}

	public void setModuleUrl(String moduleUrl) {
		this.moduleUrl = moduleUrl;
	}

	@Column(name = "I_LEVEL", precision = 22, scale = 0)
	public Integer getILevel() {
		return this.ILevel;
	}

	public void setILevel(Integer ILevel) {
		this.ILevel = ILevel;
	}

	@Column(name = "LEAF", precision = 22, scale = 0)
	public Boolean getLeaf() {
		return this.leaf;
	}

	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}

	@Column(name = "APPLICATION", length = 100)
	public String getApplication() {
		return this.application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	@Column(name = "CONTROLLER", length = 100)
	public String getController() {
		return this.controller;
	}

	public void setController(String controller) {
		this.controller = controller;
	}

	@Column(name = "ENABLE", precision = 1, scale = 0)
	public Boolean getEnable() {
		return this.enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	@Column(name = "PRIORITY", precision = 22, scale = 0)
	public Integer getPriority() {
		return this.priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysModules")
	public Set<SysResources> getSysResourceses() {
		return this.sysResourceses;
	}

	public void setSysResourceses(Set<SysResources> sysResourceses) {
		this.sysResourceses = sysResourceses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysModules")
	public Set<SysRolesModules> getSysRolesMoudleses() {
		return this.sysRolesMoudleses;
	}

	public void setSysRolesMoudleses(Set<SysRolesModules> sysRolesMoudleses) {
		this.sysRolesMoudleses = sysRolesMoudleses;
	}

}