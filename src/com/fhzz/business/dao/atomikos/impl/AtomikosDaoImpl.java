/**
 * 
 */
package com.fhzz.business.dao.atomikos.impl;

import org.springframework.stereotype.Repository;

import com.fhzz.business.dao.atomikos.AtomikosDao;
import com.fhzz.business.entity.Welcome;
import com.fhzz.core.dao.impl.BaseDaoImpl;

/**
 * @author YangYi
 * 
 */
@Repository
public class AtomikosDaoImpl extends BaseDaoImpl<Welcome> implements
		AtomikosDao {

	public void saveByJdbc(Welcome welcome) {
		String sql = "INSERT INTO WELCOME VALUES(?,?,?)";
		getJdbcTemplate().update(sql, welcome.getId(), welcome.getUserId(),
				welcome.getUserName());
	}
}
