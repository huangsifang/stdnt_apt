package com.hsf.stdntapt.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

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
import com.hsf.stdntapt.entity.Dormitory;
import com.hsf.stdntapt.entity.Floor;
import com.hsf.stdntapt.entity.Student;
import com.hsf.stdntapt.service.ApartmentService;
import com.hsf.stdntapt.service.StudentService;

@Controller
@RequestMapping("/apartment")
public class ApartmentController {
	@Resource
	ApartmentService apartmentService;

	@Resource
	StudentService studentService;

	@RequiresPermissions("apartment:view")
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		List<Apartment> apartList = apartmentService.findAll();
		for (Apartment apart : apartList) {
			apart.setFloorNum(apartmentService.findFloorNum(apart.getApartId()));
			apart.setDormNum(apartmentService.findApartDormNum(apart.getApartId()));
		}
		model.addAttribute("apartList", apartList);
		return "apartment/list";
	}

	@RequiresPermissions("apartment:create")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String showCreateForm(Model model) {
		model.addAttribute("apartment", new Apartment());
		model.addAttribute("op", "新增");
		return "apartment/edit";
	}

	@RequiresPermissions("apartment:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(Apartment apartment, RedirectAttributes redirectAttributes) {
		apartmentService.createApartment(apartment);
		for (int i = 0; i < apartment.getFloorNum(); i++) {
			Floor floor = new Floor(apartment.getApartId(), i + 1);
			apartmentService.createFloor(floor);
		}
		redirectAttributes.addFlashAttribute("msg", "新增成功");
		return "redirect:/apartment";
	}

	@RequiresPermissions("apartment:update")
	@RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
	public String showUpdateForm(@PathVariable("id") int id, Model model) {
		model.addAttribute("apartment", apartmentService.findOne(id));
		model.addAttribute("op", "修改");
		return "apartment/edit";
	}

	@RequiresPermissions("apartment:update")
	@RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
	public String update(Apartment apartment, RedirectAttributes redirectAttributes) {
		apartmentService.updateApartment(apartment);
		redirectAttributes.addFlashAttribute("msg", "修改成功");
		return "redirect:/apartment";
	}

	@RequiresPermissions("apartment:delete")
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String showDeleteForm(@PathVariable("id") int id, Model model) {
		model.addAttribute("apartment", apartmentService.findOne(id));
		model.addAttribute("op", "删除");
		return "apartment/edit";
	}

	@RequiresPermissions("apartment:delete")
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	public String delete(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
		apartmentService.deleteApartment(id);
		redirectAttributes.addFlashAttribute("msg", "删除成功");
		return "redirect:/apartment";
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
		return "apartment/floor/list";
	}

	@RequiresPermissions("apartment:create")
	@RequestMapping(value = "{apartId}/{floorId}/dorm/create", method = RequestMethod.POST)
	public String dormCreate(@PathVariable("apartId") int apartId, @PathVariable("floorId") int floorId,
			@RequestParam(value = "dormNum") int dormNum, @RequestParam(value = "currentDormNum") int currentDormNum,
			RedirectAttributes redirectAttributes) {
		for (int i = currentDormNum; i < dormNum; i++) {
			Dormitory dorm = new Dormitory(i + 1, floorId);
			dorm.setLeaderId(1);
			apartmentService.createDorm(dorm);
		}
		redirectAttributes.addFlashAttribute("msg", "新增成功");
		return "redirect:/apartment/" + apartId + "/floor";
	}

	@RequiresPermissions("apartment:view")
	@RequestMapping(value = "dorm/{dormId}", method = RequestMethod.GET)
	public String dormDetail(@PathVariable("dormId") int dormId, Model model) {
		Dormitory dorm = apartmentService.findOneDorm(dormId);
		Student std = studentService.findOneStd(dorm.getLeaderId());
		dorm.setLeaderName(std.getStdName());
		model.addAttribute("dorm", dorm);
		return "apartment/floor/dorm";
	}

	@RequiresPermissions("apartment:update")
	@RequestMapping(value = "/dorm/update", method = RequestMethod.POST, produces = "text/html;charset=UTF-8;")
	@ResponseBody
	public String updateDorm(@RequestParam("dormId") int dormId, @RequestParam("fee") BigDecimal fee,
			@RequestParam("leaderId") int leaderId) {
		String msg = "";
		try {
			Dormitory dorm = new Dormitory();
			dorm.setId(dormId);
			dorm.setFee(fee);
			dorm.setLeaderId(leaderId);
			apartmentService.updateDorm(dorm);
			msg = "修改成功!";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "修改失败！";
		}
		return msg;
	}
}
