/**
 * 
 */
package com.fhzz.business.dao.sys;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.fhzz.business.entity.sys.SysUsers;

/**
 * @author YangYi
 * 
 */
public interface SysUsersDao {
	public SysUsers getByUsername(String username);

	public Collection<GrantedAuthority> loadUserAuthorities(String username);
}
