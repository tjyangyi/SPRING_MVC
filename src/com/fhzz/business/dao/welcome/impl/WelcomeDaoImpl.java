/**
 * 
 */
package com.fhzz.business.dao.welcome.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.fhzz.business.dao.welcome.WelcomeDao;
import com.fhzz.business.entity.Welcome;
import com.fhzz.core.dao.impl.BaseDaoImpl;

/**
 * @author: YangYi
 * @CreateTime: 2018年1月30日 上午9:08:33
 * @Copyright: FHZZ
 */
@Repository
public class WelcomeDaoImpl extends BaseDaoImpl<Welcome> implements WelcomeDao {

	@Override
	public List<Welcome> queryAllWelcome() {
		String sql = "SELECT * FROM WELCOME";
		RowMapper<Welcome> rowMapper = BeanPropertyRowMapper.newInstance(Welcome.class);
		List<Welcome> list = getJdbcTemplate().query(sql, rowMapper);
		return list;
	}

}
