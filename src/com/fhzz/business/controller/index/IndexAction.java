/**
 * 
 */
package com.fhzz.business.controller.index;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fhzz.core.controller.BaseAction;

/**
 * @author YangYi
 * 
 */
@Controller
public class IndexAction extends BaseAction {
	Log logger = LogFactory.getLog(IndexAction.class);

	@RequestMapping("toIndex")
	public String toLogin() {
		return "index/index";
	}

}
