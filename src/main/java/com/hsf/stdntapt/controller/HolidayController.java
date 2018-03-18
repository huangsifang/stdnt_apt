package com.hsf.stdntapt.controller;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import com.hsf.stdntapt.entity.HoliRecord;
import com.hsf.stdntapt.entity.Holiday;
import com.hsf.stdntapt.service.ApartmentService;
import com.hsf.stdntapt.service.HolidayService;
import com.hsf.stdntapt.service.StudentService;
import com.hsf.stdntapt.service.UserService;

@Controller
@RequestMapping("/holiday")
public class HolidayController {

	@Resource
	HolidayService holidayService;

	@Resource
	ApartmentService apartmentService;

	@Resource
	UserService userService;

	@Resource
	StudentService studentService;

	@RequestMapping(method = RequestMethod.GET)
	public String holidayList(final ModelMap model) {
		final List<Holiday> holidayList = holidayService.findAllHolidays();
		String username = SecurityUtils.getSubject().getPrincipal().toString();
		Set<String> roles = userService.findRoles(username);
		for (String role : roles) {
			if (role.equals("student")) {
				int apartId = apartmentService.findStdApartId(Integer.parseInt(username));
				model.addAttribute("apartId", apartId);
				break;
			}
		}
		model.addAttribute("holidayList", holidayList);
		return "holiday/list";
	}

