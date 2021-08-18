package com.xiaowen.service.impl;

import org.apache.commons.lang3.StringUtils;

import com.xiaowen.dao.UserDao;
import com.xiaowen.dao.impl.UserDaoImpl;
import com.xiaowen.exception.LoginException;
import com.xiaowen.exception.ParamException;
import com.xiaowen.pojo.User;
import com.xiaowen.service.UserService;
import com.xiaowen.util.AssertUtils;

public class UserServiceImpl implements UserService {

	private UserDao userDao = new UserDaoImpl();
	@Override
	public User login(String userName, String userPwd) throws ParamException,LoginException {
		
		AssertUtils.isTrue(StringUtils.isBlank(userName), "请输入用户名");
		AssertUtils.isTrue(StringUtils.isBlank(userPwd), "请输入密码");
		/*if (StringUtils.isBlank(userName)) {
			throw new ParamException("请输入用户名");
		}
		if (StringUtils.isBlank(userPwd)) {
			throw new ParamException("请输入密码");
		}*/
		User user = userDao.selUserByUsernameAndPwd(userName, userPwd);
		AssertUtils.isLoginFail(user == null, "登录失败！原因可能如下：1.用户不存在 2.系统错误");
		/*if (user == null) {
			throw new LoginException("登录失败！原因可能如下：1.用户不存在 2.系统错误");
		}*/
		return user;
	}

}
