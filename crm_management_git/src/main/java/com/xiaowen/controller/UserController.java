package com.xiaowen.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaowen.base.ResultInfo;
import com.xiaowen.exception.LoginException;
import com.xiaowen.exception.ParamException;
import com.xiaowen.pojo.User;
import com.xiaowen.service.UserService;

/**
 * 
 * @Description
 * @author xiaowen
 * @version
 * @date 2021年9月2日下午3:57:14
 *
 */
@Controller
public class UserController {
	
	@Autowired
	private UserService userServiceImpl;
	
	@RequestMapping("login")
	public String login(String userName, String userPwd, HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("UserController login...");
		/*try {
			userServiceImpl.login(userName, userPwd, resp);
			System.out.println(req.getContextPath());
			return "/WEB-INF/views/main.jsp";
		} catch (ParamException e){
			e.printStackTrace();
			req.setAttribute("code", e.getCode());
			req.setAttribute("msg", e.getMsg());
			return "/WEB-INF/views/error.jsp";
		} catch (LoginException e) {
			e.printStackTrace();
			req.setAttribute("code", e.getCode());
			req.setAttribute("msg", e.getMsg());
			return "/WEB-INF/views/error.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("code", 500);
			req.setAttribute("msg", "系统内部错误");
			return "/WEB-INF/views/error.jsp";
		}*/
		
		userServiceImpl.login(userName, userPwd, resp);
		System.out.println(req.getContextPath());
		return "/WEB-INF/views/main.jsp";
		
	}
	
	@RequestMapping("register")
	public String register(User user, String confirmPwd, HttpServletRequest req) {
		/*try {
			userServiceImpl.register(user, confirmPwd);
			return "redirect:/index.jsp";
		} catch (ParamException e) {
			e.printStackTrace();
			req.setAttribute("code", e.getCode());
			req.setAttribute("msg", e.getMsg());
			return "/WEB-INF/views/error.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("code", 500);
			req.setAttribute("msg", "系统内部错误");
			return "/WEB-INF/views/error.jsp";
		}*/ 
		
		userServiceImpl.register(user, confirmPwd);
		return "redirect:/index.jsp";
	}
	
	@RequestMapping("toUpdatePwdPage")
	public String toUpdatePwd() {
		return "/WEB-INF/views/updatePwd.jsp";
	}
	
	@ResponseBody
	@RequestMapping("updatePwd")
	public ResultInfo updatePwd(HttpServletRequest req, String oldPwd, String newPwd, String confirmPwd) {
		ResultInfo resultInfo = new ResultInfo();
		/*try {
			userServiceImpl.updatUserPwd(req, oldPwd, newPwd, confirmPwd);
			resultInfo.setCode(200);
			resultInfo.setMsg("操作成功！");
		} catch (ParamException e) {
			e.printStackTrace();
			resultInfo.setCode(e.getCode());
			resultInfo.setMsg(e.getMsg());
		} catch (LoginException e) {
			e.printStackTrace();
			resultInfo.setCode(e.getCode());
			resultInfo.setMsg(e.getMsg());
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		userServiceImpl.updatUserPwd(req, oldPwd, newPwd, confirmPwd);
		resultInfo.setCode(200);
		resultInfo.setMsg("操作成功！");
		return resultInfo;
	}
	
	@RequestMapping("toEditPage")
	public String toUpdateUserInfo(HttpServletRequest req) {
		/*try {
			User user = userServiceImpl.getUserByIdFromCookie(req);
			System.out.println(user);
			req.setAttribute("userInfo", user);
			return "/WEB-INF/views/edit.jsp";
		} catch (LoginException e) {
			e.printStackTrace();
			req.setAttribute("code", e.getCode());
			req.setAttribute("msg", e.getMsg());
			return "/WEB-INF/views/error.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("code", 500);
			req.setAttribute("msg", "系统内部错误");
			return "/WEB-INF/views/error.jsp";
		}*/
		
		User user = userServiceImpl.getUserByIdFromCookie(req);
		System.out.println(user);
		req.setAttribute("userInfo", user);
		return "/WEB-INF/views/edit.jsp";
	}
	
	@ResponseBody
	@RequestMapping("edit")
	public ResultInfo edit(HttpServletRequest req, String userName, String trueName) {
		ResultInfo resultInfo = new ResultInfo();
		/*try {
			userServiceImpl.updateUserInfo(req, userName, trueName);
			resultInfo.setCode(200);
			resultInfo.setMsg("操作成功");
		} catch (ParamException e) {
			e.printStackTrace();
			resultInfo.setCode(e.getCode());
			resultInfo.setMsg(e.getMsg());
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		userServiceImpl.updateUserInfo(req, userName, trueName);
		resultInfo.setCode(200);
		resultInfo.setMsg("操作成功");
		return resultInfo;
	}
	
	@RequestMapping("main")
	public String main() {
		return "/WEB-INF/views/main.jsp";
	}
	
	@RequestMapping("toUserListPage")
	public String toUserListPage(HttpServletRequest req) {
		req.setAttribute("userList", userServiceImpl.getAllUsers());
		return "/WEB-INF/views/userList.jsp";
	}
	
	@RequestMapping("queryByCookie")
	@ResponseBody
	public ResultInfo query(HttpServletRequest req) {
		ResultInfo resultInfo = new ResultInfo();
		/*try {
			User user = userServiceImpl.getUserByIdFromCookie(req);
			System.out.println(user);
			resultInfo.setCode(200);
			resultInfo.setMsg("操作成功！");
			resultInfo.setResult(user);
		} catch (LoginException e) {
			e.printStackTrace();
			resultInfo.setCode(e.getCode());
			resultInfo.setMsg(e.getMsg());
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		User user = userServiceImpl.getUserByIdFromCookie(req);
		System.out.println(user);
		resultInfo.setCode(200);
		resultInfo.setMsg("操作成功！");
		resultInfo.setResult(user);
		return resultInfo;
	}
}
