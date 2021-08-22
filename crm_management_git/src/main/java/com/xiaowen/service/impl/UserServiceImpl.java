package com.xiaowen.service.impl;

import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

import com.xiaowen.dao.UserDao;
import com.xiaowen.dao.impl.UserDaoImpl;
import com.xiaowen.exception.LoginException;
import com.xiaowen.exception.ParamException;
import com.xiaowen.exception.SystemException;
import com.xiaowen.pojo.User;
import com.xiaowen.service.UserService;
import com.xiaowen.util.AssertUtils;
import com.xiaowen.util.BASE64Utils;
import com.xiaowen.util.CookieUtils;
import com.xiaowen.util.MD5Util;

public class UserServiceImpl implements UserService {

	private UserDao userDao = new UserDaoImpl();
	@Override
	public User login(String userName, String userPwd, HttpServletResponse resp) throws ParamException,LoginException {
		// 后台非空 验证
		AssertUtils.isTrue(StringUtils.isBlank(userName), "请输入用户名");
		AssertUtils.isTrue(StringUtils.isBlank(userPwd), "请输入密码");
		
		User user = userDao.selUserByUsernameAndPwd(userName, MD5Util.md5Encrypt(userPwd));
		AssertUtils.isLoginFail(user == null, "登录失败！原因可能如下：1.用户不存在 2.系统错误");
		
		/*
		 * 使用cookie存储当前登录状态
		 */
		CookieUtils.setCookie(resp, "userId", BASE64Utils.encryptBASE64(user.getId()), 1);
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
		// 对密码进行加密
		user.setUserPwd(MD5Util.md5Encrypt(user.getUserPwd()));
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

	@Override
	public User getUserByIdFromCookie(String idStr) throws ParamException, SystemException, NumberFormatException {
		
		return userDao.selUserById(Integer.parseInt(BASE64Utils.decryptBASE64(idStr)));
	}

}
