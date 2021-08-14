package crm_management_git;

import java.sql.Connection;

import org.junit.Assert;
import org.junit.Test;

import com.xiaowen.util.DBUtils;

public class DBUtilsTest {

	@Test
	public void testGetConnection() {
		Connection conn = DBUtils.getConnection();
		Assert.assertNotNull("数据库连接失败！", conn);
	}
}
