package com.fhzz.core.sercurity.service;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fhzz.core.sercurity.dao.SysUsersDao;
import com.fhzz.core.sercurity.entity.SysUsers;

/**
 * @author YangYi
 * @description 自定义用户验证服务
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
	Log logger = LogFactory.getLog(CustomUserDetailsService.class);
	@Autowired
	private SysUsersDao sysUsersDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysUsers user = this.sysUsersDao.getByUsername(username);
		Collection<GrantedAuthority> auths = this.sysUsersDao.loadUserAuthorities(username);// 得到用户的权限
		user.setAuthorities(auths);
		logger.info("用户登录:" + username + ",拥有权限:" + user.getAuthorities());
		return user;
	}
}
