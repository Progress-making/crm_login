package com.xiaowen.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xiaowen.exception.LoginException;
import com.xiaowen.exception.ParamException;
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
	 * @param resp
	 * @return
	 * @throws LoginException
	 */
	public User login(String userName, String userPwd, HttpServletResponse resp) throws LoginException;
	
	public int register(User user, String confirmPwd) throws ParamException;
	
	public User getUserByUsername(String userName) throws ParamException;
	
	public User getUserByIdFromCookie(HttpServletRequest req) throws LoginException;
	
	/**
	 * 修改用户密码
	 * 
	 * @Description
	 * @author xiaowen
	 * @date 2021年8月26日下午12:22:36
	 * @param req
	 * @param oldPwd
	 * @param newPwd
	 * @param confirmPwd
	 * @return
	 * @throws ParamException
	 * @throws LoginException
	 */
	public int updatUserPwd(HttpServletRequest req, String oldPwd, String newPwd, String confirmPwd) throws ParamException, LoginException;
	
	/**
	 * 修改用户基本信息
	 * 
	 * @Description
	 * @author xiaowen
	 * @date 2021年8月26日下午4:42:28
	 * @param req
	 * @param userName
	 * @param trueName
	 * @return
	 * @throws ParamException
	 */
	public int updateUserInfo(HttpServletRequest req, String userName, String trueName) throws ParamException;

	/**
	 * 查询所有用户信息
	 * 
	 * @Description
	 * @author xiaowen
	 * @date 2021年9月1日下午3:08:09
	 * @return
	 */
	public List<User> getAllUsers();
}
