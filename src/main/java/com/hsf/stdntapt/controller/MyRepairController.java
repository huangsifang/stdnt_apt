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
import com.hsf.stdntapt.entity.Floor;
import com.hsf.stdntapt.entity.Repair;
import com.hsf.stdntapt.entity.RepairRecord;
import com.hsf.stdntapt.entity.Student;
import com.hsf.stdntapt.service.ApartmentService;
import com.hsf.stdntapt.service.RepairService;
import com.hsf.stdntapt.service.ResourceService;
import com.hsf.stdntapt.service.StudentService;
import com.hsf.stdntapt.service.UserService;

@Controller
@RequestMapping("/myRepair")
public class MyRepairController {
	@Resource
	RepairService repairService;

	@Resource
	ApartmentService apartmentService;

	@Resource
	UserService userService;

	@Resource
	StudentService studentService;

	@Resource
	ResourceService resourceService;

	@RequiresPermissions("myRepair:view")
	@RequestMapping(method = RequestMethod.GET)
	public String myRepairList(@RequestParam(value = "start", required = false, defaultValue = "0") int start,
			@RequestParam(value = "size", required = false, defaultValue = "10") int size, final ModelMap model) {
		String username = SecurityUtils.getSubject().getPrincipal().toString();
		int repairmanId = Integer.parseInt(username);
		List<RepairRecord> myRepairRecordList = repairService.findMyRepairRecordListByPage(start, size, repairmanId);
		for (RepairRecord record : myRepairRecordList) {
			Repair repair = repairService.findOneRepair(record.getRepairId());
			int dormId = repair.getDormId();
			Dormitory dorm = apartmentService.findOneDorm(dormId);
			Floor floor = apartmentService.findOneFloor(dorm.getFloorId());
			repair.setDormNo(floor.getFloorNo() * 100 + dorm.getDormNo());
			Apartment apart = apartmentService.findOne(floor.getApartId());
			repair.setApartName(apart.getApartName());
			Student std = studentService.findOneStd(repair.getApplicantId());
			if (std != null) {
				repair.setApplicantName(std.getStdName());
				repair.setApplicantTel(std.getStdTel());
			}
			repair.setRepairTypeName(repairService.findRepairTypeName(repair.getRepairType()));
			record.setRepair(repair);
		}
		model.addAttribute("myRepairRecordList", myRepairRecordList);
		model.addAttribute("start", start);
		model.addAttribute("allCount", repairService.findMyRepairRecordList(repairmanId).size());

		Set<String> permissions = userService.findPermissions(username);
		List<com.hsf.stdntapt.entity.Resource> menus = resourceService.findMenus(permissions);
		model.addAttribute("menus", menus);
		return "repair/repairmanRecordList";
	}

	@RequiresPermissions("myRepair:update")
	@RequestMapping(value = "/record/finish", method = RequestMethod.POST, produces = "text/html;charset=UTF-8;")
	@ResponseBody
	public String finishRepairRecord(@RequestParam("repairId") int repairId,
			@RequestParam("repairTime") String repairTime) {
		String msg = "";
		System.out.println(repairTime);
		try {
			String username = SecurityUtils.getSubject().getPrincipal().toString();
			int repairmanId = Integer.parseInt(username);
			RepairRecord record = repairService.findOneRepairRecordFromRepairId(repairId);
			if (record.getRepairmanId() == repairmanId) {
				repairService.finishedRepairRecord(repairId, repairTime);
				msg = "处理成功!";
			} else {
				msg = "您没有权限处理该维修!";
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "处理失败！";
		}
		return msg;
	}

	@RequiresPermissions("repairRecord:delete")
	@RequestMapping(value = "/{repairId}/record/delete", method = RequestMethod.POST, produces = "text/html;charset=UTF-8;")
	@ResponseBody
	public String deleteRepairRecord(@PathVariable("repairId") int repairId) {
		String msg = "";
		try {
			String username = SecurityUtils.getSubject().getPrincipal().toString();
			RepairRecord record = repairService.findOneRepairRecordFromRepairId(repairId);
			if (record.getState() == 1 && username.equals(String.valueOf(record.getRepairmanId()))) {
				repairService.deleteRepairRecord(repairId);
				msg = "删除成功!";
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "删除失败！";
		}
		return msg;
	}

}
