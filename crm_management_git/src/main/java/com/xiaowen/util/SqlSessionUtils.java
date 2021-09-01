package com.xiaowen.util;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionUtils {
	
	private static InputStream is;
	private static SqlSessionFactory factory;
	// 类加载的时候就读取mybatis.xml的配置文件，并初始化SqlSessionFactory
	static {
		is = SqlSession.class.getClassLoader().getResourceAsStream("mybatis.xml");
		//使用工厂设计模式
		factory = new SqlSessionFactoryBuilder().build(is);
	}

	public static SqlSession getSqlSession() {
		if (factory == null) {
			return null;
		}
		//生产SqlSession对象
		SqlSession session = factory.openSession();
		return session;
	}
	
	public static void closeSqlSession(SqlSession session) {
		if (session != null) {
			session.close();
		}
	}
}
