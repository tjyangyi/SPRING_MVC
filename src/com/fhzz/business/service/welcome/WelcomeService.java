/**
 * 
 */
package com.fhzz.business.service.welcome;

import java.util.List;

import com.fhzz.business.entity.Welcome;

/**
 * @author: YangYi
 * @CreateTime: 2018年1月29日 下午5:06:52
 * @Copyright: FHZZ
 */
public interface WelcomeService {
	void saveWelcome(Welcome welcome);
	
	List<Welcome> queryAllWelcome();
}
