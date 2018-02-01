package com.fhzz.business.entity;

import javax.persistence.*;

import java.util.Date;

/**
 * UserInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "USER_INFO", schema = "PSOPUSER")
@SuppressWarnings("serial")
public class UserInfo implements java.io.Serializable {

	// Fields

	private String custId;
	private String orgId;
	private String roleId;
	private String custName;
	private String realName;
	private String custPhone;
	private String custEmail;
	private String picPath;
	private String remark;
	private String custCode;
	private String regionId;
	private String custPassword;
	private String custTypeflag;
	private String isVip;
	private String parentId;
	private String custOutCode;
	private String state;
	private String onlineState;
	private Date stateDate;
	private Date effDate;
	private Date expDate;
	private String usable;
	private String custGender;
	private String job;
	private String lastRegisterIp;
	private Date lastRegisterTime;
	private Byte custLevel;
	private Long topicImageSeq;
	private String custDepartment;
	private Double longitude;
	private Double latitude;
	private Integer mobileOnlineStatus;
	private Boolean isZBZ; // 是否为专班长
	/**
	 * 张葳添加
	 */
	private String orgName;// 组织名称
	private String orgLevel;// 组织级别
	private Integer isLeader;// 是否是领导
	private String parentOrgId; // 父组织id
	private String isAdmin; // 是否是管理员

	// Constructors

	/** default constructor */
	public UserInfo() {
	}

	/** full constructor */
	public UserInfo(String custId, String orgId, String roleSequence, String custName, String realName,
			String custPhone, String custEmail, String picPath, String remark, String custCode, String regionId,
			String custPassword, String custTypeflag, String isVip, String parentId, String custOutCode, String state,
			String onlineState, Date stateDate, Date effDate, Date expDate, String usable, String custGender,
			String job, String lastRegisterIp, Date lastRegisterTime, Byte custLevel, Long topicImageSeq,
			String custDepartment, Double longitude, Double latitude, String parentOrgId) {
		this.custId = custId;
		this.orgId = orgId;
		this.roleId = roleSequence;
		this.custName = custName;
		this.realName = realName;
		this.custPhone = custPhone;
		this.custEmail = custEmail;
		this.picPath = picPath;
		this.remark = remark;
		this.custCode = custCode;
		this.regionId = regionId;
		this.custPassword = custPassword;
		this.custTypeflag = custTypeflag;
		this.isVip = isVip;
		this.parentId = parentId;
		this.custOutCode = custOutCode;
		this.state = state;
		this.onlineState = onlineState;
		this.stateDate = stateDate;
		this.effDate = effDate;
		this.expDate = expDate;
		this.usable = usable;
		this.custGender = custGender;
		this.job = job;
		this.lastRegisterIp = lastRegisterIp;
		this.lastRegisterTime = lastRegisterTime;
		this.custLevel = custLevel;
		this.topicImageSeq = topicImageSeq;
		this.custDepartment = custDepartment;
		this.longitude = longitude;
		this.latitude = latitude;
		this.parentOrgId = parentOrgId;
	}

	// Property accessors
	@Id
	@Column(name = "CUST_ID", unique = true, nullable = false, length = 48)
	public String getCustId() {
		return this.custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	@Column(name = "CUST_NAME", length = 48)
	public String getCustName() {
		return this.custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	@Column(name = "REAL_NAME", length = 48)
	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Column(name = "CUST_PHONE", length = 24)
	public String getCustPhone() {
		return this.custPhone;
	}

	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}

	@Column(name = "CUST_EMAIL", length = 24)
	public String getCustEmail() {
		return this.custEmail;
	}

	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}

	@Column(name = "PIC_PATH", length = 128)
	public String getPicPath() {
		return this.picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	@Column(name = "REMARK", length = 128)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "CUST_CODE", length = 24)
	public String getCustCode() {
		return this.custCode;
	}

	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}

	@Column(name = "REGION_ID", length = 24)
	public String getRegionId() {
		return this.regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	@Column(name = "CUST_PASSWORD", length = 128)
	public String getCustPassword() {
		return this.custPassword;
	}

	public void setCustPassword(String custPassword) {
		this.custPassword = custPassword;
	}

	@Column(name = "CUST_TYPEFLAG", length = 3)
	public String getCustTypeflag() {
		return this.custTypeflag;
	}

	public void setCustTypeflag(String custTypeflag) {
		this.custTypeflag = custTypeflag;
	}

	@Column(name = "IS_VIP", length = 3)
	public String getIsVip() {
		return this.isVip;
	}

	public void setIsVip(String isVip) {
		this.isVip = isVip;
	}

	@Column(name = "PARENT_ID", length = 24)
	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Column(name = "CUST_OUT_CODE", length = 18)
	public String getCustOutCode() {
		return this.custOutCode;
	}

	public void setCustOutCode(String custOutCode) {
		this.custOutCode = custOutCode;
	}

	@Column(name = "STATE", length = 3)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "ONLINE_STATE", length = 3)
	public String getOnlineState() {
		return this.onlineState;
	}

	public void setOnlineState(String onlineState) {
		this.onlineState = onlineState;
	}

	@Column(name = "STATE_DATE", length = 7)
	public Date getStateDate() {
		return this.stateDate;
	}

	public void setStateDate(Date stateDate) {
		this.stateDate = stateDate;
	}

	@Column(name = "EFF_DATE", length = 7)
	public Date getEffDate() {
		return this.effDate;
	}

	public void setEffDate(Date effDate) {
		this.effDate = effDate;
	}

	@Column(name = "EXP_DATE", length = 7)
	public Date getExpDate() {
		return this.expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	@Column(name = "USABLE", length = 1)
	public String getUsable() {
		return this.usable;
	}

	public void setUsable(String usable) {
		this.usable = usable;
	}

	@Column(name = "CUST_GENDER", length = 3)
	public String getCustGender() {
		return this.custGender;
	}

	public void setCustGender(String custGender) {
		this.custGender = custGender;
	}

	@Column(name = "JOB", length = 50)
	public String getJob() {
		return this.job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	@Column(name = "LAST_REGISTER_IP", length = 48)
	public String getLastRegisterIp() {
		return this.lastRegisterIp;
	}

	public void setLastRegisterIp(String lastRegisterIp) {
		this.lastRegisterIp = lastRegisterIp;
	}

	@Column(name = "LAST_REGISTER_TIME", length = 7)
	public Date getLastRegisterTime() {
		return this.lastRegisterTime;
	}

	public void setLastRegisterTime(Date lastRegisterTime) {
		this.lastRegisterTime = lastRegisterTime;
	}

	@Column(name = "CUST_LEVEL", precision = 2, scale = 0)
	public Byte getCustLevel() {
		return this.custLevel;
	}

	public void setCustLevel(Byte custLevel) {
		this.custLevel = custLevel;
	}

	@Column(name = "TOPIC_IMAGE_SEQ", precision = 38, scale = 0)
	public Long getTopicImageSeq() {
		return this.topicImageSeq;
	}

	public void setTopicImageSeq(Long topicImageSeq) {
		this.topicImageSeq = topicImageSeq;
	}

	@Column(name = "CUST_DEPARTMENT", length = 64)
	public String getCustDepartment() {
		return this.custDepartment;
	}

	public void setCustDepartment(String custDepartment) {
		this.custDepartment = custDepartment;
	}

	@Column(name = "LONGITUDE", precision = 20, scale = 7)
	public Double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@Column(name = "LATITUDE", precision = 20, scale = 7)
	public Double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Column(name = "ORG_ID")
	public String getOrgId() {
		return orgId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Column(name = "ROLE_SEQUENCE")
	public String getRoleId() {
		return roleId;
	}

	@Transient
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Integer getMobileOnlineStatus() {
		return mobileOnlineStatus;
	}

	public void setMobileOnlineStatus(final Integer mobileOnlineStatus) {
		this.mobileOnlineStatus = mobileOnlineStatus;
	}

	@Transient
	public Boolean getIsZBZ() {
		return isZBZ;
	}

	public void setIsZBZ(Boolean isZBZ) {
		this.isZBZ = isZBZ;
	}

	@Column(name = "IS_LEADER")
	public Integer getIsLeader() {
		return isLeader;
	}

	public void setIsLeader(Integer isLeader) {
		this.isLeader = isLeader;
	}

	@Transient
	public String getParentOrgId() {
		return parentOrgId;
	}

	public void setParentOrgId(String parentOrgId) {
		this.parentOrgId = parentOrgId;
	}

	@Transient
	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Transient
	public String getOrgLevel() {
		return orgLevel;
	}

	public void setOrgLevel(String orgLevel) {
		this.orgLevel = orgLevel;
	}

}