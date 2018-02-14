/**
 * 
 */
package com.fhzz.core.log.interceptor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.fhzz.core.log.anotation.OperationLog;
import com.fhzz.core.log.entity.LogRecord;
import com.fhzz.core.log.service.LogRecordService;
import com.fhzz.core.sercurity.entity.SysUsers;

/**
 * @author YangYi
 * 
 */
@Aspect
@Component
public class OperationLogInterceptor {
	Log logger = LogFactory.getLog(OperationLogInterceptor.class);

	@Value("${OperationLogInterceptor.setTargetMethodResult.enable}")
	private boolean enableSetTargetMethodResult;// 日志是否记录方法的返回值,由于列表类型的返回值可能会特别长,此处管理是否记录返回值

	@Value("${OperationLogInterceptor.saveToDatabase.enable}")
	private boolean enableSaveToDatabase;// 日志是否写入到数据库LOG_RECORD表，特别地,@annotation注解的方法不受此控制，一定写入数据库

	@Value("${OperationLogInterceptor.annotationPointcut.enable}")
	private boolean enableAnnotationPointcut;// 根据配置文件是否打开@annotation注解方法的日志

	@Value("${OperationLogInterceptor.actionPointcut.enable}")
	private boolean enableActionPointcut;// 根据配置文件是否打开action层所有方法的日志

	@Value("${OperationLogInterceptor.servicePointcut.enable}")
	private boolean enableServicePointcut;// 根据配置文件是否打开service层所有方法的日志

	@Autowired
	private LogRecordService logRecordService;

	/**
	 * 对@OperationLog注解了的方法进行拦截
	 * 
	 * @param joinPoint
	 *            连接点
	 * @param operationLog
	 *            operationLog注解
	 * @return
	 * 
	 * @throws Throwable
	 */
	@Around("@annotation(operationLog)")
	public Object aroundAnnotationPointcut(ProceedingJoinPoint joinPoint, OperationLog operationLog) throws Throwable {
		if (!enableAnnotationPointcut) {
			return joinPoint.proceed();
		}
		return this.aroundMethod(joinPoint, operationLog, LogRecord.Pointcut.ANNOTATION_OPERATION_LOG);
	}

	/**
	 * 对action层，即com.fhzz.business.action下所有类的所有方法拦截,但不能拦截login下的action
	 * 
	 * @param joinPoint
	 *            连接点
	 * @return
	 * 
	 * @throws Throwable
	 */
	@Around("execution(* com.fhzz.business.controller..*.*(..)) && !execution(* com.fhzz.business.controller.login..*.*(..))")
	public Object aroundActionPointcut(ProceedingJoinPoint joinPoint) throws Throwable {
		if (!enableActionPointcut || this.isOperationLogAnnotationExist(joinPoint)) { // 如果被拦截的方法标有OperationLog注解交由aroundAnnotationPointcut方法处理，此方法中不处理
			return joinPoint.proceed();
		}
		return this.aroundMethod(joinPoint, null, LogRecord.Pointcut.EXECUTION_CONTROLLER);
	}

	/**
	 * 对service层，即com.fhzz.business.service下所有类的所有方法拦截
	 * 
	 * @param joinPoint
	 *            连接点
	 * @return
	 * 
	 * @throws Throwable
	 */
	@Around("execution(* com.fhzz.business.service..*.*(..))")
	public Object aroundServicePointcut(ProceedingJoinPoint joinPoint) throws Throwable {
		if (!enableServicePointcut || this.isOperationLogAnnotationExist(joinPoint)) {// 如果被拦截的方法标有OperationLog注解交由aroundAnnotationPointcut方法处理，此方法中不处理
			return joinPoint.proceed();
		}
		return this.aroundMethod(joinPoint, null, LogRecord.Pointcut.EXECUTION_SERVICE);// Thread.currentThread().getStackTrace()[1].getMethodName()获取当前执行的方法名称
	}

