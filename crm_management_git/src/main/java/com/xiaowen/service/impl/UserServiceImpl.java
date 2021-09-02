package com.xiaowen.service.impl;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaowen.exception.LoginException;
import com.xiaowen.exception.ParamException;
import com.xiaowen.mapper.UserMapper;
import com.xiaowen.pojo.User;
import com.xiaowen.service.UserService;
import com.xiaowen.util.AssertUtils;
import com.xiaowen.util.BASE64Utils;
import com.xiaowen.util.CookieUtils;
import com.xiaowen.util.MD5Util;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User login(String userName, String userPwd, HttpServletResponse resp) throws ParamException,LoginException {
		// 后台非空 验证
		AssertUtils.isTrue(StringUtils.isBlank(userName), "请输入用户名");
		AssertUtils.isTrue(StringUtils.isBlank(userPwd), "请输入密码");
		
		User user = userMapper.selUserByUsernameAndPwd(userName, MD5Util.md5Encrypt(userPwd));
		AssertUtils.isLoginFail(user == null, "登录失败！用户不存在：用户名错误或密码错误！");
		
		/*
		 * 使用cookie存储当前登录状态
		 */
		CookieUtils.setCookie(resp, "userId", BASE64Utils.encryptBASE64(user.getId()), 1);
		return user;
	}
	
	@Override
	public int register(User user, String confirmPwd) throws ParamException {
		AssertUtils.isTrue(StringUtils.isBlank(user.getUserName()), "请输入用户名");
		AssertUtils.isTrue(StringUtils.isBlank(user.getUserPwd()), "请输入密码");
		AssertUtils.isTrue(StringUtils.isBlank(user.getTrueName()), "请输入用户真实姓名");
		AssertUtils.isTrue(StringUtils.isBlank(confirmPwd), "请输入确认密码");
		AssertUtils.isTrue(!user.getUserPwd().equals(confirmPwd), "两次密码输入不一致");
		
		User userBean = userMapper.selUserByUsername(user.getUserName());
		AssertUtils.isTrue(userBean != null, "用户已存在");
		// 对密码进行加密
		user.setUserPwd(MD5Util.md5Encrypt(user.getUserPwd()));
		return userMapper.insUserSingle(user);
	}

	@Override
	public User getUserByUsername(String userName) throws ParamException {
		// 参数为null验证
		AssertUtils.isTrue(StringUtils.isBlank(userName), "用户名为必填项");
		User user = userMapper.selUserByUsername(userName);
		return user;
	}

	@Override
	public User getUserByIdFromCookie(HttpServletRequest req) throws LoginException {
		Cookie cookie = CookieUtils.getCookie(req, "userId");
		AssertUtils.isLoginFail(cookie == null, "用户未登录，请先登录！");
		User user = userMapper.selUserById(Integer.parseInt(BASE64Utils.decryptBASE64(cookie.getValue())));
		AssertUtils.isLoginFail(user == null, "用户不存在");
		return user;
	}

	/**
	 * 密码修改的参数校验
	 * 
	 * @Description
	 * @author xiaowen
	 * @date 2021年8月25日下午2:42:55
	 * @param user
	 * @param oldPwd
	 * @param newPwd
	 * @param confirmPwd
	 */
	private void checkUpdatePwdParams(User user, String oldPwd, String newPwd, String confirmPwd) {
		AssertUtils.isTrue(StringUtils.isBlank(oldPwd), "请输入原始密码");
		AssertUtils.isTrue(StringUtils.isBlank(newPwd), "请输入新密码");
		AssertUtils.isTrue(oldPwd.equals(newPwd), "新密码不能与旧密码相同");
		AssertUtils.isTrue(StringUtils.isBlank(confirmPwd), "请输入确认密码");
		AssertUtils.isTrue(!newPwd.equals(confirmPwd), "新密码与确认密码输入不一致");
		AssertUtils.isTrue(!(MD5Util.md5Encrypt(oldPwd).equals(user.getUserPwd())), "原始密码错误，请重新输入");
		
	}

	@Override
	public int updatUserPwd(HttpServletRequest req, String oldPwd, String newPwd, String confirmPwd) throws ParamException, LoginException {
		User user = getUserByIdFromCookie(req);
		checkUpdatePwdParams(user, oldPwd, newPwd, confirmPwd);
		user.setUserPwd(MD5Util.md5Encrypt(newPwd));
		
		return userMapper.updUserPwd(user);
	}
	
	@Override
	public int updateUserInfo(HttpServletRequest req, String userName, String trueName) throws ParamException {
		User user = getUserByIdFromCookie(req);
		checkValidUser(user.getUserName(), userName, trueName);
		user.setUserName(userName);
		user.setTrueName(trueName);
		
		return userMapper.updUserById(user);
	}

	/**
	 * 参数验证
	 * 1.非空验证
	 * 2.在排除指定用户之外的范围内，验证指定用户的存在性
	 * 
	 * @Description
	 * @author xiaowen
	 * @date 2021年8月26日下午3:06:01
	 * @param oldName
	 * @param newName
	 * @throws ParamException
	 */
	private void checkValidUser(String oldName, String newName, String trueName) {
		AssertUtils.isTrue(StringUtils.isBlank(newName), "请填写用户名");
		AssertUtils.isTrue(StringUtils.isBlank(trueName), "请填写真实姓名");
		AssertUtils.isTrue(oldName == null, "系统漏洞！原用户名不可能为null!");
		
		List<User> list = userMapper.selMultiUsersExceptName(oldName);
		if (list == null || list.size() < 1) {
			return;
		}
		for (User user : list) {
			System.out.println(user);
			AssertUtils.isTrue(user.getUserName().equals(newName), "该用户名已存在，请重新输入");
		}
	}

	@Override
	public List<User> getAllUsers() {
		List<User> list = userMapper.selAllUsers();
		return list;
	}
	

}
