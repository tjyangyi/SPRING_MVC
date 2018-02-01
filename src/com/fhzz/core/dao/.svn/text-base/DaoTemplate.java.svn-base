package com.fhzz.core.dao;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author: YangYi
 * @CreateTime: 2018年1月29日 下午5:17:45
 * @Copyright: FHZZ
 */
@Repository
public class DaoTemplate {
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private HibernateTemplateSupport hibernateTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public HibernateTemplateSupport getHibernateTemplate() {
		return hibernateTemplate;
	}
}
