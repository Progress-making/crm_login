package com.xiaowen.mapper;

import java.util.List;

import com.xiaowen.pojo.User;

public interface UserMapper {

	public User selUserById(Integer id);
	
	public User selUserByUsername(String userName);
	
	public User selUserByUsernameAndPwd(String userName, String userPwd);
	
	public int insUserSingle(User user);
	
	public int updUserPwd(User user, String newPwd);
	
	public int updUserById(User user, int id);
	
	public List<User> selMultiUsersExceptName(String userName);
}
