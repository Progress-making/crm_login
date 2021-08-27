package com.xiaowen.dao;

import java.sql.Connection;
import java.util.List;

import com.xiaowen.pojo.User;

public interface UserDao {

	public User selUserById(Connection conn, Integer id);
	
	/**
	 * select user by username
	 * 
	 * @Description
	 * @author xiaowen
	 * @date 2021年8月18日下午9:14:52
	 * @param userName
	 * @return
	 */
	public User selUserByUsername(Connection conn, String userName);
	
	public User selUserByUsernameAndPwd(Connection conn, String userName, String userPwd);
	
	/**
	 * insert 新增用户
	 * 
	 * @Description
	 * @author xiaowen
	 * @date 2021年8月18日下午5:35:24
	 * @param user
	 * @return
	 */
	public int insUserSingle(Connection conn, User user);
	
	/**
	 * update 修改用户信息
	 * 
	 * @Description
	 * @author xiaowen
	 * @date 2021年8月24日下午6:01:05
	 * @param user
	 * @return
	 */
	public int updUserPwd(Connection conn, User user, String newPwd);
	
	/**
	 * 根据用户id修改用户基本资料
	 * 
	 * @Description
	 * @author xiaowen
	 * @date 2021年8月26日下午3:05:25
	 * @param conn
	 * @param user
	 * @param id
	 * @return
	 */
	public int updUserById(Connection conn, User user, int id);
	
	/**
	 * 查询 除指定用户名之外的所有用户并返回
	 * 
	 * @Description
	 * @author xiaowen
	 * @date 2021年8月26日下午8:21:29
	 * @param conn
	 * @param userName
	 * @return
	 */
	public List<User> selMultiUsersExceptName(Connection conn, String userName);
}
