package com.xiaowen.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xiaowen.exception.LoginException;
import com.xiaowen.exception.ParamException;
import com.xiaowen.exception.SystemException;
import com.xiaowen.pojo.User;

public interface UserService {

	/**
	 * 用户登录的接口
	 * 
	 * @Description
	 * @author xiaowen
	 * @date 2021年8月16日下午4:44:51
	 * @param userName
	 * @param userPwd
	 * @return
	 * @throws LoginException
	 */
	public User login(String userName, String userPwd, HttpServletResponse resp) throws LoginException;
	
	public int register(User user, String confirmPwd) throws ParamException, SystemException;
	
	public User getUserByUsername(String userName) throws ParamException, SystemException;
	
	public User getUserByIdFromCookie(String idStr) throws ParamException, SystemException;
}
