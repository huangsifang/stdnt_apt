package com.hsf.stdntapt.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hsf.stdntapt.entity.Resource;
import com.hsf.stdntapt.entity.User;
import com.hsf.stdntapt.service.ResourceService;
import com.hsf.stdntapt.service.UserService;
import com.hsf.stdntapt.tool.CurrentUser;

/**
 * <p>
 * User: Zhang Kaitao
 * <p>
 * Date: 14-2-14
 * <p>
 * Version: 1.0
 */
@Controller
public class IndexController {

	@Autowired
	private ResourceService resourceService;
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/")
	public String index(@CurrentUser User loginUser, Model model) {
		Set<String> permissions = userService.findPermissions(loginUser.getUsername());
		List<Resource> menus = resourceService.findMenus(permissions);
		model.addAttribute("menus", menus);
		return "index";
	}

}
