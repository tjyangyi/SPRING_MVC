/**
 * 
 */
package com.fhzz.business.dao.hbase;

import java.util.List;

import com.fhzz.business.entity.hbase.TWbSwryxx;
import com.fhzz.core.dao.BaseDao;

/**
 * @author: YangYi
 * @CreateTime: 2018年3月19日 下午2:32:06
 * @Copyright: FHZZ
 */
public interface HBaseDemoDao extends BaseDao<TWbSwryxx>{
	public List<TWbSwryxx> scan(String startRow, String stopRow);
}
