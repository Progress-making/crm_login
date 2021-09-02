package crm_management_git;

import org.junit.Assert;
import org.junit.Test;

import com.xiaowen.pojo.User;
import com.xiaowen.service.UserService;
import com.xiaowen.service.impl.UserServiceImpl;
import com.xiaowen.util.MD5Util;

public class UserServiceTest {
	UserService userService = new UserServiceImpl();
	
	@Test
	public void testLogin() {
		String userName = "大姚";
		String userPwd = "ym123456789";
		User user = userService.login(userName, MD5Util.md5Encrypt(userPwd), null);
		System.out.println(user);
		Assert.assertNotNull("登录失败", user);
	}
	
	@Test
	public void testGetUserByUsername() {
		String userName = "大姚";
		User user = userService.getUserByUsername(userName);
		System.out.println(user);
		Assert.assertNotNull("查询失败", user);
	}
	
	@Test
	public void testRegister() {
		User user = new User();
		user.setUserName("Leo");
		user.setUserPwd("Leo123456");
		user.setTrueName("莱昂纳多·迪卡普里奥");
		String confirmPwd = user.getUserPwd();
		int result = userService.register(user, confirmPwd);
		System.out.println(result);
		Assert.assertTrue("注册失败", result > 0);
	}
	
	@Test
	public void testUpdateUserInfo() {
		String userName = "黄金左脚no1";
		String trueName = "梅西";
		
		int result = userService.updateUserInfo(null, userName, trueName);
		Assert.assertTrue("修改失败", result > 0);
	}
	
	@Test
	public void testUpdatUserPwd() {
		String oldPwd = "cl123456789";
		String newPwd = "cl123456";
		String confirmPwd = "cl123456";
		int result = userService.updatUserPwd(null, oldPwd, newPwd, confirmPwd);
		Assert.assertTrue("密码修改失败", result > 0);
	}

}
