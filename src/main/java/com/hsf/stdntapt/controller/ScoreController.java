package com.hsf.stdntapt.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hsf.stdntapt.entity.Apartment;
import com.hsf.stdntapt.entity.DormScore;
import com.hsf.stdntapt.entity.Dormitory;
import com.hsf.stdntapt.entity.Floor;
import com.hsf.stdntapt.service.ApartmentService;
import com.hsf.stdntapt.service.DormService;

@Controller
@RequestMapping("/score")
public class ScoreController {
	@Resource
	DormService dormService;

	@Resource
	ApartmentService apartmentService;

	@RequestMapping(method = RequestMethod.GET)
	public String scoreList(final ModelMap model) {
		String username = SecurityUtils.getSubject().getPrincipal().toString();
		List<Apartment> apartList = null;
		if (username.equals("admin")) {
			apartList = apartmentService.findAll();
		} else {
			apartList = apartmentService.findStaffAparts(Integer.parseInt(username));
		}
		final List<DormScore> newScoreList = dormService.getNewScores();
		for (DormScore score : newScoreList) {
			Floor floor = apartmentService.findFloorFromDormId(score.getDormId());
			int floorId = 0;
			int floorNo = 0;
			int apartId = 0;
			if (floor != null) {
				floorId = floor.getId();
				floorNo = floor.getFloorNo();
			} else {
				model.addAttribute("msg", "没有找到对应的楼层！");
			}
			Apartment apart = apartmentService.findApartFromFloorId(floorId);
			if (apart != null) {
				apartId = apart.getApartId();
			} else {
				model.addAttribute("msg", "没有找到对应的公寓！");
			}
			Dormitory dorm = apartmentService.findOneDorm(score.getDormId());
			if (dorm != null) {
				score.setFloorDormNo(floorNo * 100 + dorm.getDormNo());
			}
			score.setApartId(apartId);
		}
		model.addAttribute("apartList", apartList);
		model.addAttribute("newScoreList", newScoreList);
		return "score/list";
	}

	@RequiresPermissions("score:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = "text/html;charset=UTF-8;")
	@ResponseBody
	public String createScore(@RequestParam("apartId") int apartId, @RequestParam("floorDormId") int floorDormId,
			@RequestParam("score") int score) {
		String msg = "";
		try {
			int floorNo = floorDormId / 100;
			int dormNo = floorDormId % 100;
			int dormId = 0;
			int floorId = 0;
			Floor floor = apartmentService.findFloorByApartIdFloorNo(apartId, floorNo);
			if (floor != null) {
				floorId = floor.getId();
			}
			Dormitory dorm = apartmentService.findByDormNoFloorId(dormNo, floorId);
			if (dorm != null) {
				dormId = dorm.getId();
			}
			String username = SecurityUtils.getSubject().getPrincipal().toString();
			DormScore dormScore = new DormScore(dormId, score, Integer.parseInt(username));
			dormService.createDormScore(dormScore);
			msg = "修改成功!";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "修改失败！";
		}
		return msg;
	}

}
