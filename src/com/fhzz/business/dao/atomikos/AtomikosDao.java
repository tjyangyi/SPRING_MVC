/**
 * 
 */
package com.fhzz.business.dao.atomikos;

import com.fhzz.business.entity.Welcome;
import com.fhzz.core.dao.BaseDao;

/**
 * @author YangYi
 * 
 */
public interface AtomikosDao extends BaseDao<Welcome> {
	void saveByJdbc(Welcome welcome);
}
