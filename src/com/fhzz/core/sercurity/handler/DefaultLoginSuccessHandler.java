/**
 * 
 */
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
import org.springframework.dao.DataAccessException;
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
public class DefaultLoginSuccessHandler implements AuthenticationSuccessHandler {
	Log logger = LogFactory.getLog(DefaultLoginSuccessHandler.class);

	@Value("/toIndex.do")
	private String defaultTargetUrl;
	@Autowired
	private SysUsersDao sysUsersDao;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		this.saveLoginInfo(request, authentication);
		logger.info("Login success,forward to " + this.defaultTargetUrl);
		request.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());// 登录成功之后将SECURITY放入上下文中
		request.getRequestDispatcher(this.defaultTargetUrl).forward(request, response);
	}

	private void saveLoginInfo(HttpServletRequest request, Authentication authentication) {
		SysUsers user = (SysUsers) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		logger.info("user =" + user);
		logger.info("userId = " + user.getUserId());
		logger.info("username = " + user.getUsername());
		try {
			String ip = HTTPUtils.getIpAddress(request);
			user.setLastLogin(new Date());
			user.setLoginIp(ip);
			this.sysUsersDao.saveOrUpdateSysUser(user);
		} catch (DataAccessException e) {
			if (logger.isWarnEnabled()) {
				logger.warn("无法更新用户登录信息至数据库", e);
			}
		}
	}

}
