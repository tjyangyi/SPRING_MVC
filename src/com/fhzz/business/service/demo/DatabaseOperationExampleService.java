/**
 * 
 */
package com.fhzz.business.service.demo;

import com.fhzz.business.entity.DemoTable;

/**
 * @author: YangYi
 * @CreateTime: 2018年2月11日 下午2:21:33
 * @Copyright: FHZZ
 */
public interface DatabaseOperationExampleService {
	public void saveDemoTable(DemoTable demoTable);
	
	public void updateDemoTable(DemoTable demoTable);
	
	public DemoTable getDemoTable(String id);
}
