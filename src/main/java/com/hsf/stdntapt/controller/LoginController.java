package com.hsf.stdntapt.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hsf.stdntapt.tool.BaseTest;

@Controller
public class LoginController extends BaseTest {

	private static Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = "/login")
	public String showLoginForm(HttpServletRequest req, Model model) {
		String exceptionClassName = (String) req.getAttribute("shiroLoginFailure");
		String error = null;
		if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
			error = "用户名/密码错误";
		} else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
			error = "用户名/密码错误";
		} else if (exceptionClassName != null) {
			error = "其他错误：" + exceptionClassName;
		}
		logger.error(error);
		model.addAttribute("error", error);
		return "login";
	}

	// @RequestMapping(value = "login")
	// public String login(final HttpServletRequest req, final ModelMap model) {
	// String error = null;
	// String username = req.getParameter("username");
	// String password = req.getParameter("password");
	// try {
	// BeanUtilsBean.getInstance().getConvertUtils().register(new
	// EnumConverter(), JdbcRealm.SaltStyle.class);
	// login(username, password);
	// } catch (UnknownAccountException e) {
	// error = "用户名/密码错误";
	// } catch (IncorrectCredentialsException e) {
	// error = "用户名/密码错误";
	// } catch (AuthenticationException e) {
	// // 其他错误，比如锁定，如果想单独处理请单独catch处理
	// error = "其他错误：" + e.getMessage();
	// }
	//
	// if (error != null) {// 出错了，返回登录页面
	// model.addAttribute("error", error);
	// return "login";
	// } else {// 登录成功
	// return "loginSuccess";
	// }
	// }

	@RequestMapping("/hello1")
	public String hello1() {
		SecurityUtils.getSubject().checkRole("admin");
		return "loginSuccess";
	}

	@RequiresRoles("admin")
	@RequestMapping("/hello2")
	public String hello2() {
		return "loginSuccess";
	}

}
