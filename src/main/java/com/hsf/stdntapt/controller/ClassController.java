package com.hsf.stdntapt.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hsf.stdntapt.entity.Class;
import com.hsf.stdntapt.service.ClassService;

@Controller
@RequestMapping("/class")
public class ClassController {
	@Resource
	ClassService classService;

	@RequiresPermissions(value = { "user:view", "userStudent:view" }, logical = Logical.OR)
	@RequestMapping(value = "/{speciId}", method = RequestMethod.GET)
	@ResponseBody
	public List<Class> speciClass(@PathVariable(value = "speciId") int speciId) {
		List<Class> classList = classService.findSpeciAllClass(speciId);
		return classList;
	}
}
