package com.xiaowen.dao.impl;

import java.sql.Connection;
import java.util.List;

import com.xiaowen.dao.BaseDao;
import com.xiaowen.dao.UserDao;
import com.xiaowen.pojo.User;

public class UserDaoImpl extends BaseDao implements UserDao {

	@Override
	public User selUserById(Connection conn, Integer id) {
		String sql = "select id,user_name userName, user_pwd userPwd, true_name trueName from t_user where id = ?";
		return getInstanceForBean(conn, User.class, sql, id);
	}

	@Override
	public User selUserByUsernameAndPwd(Connection conn, String userName, String userPwd) {
		String sql = "select id, user_name userName, user_pwd userPwd, true_name trueName from t_user where user_name = ? and user_pwd = ?";
		return getInstanceForBean(conn, User.class, sql, userName, userPwd);
	}

	@Override
	public int insUserSingle(Connection conn, User user) {
		String sql = "insert into t_user values(default, ?, ?, ?)";
		return update(conn, sql, user.getUserName(), user.getUserPwd(), user.getTrueName());
	}

	@Override
	public User selUserByUsername(Connection conn, String userName) {
		String sql = "select id, user_name userName, user_pwd userPwd, true_name trueName from t_user where user_name = ?";
		return getInstanceForBean(conn, User.class, sql, userName);
	}

	@Override
	public int updUserPwd(Connection conn, User user) {
		String sql = "update t_user set user_pwd = ? where id = ?";
		return update(conn, sql, user.getUserPwd(), user.getId());
	}

	@Override
	public int updUserById(Connection conn, User user) {
		String sql = "update t_user set user_name = ?, true_name = ? where id = ?";
		return update(conn, sql, user.getUserName(), user.getTrueName(), user.getId());
	}

	@Override
	public List<User> selMultiUsersExceptName(Connection conn, String userName) {
		String sql = "select id, user_name userName, user_pwd userPwd, true_name trueName from t_user where user_name <> ?";
		return getInstanceForList(conn, User.class, sql, userName);
	}

}
