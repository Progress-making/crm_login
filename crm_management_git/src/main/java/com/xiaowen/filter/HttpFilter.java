package com.xiaowen.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class HttpFilter
 * 定义一个基于Http协议的Filter
 * 
 * @Description
 * @author xiaowen
 * @version
 * @date 2021年8月16日下午10:48:43
 *
 */
public abstract class HttpFilter implements Filter {
	
	private FilterConfig config;
	
	/**
	 * 获取Filter的配置信息
	 * 
	 * @Description
	 * @author xiaowen
	 * @date 2021年8月16日下午10:54:41
	 * @return
	 */
	public FilterConfig getFilterConfig() {
		return config;
	}
	
	/**
	 * init() 用以子类重写
	 * 
	 * @Description
	 * @author xiaowen
	 * @date 2021年8月16日下午10:49:59
	 * @throws ServletException
	 */
	public abstract void init() throws ServletException;

    /**
     * Default constructor. 
     */
    public HttpFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		doFilter(req, resp, chain);
	}
	
	/**
	 * doFilter() 用以子类重写，具体功能完善
	 * 
	 * @Description
	 * @author xiaowen
	 * @date 2021年8月16日下午10:47:12
	 * @param req
	 * @param resp
	 * @param chain
	 * @throws IOException
	 * @throws ServletException
	 */
	public abstract void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
			throws IOException, ServletException;

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.config = fConfig;
		init();
	}

}
