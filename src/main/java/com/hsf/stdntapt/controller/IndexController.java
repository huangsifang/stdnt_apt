package com.hsf.stdntapt.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hsf.stdntapt.entity.Apartment;
import com.hsf.stdntapt.entity.Resource;
import com.hsf.stdntapt.entity.Staff;
import com.hsf.stdntapt.entity.StaffRota;
import com.hsf.stdntapt.entity.User;
import com.hsf.stdntapt.service.ApartmentService;
import com.hsf.stdntapt.service.ResourceService;
import com.hsf.stdntapt.service.StaffService;
import com.hsf.stdntapt.service.UserService;
import com.hsf.stdntapt.tool.CurrentUser;

@Controller
public class IndexController {

	@Autowired
	private ResourceService resourceService;
	@Autowired
	private UserService userService;
	@Autowired
	private ApartmentService apartmentService;
	@Autowired
	private StaffService staffService;

	@RequestMapping(value = "/")
	public String index(@CurrentUser User loginUser, Model model) {
		Set<String> permissions = userService.findPermissions(loginUser.getUsername());
		List<Resource> menus = resourceService.findMenus(permissions);
		model.addAttribute("menus", menus);
		List<Apartment> apartList = apartmentService.findAll();
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 7;
		for (Apartment apart : apartList) {
			List<StaffRota> rotaList = apartmentService.findApartRotaAllByWeek(apart.getApartId(), w);
			for (StaffRota rota : rotaList) {
				Staff staff = staffService.findOneStaff(rota.getStaffId());
				rota.setStaff(staff);
			}
			apart.setRotas(rotaList);
		}
		model.addAttribute("apartList", apartList);
		return "index";
	}

}
