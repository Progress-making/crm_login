package com.xiaowen.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaseController {
	
	@RequestMapping("toErrorPage")
	public String toErrorPage(HttpServletRequest req, String code, String msg) {
		req.setAttribute("code", code);
		req.setAttribute("msg", msg);
		return "/WEB-INF/views/error.jsp";
	}

}
