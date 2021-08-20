package com.xiaowen.dao;

import com.xiaowen.pojo.User;

public interface UserDao {

	public User selUserById(Integer id);
	
	/**
	 * select user by username
	 * 
	 * @Description
	 * @author xiaowen
	 * @date 2021年8月18日下午9:14:52
	 * @param userName
	 * @return
	 */
	public User selUserByUsername(String userName);
	
	public User selUserByUsernameAndPwd(String userName, String userPwd);
	
	/**
	 * insert 新增用户
	 * 
	 * @Description
	 * @author xiaowen
	 * @date 2021年8月18日下午5:35:24
	 * @param user
	 * @return
	 */
	public int insUserSingle(User user);
}
