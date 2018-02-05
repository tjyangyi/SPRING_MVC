/**
 * 
 */
package com.fhzz.business.dao.sys.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import com.fhzz.business.dao.sys.SysUsersDao;
import com.fhzz.business.entity.sys.SysAuthorities;
import com.fhzz.business.entity.sys.SysUsers;
import com.fhzz.core.dao.DaoTemplate;

/**
 * @author YangYi
 * 
 */
@Repository
public class SysUsersDaoImpl extends DaoTemplate implements SysUsersDao {

	@Override
	public SysUsers getByUsername(String username) {
		return getHibernateTemplate().findUniqueBy(SysUsers.class, "username",
				username);
	}

	@Override
	public Collection<GrantedAuthority> loadUserAuthorities(String username) {
		List<SysAuthorities> list = this.getSysAuthoritiesByUsername(username);
		List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		for (SysAuthorities authority : list) {
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(
					authority.getAuthorityMark());
			auths.add(grantedAuthority);
		}
		return auths;
	}

	/**
	 * 先根据用户名获取到SysAuthorities集合
	 * 
	 * @param username
	 * @return
	 */
	private List<SysAuthorities> getSysAuthoritiesByUsername(String username) {
		String sql = "SELECT * FROM SYS_AUTHORITIES WHERE AUTHORITY_ID IN( "
				+ "SELECT DISTINCT AUTHORITY_ID FROM SYS_ROLES_AUTHORITIES  S1 "
				+ "JOIN SYS_USERS_ROLES S2 ON S1.ROLE_ID = S2.ROLE_ID "
				+ "JOIN SYS_USERS S3 ON S3.USER_ID = S2.USER_ID AND S3.USERNAME=?)";
		List<SysAuthorities> list = getJdbcTemplate().query(sql,
				BeanPropertyRowMapper.newInstance(SysAuthorities.class),
				username);
		return list;
	}

}
