package com.hsf.stdntapt.tool;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.testng.annotations.AfterSuite;

/**
 * <p>
 * User: Zhang Kaitao
 * <p>
 * Date: 14-1-26
 * <p>
 * Version: 1.0
 */
public abstract class BaseTest {

	@AfterSuite
	public void tearDown() throws Exception {
		ThreadContext.unbindSubject();// 退出时请解除绑定Subject到线程 否则对下次测试造成影响
	}

	protected void login(String username, String password) {
		// 得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);

		subject.login(token);
	}

	public Subject subject() {
		return SecurityUtils.getSubject();
	}
}
