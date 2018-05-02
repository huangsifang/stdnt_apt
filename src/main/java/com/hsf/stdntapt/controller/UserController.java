package com.hsf.stdntapt.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hsf.stdntapt.entity.Class;
import com.hsf.stdntapt.entity.Consellor;
import com.hsf.stdntapt.entity.Dormitory;
import com.hsf.stdntapt.entity.HoliRecord;
import com.hsf.stdntapt.entity.Repair;
import com.hsf.stdntapt.entity.RepairType;
import com.hsf.stdntapt.entity.Repairman;
import com.hsf.stdntapt.entity.Resource;
import com.hsf.stdntapt.entity.Role;
import com.hsf.stdntapt.entity.Speciality;
import com.hsf.stdntapt.entity.Staff;
import com.hsf.stdntapt.entity.Student;
import com.hsf.stdntapt.entity.User;
import com.hsf.stdntapt.service.ApartmentService;
import com.hsf.stdntapt.service.ClassService;
import com.hsf.stdntapt.service.ConsellorService;
import com.hsf.stdntapt.service.HolidayService;
import com.hsf.stdntapt.service.RepairService;
import com.hsf.stdntapt.service.ResourceService;
import com.hsf.stdntapt.service.RoleService;
import com.hsf.stdntapt.service.SpeciService;
import com.hsf.stdntapt.service.StaffService;
import com.hsf.stdntapt.service.StudentService;
import com.hsf.stdntapt.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private StaffService staffService;

	@Autowired
	private ConsellorService consellorService;

	@Autowired
	private StudentService studentService;

	@Autowired
	private RepairService repairService;

	@Autowired
	private ClassService classService;

	@Autowired
	private SpeciService speciService;

	@Autowired
	private ResourceService resourceService;

	@Autowired
	private ApartmentService apartmentService;

	@Autowired
	private HolidayService holidayService;

	@RequiresPermissions("user:view")
	@RequestMapping(method = RequestMethod.GET)
	public String list(@RequestParam(value = "start", required = false, defaultValue = "0") int start,
			@RequestParam(value = "size", required = false, defaultValue = "10") int size, Model model) {
		setCommonData(model);
		List<Speciality> speciList = speciService.findSpeciAll();
		List<Class> classList = classService.findSpeciAllClass(1);
		List<RepairType> allTypeList = repairService.findAllRepairType();
		List<Role> roleList = roleService.findAll();
		List<User> userList = userService.findOneRoleAllPage(start, size, 1);
		for (User user : userList) {
			String userName = user.getUsername();
			int userId = 1;
			if (!userName.equals("admin")) {
				userId = Integer.parseInt(userName);
			}
			List<Role> roles = new ArrayList();
			for (Long oneRoleId : user.getRoleIds()) {
				if (oneRoleId == 2) {
					Staff staff = staffService.findOneStaff(userId);
					if (staff != null) {
						user.setName(staff.getStaffName());
					}
				} else if (oneRoleId == 3) {
					Consellor consellor = consellorService.findOneConsellor(userId);
					if (consellor != null) {
						user.setName(consellor.getConsellName());
					}
				} else if (oneRoleId == 4) {
					Student student = studentService.findOneStd(userId);
					if (student != null) {
						user.setName(student.getStdName());
					}
				} else if (oneRoleId == 5) {
					Repairman repairman = repairService.findRepairman(userId);
					if (repairman != null) {
						user.setName(repairman.getRepairmanName());
					}
				}
				Role role = roleService.findOne(oneRoleId);
				roles.add(role);
			}
			user.setRoles(roles);
		}
		model.addAttribute("userList", userList);
		model.addAttribute("roleList", roleList);
		model.addAttribute("speciList", speciList);
		model.addAttribute("classList", classList);
		model.addAttribute("allTypeList", allTypeList);
		model.addAttribute("roleId", 1);
		model.addAttribute("start", start);
		model.addAttribute("allCount", userService.findOneRoleAll(1).size());

		String username = SecurityUtils.getSubject().getPrincipal().toString();
		Set<String> permissions = userService.findPermissions(username);
		List<Resource> menus = resourceService.findMenus(permissions);
		model.addAttribute("menus", menus);
		return "user/list";
	}

	@RequiresPermissions(value = { "user:view", "userStudent:view" }, logical = Logical.OR)
	@RequestMapping(value = "/role/{roleId}", method = RequestMethod.GET)
	public String userRolelist(@RequestParam(value = "start", required = false, defaultValue = "0") int start,
			@RequestParam(value = "size", required = false, defaultValue = "10") int size,
			@PathVariable("roleId") int roleId, Model model) {
		List<Speciality> speciList = speciService.findSpeciAll();
		List<Class> classList = classService.findSpeciAllClass(1);
		List<RepairType> allTypeList = repairService.findAllRepairType();

		List<User> userList = userService.findOneRoleAllPage(start, size, roleId);
		for (User user : userList) {
			String userName = user.getUsername();
			int userId = 1;
			if (!userName.equals("admin")) {
				userId = Integer.parseInt(userName);
			}
			List<Role> roles = new ArrayList();
			for (Long oneRoleId : user.getRoleIds()) {
				if (oneRoleId == 2) {
					Staff staff = staffService.findOneStaff(userId);
					if (staff != null) {
						user.setName(staff.getStaffName());
					}
				} else if (oneRoleId == 3) {
					Consellor consellor = consellorService.findOneConsellor(userId);
					if (consellor != null) {
						user.setName(consellor.getConsellName());
					}
				} else if (oneRoleId == 4) {
					Student student = studentService.findOneStd(userId);
					if (student != null) {
						user.setName(student.getStdName());
					}
				} else if (oneRoleId == 5) {
					Repairman repairman = repairService.findRepairman(userId);
					if (repairman != null) {
						user.setName(repairman.getRepairmanName());
					}
				}
				Role role = roleService.findOne(oneRoleId);
				roles.add(role);
			}
			user.setRoles(roles);
		}
		model.addAttribute("userList", userList);
		model.addAttribute("speciList", speciList);
		model.addAttribute("classList", classList);
		String username = SecurityUtils.getSubject().getPrincipal().toString();
		Set<String> permissions = userService.findPermissions(username);
		if (permissions.contains("user:*")) {
			setCommonData(model);
		}
		model.addAttribute("allTypeList", allTypeList);
		model.addAttribute("roleId", roleId);
		model.addAttribute("start", start);
		model.addAttribute("allCount", userService.findOneRoleAll(roleId).size());

		List<Resource> menus = resourceService.findMenus(permissions);
		model.addAttribute("menus", menus);
		return "user/list";
	}

	@RequiresPermissions(value = { "user:create", "userStudent:create" }, logical = Logical.OR)
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = "text/html;charset=UTF-8;")
	@ResponseBody
	public String create(@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password,
			@RequestParam(value = "roleIds", required = false, defaultValue = "4") String roleIds,
			@RequestParam(value = "name") String name, @RequestParam(value = "sex") String sex,
			@RequestParam(value = "tel") String tel, @RequestParam(value = "hiredate") String hiredate,
			@RequestParam(value = "isParty") String isParty, @RequestParam(value = "classId") int classId,
			@RequestParam(value = "typeIds", required = false) int[] typeIds) {
		String msg = "";
		boolean isPartyBoolean = false;
		if (!isParty.isEmpty()) {
			isPartyBoolean = true;
		}
		try {
			User user = new User(username, password);
			user.setRoleIdsStr(roleIds);

			int usernameInt = Integer.parseInt(username);
			int sexInt = 1;
			if (sex.equals("female")) {
				sexInt = 2;
			}
			String leavedate = null;
			List<Long> roleIdsList = user.getRoleIds();
			for (Long roleId : roleIdsList) {
				if (roleId == 2) {
					staffService.insertStaffList(usernameInt, name, sexInt, tel, hiredate, leavedate);
				} else if (roleId == 3) {
					consellorService.insertConsellorList(usernameInt, name, sexInt, tel);
				} else if (roleId == 4) {
					studentService.insertStudentList(usernameInt, name, sexInt, tel, hiredate, isPartyBoolean, classId);
				} else if (roleId == 5) {
					repairService.insertRepairmanList(usernameInt, name, sexInt, tel);
					for (int typeId : typeIds) {
						repairService.insertRepairmanTypeRelation(usernameInt, typeId);
					}
				}
			}
			userService.createUser(user);
			msg = "success";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "error";
		}
		return msg;
	}

	@RequiresPermissions(value = { "user:update", "userStudent:update" }, logical = Logical.OR)
	@RequestMapping(value = "/{id}/update", method = RequestMethod.POST, produces = "text/html;charset=UTF-8;")
	@ResponseBody
	public String update(@PathVariable("id") Long id, @RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password,
			@RequestParam(value = "roleIds", required = false, defaultValue = "4") String roleIds,
			@RequestParam(value = "name") String name, @RequestParam(value = "sex") String sex,
			@RequestParam(value = "tel") String tel, @RequestParam(value = "hiredate") String hiredate,
			@RequestParam(value = "isParty") String isParty, @RequestParam(value = "classId") int classId,
			@RequestParam(value = "typeIds", required = false) List<String> typeIds) {
		String msg = "";
		boolean isPartyBoolean = false;
		if (isParty.equals("true")) {
			isPartyBoolean = true;
		} else {
			isPartyBoolean = false;
		}
		try {
			int usernameInt = Integer.parseInt(username);
			int sexInt = 1;
			if (sex.equals("female")) {
				sexInt = 2;
			}
			String leavedate = null;

			User currentUser = userService.findOne(id);
			List<Long> currentRoleIdsList = currentUser.getRoleIds();
			for (int i = 0; i < currentRoleIdsList.size(); i++) {
				Long roleId = currentRoleIdsList.get(i);
				if (!roleIds.contains(String.valueOf(roleId))) {
					if (roleId == 2) {
						staffService.deleteOne(usernameInt);
					} else if (roleId == 3) {
						consellorService.deleteOne(usernameInt);
					} else if (roleId == 4) {
						studentService.deleteOne(usernameInt);
					} else if (roleId == 5) {
						repairService.deleteOneRepairman(usernameInt);
						repairService.deleteRepairmanAllType(usernameInt);
					}
				}
			}

			User user = new User();
			user.setId(id);
			user.setRoleIdsStr(roleIds);
			userService.updateUser(user);

			List<Long> roleIdsList = user.getRoleIds();
			for (Long roleId : roleIdsList) {
				if (roleId == 2) {
					Staff staff = staffService.findOneStaff(usernameInt);
					if (staff == null) {
						staffService.insertStaffList(usernameInt, name, sexInt, tel, hiredate, leavedate);
					} else {
						Staff newStaff = new Staff(usernameInt, name);
						newStaff.setStaffSex(sexInt);
						newStaff.setStaffTel(tel);
						if (hiredate.equals("")) {
							newStaff.setHiredateStr(null);
						} else {
							newStaff.setHiredateStr(hiredate);
						}
						staffService.updateStaff(newStaff);
					}
				} else if (roleId == 3) {
					Consellor consellor = consellorService.findOneConsellor(usernameInt);
					if (consellor == null) {
						consellorService.insertConsellorList(usernameInt, name, sexInt, tel);
					} else {
						Consellor newConsellor = new Consellor(usernameInt, name);
						newConsellor.setConsellSex(sexInt);
						newConsellor.setConsellTel(tel);
						consellorService.updateConsellor(newConsellor);
					}
				} else if (roleId == 4) {
					Student student = studentService.findOneStd(usernameInt);
					if (student == null) {
						studentService.insertStudentList(usernameInt, name, sexInt, tel, hiredate, isPartyBoolean,
								classId);
					} else {
						Student newStudent = new Student(usernameInt, name);
						newStudent.setStdSex(sexInt);
						newStudent.setStdTel(tel);
						newStudent.setEnterTimeStr(hiredate);
						newStudent.setParty(isPartyBoolean);
						newStudent.setClassId(classId);
						studentService.updateStudent(newStudent);
					}
				} else if (roleId == 5) {
					Repairman repairman = repairService.findRepairman(usernameInt);
					if (repairman == null) {
						repairService.insertRepairmanList(usernameInt, name, sexInt, tel);
						for (String typeId : typeIds) {
							repairService.insertRepairmanTypeRelation(usernameInt, Integer.parseInt(typeId));
						}
					} else {
						Repairman newRepairman = new Repairman(usernameInt, name);
						newRepairman.setRepairmanSex(sexInt);
						newRepairman.setRepairmanTel(tel);
						repairService.updateRepairman(newRepairman);
						List<String> currentTypes = repairService.findRepairmanTypes(usernameInt);
						for (String currentType : currentTypes) {
							if (!typeIds.contains(currentType)) {
								repairService.deleteRepairmanType(usernameInt, Integer.parseInt(currentType));
							}
						}
						for (String typeId : typeIds) {
							if (!currentTypes.contains(typeId)) {
								repairService.insertRepairmanTypeRelation(usernameInt, Integer.parseInt(typeId));
							}
						}
					}
				}
			}
			msg = "success";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "error";
		}
		return msg;
	}

	@RequiresPermissions(value = { "user:delete", "userStudent:delete" }, logical = Logical.OR)
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST, produces = "text/html;charset=UTF-8;")
	@ResponseBody
	public String delete(@PathVariable("id") Long id, @RequestParam(value = "username") int username,
			@RequestParam(value = "roleIdsStr") String roleIdsStr) {
		String msg = "";
		try {
			if (roleIdsStr.contains("2")) {
				userService.deleteUser(id);
				staffService.deleteOne(username);
			} else if (roleIdsStr.contains("3")) {
				userService.deleteUser(id);
				consellorService.deleteOne(username);
			} else if (roleIdsStr.contains("4")) {
				Dormitory dorm = apartmentService.findStdDorm(username);
				if (dorm != null) {
					return "errorDorm";
				}
				List<Dormitory> dormList = apartmentService.findDormByLeaderId(username);
				if (dormList.size() != 0) {
					return "errorDormLeader";
				}
				List<Repair> repairList = repairService.findRepairByApplicantId(username);
				if (repairList.size() != 0) {
					return "errorRepair";
				}
				List<HoliRecord> holiRecordList = holidayService.findStdAllHoliRecord(username);
				if (holiRecordList.size() != 0) {
					return "errorHoliRecord";
				}
				userService.deleteUser(id);
				studentService.deleteOne(username);
			} else if (roleIdsStr.contains("5")) {
				userService.deleteUser(id);
				repairService.deleteOneRepairman(username);
				repairService.deleteRepairmanAllType(username);
			}
			msg = "success";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "error";
		}
		return msg;
	}

	@RequestMapping(value = "/{id}/changePassword", method = RequestMethod.POST, produces = "text/html;charset=UTF-8;")
	@ResponseBody
	public String changePassword(@PathVariable("id") Long id, @RequestParam("newPassword") String newPassword) {
		String msg = "";
		try {
			userService.changePassword(id, newPassword);
			msg = "success";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "error";
		}
		return msg;
	}

	private void setCommonData(Model model) {
		model.addAttribute("roleList", roleService.findAll());
	}
}
