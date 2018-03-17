package com.hsf.stdntapt.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hsf.stdntapt.entity.Holiday;
import com.hsf.stdntapt.service.HolidayService;

@Controller
@RequestMapping("/holiday")
public class HolidayController {
	@Resource
	HolidayService holidayService;

	@RequestMapping(method = RequestMethod.GET)
	public String holidayList(final ModelMap model) {
		final List<Holiday> holidayList = holidayService.findAllHolidays();
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

}
