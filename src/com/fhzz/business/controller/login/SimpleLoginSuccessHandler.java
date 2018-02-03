/**
 * 
 */
package com.fhzz.business.controller.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @author YangYi
 * 
 */
public class SimpleLoginSuccessHandler implements AuthenticationSuccessHandler,
		InitializingBean {

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest arg0,
			HttpServletResponse arg1, Authentication arg2) throws IOException,
			ServletException {
		// TODO Auto-generated method stub
		
	}

}
