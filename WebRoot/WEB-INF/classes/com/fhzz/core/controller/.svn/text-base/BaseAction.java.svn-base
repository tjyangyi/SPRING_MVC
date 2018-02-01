package com.fhzz.core.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;

import com.fhzz.business.entity.UserInfo;

/**
 * 
 * @author: YangYi
 * @CreateTime: 2018年1月29日 下午4:50:16
 * @Copyright: FHZZ
 */
@Controller
public class BaseAction {
	/**
	 * 将信息返回给客户端
	 * 
	 * @param msg
	 * @param response
	 * @throws IOException
	 */
	public void writeTo(String msg, HttpServletResponse response) throws IOException {
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			response.setContentType("application/text; charset=UTF-8");
			response.setHeader("Content-disposition", "attachment; ");
			response.setContentLength(msg.getBytes().length);
			os.write(msg.getBytes()); //
			os.flush();
			os.close();
		} catch (Exception e) {
		} finally {
			try {
				if (os != null) {
					os.close();
					os = null;
				}
			} catch (Exception e2) {
				os = null;
			}
		}
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

	/**
	 * Ajax发送信息
	 */
	public void sendAjax(HttpServletResponse response, Object obj) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(obj);
		out.flush();
		out.close();
	}

	/**
	 * 
	 * 从session里获取当前登录的用户
	 * 
	 * @param request
	 * @return
	 */
	public UserInfo getCurrentUser(HttpServletRequest request) {
		UserInfo user = new UserInfo();
		if ("127.0.0.1".equals(request.getRemoteAddr())) {
			user.setCustId("410483554096");
			user.setCustName("本地登录的测试用户");
			user.setOrgName("1");
			user.setIsLeader(2);
			user.setRealName("本地登录的测试用户");
			user.setOrgId("44335897692635728163");
		} else {
			try {
				user = (UserInfo) request.getSession().getAttribute("user");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return user;
	}

}
