/**
 * 
 */
package com.fhzz.business.service.hbase.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fhzz.business.dao.hbase.HBaseDemoDao;
import com.fhzz.business.entity.hbase.TWbSwryxx;
import com.fhzz.business.service.hbase.HBaseDemoService;

/**
 * @author: YangYi
 * @CreateTime: 2018年3月19日 下午2:30:47
 * @Copyright: FHZZ
 */
@Service
public class HBaseDemoServiceImpl implements HBaseDemoService {
	@Autowired
	private  HBaseDemoDao hbaseDemoDao;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	
	@Override
	public List<TWbSwryxx> getSwryxx(List<String> swcsdmList, Date swsjStart, Date swsjEnd) {
		List<TWbSwryxx> list = new ArrayList<TWbSwryxx>();
		for(String swcsdm : swcsdmList){
			String startRow = swcsdm + "_" +  sdf.format(swsjStart);
			String stopRow = swcsdm + "_" +  sdf.format(swsjEnd);
			List<TWbSwryxx> oneResult = hbaseDemoDao.scan(startRow, stopRow);
			list.addAll(oneResult);
		}
		return list;
	}

}
