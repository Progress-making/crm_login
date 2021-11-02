package com.xiaowen.exception;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaowen.base.ResultInfo;
import com.xiaowen.exception.LoginException;

@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver{

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		ModelAndView mv = createDefaultModelAndView(request, ex);
		
		// 用户未登录异常处理
		if (ex instanceof LoginException) {
			LoginException e = (LoginException)ex;
			mv.addObject("code", e.getCode());
			mv.addObject("msg", e.getMsg());
			return mv;
		}
		
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod)handler;
			ResponseBody responseBody = handlerMethod.getMethodAnnotation(ResponseBody.class);
			if (null == responseBody) { // 普通页面请求
				if (ex instanceof ParamException) {
					ParamException e = (ParamException)ex;
					mv.addObject("code", e.getCode());
					mv.addObject("msg", e.getMsg());
				}
			} else { // json请求
				ResultInfo resultInfo = new ResultInfo();
//				resultInfo.setCode(500);
//				resultInfo.setMsg("系统繁忙"); // 默认报错信息
				if (ex instanceof ParamException) {
					ParamException e = (ParamException)ex;
					resultInfo.setCode(e.getCode());
					resultInfo.setMsg(e.getMsg());
				}
				
				response.setContentType("application/json; charset=utf-8");
				ObjectMapper mapper = new ObjectMapper();
				PrintWriter writer = null;
				try {
					writer = response.getWriter();
					writer.write(mapper.writeValueAsString(resultInfo));
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (writer != null) {
						writer.close();
					}
				}
			}
		}
		return mv;
	}

	private ModelAndView createDefaultModelAndView(HttpServletRequest req, Exception ex) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/WEB-INF/views/error.jsp");// 设置一个默认的错误视图
		mv.addObject("code", 500);// 默认错误编码
		mv.addObject("msg", ex.getMessage());// 默认错误信息
		return mv;
	}
}