	@RequiresPermissions("holiday:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = "text/html;charset=UTF-8;")
	@ResponseBody
	public String createHoliday(@RequestParam("holiId") int holiId, @RequestParam("holiName") String holiName,
			@RequestParam("startTime") String startTime, @RequestParam("endTime") String endTime) {
		String msg = "";
		try {
			Holiday holiday = new Holiday(holiId, holiName);
			holiday.setStartTime(startTime);
			holiday.setEndTime(endTime);
			holidayService.createHoliday(holiday);
			msg = "新增成功!";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "新增失败！";
		}
		return msg;
	}

	@RequiresPermissions("holiday:update")
	@RequestMapping(value = "/{holiId}/update", method = RequestMethod.POST, produces = "text/html;charset=UTF-8;")
	@ResponseBody
	public String updateHoliday(@PathVariable("holiId") int holiId, @RequestParam("holiName") String holiName,
			@RequestParam("startTime") String startTime, @RequestParam("endTime") String endTime) {
		String msg = "";
		try {
			Holiday holiday = new Holiday(holiId, holiName);
			holiday.setStartTime(startTime);
			holiday.setEndTime(endTime);
			holidayService.updateHoliday(holiday);
			msg = "修改成功!";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "修改失败！";
		}
		return msg;
	}

	@RequiresPermissions("holiday:delete")
	@RequestMapping(value = "/{holiId}/delete", method = RequestMethod.POST, produces = "text/html;charset=UTF-8;")
	@ResponseBody
	public String deleteHoliday(@PathVariable("holiId") int holiId) {
		String msg = "";
		try {
			holidayService.deleteHoliday(holiId);
			msg = "删除成功!";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "删除失败！";
		}
		return msg;
	}

	@RequiresPermissions("record:create")
	@RequestMapping(value = "/{holiId}/record/create", method = RequestMethod.POST, produces = "text/html;charset=UTF-8;")
	@ResponseBody
	public String createRecord(@PathVariable("holiId") int holiId, @RequestParam("apartId") int apartId,
			@RequestParam("recordStartTime") String startTime, @RequestParam("recordEndTime") String endTime,
			@RequestParam("recordOptionsRadios") String recordOptionsRadios, HttpServletRequest request) {
		String msg = "";
		try {
			String username = SecurityUtils.getSubject().getPrincipal().toString();
			int stdId = Integer.parseInt(username);
			List<HoliRecord> currentRecord = holidayService.findHoliRecord(holiId, stdId);
			if (!currentRecord.isEmpty()) {
				msg = "您已登记过!";
			} else {
				HoliRecord record = new HoliRecord(holiId, stdId);
				record.setApartId(apartId);
				record.setStartTime(startTime);
				record.setEndTime(endTime);
				if (recordOptionsRadios.equals("school")) {
					record.setInHome(false);
					if (request.getParameter("isOut") != null) {
						record.setOut(true);
					} else {
						record.setOut(false);
					}
					holidayService.createHoliRecord(record);
				} else if (recordOptionsRadios.equals("home")) {
					String address = request.getParameter("address").toString();
					record.setInHome(true);
					record.setAddress(address);
					holidayService.createHoliRecord(record);
				}
				msg = "新增成功!";
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "新增失败！";
		}
		return msg;
	}

	@RequiresPermissions("record:view")
	@RequestMapping(value = "/{holiId}/apart/record", method = RequestMethod.GET)
	public String recordList(@PathVariable("holiId") int holiId, final ModelMap model) {
		String username = SecurityUtils.getSubject().getPrincipal().toString();
		List<Apartment> apartList = null;
		if (username.equals("admin")) {
			apartList = apartmentService.findAll();
		} else {
			apartList = apartmentService.findStaffAparts(Integer.parseInt(username));
		}
		int apartId = apartList.get(0).getApartId();
		final List<HoliRecord> recordList = holidayService.findApartAllRecords(holiId, apartId);
		for (HoliRecord record : recordList) {
			String stdName = studentService.findStdName(record.getStdId());
			if (stdName != null) {
				record.setStdName(stdName);
			}
			if (record.isInHome()) {
				record.setHomeOrSchool("返家");
				record.setIsOutStr("");
			} else {
				record.setHomeOrSchool("留校");
				if (record.isOut()) {
					record.setIsOutStr("是");
				} else {
					record.setIsOutStr("否");
				}
			}
		}
		int apartStdNum = apartmentService.findApartStdNum(apartId);
		model.addAttribute("recordList", recordList);
		model.addAttribute("recordNum", recordList.size());
		model.addAttribute("apartStdNum", apartStdNum);
		model.addAttribute("apartList", apartList);
		return "holiday/record";
	}

	@RequiresPermissions("record:view")
	@RequestMapping(value = "/{holiId}/apart/{apartId}/record", method = RequestMethod.GET)
	public String apartRecordList(@PathVariable("holiId") int holiId, @PathVariable("apartId") int apartId,
			final ModelMap model) {
		String username = SecurityUtils.getSubject().getPrincipal().toString();
		List<Apartment> apartList = null;
		if (username.equals("admin")) {
			apartList = apartmentService.findAll();
		} else {
			apartList = apartmentService.findStaffAparts(Integer.parseInt(username));
		}
		final List<HoliRecord> recordList = holidayService.findApartAllRecords(holiId, apartId);
		for (HoliRecord record : recordList) {
			String stdName = studentService.findStdName(record.getStdId());
			if (stdName != null) {
				record.setStdName(stdName);
			}
			if (record.isInHome()) {
				record.setHomeOrSchool("返家");
				record.setIsOutStr("");
			} else {
				record.setHomeOrSchool("留校");
				if (record.isOut()) {
					record.setIsOutStr("是");
				} else {
					record.setIsOutStr("否");
				}
			}
		}
		int apartStdNum = apartmentService.findApartStdNum(apartId);
		model.addAttribute("recordList", recordList);
		model.addAttribute("recordNum", recordList.size());
		model.addAttribute("apartStdNum", apartStdNum);
		model.addAttribute("apartList", apartList);
		return "holiday/record";
	}

	@RequiresPermissions("record:delete")
	@RequestMapping(value = "/record/delete", method = RequestMethod.POST, produces = "text/html;charset=UTF-8;")
	@ResponseBody
	public String deleteRecord(@RequestParam("holiId") int holiId, @RequestParam("stdId") int stdId) {
		String msg = "";
		try {
			holidayService.deleteHoliRecord(holiId, stdId);
			msg = "删除成功！";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

}
