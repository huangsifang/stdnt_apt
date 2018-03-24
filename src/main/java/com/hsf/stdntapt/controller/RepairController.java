package com.hsf.stdntapt.controller;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hsf.stdntapt.entity.Apartment;
import com.hsf.stdntapt.entity.Dormitory;
import com.hsf.stdntapt.entity.Repair;
import com.hsf.stdntapt.entity.RepairRecord;
import com.hsf.stdntapt.entity.RepairType;
import com.hsf.stdntapt.entity.Student;
import com.hsf.stdntapt.service.ApartmentService;
import com.hsf.stdntapt.service.RepairService;
import com.hsf.stdntapt.service.StudentService;
import com.hsf.stdntapt.service.UserService;

@Controller
@RequestMapping("/repair")
public class RepairController {
	@Resource
	RepairService repairService;

	@Resource
	ApartmentService apartmentService;

	@Resource
	UserService userService;

	@Resource
	StudentService studentService;

	@RequestMapping(method = RequestMethod.GET)
	public String repairList(final ModelMap model) {
		String username = SecurityUtils.getSubject().getPrincipal().toString();
		List<Apartment> apartList = null;
		if (username.equals("admin")) {
			apartList = apartmentService.findAll();
		} else {
			Set<String> roles = userService.findRoles(username);
			for (String role : roles) {
				if (role.equals("staff")) {
					apartList = apartmentService.findStaffAparts(Integer.parseInt(username));
					break;
				}
			}
		}
		if (apartList != null) {
			int apartId = apartList.get(0).getApartId();
			final List<Repair> repairList = repairService.getApartRepairs(apartId);
			for (Repair repair : repairList) {
				Student std = studentService.findOneStd(repair.getApplicantId());
				if (std != null) {
					repair.setApplicantName(std.getStdName());
				}
				repair.setRepairTypeName(repairService.findRepairTypeName(repair.getRepairType()));
				RepairRecord record = repairService.findRepairRecord(repair.getId());
				if (record != null) {
					repair.setState(record.getState());
				} else {
					repair.setState(0);
				}
			}
			model.addAttribute("repairList", repairList);
		} else {
			Dormitory dorm = apartmentService.findStdDorm(Integer.parseInt(username));
			if (dorm != null) {
				List<Repair> dormRepairList = repairService.findDormRepairs(dorm.getId());
				for (Repair repair : dormRepairList) {
					Student std = studentService.findOneStd(repair.getApplicantId());
					if (std != null) {
						repair.setApplicantName(std.getStdName());
					}
					repair.setRepairTypeName(repairService.findRepairTypeName(repair.getRepairType()));
					RepairRecord record = repairService.findRepairRecord(repair.getId());
					if (record != null) {
						repair.setState(record.getState());
					} else {
						repair.setState(0);
					}
				}
				model.addAttribute("dormRepairList", dormRepairList);
			}
		}
		List<RepairType> allType = repairService.findAllRepairType();
		model.addAttribute("allType", allType);
		model.addAttribute("apartList", apartList);
		return "repair/list";
	}

	@RequiresPermissions("repair:view")
	@RequestMapping(value = "/{apartId}", method = RequestMethod.GET)
	public String apartRepairList(@PathVariable("apartId") int apartId, final ModelMap model) {
		String username = SecurityUtils.getSubject().getPrincipal().toString();
		List<Apartment> apartList = null;
		if (username.equals("admin")) {
			apartList = apartmentService.findAll();
		} else {
			apartList = apartmentService.findStaffAparts(Integer.parseInt(username));
		}
		final List<Repair> repairList = repairService.getApartRepairs(apartId);
		for (Repair repair : repairList) {
			repair.setRepairTypeName(repairService.findRepairTypeName(repair.getRepairType()));
			repair.setState(repairService.findRepairState(repair.getId()));
		}
		model.addAttribute("apartList", apartList);
		model.addAttribute("repairList", repairList);
		return "repair/list";
	}

	@RequiresPermissions("repair:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = "text/html;charset=UTF-8;")
	@ResponseBody
	public String createHoliday(@RequestParam("repairType") int repairType, @RequestParam("remark") String remark) {
		String msg = "";
		try {
			String username = SecurityUtils.getSubject().getPrincipal().toString();
			int applicantId = Integer.parseInt(username);
			Dormitory dorm = apartmentService.findStdDorm(applicantId);
			if (dorm != null) {
				Repair repair = new Repair(dorm.getId(), applicantId, repairType);
				repair.setRemark(remark);
				repairService.createRepair(repair);
			}
			msg = "新增成功!";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "新增失败！";
		}
		return msg;
	}
}
