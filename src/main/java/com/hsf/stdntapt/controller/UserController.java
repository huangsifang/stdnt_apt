package com.hsf.stdntapt.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hsf.stdntapt.entity.User;
import com.hsf.stdntapt.service.RoleService;
import com.hsf.stdntapt.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@RequiresPermissions("user:view")
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		setCommonData(model);
		model.addAttribute("userList", userService.findAll());
		return "user/list";
	}

	@RequiresPermissions("user:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = "text/html;charset=UTF-8;")
	@ResponseBody
	public String create(@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password, @RequestParam(value = "roleIds") String roleIds) {
		String msg = "";
		try {
			User user = new User(username, password);
			user.setRoleIdsStr(roleIds);
			userService.createUser(user);
			msg = "success";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "error";
		}
		return msg;
	}

	@RequiresPermissions("user:update")
	@RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {
		setCommonData(model);
		model.addAttribute("user", userService.findOne(id));
		model.addAttribute("op", "修改");
		return "user/edit";
	}

	@RequiresPermissions("user:update")
	@RequestMapping(value = "/{id}/update", method = RequestMethod.POST, produces = "text/html;charset=UTF-8;")
	@ResponseBody
	public String update(@PathVariable("id") Long id, @RequestParam(value = "roleIds") String roleIds) {
		String msg = "";
		try {
			User user = new User();
			user.setId(id);
			user.setRoleIdsStr(roleIds);
			userService.updateUser(user);
			msg = "success";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "error";
		}
		return msg;
	}

	@RequiresPermissions("user:delete")
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String showDeleteForm(@PathVariable("id") Long id, Model model) {
		setCommonData(model);
		model.addAttribute("user", userService.findOne(id));
		model.addAttribute("op", "删除");
		return "user/edit";
	}

	@RequiresPermissions("user:delete")
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		userService.deleteUser(id);
		redirectAttributes.addFlashAttribute("msg", "删除成功");
		return "redirect:/user";
	}

	@RequiresPermissions("user:update")
	@RequestMapping(value = "/{id}/changePassword", method = RequestMethod.GET)
	public String showChangePasswordForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("user", userService.findOne(id));
		model.addAttribute("op", "修改密码");
		return "user/changePassword";
	}

	@RequiresPermissions("user:update")
	@RequestMapping(value = "/{id}/changePassword", method = RequestMethod.POST)
	public String changePassword(@PathVariable("id") Long id, String newPassword,
			RedirectAttributes redirectAttributes) {
		userService.changePassword(id, newPassword);
		redirectAttributes.addFlashAttribute("msg", "修改密码成功");
		return "redirect:/user";
	}

	private void setCommonData(Model model) {
		model.addAttribute("roleList", roleService.findAll());
	}
}
