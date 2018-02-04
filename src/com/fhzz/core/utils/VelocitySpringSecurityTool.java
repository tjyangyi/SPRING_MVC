/**
 * 
 */
package com.fhzz.core.utils;

import org.springframework.stereotype.Component;

/**
 * @author YangYi
 * 
 */
@Component("velocitySpringSecurityTool")
public class VelocitySpringSecurityTool {
	boolean ifAnyGranted(String userAuthoritys,String needGrantedAuthoritys) {
		return userAuthoritys.contains(needGrantedAuthoritys);
	}
}
