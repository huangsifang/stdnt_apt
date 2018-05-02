package com.hsf.stdntapt.controller;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hsf.stdntapt.entity.Student;
import com.hsf.stdntapt.service.ClassService;
import com.hsf.stdntapt.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {
	@Resource
	StudentService studentService;

	@Resource
	ClassService classService;

	@RequestMapping(value = "/stdName", method = RequestMethod.GET, produces = "text/html;charset=UTF-8;")
	@ResponseBody
	public String studentName(@RequestParam(value = "stdId") int stdId) {
		Student std = studentService.findOneStd(stdId);
		return std.getStdName();
	}

	@RequiresPermissions(value = { "user:view", "userStudent:view" }, logical = Logical.OR)
	@RequestMapping(value = "/{stdId}", method = RequestMethod.GET)
	@ResponseBody
	public Student staff(@PathVariable(value = "stdId") int stdId) {
		Student student = studentService.findOneStd(stdId);
		int classId = student.getClassId();
		int speciId = classService.findClassSpeciId(classId);
		student.setSpeciId(speciId);
		return student;
	}
}
