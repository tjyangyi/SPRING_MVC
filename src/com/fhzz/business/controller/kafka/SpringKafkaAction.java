/**
 * 
 */
package com.fhzz.business.controller.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fhzz.core.controller.BaseAction;
import com.fhzz.core.kafka.KafkaProducerServer;

/**
 * @author YangYi
 * 
 */
@Controller
public class SpringKafkaAction extends BaseAction {
	Log logger = LogFactory.getLog(SpringKafkaAction.class);

	@Autowired
	private KafkaProducerServer kafkaProducerServer;

	@RequestMapping("testKafkaProducerServer")
	@ResponseBody
	public Map<String, Object> testKafkaProducerServer() {
		String topic = "orderTopic";
		String value = "test";
		String ifPartition = "0";
		Integer partitionNum = 3;
		String role = "test";// 用来生成key
		Map<String, Object> res = kafkaProducerServer.sndMesForTemplate(topic,
				value, ifPartition, partitionNum, role);

		System.out.println("测试结果如下：===============");
		String message = (String) res.get("message");
		String code = (String) res.get("code");

		System.out.println("code:" + code);
		System.out.println("message:" + message);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		return result;
	}

}
