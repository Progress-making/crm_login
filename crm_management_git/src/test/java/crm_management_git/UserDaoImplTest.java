package crm_management_git;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

import com.xiaowen.dao.UserDao;
import com.xiaowen.dao.impl.UserDaoImpl;
import com.xiaowen.pojo.User;

public class UserDaoImplTest {

	private UserDao userDao = new UserDaoImpl();
	@Test
	public void testSelUserById() throws SQLException{
		User user = userDao.selUserById(1);
		System.out.println(user.getUserName());
		Assert.assertNotNull("查询失败！", user);
	}
}
