package com.hsf.stdntapt.controller;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hsf.stdntapt.entity.Apartment;
import com.hsf.stdntapt.entity.Class;
import com.hsf.stdntapt.entity.Consellor;
import com.hsf.stdntapt.entity.RepairType;
import com.hsf.stdntapt.entity.Repairman;
import com.hsf.stdntapt.entity.Staff;
import com.hsf.stdntapt.entity.Student;
import com.hsf.stdntapt.entity.User;
import com.hsf.stdntapt.service.ApartmentService;
import com.hsf.stdntapt.service.ClassService;
import com.hsf.stdntapt.service.ConsellorService;
import com.hsf.stdntapt.service.RepairService;
import com.hsf.stdntapt.service.ResourceService;
import com.hsf.stdntapt.service.StaffService;
import com.hsf.stdntapt.service.StudentService;
import com.hsf.stdntapt.service.UserService;

@Controller
@RequestMapping("/personal")
public class PersonalController {
	@Resource
	StudentService studentService;

	@Autowired
	private UserService userService;

	@Autowired
	private StaffService staffService;

	@Resource
	ApartmentService apartmentService;

	@Resource
	ClassService classService;

	@Resource
	ConsellorService consellorService;

	@Resource
	RepairService repairService;

	@Resource
	ResourceService resourceService;

	@RequestMapping(method = RequestMethod.GET)
	public String personal(Model model) {
		String username = SecurityUtils.getSubject().getPrincipal().toString();
		int userId = 1;
		if (!username.equals("admin")) {
			userId = Integer.parseInt(username);
		}
		User user = userService.findByUsername(username);
		model.addAttribute("user", user);
		Set<String> roles = userService.findRoles(username);
		if (roles.contains("student")) {
			Student student = studentService.findOneStd(userId);
			Apartment apart = apartmentService.findStdApart(userId);
			if (apart != null) {
				student.setApartName(apart.getApartName());
			}
			int classId = student.getClassId();
			if (classId != 0) {
				Class stdClass = classService.findOne(classId);
				if (stdClass != null) {
					student.setClassName(stdClass.getClassName());
				}
			}
			model.addAttribute("userName", student.getStdName());
			model.addAttribute("userSex", student.getStdSex());
			model.addAttribute("userTel", student.getStdTel());
			model.addAttribute("student", student);
		}
		if (roles.contains("staff")) {
			Staff staff = staffService.findOneStaff(userId);
			List<Apartment> aparts = apartmentService.findStaffAparts(userId);
			staff.setAparts(aparts);
			model.addAttribute("userName", staff.getStaffName());
			model.addAttribute("userSex", staff.getStaffSex());
			model.addAttribute("userTel", staff.getStaffTel());
			model.addAttribute("staff", staff);
		}
		if (roles.contains("consellor")) {
			Consellor consellor = consellorService.findOneConsellor(userId);
			model.addAttribute("userName", consellor.getConsellName());
			model.addAttribute("userSex", consellor.getConsellSex());
			model.addAttribute("userTel", consellor.getConsellTel());
		}
		if (roles.contains("repairman")) {
			Repairman repairman = repairService.findRepairman(userId);
			List<RepairType> types = repairService.findRepairmanAllTypes(userId);
			if (types != null) {
				repairman.setTypes(types);

			}
			model.addAttribute("userName", repairman.getRepairmanName());
			model.addAttribute("userSex", repairman.getRepairmanSex());
			model.addAttribute("userTel", repairman.getRepairmanTel());
			model.addAttribute("repairman", repairman);
		}
		model.addAttribute("userId", userId);

		Set<String> permissions = userService.findPermissions(username);
		List<com.hsf.stdntapt.entity.Resource> menus = resourceService.findMenus(permissions);
		model.addAttribute("menus", menus);
		return "personal/personal";
	}
}
