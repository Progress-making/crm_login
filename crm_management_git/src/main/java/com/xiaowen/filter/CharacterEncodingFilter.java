package com.xiaowen.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CharacterEncodingFilter extends HttpFilter {
	private String encoding = null;

	@Override
	public void init() throws ServletException {
		encoding = getFilterConfig().getInitParameter("encoding");
	}

	@Override
	public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		if (encoding != null) {
			System.out.println("字符编码为：" + encoding);
			req.setCharacterEncoding(encoding);
			resp.setContentType("text/html; charset=" + encoding);
		}
		chain.doFilter(req, resp);
	}

}
