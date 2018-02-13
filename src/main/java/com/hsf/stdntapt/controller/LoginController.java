package com.hsf.stdntapt.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hsf.stdntapt.tool.BaseTest;
import com.hsf.stdntapt.tool.EnumConverter;

@Controller
public class LoginController extends BaseTest {

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(final HttpServletRequest req, final ModelMap model) {
		String error = null;
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		try {
			BeanUtilsBean.getInstance().getConvertUtils().register(new EnumConverter(), JdbcRealm.SaltStyle.class);
			login(username, password);
		} catch (UnknownAccountException e) {
			error = "用户名/密码错误";
		} catch (IncorrectCredentialsException e) {
			error = "用户名/密码错误";
		} catch (AuthenticationException e) {
			// 其他错误，比如锁定，如果想单独处理请单独catch处理
			error = "其他错误：" + e.getMessage();
		}

		if (error != null) {// 出错了，返回登录页面
			model.addAttribute("error", error);
			return "login";
		} else {// 登录成功
			return "loginSuccess";
		}
	}

}
