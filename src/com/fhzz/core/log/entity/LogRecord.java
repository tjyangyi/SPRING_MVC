package com.fhzz.core.log.entity;

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
@Table(name = "LOG_RECORD")
public class LogRecord implements java.io.Serializable {

	public class Pointcut {
		public static final String ANNOTATION_OPERATION_LOG = "@annotation(operationLog)";
		public static final String EXECUTION_CONTROLLER = "execution(* com.fhzz.business.controller..*.*(..))";
		public static final String EXECUTION_SERVICE = "execution(* com.fhzz.business.service..*.*(..))";
	}

	// Fields
	private static final long serialVersionUID = -3038598717546880774L;
	private String logId;
	private Date operationStartTime;// 方法开始时间
	private Date operationEndTime;// 方法结束时间
	private Long operationElapsedTimeMillis;// 方法耗时
	private String operationType;// 操作类型
	private String operationDesc;// 操作描述
	private String operationUsername;// 操作人NAME
	private String operationUserId;// 操作人ID
	private String targetClass;// 调用方法所属的类
	private String targetMethod;// 调用的方法
	private String targetMethodResult;// 调用方法返回值
	private String targetMethodException;// 调用方法抛出的异常
	private String targetMethodParams;// 调用方法传入的参数
	private String pointcutMethodName;// 切点方法名称;

	/**
	 * 修正返回值,返回值很容易大于2000,此处直接截取前2000字符
	 */
	private void fixTargetMethodResult() {
		if (this.getTargetMethodResult().length() > 2000) {// 处理
			this.targetMethodResult = this.targetMethodResult.substring(0, 2000);
		}
	}

	// Constructors

	/** default constructor */
	public LogRecord() {
	}

	/** minimal constructor */
	public LogRecord(String logId) {
		this.logId = logId;
	}

	public LogRecord(String operationUserId, String operationUsername, String operationType, String operationDesc,
			String targetClass, String targetMethod, String targetMethodParams, Date operationStartTime,
			String pointcutMethodName) {
		super();
		this.operationUserId = operationUserId;
		this.operationUsername = operationUsername;
		this.operationType = operationType;
		this.operationDesc = operationDesc;
		this.targetClass = targetClass;
		this.targetMethod = targetMethod;
		this.targetMethodParams = targetMethodParams;
		this.operationStartTime = operationStartTime;
		this.pointcutMethodName = pointcutMethodName;
	}

	/** full constructor */
	public LogRecord(String logId, String operationUserId, String operationUsername, String operationType,
			String operationDesc, String targetClass, String targetMethod, String targetMethodParams,
			String targetMethodResult, String targetMethodException, Date operationStartTime, Date operationEndTime,
			Long operationElapsedTimeMillis, String pointcutMethodName) {
		this.logId = logId;
		this.operationUserId = operationUserId;
		this.operationUsername = operationUsername;
		this.operationType = operationType;
		this.operationDesc = operationDesc;
		this.targetClass = targetClass;
		this.targetMethod = targetMethod;
		this.targetMethodParams = targetMethodParams;
		this.targetMethodResult = targetMethodResult;
		this.targetMethodException = targetMethodException;
		this.operationStartTime = operationStartTime;
		this.operationEndTime = operationEndTime;
		this.operationElapsedTimeMillis = operationElapsedTimeMillis;
		this.pointcutMethodName = pointcutMethodName;
		this.fixTargetMethodResult();
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

	@Column(name = "OPERATION_USERNAME", length = 100)
	public String getOperationUsername() {
		return operationUsername;
	}

	public void setOperationUsername(String operationUsername) {
		this.operationUsername = operationUsername;
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
		this.fixTargetMethodResult();
	}

	@Column(name = "TARGET_METHOD_EXCEPTION", length = 2000)
	public String getTargetMethodException() {
		return targetMethodException;
	}

	public void setTargetMethodException(String targetMethodException) {
		this.targetMethodException = targetMethodException;
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
		this.operationElapsedTimeMillis = this.getOperationEndTime().getTime() - this.getOperationStartTime().getTime();
	}

	@Column(name = "OPERATION_ELAPSED_TIME_MILLIS", precision = 16, scale = 0)
	public Long getOperationElapsedTimeMillis() {
		return this.operationElapsedTimeMillis;
	}

	public void setOperationElapsedTimeMillis(Long operationElapsedTimeMillis) {
		this.operationElapsedTimeMillis = operationElapsedTimeMillis;
	}

	@Column(name = "POINTCUT_METHOD_NAME", length = 255)
	public String getPointcutMethodName() {
		return pointcutMethodName;
	}

	public void setPointcutMethodName(String pointcutMethodName) {
		this.pointcutMethodName = pointcutMethodName;
	}

	@Override
	public String toString() {
		return System.lineSeparator() + "LogRecord [logId=" + logId + "," + System.lineSeparator()
				+ " operationStartTime=" + operationStartTime + ", operationEndTime=" + operationEndTime
				+ ", operationElapsedTimeMillis=" + operationElapsedTimeMillis + "," + System.lineSeparator()
				+ " operationType=" + operationType + ", operationDesc=" + operationDesc + "," + System.lineSeparator()
				+ " operationUsername=" + operationUsername + ", operationUserId=" + operationUserId + ","
				+ System.lineSeparator() + " targetClass=" + targetClass + ", targetMethod=" + targetMethod + ","
				+ System.lineSeparator() + " targetMethodResult=" + targetMethodResult + "," + System.lineSeparator()
				+ " targetMethodException=" + targetMethodException + "," + System.lineSeparator()
				+ " targetMethodParams=" + targetMethodParams + "," + System.lineSeparator() + " pointcutMethodName="
				+ pointcutMethodName + "]";
	}

}