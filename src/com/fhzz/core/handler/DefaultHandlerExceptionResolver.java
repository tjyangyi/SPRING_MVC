/**
 * 
 */
package com.fhzz.core.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.fhzz.core.utils.HTTPUtils;

/**
 * @author YangYi
 * 
 */
@Component
public class DefaultHandlerExceptionResolver implements HandlerExceptionResolver {
	Log logger = LogFactory.getLog(DefaultHandlerExceptionResolver.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object object,
			Exception exception) {
		logger.error(exception.getMessage(),exception);// 异常日志记录
		// 判断是否AJAX请求
		if (!HTTPUtils.isAjaxRequest(request)) {// 如果不是AJAX请求,VM格式返回
			ModelAndView modelAndView = new ModelAndView("error/errorInfoPage"); // 统一跳转到errorPage页面
			modelAndView.addObject("success", false);
			modelAndView.addObject("errorMsg", exception.getMessage());
			return modelAndView;
		} else {// 如果是AJAX请求,JSON格式返回
			ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
			modelAndView.addObject("success", false);
			modelAndView.addObject("errorMsg", exception.getMessage());
			return modelAndView;
		}
	}
}