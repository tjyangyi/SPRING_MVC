/**
 * 
 */
package com.fhzz.business.service.hbase.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fhzz.business.entity.TWbSwryxx;
import com.fhzz.business.service.hbase.HbaseDemoService;

/**
 * @author: YangYi
 * @CreateTime: 2018年3月19日 下午2:30:47
 * @Copyright: FHZZ
 */
@Service
public class HbaseDemoServiceImpl implements HbaseDemoService {

	@Override
	public List<TWbSwryxx> getSwryxx(List<String> swcsdmList, Date swsjStart, Date swsjEnd) {
		// TODO Auto-generated method stub
		return null;
	}

}
