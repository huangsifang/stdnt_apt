package com.hsf.stdntapt.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hsf.stdntapt.entity.Apartment;
import com.hsf.stdntapt.entity.Bed;
import com.hsf.stdntapt.entity.DormScore;
import com.hsf.stdntapt.entity.Dormitory;
import com.hsf.stdntapt.entity.Floor;
import com.hsf.stdntapt.entity.HoliRecord;
import com.hsf.stdntapt.entity.Repair;
import com.hsf.stdntapt.entity.Staff;
import com.hsf.stdntapt.entity.Student;
import com.hsf.stdntapt.service.ApartmentService;
import com.hsf.stdntapt.service.DormService;
import com.hsf.stdntapt.service.HolidayService;
import com.hsf.stdntapt.service.RepairService;
import com.hsf.stdntapt.service.ResourceService;
import com.hsf.stdntapt.service.StudentService;
import com.hsf.stdntapt.service.UserService;

@Controller
@RequestMapping("/apartment")
public class ApartmentController {

	@Resource
	ApartmentService apartmentService;

	@Resource
	StudentService studentService;

	@Resource
	UserService userService;

	@Resource
	RepairService repairService;

	@Resource
	HolidayService holidayService;

	@Resource
	DormService dormService;

	@Resource
	ResourceService resourceService;

	@RequiresPermissions("apartment:view")
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		String username = SecurityUtils.getSubject().getPrincipal().toString();
		List<Apartment> apartList = null;
		if (username.equals("admin")) {
			apartList = apartmentService.findAll();
		} else {
			apartList = apartmentService.findStaffAparts(Integer.parseInt(username));
		}

		for (Apartment apart : apartList) {
			apart.setFloorNum(apartmentService.findFloorNum(apart.getApartId()));
			apart.setDormNum(apartmentService.findApartDormNum(apart.getApartId()));
			List<Staff> staffs = apartmentService.findApartStaffs(apart.getApartId());
			apart.setStaffs(staffs);
		}
		model.addAttribute("apartList", apartList);

