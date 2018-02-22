/**
 * 
 */
package com.fhzz.core.quartz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fhzz.core.dao.impl.BaseDaoImpl;
import com.fhzz.core.quartz.entity.QrtzJobDetails;

/**
 * @author YangYi
 * 
 */
@Repository
public class QuartzDao extends BaseDaoImpl<Object> {
	@SuppressWarnings("unchecked")
	@Transactional
	public List<QrtzJobDetails> getAllJobDetails() {
		String hql = "FROM QrtzJobDetails";
		List<QrtzJobDetails> list = (List<QrtzJobDetails>) getHibernateTemplate().find(hql);
		return list;
	}

	@Transactional
	public void deleteNotExistedJob(QrtzJobDetails jobDetail) {
		getHibernateTemplate().delete(jobDetail);
	}
}
