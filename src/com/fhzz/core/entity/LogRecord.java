package com.fhzz.core.entity;
// default package

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * LogRecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "LOG_RECORD", schema = "PSOPUSER")
public class LogRecord implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = -3038598717546880774L;
	private String logId;
	private String operationUserId;
	private String operationUeraname;
	private String operationType;
	private String operationDesc;
	private String targetClass;
	private String targetMethod;
	private String targetMethodParams;
	private String targetMethodResult;
	private Date operationStartTime;
	private Date operationEndTime;
	private Long operationElapsedTimeMillis;

	// Constructors

	/** default constructor */
	public LogRecord() {
	}

	/** minimal constructor */
	public LogRecord(String logId) {
		this.logId = logId;
	}

	/** full constructor */
	public LogRecord(String logId, String operationUserId,
			String operationUeraname, String operationType,
			String operationDesc, String targetClass, String targetMethod,
			String targetMethodParams, String targetMethodResult,
			Date operationStartTime, Date operationEndTime,
			Long operationElapsedTimeMillis) {
		this.logId = logId;
		this.operationUserId = operationUserId;
		this.operationUeraname = operationUeraname;
		this.operationType = operationType;
		this.operationDesc = operationDesc;
		this.targetClass = targetClass;
		this.targetMethod = targetMethod;
		this.targetMethodParams = targetMethodParams;
		this.targetMethodResult = targetMethodResult;
		this.operationStartTime = operationStartTime;
		this.operationEndTime = operationEndTime;
		this.operationElapsedTimeMillis = operationElapsedTimeMillis;
	}

	// Property accessors
	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	@Column(name = "LOG_ID", unique = true, nullable = false, length = 100)
	public String getLogId() {
		return this.logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	@Column(name = "OPERATION_USER_ID", length = 100)
	public String getOperationUserId() {
		return this.operationUserId;
	}

	public void setOperationUserId(String operationUserId) {
		this.operationUserId = operationUserId;
	}

	@Column(name = "OPERATION_UERANAME", length = 100)
	public String getOperationUeraname() {
		return this.operationUeraname;
	}

	public void setOperationUeraname(String operationUeraname) {
		this.operationUeraname = operationUeraname;
	}

	@Column(name = "OPERATION_TYPE")
	public String getOperationType() {
		return this.operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	@Column(name = "OPERATION_DESC")
	public String getOperationDesc() {
		return this.operationDesc;
	}

	public void setOperationDesc(String operationDesc) {
		this.operationDesc = operationDesc;
	}

	@Column(name = "TARGET_CLASS")
	public String getTargetClass() {
		return this.targetClass;
	}

	public void setTargetClass(String targetClass) {
		this.targetClass = targetClass;
	}

	@Column(name = "TARGET_METHOD")
	public String getTargetMethod() {
		return this.targetMethod;
	}

	public void setTargetMethod(String targetMethod) {
		this.targetMethod = targetMethod;
	}

	@Column(name = "TARGET_METHOD_PARAMS", length = 2000)
	public String getTargetMethodParams() {
		return this.targetMethodParams;
	}

	public void setTargetMethodParams(String targetMethodParams) {
		this.targetMethodParams = targetMethodParams;
	}

	@Column(name = "TARGET_METHOD_RESULT", length = 2000)
	public String getTargetMethodResult() {
		return this.targetMethodResult;
	}

	public void setTargetMethodResult(String targetMethodResult) {
		this.targetMethodResult = targetMethodResult;
	}

	@Column(name = "OPERATION_START_TIME", length = 7)
	public Date getOperationStartTime() {
		return this.operationStartTime;
	}

	public void setOperationStartTime(Date operationStartTime) {
		this.operationStartTime = operationStartTime;
	}

	@Column(name = "OPERATION_END_TIME", length = 7)
	public Date getOperationEndTime() {
		return this.operationEndTime;
	}

	public void setOperationEndTime(Date operationEndTime) {
		this.operationEndTime = operationEndTime;
	}

	@Column(name = "OPERATION_ELAPSED_TIME_MILLIS", precision = 16, scale = 0)
	public Long getOperationElapsedTimeMillis() {
		return this.operationElapsedTimeMillis;
	}

	public void setOperationElapsedTimeMillis(Long operationElapsedTimeMillis) {
		this.operationElapsedTimeMillis = operationElapsedTimeMillis;
	}

}