		Set<String> permissions = userService.findPermissions(username);
		List<com.hsf.stdntapt.entity.Resource> menus = resourceService.findMenus(permissions);
		model.addAttribute("menus", menus);
		return "apartment/list";
	}

	@RequiresPermissions("apartment:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = "text/html;charset=UTF-8;")
	@ResponseBody
	public String create(@RequestParam(value = "apartId") int apartId,
			@RequestParam(value = "apartName") String apartName, @RequestParam(value = "floorNum") int floorNum,
			@RequestParam(value = "aFloorDormNum") int aFloorDormNum,
			@RequestParam(value = "aDormBedNum") int aDormBedNum,
			@RequestParam(value = "aStdYearFee") BigDecimal aStdYearFee) {
		String msg = "";
		try {
			Apartment apartment = new Apartment(apartId, apartName, floorNum);
			apartmentService.createApartment(apartment);
			for (int j = 0; j < apartment.getFloorNum(); j++) {
				Floor floor = new Floor(apartment.getApartId(), j + 1);
				apartmentService.createFloor(floor);
				for (int k = 0; k < aFloorDormNum; k++) {
					Dormitory dorm = new Dormitory(k + 1, floor.getId());
					dorm.setFee(aStdYearFee);
					dorm.setLeaderId(1);
					apartmentService.createDorm(dorm);
					for (int z = 0; z < aDormBedNum; z++) {
						Bed bed = new Bed(z + 1, dorm.getId());
						bed.setStdId(1);
						apartmentService.createBed(bed);
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

	@RequiresPermissions("apartment:update")
	@RequestMapping(value = "/{apartId}/update", method = RequestMethod.POST, produces = "text/html;charset=UTF-8;")
	@ResponseBody
	public String updateApartStaff(@PathVariable("apartId") int apartId, @RequestParam("apartName") String apartName,
			@RequestParam("staffId") int[] staffId) {
		String msg = "";
		try {
			Apartment apartment = new Apartment(apartId, apartName);
			apartmentService.updateApartment(apartment);
			for (int id : staffId) {
				Staff staff = apartmentService.findApartStaff(apartId, id);
				if (staff == null) {
					apartmentService.createApartStaff(apartId, id);
				}
			}
			msg = "success";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "error";
		}
		return msg;
	}

	@RequiresPermissions("apartment:delete")
	@RequestMapping(value = "/{apartId}/delete", method = RequestMethod.POST, produces = "text/html;charset=UTF-8;")
	@ResponseBody
	public String delete(@PathVariable("apartId") int apartId) {
		String msg = "";
		boolean flag = false;
		try {
			List<Staff> staffList = apartmentService.findApartStaffs(apartId);
			if (!staffList.isEmpty()) {
				msg = "errorStaff";
				return msg;
			}
			List<DormScore> scoreList = dormService.findApartDormScore(apartId);
			if (!scoreList.isEmpty()) {
				msg = "errorScore";
				return msg;
			}
			List<HoliRecord> holidayList = holidayService.findApartAllHoliRecords(apartId);
			if (!holidayList.isEmpty()) {
				msg = "errorHoliday";
				return msg;
			}
			List<Floor> floorList = apartmentService.findFloorAll(apartId);
			for (Floor floor : floorList) {
				List<Dormitory> dormList = apartmentService.findFloorDormAll(floor.getId());
				for (Dormitory dorm : dormList) {
					List<Repair> repairList = repairService.findDormRepairs(dorm.getId());
					if (!repairList.isEmpty()) {
						msg = "errorRepair";
						flag = true;
						break;
					}
					List<Bed> bedList = apartmentService.findBedsFromDorm(dorm.getId());
					for (Bed bed : bedList) {
						if (bed.getStdId() != 1) {
							msg = "errorBed";
							flag = true;
							break;
						}
						apartmentService.deleteBed(dorm.getId(), bed.getBedId());
					}
					if (flag) {
						break;
					}
					apartmentService.deleteDorm(dorm.getId());
				}
				if (flag) {
					break;
				}
				apartmentService.deleteFloor(floor.getId());
			}
			if (!flag) {
				apartmentService.deleteApartment(apartId);
				msg = "success";
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "error";
		}
		return msg;
	}

	@RequiresPermissions("apartment:delete")
	@RequestMapping(value = "/{apartId}/floor/{floorId}/delete", method = RequestMethod.POST, produces = "text/html;charset=UTF-8;")
	@ResponseBody
	public String deleteFloor(@PathVariable("apartId") int apartId, @PathVariable("floorId") int floorId) {
		String msg = "";
		boolean flag = false;
		try {
			List<Dormitory> dormList = apartmentService.findFloorDormAll(floorId);
			for (Dormitory dorm : dormList) {
				List<Repair> repairList = repairService.findDormRepairs(dorm.getId());
				if (!repairList.isEmpty()) {
					msg = "errorRepair";
					flag = true;
					break;
				}
				List<DormScore> scoreList = dormService.findOneDormScore(dorm.getId());
				if (!scoreList.isEmpty()) {
					msg = "errorScore";
					flag = true;
					break;
				}
				List<Bed> bedList = apartmentService.findBedsFromDorm(dorm.getId());
				for (Bed bed : bedList) {
					if (bed.getStdId() != 1) {
						msg = "errorBed";
						flag = true;
						break;
					}
					apartmentService.deleteBed(dorm.getId(), bed.getBedId());
				}
				if (flag) {
					break;
				}
				apartmentService.deleteDorm(dorm.getId());
			}
			if (!flag) {
				apartmentService.deleteFloor(floorId);
				msg = "success";
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "error";
		}
		return msg;
	}

	@RequiresPermissions("apartment:view")
	@RequestMapping(value = "/{id}/floor", method = RequestMethod.GET)
	public String floorList(@PathVariable("id") int id, Model model) {
		List<Floor> floorList = apartmentService.findFloorAll(id);
		for (Floor floor : floorList) {
			floor.setDormNum(apartmentService.findFloorDormNum(floor.getId()));
			List<Dormitory> dormList = apartmentService.findFloorDormAll(floor.getId());
			floor.setDormList(dormList);
		}
		model.addAttribute("floorList", floorList);
		model.addAttribute("floorNum", floorList.size());
		model.addAttribute("apartId", id);
		return "apartment/floor/list";
	}

	@RequiresPermissions("apartment:view")
	@RequestMapping(value = "/{apartId}/floor/create", method = RequestMethod.POST, produces = "text/html;charset=UTF-8;")
	@ResponseBody
	public String createFloor(@PathVariable("apartId") int apartId, @RequestParam(value = "floorNum") int floorNum) {
		String msg = "";
		try {
			Floor floor = new Floor(apartId, floorNum + 1);
			apartmentService.createFloor(floor);
			msg = "success";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "error";
		}
		return msg;
	}

	@RequiresPermissions("apartment:create")
	@RequestMapping(value = "{apartId}/{floorId}/dorm/create", method = RequestMethod.POST, produces = "text/html;charset=UTF-8;")
	@ResponseBody
	public String dormCreate(@PathVariable("apartId") int apartId, @PathVariable("floorId") int floorId,
			@RequestParam(value = "dormNum") int dormNum, @RequestParam(value = "currentDormNum") int currentDormNum,
			@RequestParam(value = "dormFee") BigDecimal dormFee, RedirectAttributes redirectAttributes) {
		int dormId = 0;
		String msg = "";
		try {
			for (int i = 0; i < dormNum; i++) {
				if (i >= currentDormNum) { // 新增宿舍
					Dormitory dorm = new Dormitory(i + 1, floorId);
					dorm.setFee(dormFee);
					dorm.setLeaderId(1);
					apartmentService.createDorm(dorm);
					dormId = dorm.getId();
				} else { // 获取已有的宿舍Id
					Dormitory dorm = apartmentService.findByDormNoFloorId(i + 1, floorId);
					if (dorm != null) {
						dormId = dorm.getId();
						dorm.setFee(dormFee);
						apartmentService.updateDorm(dorm);
					} else {
						break;
					}
				}
				/* 新增床位 */
				// int dormBedNum = apartmentService.getDormBedNum(dormId);
				// if (dormNum <= currentDormNum && aDormBedNum <= dormBedNum) {
				// msg = "errorEmpty";
				// break;
				// }
				// for (int j = dormBedNum; j < aDormBedNum; j++) {
				// Bed bed = new Bed(j + 1, dormId);
				// bed.setStdId(1);
				// apartmentService.createBed(bed);
				// }
				msg = "success";
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "error";
		}
		return msg;
	}

	@RequiresPermissions("apartment:view")
	@RequestMapping(value = "{apartId}/dorm/{dormId}", method = RequestMethod.GET)
	public String dormDetail(@PathVariable("apartId") int apartId, @PathVariable("dormId") int dormId, Model model) {
		Dormitory dorm = apartmentService.findOneDorm(dormId);
		Floor floor = apartmentService.findOneFloor(dorm.getFloorId());
		dorm.setFloorNo(floor.getFloorNo());
		// Student leader = studentService.findOneStd(dorm.getLeaderId());
		// dorm.setLeaderName(leader.getStdName());
		model.addAttribute("dorm", dorm);
		List<Bed> bedList = apartmentService.findBedsFromDorm(dormId);
		for (Bed bed : bedList) {
			if (bed.getStdId() > 0) {
				Student std = studentService.findOneStd(bed.getStdId());
				if (std != null) {
					bed.setStdName(std.getStdName());
				}
			}
		}
		model.addAttribute("bedList", bedList);
		model.addAttribute("apartId", apartId);
		model.addAttribute("dormId", dormId);
		return "apartment/floor/dorm";
	}

	@RequiresPermissions("apartment:delete")
	@RequestMapping(value = "{apartId}/dorm/{dormId}/delete", method = RequestMethod.POST, produces = "text/html;charset=UTF-8;")
	@ResponseBody
	public String deleteDorm(@PathVariable("apartId") int apartId, @PathVariable("dormId") int dormId) {
		String msg = "";
		boolean flag = false;
		try {
			List<DormScore> scoreList = dormService.findOneDormScore(dormId);
			if (!scoreList.isEmpty()) {
				msg = "errorScore";
				return msg;
			}

			List<Repair> repairList = repairService.findDormRepairs(dormId);
			if (!repairList.isEmpty()) {
				msg = "errorRepair";
				return msg;
			}
			List<Bed> bedList = apartmentService.findBedsFromDorm(dormId);
			for (Bed bed : bedList) {
				if (bed.getStdId() != 1) {
					msg = "errorBed";
					flag = true;
					break;
				}
				apartmentService.deleteBed(dormId, bed.getBedId());
			}
			if (!flag) {
				apartmentService.deleteDorm(dormId);
				msg = "success";
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "error";
		}
		return msg;
	}

	@RequiresPermissions("apartment:view")
	@RequestMapping(value = "{apartId}/floorDormId/{floorDormId}/check", method = RequestMethod.GET, produces = "text/html;charset=UTF-8;")
	@ResponseBody
	public String checkDormDetail(@PathVariable("apartId") int apartId, @PathVariable("floorDormId") int floorDormId,
			Model model) {
		int floorNo = floorDormId / 100;
		int dormNo = floorDormId % 100;
		Floor floor = apartmentService.findFloorByApartIdFloorNo(apartId, floorNo);
		int floorId = 0;
		if (floor != null) {
			floorId = floor.getId();
		} else {
			return "errorFloor";
		}
		Dormitory dorm = apartmentService.findByDormNoFloorId(dormNo, floorId);
		if (dorm == null) {
			return "errorDorm";
		}
		return "success";
	}

	@RequiresPermissions("apartment:view")
	@RequestMapping(value = "{apartId}/floorDormId/{floorDormId}", method = RequestMethod.GET)
	public String findDormDetail(@PathVariable("apartId") int apartId, @PathVariable("floorDormId") int floorDormId,
			Model model) {
		int floorNo = floorDormId / 100;
		int dormNo = floorDormId % 100;
		Floor floor = apartmentService.findFloorByApartIdFloorNo(apartId, floorNo);
		Dormitory dorm = apartmentService.findByDormNoFloorId(dormNo, floor.getId());
		dorm.setFloorNo(floorNo);
		model.addAttribute("dorm", dorm);
		List<Bed> bedList = apartmentService.findBedsFromDorm(dorm.getId());
		for (Bed bed : bedList) {
			if (bed.getStdId() > 0) {
				Student std = studentService.findOneStd(bed.getStdId());
				if (std != null) {
					bed.setStdName(std.getStdName());
				}
			}
		}
		model.addAttribute("bedList", bedList);
		return "apartment/floor/dorm";
	}

	@RequiresPermissions("apartment:update")
	@RequestMapping(value = "{apartId}/dorm/update", method = RequestMethod.POST, produces = "text/html;charset=UTF-8;")
	@ResponseBody
	public String updateDorm(@PathVariable("apartId") int apartId, @RequestParam("dormId") int dormId,
			@RequestParam("fee") BigDecimal fee) {
		String msg = "";
		try {
			Dormitory dorm = new Dormitory();
			dorm.setId(dormId);
			dorm.setFee(fee);
			// dorm.setLeaderId(leaderId);
			apartmentService.updateDorm(dorm);
			msg = "success";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "error";
		}
		return msg;
	}

	@RequiresPermissions("apartment:update")
	@RequestMapping(value = "/staff/delete", method = RequestMethod.POST, produces = "text/html;charset=UTF-8;")
	@ResponseBody
	public String deleteApartStaff(@RequestParam("apartId") int apartId, @RequestParam("staffId") int staffId) {
		String msg = "";
		try {
			apartmentService.deleteApartStaff(apartId, staffId);
			msg = "success";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "error";
		}
		return msg;
	}

	@RequiresPermissions("apartment:update")
	@RequestMapping(value = "{apartId}/dorm/student/update", method = RequestMethod.POST, produces = "text/html;charset=UTF-8;")
	@ResponseBody
	public String updateDormStd(@PathVariable("apartId") int apartId, @RequestParam("bedId") int bedId,
			@RequestParam("dormId") int dormId, @RequestParam("stdId") int stdId) {
		String msg = "";
		try {
			Bed bed = new Bed(bedId, dormId);
			bed.setStdId(stdId);
			apartmentService.updateDormStd(bed);
			msg = "success";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "error";
		}
		return msg;
	}

	@RequiresPermissions("apartment:update")
	@RequestMapping(value = "{apartId}/dorm/leader/update", method = RequestMethod.POST, produces = "text/html;charset=UTF-8;")
	@ResponseBody
	public String updateDormLeader(@PathVariable("apartId") int apartId, @RequestParam("dormId") int dormId,
			@RequestParam("stdId") int stdId) {
		String msg = "";
		try {
			apartmentService.updateDormLeader(dormId, stdId);
			msg = "success";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "error";
		}
		return msg;
	}

	@RequiresPermissions("apartment:create")
	@RequestMapping(value = "{apartId}/dorm/bed/create", method = RequestMethod.POST, produces = "text/html;charset=UTF-8;")
	@ResponseBody
	public String addBed(@PathVariable("apartId") int apartId, @RequestParam("dormId") int dormId) {
		String msg = "";
		try {
			List<Bed> bedList = apartmentService.findBedsFromDorm(dormId);
			int i = 0;
			for (i = 0; i < bedList.size(); i++) {
				if (bedList.get(i).getBedId() != i + 1) {
					Bed bed = new Bed(i + 1, dormId);
					bed.setStdId(1);
					apartmentService.createBed(bed);
					break;
				}
			}
			if (i >= bedList.size()) {
				Bed bed = new Bed(i + 1, dormId);
				bed.setStdId(1);
				apartmentService.createBed(bed);
			}
			msg = "success";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "error";
		}
		return msg;
	}

	@RequiresPermissions("apartment:delete")
	@RequestMapping(value = "{apartId}/dorm/bed/delete", method = RequestMethod.POST, produces = "text/html;charset=UTF-8;")
	@ResponseBody
	public String deleteBed(@PathVariable("apartId") int apartId, @RequestParam("dormId") int dormId,
			@RequestParam("bedId") int bedId) {
		String msg = "";
		try {
			apartmentService.deleteBed(dormId, bedId);
			msg = "success";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "error";
		}
		return msg;
	}
}
