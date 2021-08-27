package com.xiaowen.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtils {

	/**
	 * 获取数据库连接
	 * 
	 * @Description
	 * @author xiaowen
	 * @date 2021年8月14日下午3:15:30
	 * @return
	 */
	public static Connection getConnection() {
		Connection conn = null;
		InputStream is = null;
		Properties props = new Properties();
		try {
			/*
			 *  通过流的方式获取资源resorces目录下的文件
			 *  注意点：对于静态类，只能用静态类名.class.getClassLoader().getResourceAsStream(文件的相对路径)的方式获取；
			 *        直接调用ClassLoader.getSystemClassLoader().getResourceAsStream(文件相对路径)的方式无法在tomcat获取到流对象。
			 *        两种方式都可以在本地获取到对应文件流对象。但奇怪的就是在部署到tomcat服务器就只能用第一种方式！！！
			 *        拿到的是同样的系统的ClassLoader对象。此处存疑！！！
			 */
			is = DBUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
			// is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
			System.out.println(is);
			props.load(is);
			String driver = props.getProperty("driver");
			String url = props.getProperty("url");
			String user = props.getProperty("user");
			String password = props.getProperty("password");
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return conn;
	}
	
	/**
	 * 数据库资源关闭
	 * 
	 * @Description
	 * @author xiaowen
	 * @date 2021年8月14日下午3:16:29
	 * @param conn
	 * @param pstmt
	 * @param rs
	 */
	public static void closeResource(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 涉及到事务的关闭连接
	 * 
	 * @Description
	 * @author xiaowen
	 * @date 2021年8月26日下午2:04:37
	 * @param conn
	 */
	public static void closeConn(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
