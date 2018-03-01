package com.fhzz.core.sercurity.handler;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fhzz.core.sercurity.dao.SysUsersDao;
import com.fhzz.core.sercurity.entity.SysUsers;
import com.fhzz.core.utils.HTTPUtils;

/**
 * @author YangYi
 * 
 */
@Service
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {
	Log logger = LogFactory.getLog(CustomLoginSuccessHandler.class);

	@Value("/toIndex.do")
	private String defaultTargetUrl;
	@Autowired
	private SysUsersDao sysUsersDao;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		this.saveLoginInfo(request, authentication);
		logger.info("登录成功,即将forward:" + this.defaultTargetUrl);
		// 登录成功之后将SECURITY放入上下文中
		request.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
		request.getRequestDispatcher(this.defaultTargetUrl).forward(request, response);
	}

	private void saveLoginInfo(HttpServletRequest request, Authentication authentication) {
		SysUsers user = (SysUsers) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String ip = HTTPUtils.getIpAddress(request);
		user.setLastLogin(new Date());
		user.setLoginIp(ip);
		logger.info("登录用户:" + user);
		this.sysUsersDao.saveOrUpdateSysUser(user);
	}

}
