package com.xiaowen.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 封装有关cookie的获取、设置、删除等操作
 * 
 * @Description
 * @author xiaowen
 * @version
 * @date 2021年8月20日下午8:39:31
 *
 */
public class CookieUtils {
	
	/**
	 * 获取指定cookieName 的Cookie实例
	 * 
	 * @Description
	 * @author xiaowen
	 * @date 2021年8月20日下午8:40:50
	 * @param req
	 * @param cookieName
	 * @return
	 */
	public static Cookie getCookie(HttpServletRequest req, String cookieName) {
		if (req == null) {
			return null;
		}
		Cookie[] cookies = req.getCookies();
		if (cookies == null || cookies.length < 1) {
			return null;
		}
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(cookieName)) {
				return cookie;
			}
		}
		return null;
	}
	
	/**
	 * 添加一个新的Cookie实例对象 并设置失效时间与作用域
	 * 
	 * @Description
	 * @author xiaowen
	 * @date 2021年8月20日下午8:42:03
	 * @param resp
	 * @param cookieName
	 * @param cookieValue
	 * @param dayAliveTime
	 */
	public static void setCookie(HttpServletResponse resp, String cookieName, String cookieValue, int dayAliveTime) {
		if (resp == null) {
			return;
		}
		Cookie cookie = new Cookie(cookieName, cookieValue);
		cookie.setMaxAge(dayAliveTime * 60 * 60 * 24);
		cookie.setPath("/");
		resp.addCookie(cookie);
	}
	
	/**
	 * 删除指定的Cookie实例对象
	 * 
	 * @Description
	 * @author xiaowen
	 * @date 2021年8月20日下午8:43:38
	 * @param cookie
	 */
	public static void deleteCookie(Cookie cookie) {
		if (cookie != null) {
			cookie.setMaxAge(0);
		}
	}
	
}
