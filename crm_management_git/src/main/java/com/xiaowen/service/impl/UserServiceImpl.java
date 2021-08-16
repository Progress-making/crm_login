package com.xiaowen.service.impl;

import com.xiaowen.dao.UserDao;
import com.xiaowen.dao.impl.UserDaoImpl;
import com.xiaowen.exception.LoginException;
import com.xiaowen.pojo.User;
import com.xiaowen.service.UserService;

public class UserServiceImpl implements UserService {

	private UserDao userDao = new UserDaoImpl();
	@Override
	public User login(String userName, String userPwd) throws LoginException {
		User user = userDao.selUserByUsernameAndPwd(userName, userPwd);
		if (user == null) {
			throw new LoginException();
		}
		return user;
	}

}
