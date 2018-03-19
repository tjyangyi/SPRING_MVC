/**
 * 
 */
package com.fhzz.business.job;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fhzz.business.entity.DemoTable;
import com.fhzz.business.service.db.HibernateDemoService;
import com.fhzz.business.service.db.JdbcDemoService;
import com.fhzz.core.quartz.job.AbstractJob;

/**
 * @author: YangYi
 * @CreateTime: 2018年2月11日 下午1:32:32
 * @Copyright: FHZZ
 */
@Component
public class DatabaseExampleJob extends AbstractJob {
	Log logger = LogFactory.getLog(DatabaseExampleJob.class);

	@Autowired
	private HibernateDemoService hibernateDemoService;
	@Autowired
	private JdbcDemoService jdbcDemoService;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		logger.info("开始执行DatabaseExampleJob");
		// 使用jdbc方式批量插入，效率很快
		// 不需要配置<tx:method name="batch*" propagation="NOT_SUPPORTED"/>
		List<DemoTable> list1 = new ArrayList<DemoTable>();
		for (int i = 0; i < 10; i++) {
			Random random = new Random();
			int count = random.nextInt(90000) + 10000;
			String name = String.valueOf(count);
			Date date = new Date();
			DemoTable demoTable = new DemoTable(null, name, count, date, date,
					date, sdf.format(date));
			list1.add(demoTable);
		}
		jdbcDemoService.batchSaveDemoTable(list1);
		// 使用hibernate方式批量插入，效率较慢
		// 需要配置<tx:method name="batch*" propagation="NOT_SUPPORTED"/>
		// List<DemoTable> list2 = new ArrayList<DemoTable>();
		// for (int i = 0; i < 30000; i++) {
		// Random random = new Random(10);
		// int count = random.nextInt(20000000);
		// String name = "name" + count;
		// DemoTable demoTable = new DemoTable(null, name, count, new Date(),
		// new Date(), new Date());
		// list2.add(demoTable);
		// }
		// hibernateDemoService.batchSaveDemoTable(list2);
		logger.info("结束执行DatabaseExampleJob");
	}

}
