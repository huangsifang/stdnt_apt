package com.hsf.stdntapt.controller;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hsf.stdntapt.entity.Staff;
import com.hsf.stdntapt.service.StaffService;

@Controller
@RequestMapping("/staff")
public class StaffController {
	@Resource
	StaffService staffService;

	@RequiresPermissions("user:view")
	@RequestMapping(value = "/staffName", method = RequestMethod.GET, produces = "text/html;charset=UTF-8;")
	@ResponseBody
	public String staffName(@RequestParam(value = "staffId") int staffId) {
		Staff staff = staffService.findOneStaff(staffId);
		String staffName = "";
		if (staff != null) {
			staffName = staff.getStaffName();
		}
		return staffName;
	}

	@RequiresPermissions("user:view")
	@RequestMapping(value = "/{staffId}", method = RequestMethod.GET)
	@ResponseBody
	public Staff staff(@PathVariable(value = "staffId") int staffId) {
		Staff staff = staffService.findOneStaff(staffId);
		return staff;
	}
}
