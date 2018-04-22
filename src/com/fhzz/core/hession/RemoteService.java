package com.fhzz.core.hession;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * @FileName : (RemoteService.java)
 * 
 * @description : 服务端Bean的方法统一接口调用实现
 * @author : heshan
 * @version : Version No.1
 * @create : 2015-5-19 下午01:42:07
 * @modify : 2015-5-19 下午01:42:07
 * @copyright : FiberHome FHZ Telecommunication Technologies Co.Ltd.
 */
@SuppressWarnings("rawtypes")
public class RemoteService implements IRemoteService {
	private Log log = LogFactory.getLog(RemoteService.class);

	private WebApplicationContext webApplicationContext;

	public RemoteService() {
		webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
	}

	
	/**
	 * 
	 * @Description:远程方法调用对外提供接口 
	 * 
	 * @param: beanName     调用Bean名称
	 * @param: methodName 调用的方法
	 * @param: paramTypes    输入参数类型
	 * @param: params           输入参数
	 * @return 方法返回值
	 * @throws Exception 抛出异常信息
	 */
	@Override
	public Object invokeRemote(String beanName, String methodName,
			String[] paramTypes, Object[] params) throws Exception {
		try {
			Assert.hasText(beanName);
			Assert.hasText(methodName);

			Object obj = null;

			if (null != beanName && beanName.indexOf(".") != -1) {
				Class beanClass = Class.forName(beanName);
				obj = beanClass.newInstance();
			} else {
				if (!beanName.matches("[A-Z]{2}.*")) {// 前两个字母大写时，首字母不需要转小写
					beanName = beanName.substring(0, 1).toLowerCase()
							+ beanName.substring(1);
				}
				obj = webApplicationContext.getBean(beanName);
			}

			Class[] paramClasses = null;
			if (null != paramTypes) {
				paramClasses = new Class[paramTypes.length];
				for (int i = 0; i < paramTypes.length; i++) {
					paramClasses[i] = Class.forName(paramTypes[i]);
				}
			}

			if (null != params)
				return MethodUtils.invokeMethod(obj, methodName, params,
						paramClasses);
			else
				return MethodUtils.invokeMethod(obj, methodName, null);
		} catch (Exception e) {
			log.error("RemoteService invokeMethod Failure! ");
			throw e;
		}
	}

}
