package com.xiaowen.dao;

import com.xiaowen.pojo.User;

public interface UserDao {

	public User selUserById(Integer id);
	
	public User selUserByUsernameAndPwd(String userName, String userPwd);
}
