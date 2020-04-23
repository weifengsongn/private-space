package com.swf.component;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: private-space
 * @description:
 * @author: Wfsong
 * @create: 2020-04-10 07:31
 **/

public class LoginInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Object username = request.getSession().getAttribute("username");
		if (StringUtils.isEmpty(username)) {
			request.setAttribute("message","请登录");
			request.getRequestDispatcher("/index").forward(request,response);
			return false;
		} else {
			return true;
		}

	}
}
