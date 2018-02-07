/**
 * 
 */
package com.fhzz.core.sercurity.controller;

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
public class LoginAction extends BaseAction {
	Log logger = LogFactory.getLog(LoginAction.class);
	
	@RequestMapping("toLogin")  
    public String toLogin() {  
        return "login/login";  
    }  
	
	
}
