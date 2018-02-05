package com.fhzz.core.log;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface OperationLog {

	// 操作类型
	OperationTypeEnum operationType() default OperationTypeEnum.Undefined;

	// 操作描述
	String operationDesc() default "";

}

