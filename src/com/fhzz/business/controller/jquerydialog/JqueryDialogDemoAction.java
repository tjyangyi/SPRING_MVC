/**
 * 
 */
package com.fhzz.business.controller.jquerydialog;

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
public class JqueryDialogDemoAction extends BaseAction {
	Log logger = LogFactory.getLog(JqueryDialogDemoAction.class);
	
	@RequestMapping("toJqueryDialogDemo")
	public String toJqueryDialogDemo() {
		return "jquerydialog/jqueryDialogDemo";
	}
	
	@RequestMapping("toJqueryDialogContent")
	public String toJqueryDialogContent() {
		return "jquerydialog/jqueryDialogContent";
	}
	
	@RequestMapping("toJqueryDialogContentInnerDialog")
	public String toJqueryDialogContentInnerDialog() {
		return "jquerydialog/jqueryDialogContentInnerDialog";
	}
	
}
