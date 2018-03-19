/**
 * 
 */
package com.fhzz.business.dao.hbase.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.fhzz.business.dao.hbase.HBaseDemoDao;
import com.fhzz.business.entity.hbase.TWbSwryxx;
import com.fhzz.core.dao.impl.BaseDaoImpl;

/**
 * @author: YangYi
 * @CreateTime: 2018年3月19日 下午2:32:29
 * @Copyright: FHZZ
 */
@Repository
public class HBaseDemoDaoImpl extends BaseDaoImpl<TWbSwryxx> implements HBaseDemoDao {
	public List<TWbSwryxx> scan(String startRow, String stopRow) {
		return getHbaseTemplate().scan("T_WB_SWRYXX", TWbSwryxx.class, startRow, stopRow);
	}

}
