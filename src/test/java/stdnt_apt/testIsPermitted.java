package stdnt_apt;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class testIsPermitted extends BaseTest {

	private static Logger logger = LoggerFactory.getLogger("testIsPermitted");

	@Test
	public void testIsPermitted() {
		login("classpath:shiro.ini", "2001001", "123456");
		Subject subject = SecurityUtils.getSubject();
		// 判断拥有权限：user:create
		Assert.assertTrue(subject.isPermitted("user:create"));
		// 判断拥有权限：user:update and user:delete
		// Assert.assertTrue(subject.isPermittedAll("user:update",
		// "user:delete"));
		// 判断没有权限：user:view
		// Assert.assertFalse(subject.isPermitted("user:view"));

		// 6、退出
		subject.logout();
	}

	@Test(expectedExceptions = UnauthorizedException.class)
	public void testCheckPermission() {
		login("classpath:shiro.ini", "2001001", "123456");
		Subject subject = SecurityUtils.getSubject();
		// 断言拥有权限：user:create
		// subject.checkPermission("user:create");
		// 断言拥有权限：user:delete and user:update
		subject.checkPermissions("user:delete", "user:update");
		// 断言拥有权限：user:view 失败抛出异常
		// subject.checkPermissions("user:view");

		// 6、退出
		subject.logout();
	}
}
