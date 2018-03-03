package com.hsf.stdntapt.controller;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hsf.stdntapt.entity.Student;
import com.hsf.stdntapt.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {
	@Resource
	StudentService studentService;

	@RequiresPermissions("apartment:view")
	@RequestMapping(value = "/stdName", method = RequestMethod.GET, produces = "text/html;charset=UTF-8;")
	@ResponseBody
	public String studentName(@RequestParam(value = "stdId") int stdId) {
		Student std = studentService.findOneStd(stdId);
		System.out.println(std.getStdName());
		return std.getStdName();
	}
}