	/**
	 * around增强方法
	 * 
	 * @param joinPoint
	 *            连接点
	 * @param operationLog
	 *            operationLog注解
	 * @param pointcutMethodName
	 *            aroundAnnotationPointcut或aroundExecutionPointcut
	 * @return
	 * 
	 * @throws Throwable
	 */
	private Object aroundMethod(ProceedingJoinPoint joinPoint, OperationLog operationLog, String pointcutMethodName)
			throws Throwable {
		logger.debug("########开始执行:拦截器[" + this.getClass().getSimpleName() + "]切点方法[" + pointcutMethodName
				+ "]########");
		LogRecord logRecord = this.buildLogRecord(joinPoint, operationLog, pointcutMethodName);
		logger.debug("开始执行:类[" + logRecord.getTargetClass() + "]方法[" + logRecord.getTargetMethod() + "]");
		try {
			Object result = joinPoint.proceed();// ***执行真正的方法，并获取返回参数***
			if (enableSetTargetMethodResult) {
				logRecord.setTargetMethodResult(String.valueOf(result));// 返回值
			}
			logger.debug("正常结束:类[" + logRecord.getTargetClass() + "]方法[" + logRecord.getTargetMethod() + "]");
			return result;
		} catch (Exception e) {
			logRecord.setTargetMethodException(e.getMessage());
			logger.debug("异常结束:类[" + logRecord.getTargetClass() + "]方法[" + logRecord.getTargetMethod() + "]");
			throw e;
		} finally {
			logRecord.setOperationEndTime(new Date());// 结束时间
			if (LogRecord.Pointcut.ANNOTATION_OPERATION_LOG.equals(pointcutMethodName) || enableSaveToDatabase) {
				try {
					logRecordService.saveLogRecord(logRecord);
				} catch (Exception e) {
					logger.error(e, e);
				}
			}
			logger.debug(logRecord.toString());
			logger.debug("########结束执行:拦截器[" + this.getClass().getSimpleName() + "]切点方法[" + pointcutMethodName
					+ "]########");
		}
	}

	/**
	 * 构造日志记录
	 * 
	 * @param joinPoint
	 * @param operationLog
	 * @return
	 */
	private LogRecord buildLogRecord(ProceedingJoinPoint joinPoint, OperationLog operationLog, String pointcutMethodName) {
		SysUsers user = (SysUsers) SecurityContextHolder.getContext().getAuthentication().getPrincipal();// 操作人
		String operationUserId = user.getUserId();// 操作人ID
		String operationUsername = user.getUsername();// 操作人姓名
		String operationType = null;
		String operationDesc = null;
		if (operationLog != null) {
			operationType = operationLog.operationType().toString();// 操作类型
			operationDesc = operationLog.operationDesc();// 操作描述
		}
		Signature signature = joinPoint.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;
		Method method = methodSignature.getMethod();// 被调用的方法
		String targetClass = method.getDeclaringClass().getName();// 调用方法所属的类
		String targetMethod = method.getName();// 调用的方法名称
		Object[] params = joinPoint.getArgs();// 获取请求参数
		StringBuffer buff = new StringBuffer();
		for (Object param : params) {
			buff.append(param);
			buff.append(",");
		}
		String targetMethodParams = buff.toString();// 调用方法传入的参数
		Date operationStartTime = new Date();// 开始时间
		LogRecord logRecord = new LogRecord(operationUserId, operationUsername, operationType, operationDesc,
				targetClass, targetMethod, targetMethodParams, operationStartTime, pointcutMethodName);
		return logRecord;
	}

	/**
	 * 被拦截的方法是否标有OperationLog注解<br>
	 * 如果标有此注解，则交由aroundAnnotationPointcut方法处理，其他Around方法直接跳过
	 * 
	 * @param joinPoint
	 * @return
	 */
	private boolean isOperationLogAnnotationExist(ProceedingJoinPoint joinPoint) {
		Signature signature = joinPoint.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;
		Method method = methodSignature.getMethod();// 被调用的方法
		Annotation[] ans = method.getAnnotations();
		for (int i = 0; i < ans.length; i++) {
			if (ans[i].annotationType().equals(OperationLog.class)) {
				return true;
			}
		}
		return false;
	}

}
