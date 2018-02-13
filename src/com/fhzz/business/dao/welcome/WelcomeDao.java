/**
 * 
 */
package com.fhzz.business.dao.welcome;

import java.util.List;

import com.fhzz.business.entity.Welcome;
import com.fhzz.core.dao.BaseDao;

/**
 * @author: YangYi
 * @CreateTime: 2018年1月29日 下午5:08:31
 * @Copyright: FHZZ
 */
public interface WelcomeDao extends BaseDao<Welcome> {
	List<Welcome> queryAllWelcome();
}
