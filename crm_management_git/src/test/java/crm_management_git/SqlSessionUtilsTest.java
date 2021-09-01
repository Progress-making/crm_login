package crm_management_git;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import com.xiaowen.util.SqlSessionUtils;

public class SqlSessionUtilsTest {

	@Test
	public void testGetSqlSession() {
		SqlSession sqlSession = SqlSessionUtils.getSqlSession();
		System.out.println(sqlSession);
		Assert.assertNotNull("连接失败", sqlSession);
		SqlSessionUtils.closeSqlSession(sqlSession);
	}
}
