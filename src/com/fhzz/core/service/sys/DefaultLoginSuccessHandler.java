/**
 * 
 */
package com.fhzz.core.service.sys;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.fhzz.business.dao.sys.SysUsersDao;
import com.fhzz.business.entity.sys.SysUsers;
import com.fhzz.core.utils.IPUtils;

/**
 * @author YangYi
 * 
 */
@Service
public class DefaultLoginSuccessHandler implements
		AuthenticationSuccessHandler, InitializingBean {
	Log logger = LogFactory.getLog(DefaultLoginSuccessHandler.class);

	@Value("/toIndex.do")
	private String defaultTargetUrl;

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Autowired
	private SysUsersDao sysUsersDao;

	/**
	 * 初始化,验证必须有defaultTargetUrl
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		if (StringUtils.isEmpty(defaultTargetUrl)) {
			throw new Exception("You must configure defaultTargetUrl");
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		this.saveLoginInfo(request, authentication);
		logger.info("Login success,Redirecting to " + this.defaultTargetUrl);
		this.redirectStrategy.sendRedirect(request, response,
				this.defaultTargetUrl);
	}

	private void saveLoginInfo(HttpServletRequest request,
			Authentication authentication) {
		SysUsers user = (SysUsers) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		logger.info("user =" + user);
		logger.info("userId = " + user.getUserId());
		logger.info("username = " + user.getUsername());
		try {
			String ip = IPUtils.getIpAddress(request);
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
