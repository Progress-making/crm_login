package com.xiaowen.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.xiaowen.dao.UserDao;
import com.xiaowen.pojo.User;
import com.xiaowen.util.DBUtils;

public class UserDaoImpl implements UserDao {

	@Override
	public User selUserById(Integer id) throws SQLException{
		Connection conn = DBUtils.getConnection();
		String sql = "select * from t_user where id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setObject(1, id);;
		ResultSet rs = null;
		rs = pstmt.executeQuery();
		if (rs.next()) {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setUserName(rs.getString("user_name"));
			user.setUserPwd(rs.getString("user_pwd"));
			user.setTrueName(rs.getString("true_name"));
			return user;
		}
		return null;
	}

}
