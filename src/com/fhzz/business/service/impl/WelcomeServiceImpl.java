/**
 * 
 */
package com.fhzz.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fhzz.business.dao.IWelcomeDao;
import com.fhzz.business.entity.Welcome;
import com.fhzz.business.service.IWelcomeService;
import com.fhzz.core.utils.Result;

/**
 * @author: YangYi
 * @CreateTime: 2018年1月29日 下午5:07:04
 * @Copyright: FHZZ
 */
@Service
public class WelcomeServiceImpl implements IWelcomeService {
	@Autowired
	private IWelcomeDao welcomeDao;

	@Override
	public Result saveWelcome(Welcome welcome) {
		return welcomeDao.saveWelcome(welcome);
	}

}
