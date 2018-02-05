/**
 * 
 */
package com.fhzz.core.service.sys;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Service;

/**
 * @author YangYi
 * @description 自定义权限不足处理程序
 */

@Service
public class DefaultAccessDeniedHandler implements AccessDeniedHandler {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.web.access.AccessDeniedHandler#handle(javax
	 * .servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * org.springframework.security.access.AccessDeniedException)
	 */
	private String errorPage;

	public void handle(HttpServletRequest request,
			HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException,
			ServletException {
		boolean isAjax = this.isAjaxRequest(request);
		if (isAjax) {
			response.sendError(403);
		} else if (!response.isCommitted()) {
			if (errorPage != null) {
				// Put exception into request scope (perhaps of use to a view)
				request.setAttribute(WebAttributes.ACCESS_DENIED_403,
						accessDeniedException);
				// Set the 403 status code.
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				// forward to error page.
				RequestDispatcher dispatcher = request
						.getRequestDispatcher(errorPage);
				dispatcher.forward(request, response);
			} else {
				response.sendError(HttpServletResponse.SC_FORBIDDEN,
						accessDeniedException.getMessage());
			}
		}
	}

	/**
	 * The error page to use. Must begin with a "/" and is interpreted relative
	 * to the current context root.
	 * 
	 * @param errorPage
	 *            the dispatcher path to display
	 * 
	 * @throws IllegalArgumentException
	 *             if the argument doesn't comply with the above limitations
	 */
	public void setErrorPage(String errorPage) {
		if ((errorPage != null) && !errorPage.startsWith("/")) {
			throw new IllegalArgumentException("errorPage must begin with '/'");
		}
		this.errorPage = errorPage;
	}

	// 判断是否为ajax请求
	private boolean isAjaxRequest(HttpServletRequest request) {
		if (request.getHeader("x-requested-with") != null
				&& request.getHeader("x-requested-with").equals(
						"XMLHttpRequest")) {
			return true;
		}
		return false;
	}

}
