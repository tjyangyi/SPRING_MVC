/**
 * 
 */
package com.fhzz.core.quartz.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fhzz.core.dao.BaseDaoImpl;
import com.fhzz.core.quartz.entity.QrtzCronTriggers;
import com.fhzz.core.quartz.entity.QrtzJobDetails;
import com.fhzz.core.quartz.entity.QrtzTriggers;

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
		List<QrtzJobDetails> list = (List<QrtzJobDetails>) getHibernateTemplate()
				.find(hql);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<QrtzTriggers> getQrtzTriggersByJob(String jobName,
			String jobGroupName) {
		String hql = "FROM QrtzTriggers q WHERE q.qrtzJobDetails.id.jobName = ? AND q.qrtzJobDetails.id.jobGroup = ?";
		List<QrtzTriggers> list = (List<QrtzTriggers>) getHibernateTemplate()
				.find(hql, jobName, jobGroupName);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<QrtzCronTriggers> getCronTriggersByTriggers(
			List<QrtzTriggers> triggers) {
		String hql = "FROM QrtzCronTriggers q WHERE q.id.triggerName = ? AND q.id.triggerGroup = ?";
		List<QrtzCronTriggers> list = new ArrayList<QrtzCronTriggers>();
		for (QrtzTriggers trigger : triggers) {
			List<QrtzCronTriggers> qrtzCronTriggers = (List<QrtzCronTriggers>) getHibernateTemplate()
					.find(hql, trigger.getId().getTriggerName(),
							trigger.getId().getTriggerGroup());
			list.addAll(qrtzCronTriggers);
		}
		return list;
	}

	@Transactional
	public void deleteNotExistedJob(List<QrtzCronTriggers> cronTriggers, List<QrtzTriggers> triggers, QrtzJobDetails jobDetail) {
		getHibernateTemplate().deleteAll(cronTriggers);
		getHibernateTemplate().deleteAll(triggers);
		getHibernateTemplate().delete(jobDetail);
	}
}
