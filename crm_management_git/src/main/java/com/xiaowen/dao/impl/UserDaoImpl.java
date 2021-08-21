package com.xiaowen.dao.impl;

import java.sql.Connection;
import com.xiaowen.dao.BaseDao;
import com.xiaowen.dao.UserDao;
import com.xiaowen.pojo.User;
import com.xiaowen.util.DBUtils;

public class UserDaoImpl extends BaseDao implements UserDao {

	@Override
	public User selUserById(Integer id) {
		Connection conn = DBUtils.getConnection();
		String sql = "select id,user_name userName, user_pwd userPwd, true_name trueName from t_user where id = ?";
		return getInstanceForBean(conn, User.class, sql, id);
	}

	@Override
	public User selUserByUsernameAndPwd(String userName, String userPwd) {
		Connection conn = DBUtils.getConnection();
		String sql = "select id, user_name userName, user_pwd userPwd, true_name trueName from t_user where user_name = ? and user_pwd = ?";
		return getInstanceForBean(conn, User.class, sql, userName, userPwd);
	}

	@Override
	public int insUserSingle(User user) {
		Connection conn = DBUtils.getConnection();
		String sql = "insert into t_user values(default, ?, ?, ?)";
		return update(conn, sql, user.getUserName(), user.getUserPwd(), user.getTrueName());
	}

	@Override
	public User selUserByUsername(String userName) {
		Connection conn = DBUtils.getConnection();
		String sql = "select id, user_name userName, user_pwd userPwd, true_name trueName from t_user where user_name = ?";
		return getInstanceForBean(conn, User.class, sql, userName);
	}

}
