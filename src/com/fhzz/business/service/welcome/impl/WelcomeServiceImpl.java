/**
 * 
 */
package com.fhzz.business.service.welcome.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fhzz.business.dao.welcome.WelcomeDao;
import com.fhzz.business.entity.Welcome;
import com.fhzz.business.service.welcome.WelcomeService;

/**
 * @author: YangYi
 * @CreateTime: 2018年1月29日 下午5:07:04
 * @Copyright: FHZZ
 */
@Service
public class WelcomeServiceImpl implements WelcomeService {
	@Autowired
	private WelcomeDao welcomeDao;

	@Override
	public void saveWelcome(Welcome welcome) {
		welcomeDao.saveOrUpdate(welcome);
	}

	@Override
	public List<Welcome> queryAllWelcome() {
		return welcomeDao.queryAllWelcome();
	}

}
