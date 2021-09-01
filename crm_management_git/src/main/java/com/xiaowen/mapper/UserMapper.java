package com.xiaowen.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaowen.pojo.User;

public interface UserMapper {

	public User selUserById(Integer id);
	
	public User selUserByUsername(String userName);
	
	public User selUserByUsernameAndPwd(@Param("userName")String userName, @Param("password")String userPwd);
	
	public int insUserSingle(User user);
	
	public int updUserPwd(User user);
	
	public int updUserById(User user);
	
	public List<User> selMultiUsersExceptName(String userName);
	
	public List<User> selAllUsers();
}
