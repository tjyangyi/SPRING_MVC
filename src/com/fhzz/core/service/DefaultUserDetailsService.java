/**
 * 
 */
package com.fhzz.core.service;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fhzz.business.dao.sys.SysUsersDao;
import com.fhzz.business.entity.sys.SysUsers;

/**
 * @author YangYi
 * 
 */
@Service
public class DefaultUserDetailsService implements UserDetailsService {
	Log logger = LogFactory.getLog(DefaultUserDetailsService.class);
	@Autowired
	private SysUsersDao sysUsersDao;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private UserCache userCache;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		SysUsers user = (SysUsers) this.userCache.getUserFromCache(username);

		if (user == null) {
			user = this.sysUsersDao.getByUsername(username);
			if (user == null)
				throw new UsernameNotFoundException(
						this.messageSource.getMessage(
								"UserDetailsService.userNotFount",
								new Object[] { username }, null));
			// 得到用户的权限
			auths = this.sysUsersDao.loadUserAuthorities(username);
			user.setAuthorities(auths);
		}
		logger.info("*********************" + username
				+ "***********************");
		logger.info(user.getAuthorities());
		logger.info("********************************************************");
		this.userCache.putUserInCache(user);
		return user;
	}

}
