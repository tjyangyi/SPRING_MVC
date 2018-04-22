/**
 * 
 */
package com.fhzz.business.service.hession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fhzz.core.hession.IRemoteService;

/**
 * @author YangYi
 * 
 */
@Service
public class TestHessionService {
	private Logger log = Logger.getLogger(TestHessionService.class);
	public static final String REMOTE_SERVICE_NAME = "ITestService";

	@Autowired
	private IRemoteService ir;

	public String testHession() {
		try {
			return (String) ir.invokeRemote(REMOTE_SERVICE_NAME, "testHession",
					new String[] {}, new Object[] {});
		} catch (Exception e) {
			log.error("", e);
			return null;
		}
	}

	public String testHessionDatabase() {
		try {
			return (String) ir.invokeRemote(REMOTE_SERVICE_NAME, "testHessionDatabase",
					new String[] {}, new Object[] {});
		} catch (Exception e) {
			log.error("", e);
			return null;
		}
		
	}
}
