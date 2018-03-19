package com.fhzz.business.service.hbase;

import java.util.Date;
import java.util.List;

import com.fhzz.business.entity.hbase.TWbSwryxx;


public interface HBaseDemoService {
	List<TWbSwryxx> getSwryxx(List<String> swcsdmList, Date swsjStart, Date swsjEnd);

}
