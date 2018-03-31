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
import com.hsf.stdntapt.entity.RepairType;
import com.hsf.stdntapt.entity.Repairman;
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

	@RequiresPermissions("user:view")
	@RequestMapping(value = "/repairman/{repairmanId}", method = RequestMethod.GET)
	@ResponseBody
	public Repairman repairman(@PathVariable(value = "repairmanId") int repairmanId) {
		Repairman repairman = repairService.findRepairman(repairmanId);
		List<String> typeIds = repairService.findRepairmanTypes(repairmanId);
		repairman.setTypeIds(typeIds);
		return repairman;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String repairList(@RequestParam(value = "start", required = false, defaultValue = "0") int start,
			@RequestParam(value = "size", required = false, defaultValue = "10") int size, final ModelMap model) {
		String username = SecurityUtils.getSubject().getPrincipal().toString();
		Set<String> permissions = userService.findPermissions(username);
		List<RepairType> allType = repairService.findAllRepairType();
		List<Apartment> apartList = null;
		if (username.equals("admin")) {
			apartList = apartmentService.findAll();
		} else {
			apartList = apartmentService.findStaffAparts(Integer.parseInt(username));
		}
		if (apartList != null && permissions.contains("repair:*")) {
			int apartId = apartList.get(0).getApartId();
			final List<Repair> repairList = repairService.getApartRepairsByPage(start, size, apartId);
			for (Repair repair : repairList) {
				int dormId = repair.getDormId();
				Dormitory dorm = apartmentService.findOneDorm(dormId);
				Floor floor = apartmentService.findOneFloor(dorm.getFloorId());
				repair.setDormNo(floor.getFloorNo() * 100 + dorm.getDormNo());
				Student std = studentService.findOneStd(repair.getApplicantId());
				if (std != null) {
					repair.setApplicantName(std.getStdName());
				}
				repair.setRepairTypeName(repairService.findRepairTypeName(repair.getRepairType()));
				if (repair.isDeleted()) {
					repair.setState(3);
				} else {
					RepairRecord record = repairService.findRepairRecord(repair.getId());
					if (record != null) {
						repair.setState(record.getState());
					} else {
						repair.setState(0);
					}
				}
			}
			int nowApartId = apartList.get(0).getApartId();
			model.addAttribute("repairList", repairList);
			model.addAttribute("apartList", apartList);
			model.addAttribute("apartId", nowApartId);
			model.addAttribute("start", start);
			model.addAttribute("allCount", repairService.getApartRepairs(nowApartId).size());
			return "repair/apartRepairList";
		} else if (permissions.contains("repair:create")) {
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
			model.addAttribute("allType", allType);
			return "repair/dormRepairList";
		} else if (permissions.contains("repairRecord:create")) {
			int typeId = allType.get(0).getTypeId();
			final List<Repair> repairByTypeList = repairService.getRepairsByTypeByPage(start, size, typeId);
			for (Repair repair : repairByTypeList) {
				int dormId = repair.getDormId();
				Dormitory dorm = apartmentService.findOneDorm(dormId);
				Floor floor = apartmentService.findOneFloor(dorm.getFloorId());
				repair.setDormNo(floor.getFloorNo() * 100 + dorm.getDormNo());
				Apartment apart = apartmentService.findOne(floor.getApartId());
				repair.setApartName(apart.getApartName());
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
			model.addAttribute("allType", allType);
			model.addAttribute("repairByTypeList", repairByTypeList);
			model.addAttribute("typeId", typeId);
			model.addAttribute("start", start);
			model.addAttribute("allCount", repairService.getRepairsByType(typeId).size());
			return "repair/typeRepairList";
		}
		return "unauthorized";
	}

	@RequiresPermissions("repair:view")
	@RequestMapping(value = "/apart/{apartId}", method = RequestMethod.GET)
	public String apartRepairList(@RequestParam(value = "start", required = false, defaultValue = "0") int start,
			@RequestParam(value = "size", required = false, defaultValue = "10") int size,
			@PathVariable("apartId") int apartId, final ModelMap model) {
		String username = SecurityUtils.getSubject().getPrincipal().toString();
		List<Apartment> apartList = null;
		if (username.equals("admin")) {
			apartList = apartmentService.findAll();
		} else {
			apartList = apartmentService.findStaffAparts(Integer.parseInt(username));
		}
		final List<Repair> repairList = repairService.getApartRepairsByPage(start, size, apartId);
		for (Repair repair : repairList) {
			int dormId = repair.getDormId();
			Dormitory dorm = apartmentService.findOneDorm(dormId);
			Floor floor = apartmentService.findOneFloor(dorm.getFloorId());
			repair.setDormNo(floor.getFloorNo() * 100 + dorm.getDormNo());
			Student std = studentService.findOneStd(repair.getApplicantId());
			if (std != null) {
				repair.setApplicantName(std.getStdName());
			}
			repair.setRepairTypeName(repairService.findRepairTypeName(repair.getRepairType()));
			if (repair.isDeleted()) {
				repair.setState(3);
			} else {
				RepairRecord record = repairService.findRepairRecord(repair.getId());
				if (record != null) {
					repair.setState(record.getState());
				} else {
					repair.setState(0);
				}
			}
		}
		model.addAttribute("apartList", apartList);
		model.addAttribute("repairList", repairList);
		model.addAttribute("apartId", apartId);
		model.addAttribute("start", start);
		model.addAttribute("allCount", repairService.getApartRepairs(apartId).size());
		return "repair/apartRepairList";
	}

	@RequiresPermissions("repairRecord:create")
	@RequestMapping(value = "/type/{typeId}", method = RequestMethod.GET)
	public String repairByTypeList(@RequestParam(value = "start", required = false, defaultValue = "0") int start,
			@RequestParam(value = "size", required = false, defaultValue = "10") int size,
			@PathVariable("typeId") int typeId, final ModelMap model) {
		List<RepairType> allType = repairService.findAllRepairType();
		final List<Repair> repairByTypeList = repairService.getRepairsByTypeByPage(start, size, typeId);
		for (Repair repair : repairByTypeList) {
			int dormId = repair.getDormId();
			Dormitory dorm = apartmentService.findOneDorm(dormId);
			Floor floor = apartmentService.findOneFloor(dorm.getFloorId());
			repair.setDormNo(floor.getFloorNo() * 100 + dorm.getDormNo());
			Apartment apart = apartmentService.findOne(floor.getApartId());
			repair.setApartName(apart.getApartName());
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
		model.addAttribute("repairByTypeList", repairByTypeList);
		model.addAttribute("allType", allType);
		model.addAttribute("typeId", typeId);
		model.addAttribute("start", start);
		model.addAttribute("allCount", repairService.getRepairsByType(typeId).size());
		return "repair/typeRepairList";
	}

	@RequiresPermissions("repair:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = "text/html;charset=UTF-8;")
	@ResponseBody
	public String createRepair(@RequestParam("repairType") int repairType, @RequestParam("remark") String remark) {
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
			msg = "success";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "error";
		}
		return msg;
	}

	@RequestMapping(value = "/{repairId}/record", method = RequestMethod.GET)
	public String repairRecord(@PathVariable("repairId") long repairId, final ModelMap model) {
		Repair repair = repairService.findOneRepair(repairId);
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

		RepairRecord record = repairService.findOneRepairRecordFromRepairId(repairId);
		List<RepairRecord> recordHistory = repairService.findRepairHistoryRecordFromRepairId(repairId);
		for (RepairRecord history : recordHistory) {
			Repairman repairman = repairService.findRepairman(history.getRepairmanId());
			history.setRepairman(repairman);
		}
		model.addAttribute("recordHistory", recordHistory);
		if (record != null) {
			Repairman repairman = repairService.findRepairman(record.getRepairmanId());
			repair.setState(record.getState());
			model.addAttribute("record", record);
			model.addAttribute("repairman", repairman);
		} else {
			repair.setState(0);
		}

		model.addAttribute("repair", repair);
		return "repair/repairRecord";
	}

	@RequiresPermissions("repairRecord:create")
	@RequestMapping(value = "/{repairId}/record/create", method = RequestMethod.POST, produces = "text/html;charset=UTF-8;")
	@ResponseBody
	public String createRepairRecord(@PathVariable("repairId") int repairId,
			@RequestParam("repairType") int repairType) {
		String msg = "";
		try {
			String username = SecurityUtils.getSubject().getPrincipal().toString();
			int repairmanId = Integer.parseInt(username);
			List<String> types = repairService.findRepairmanTypes(repairmanId);
			if (types.contains(String.valueOf(repairType))) {
				RepairRecord record = new RepairRecord(repairId, repairmanId);
				record.setState(1);
				repairService.createRepairRecord(record);
				msg = "success";
			} else {
				msg = "errorNoPower";
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "error";
		}
		return msg;
	}

	@RequiresPermissions("myRepair:view")
	@RequestMapping(value = "/myRepair", method = RequestMethod.GET)
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
		return "repair/repairmanRecordList";
	}

	@RequiresPermissions("myRepair:update")
	@RequestMapping(value = "/record/finish", method = RequestMethod.POST, produces = "text/html;charset=UTF-8;")
	@ResponseBody
	public String finishRepairRecord(@RequestParam("repairId") int repairId,
			@RequestParam("repairTime") String repairTime) {
		String msg = "";
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

	@RequiresPermissions("repair:delete")
	@RequestMapping(value = "/{repairId}/delete", method = RequestMethod.POST, produces = "text/html;charset=UTF-8;")
	@ResponseBody
	public String deleteRepair(@PathVariable("repairId") int repairId) {
		String msg = "";
		try {
			String username = SecurityUtils.getSubject().getPrincipal().toString();
			Repair repair = repairService.findOneRepair(repairId);
			RepairRecord record = repairService.findOneRepairRecordFromRepairId(repairId);
			if (record == null && username.equals(String.valueOf(repair.getApplicantId()))) {
				repairService.deleteRepair(repairId);
				msg = "删除成功!";
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "删除失败！";
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
