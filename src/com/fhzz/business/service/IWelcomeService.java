/**
 * 
 */
package com.fhzz.business.service;

import com.fhzz.business.entity.Welcome;
import com.fhzz.core.utils.Result;

/**
 * @author: YangYi
 * @CreateTime: 2018年1月29日 下午5:06:52
 * @Copyright: FHZZ
 */
public interface IWelcomeService {
	Result saveWelcome(Welcome welcome);
}
