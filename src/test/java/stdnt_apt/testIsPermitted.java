package stdnt_apt;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class testIsPermitted {

	private static Logger logger = LoggerFactory.getLogger("testIsPermitted");

	public void login() {
		// 1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
		IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		// 2、得到SecurityManager实例 并绑定给SecurityUtils
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		// 3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("2001001", "123456");

		try {
			// 4、登录，即身份验证
			subject.login(token);
		} catch (AuthenticationException e) {
			// 5、身份验证失败
			System.out.println("身份验证失败");
			logger.error("身份验证失败." + e);
		}

		Assert.assertEquals(true, subject.isAuthenticated()); // 断言用户已经登录
	}

	@Test
	public void testIsPermitted() {
		login();
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
		login();
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
