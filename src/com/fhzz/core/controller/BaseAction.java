package com.fhzz.core.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;

/**
 * 
 * @author: YangYi
 * @CreateTime: 2018年1月29日 下午4:50:16
 * @Copyright: FHZZ
 */
@Controller
public class BaseAction {
	protected Map<String, Object> successResult() {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		return result;
	}

	/**
	 * 清空缓存
	 * 
	 * @param response
	 */
	public void clearCache(HttpServletResponse response) {
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setHeader("expires", "0");
	}

}
