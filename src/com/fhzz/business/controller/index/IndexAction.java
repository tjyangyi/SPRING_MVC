/**
 * 
 */
package com.fhzz.business.controller.index;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fhzz.business.entity.sys.SysUsers;
import com.fhzz.core.controller.BaseAction;

/**
 * @author YangYi
 * 
 */
@Controller
public class IndexAction extends BaseAction {
	Log logger = LogFactory.getLog(IndexAction.class);

	@RequestMapping("toIndex")
	public String toIndex() {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		logger.info(user);
		logger.info(user.getUsername());
		SysUsers users = (SysUsers) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		logger.info(users);
		logger.info(users.getName());
		return "index/index";
	}

}
