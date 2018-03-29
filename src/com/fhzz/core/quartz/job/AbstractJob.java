/**
 * 
 */
package com.fhzz.core.quartz.job;

import javax.annotation.PostConstruct;

import org.quartz.StatefulJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fhzz.core.quartz.service.QuartzJobRegister;

/**
 * @author: YangYi
 * @CreateTime: 2018年2月9日 下午5:05:46
 * @Copyright: FHZZ
 */
@Component
public abstract class AbstractJob implements StatefulJob {
	@Autowired
	private QuartzJobRegister quartzJobRegister;
	
	@PostConstruct
	public void init(){
		this.quartzJobRegister.regist(this);
	}

}
