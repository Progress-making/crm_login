package com.xiaowen.dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xiaowen.util.DBUtils;

public abstract class BaseDao {

	/**
	 * 查询 单个实例的通用方法
	 * 
	 * @Description
	 * @author xiaowen
	 * @date 2021年8月20日下午3:50:40
	 * @param conn
	 * @param clazz
	 * @param sql
	 * @param args
	 * @return
	 */
	public <T> T getInstanceForBean(Connection conn, Class<T> clazz, String sql, Object... args) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				pstmt.setObject(i + 1, args[i]);
			}
			rs = pstmt.executeQuery();
			if (rs.next()) {
				T t = clazz.newInstance();
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				System.out.println(columnCount);
				for (int i = 0; i < columnCount; i++) {
					String columnLabel = metaData.getColumnLabel(i + 1);
					Object columnValue = rs.getObject(columnLabel);
					Field field = clazz.getDeclaredField(columnLabel);
					field.setAccessible(true);
					field.set(t, columnValue);
				}
				return t;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeResource(conn, pstmt, rs);
		}
		return null;
	}
	
	/**
	 * 查询多例的通用方法 
	 * 
	 * @Description
	 * @author xiaowen
	 * @date 2021年8月20日下午6:51:56
	 * @param conn
	 * @param clazz
	 * @param sql
	 * @param args
	 * @return
	 */
	public <T> List<T> getInstanceForList(Connection conn, Class<T> clazz, String sql, Object... args) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<T> list = new ArrayList<T>();
		try {
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				pstmt.setObject(i + 1, args[i]);
			}
			rs = pstmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			
			while (rs.next()) {
				T t = clazz.newInstance();
				for (int i = 0; i < columnCount; i++) {
					String columnLabel = metaData.getColumnLabel(i);
					Object columnValue = rs.getObject(columnLabel);
					Field field = clazz.getDeclaredField(columnLabel);
					field.setAccessible(true);
					field.set(t, columnValue);
				}
				list.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeResource(conn, pstmt, rs);
		}
		
		return list;
	}
	
	/**
	 * 增、删、改操作的通用方法
	 * 
	 * @Description
	 * @author xiaowen
	 * @date 2021年8月20日下午3:55:44
	 * @param conn
	 * @param sql
	 * @param args
	 * @return
	 */
	public int update(Connection conn, String sql, Object... args) {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				pstmt.setObject(i + 1, args[i]);
			}
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeResource(conn, pstmt, null);
		}
		return 0;
	}
}
