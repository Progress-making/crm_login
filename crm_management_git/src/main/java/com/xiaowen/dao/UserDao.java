package com.xiaowen.dao;

import java.sql.SQLException;

import com.xiaowen.pojo.User;

public interface UserDao {

	public User selUserById(Integer id) throws SQLException;
}
