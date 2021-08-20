package com.xiaowen.service.impl;

import org.apache.commons.lang3.StringUtils;

import com.xiaowen.dao.UserDao;
import com.xiaowen.dao.impl.UserDaoImpl;
import com.xiaowen.exception.LoginException;
import com.xiaowen.exception.ParamException;
import com.xiaowen.exception.SystemException;
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
	
	@Override
	public int register(User user, String confirmPwd) throws ParamException, SystemException {
		AssertUtils.isTrue(StringUtils.isBlank(user.getUserName()), "请输入用户名");
		AssertUtils.isTrue(StringUtils.isBlank(user.getUserPwd()), "请输入密码");
		AssertUtils.isTrue(StringUtils.isBlank(user.getTrueName()), "请输入用户真实姓名");
		AssertUtils.isTrue(StringUtils.isBlank(confirmPwd), "请输入确认密码");
		AssertUtils.isTrue(!user.getUserPwd().equals(confirmPwd), "两次密码输入不一致");
		
		User userBean = userDao.selUserByUsername(user.getUserName());
		AssertUtils.isTrue(userBean != null, "用户已存在");
		
		int result = userDao.insUserSingle(user);
		AssertUtils.isSystemError(result == 0);
		return result;
	}

	@Override
	public User getUserByUsername(String userName) throws ParamException, SystemException {
		// 当参数userName 为null 或""时，返回user为null
		User user = userDao.selUserByUsername(userName);
		return user;
	}

}
