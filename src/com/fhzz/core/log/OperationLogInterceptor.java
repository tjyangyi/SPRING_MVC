/**
 * 
 */
package com.fhzz.core.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.fhzz.business.entity.sys.SysUsers;

/**
 * @author YangYi
 * 
 */
@Aspect
@Component
public class OperationLogInterceptor {
	// 环绕触发 
	// 触发条件为：注解为operationLog的
	@Around("@annotation(operationLog)")
	public Object doAroundMethod(ProceedingJoinPoint joinPoint, OperationLog operationLog)
			throws Throwable {
		long startTime = System.currentTimeMillis();// 开始时间

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
		System.out.println("操作人: " + user.getName() + "  操作类型: " + operTypeStr + "   操作描述：" + operDesc);

		long endTime = System.currentTimeMillis();// 结束时间
		float excTime = (float) (endTime - startTime) / 1000;
		System.out.println("执行时间:" + excTime + "s");
		System.out
				.println("#######################分隔符##########################");
		return result;

	}
}
