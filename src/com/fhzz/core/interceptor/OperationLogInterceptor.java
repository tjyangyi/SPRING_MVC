/**
 * 
 */
package com.fhzz.core.interceptor;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fhzz.business.entity.sys.SysUsers;
import com.fhzz.core.annotation.OperationLog;
import com.fhzz.core.dao.log.LogRecordDao;
import com.fhzz.core.entity.LogRecord;

/**
 * @author YangYi
 * 
 */
@Aspect
@Service
public class OperationLogInterceptor {
	@Autowired
	private LogRecordDao LogRecordDao;
	
	// 环绕触发
	// 触发条件为：注解为operationLog的
	@Around("@annotation(operationLog)")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = { Exception.class })
	public Object doAroundMethod(ProceedingJoinPoint joinPoint,
			OperationLog operationLog) throws Throwable {
		long startTime = System.currentTimeMillis();// 开始时间

		//String methodName = joinPoint.getSignature().getName();
		Signature signature = joinPoint.getSignature();    
		MethodSignature methodSignature = (MethodSignature)signature;
		Method targetMethod = methodSignature.getMethod();
		System.out.println("classname:" + targetMethod.getDeclaringClass().getName());    
		System.out.println("superclass:" + targetMethod.getDeclaringClass().getSuperclass().getName());    
		System.out.println("isinterface:" + targetMethod.getDeclaringClass().isInterface());    
		System.out.println("target:" + joinPoint.getTarget().getClass().getName());    
		System.out.println("proxy:" + joinPoint.getThis().getClass().getName());    
		System.out.println("method:" + targetMethod.getName());    
		
		Object[] params = joinPoint.getArgs();// 获取请求参数
		System.out.println("监听到传入参数为:");
		for (Object param : params) {
			System.out.println(param);
		}

		// ###################上面代码为方法执行前#####################
		Object result = joinPoint.proceed();// 执行方法，获取返回参数
		// ###################下面代码为方法执行后#####################
		System.out.println("返回值为:" + result);

		SysUsers user = (SysUsers) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();// 操作人

		String operTypeStr = operationLog.operationType().toString();// 操作类型
		String operDesc = operationLog.operationDesc();
		System.out.println("操作人: " + user.getName() + "  操作类型: " + operTypeStr
				+ "   操作描述：" + operDesc);

		long endTime = System.currentTimeMillis();// 结束时间
		float excTime = (float) (endTime - startTime) / 1000;
		System.out.println("执行时间:" + excTime + "s");
		System.out
				.println("#######################分隔符##########################");
		
		LogRecord logRecord = new LogRecord();
		logRecord.setOperationDesc(operDesc);
		logRecord.setOperationType(operTypeStr);
		LogRecordDao.saveLogRecord(logRecord);
		return result;

	}
}
