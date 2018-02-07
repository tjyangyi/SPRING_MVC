/**
 * 
 */
package com.fhzz.business.service.welcome.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fhzz.business.dao.welcome.IWelcomeDao;
import com.fhzz.business.entity.Welcome;
import com.fhzz.business.service.welcome.WelcomeService;
import com.fhzz.core.utils.Result;

/**
 * @author: YangYi
 * @CreateTime: 2018年1月29日 下午5:07:04
 * @Copyright: FHZZ
 */
@Service
public class WelcomeServiceImpl implements WelcomeService {
	@Autowired
	private IWelcomeDao welcomeDao;

	@Override
	public Result saveWelcome(Welcome welcome) {
		 return welcomeDao.saveWelcome(welcome);
	}

	@Override
	public Result queryAllWelcome() {
		return welcomeDao.queryAllWelcome();
	}

}
