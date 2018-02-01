/**
 * 
 */
package com.fhzz.business.dao.impl;

import org.springframework.stereotype.Repository;

import com.fhzz.business.dao.IWelcomeDao;
import com.fhzz.business.entity.Welcome;
import com.fhzz.core.dao.DaoTemplate;
import com.fhzz.core.utils.Result;

/**
 * @author: YangYi
 * @CreateTime: 2018年1月30日 上午9:08:33
 * @Copyright: FHZZ
 */
@Repository
public class WelcomeDaoImpl extends DaoTemplate implements IWelcomeDao {

	@Override
	public Result saveWelcome(Welcome welcome) {
		getHibernateTemplate().saveOrUpdate(welcome);
		return Result.getSuccessInstance();
	}

}
