/**
 * 
 */
package com.fhzz.business.dao.welcome;

import com.fhzz.business.entity.Welcome;
import com.fhzz.core.utils.Result;

/**
 * @author: YangYi
 * @CreateTime: 2018年1月29日 下午5:08:31
 * @Copyright: FHZZ
 */
public interface WelcomeDao {
	Result saveWelcome(Welcome welcome);
	
	Result queryAllWelcome();
}
