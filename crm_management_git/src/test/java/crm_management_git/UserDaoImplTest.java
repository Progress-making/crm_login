package crm_management_git;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

import com.xiaowen.dao.UserDao;
import com.xiaowen.dao.impl.UserDaoImpl;
import com.xiaowen.pojo.User;
import com.xiaowen.util.DBUtils;

public class UserDaoImplTest {

	private UserDao userDao = new UserDaoImpl();
	
	/**
	 * selUserById(Integer id)的单元测试
	 * 
	 * @Description
	 * @author xiaowen
	 * @date 2021年8月15日下午6:10:04
	 * @throws SQLException
	 */
	@Test
	public void testSelUserById() throws SQLException {
		Connection conn = DBUtils.getConnection();
		User user = userDao.selUserById(conn, 1);
		DBUtils.closeConn(conn);
		System.out.println(user.toString());
		Assert.assertNotNull("查询失败！", user);
	}
	
	/**
	 * selUserByUsernameAndPwd(String userName, String userPwd)的单元测试
	 * 
	 * @Description
	 * @author xiaowen
	 * @date 2021年8月15日下午6:11:13
	 * @throws SQLException
	 */
	@Test
	public void testSelUserByUsernameAndPwd() throws SQLException {
		Connection conn = DBUtils.getConnection();
		User user = userDao.selUserByUsernameAndPwd(conn, "黑曼巴", "kb123456");
		DBUtils.closeConn(conn);
		if (user != null) {
			System.out.println(user.toString());
		}
		Assert.assertNotNull("登录失败！", user);
	}
	
	@Test
	public void testInsertUserSingle() throws SQLException {
		User user = new User();
		user.setUserName("篮球之神");
		user.setUserPwd("qd123456");
		user.setTrueName("迈克尔·乔丹");
		Connection conn = DBUtils.getConnection();
		int result = userDao.insUserSingle(conn, user);
		DBUtils.closeConn(conn);
		Assert.assertTrue("添加失败", result > 0);
	}
}
