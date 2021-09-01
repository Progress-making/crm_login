package com.xiaowen.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionUtils {
	
	private static InputStream is;
	private static SqlSessionFactory factory;
	private static ThreadLocal<SqlSession> tl = new ThreadLocal<>();
	// 类加载的时候就读取mybatis.xml的配置文件，并初始化SqlSessionFactory
	static {
		try {
			is = Resources.getResourceAsStream("mybatis.xml");
			//使用工厂设计模式
			factory = new SqlSessionFactoryBuilder().build(is);
		} catch (IOException e) {
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
	}

	public static SqlSession getSqlSession() {
		if (factory == null) {
			return null;
		}
		// 获取SqlSession对象
		SqlSession session = tl.get();
		if (session == null) {
			tl.set(factory.openSession());
		}
		return tl.get();
	}
	
	public static void closeSqlSession() {
		if (tl.get() != null) {
			tl.get().close();
		}
		tl.set(null);
	}
}
