package com.fhzz.core.hession;


/**
 * @FileName : (IRemoteService.java)
 * 
 * @description : TODO(服务端Bean的方法统一接口调用)
 * @author : XX
 * @version : Version No.1
 * @create : 2015-5-19 下午01:27:17
 * @modify : 2015-5-19 下午01:27:17
 * @copyright : FiberHome FHZ Telecommunication Technologies Co.Ltd.
 */
public interface IRemoteService
{
	
	/**
	 * 
	 * @Title: invokeRemote
	 * @Description: 远程方法调用接口
	 * @param: @param beanName     调用Bean名称
	 * @param: @param methodName 调用的方法
	 * @param: @param paramTypes    输入参数类型
	 * @param: @param params           输入参数
	 * @param: @return						返回值			
	 * @param: @throws BusinessException   抛出异常信息
	 * @return: Object   
	 * @throws
	 */
	public  Object invokeRemote(String beanName, String methodName,String[] paramTypes, Object[] params) throws Exception;

}
