package com.xiaowen.service;

import com.xiaowen.exception.LoginException;
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
	public User login(String userName, String userPwd) throws LoginException;
}
