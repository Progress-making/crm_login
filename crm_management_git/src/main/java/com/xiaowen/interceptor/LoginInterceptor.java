package com.xiaowen.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.xiaowen.service.UserService;

/**
 * LoginInterceptor:登录拦截器
 * 作用：需要登录成功后才能访问某些特定页面，否则无法直接访问！
 * 
 * @Description
 * @author xiaowen
 * @version
 * @date 2021年11月2日下午4:41:55
 *
 */
public class LoginInterceptor implements HandlerInterceptor{
	@Autowired
	private UserService userServiceImpl;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		userServiceImpl.getUserByIdFromCookie(request);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

	
}
