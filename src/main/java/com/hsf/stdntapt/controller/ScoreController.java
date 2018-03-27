package com.hsf.stdntapt.controller;

import java.text.SimpleDateFormat;
import java.util.List;

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
import com.hsf.stdntapt.entity.DormScore;
import com.hsf.stdntapt.entity.Dormitory;
import com.hsf.stdntapt.entity.Floor;
import com.hsf.stdntapt.service.ApartmentService;
import com.hsf.stdntapt.service.DormService;
import com.hsf.stdntapt.service.UserService;

@Controller
@RequestMapping("/score")
public class ScoreController {
	@Resource
	DormService dormService;

	@Resource
	ApartmentService apartmentService;

	@Resource
	UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String scoreList(final ModelMap model) {
		String username = SecurityUtils.getSubject().getPrincipal().toString();
		if (userService.findRoles(username).contains("student")) {
			int stdId = Integer.parseInt(username);
			int apartId = apartmentService.findStdApartId(stdId);
			Dormitory dorm = apartmentService.findStdDorm(stdId);
			int dormId = dorm.getId();
			int dormNo = dorm.getDormNo();
			Floor floor = apartmentService.findFloorFromDormId(dormId);
			int floorDormNo = floor.getFloorNo() * 100 + dormNo;
			return "redirect:/score/" + apartId + "/dorm/" + floorDormNo;
		} else {
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
				int scoreApartId = 0;
				if (floor != null) {
					floorId = floor.getId();
					floorNo = floor.getFloorNo();
				} else {
					model.addAttribute("msg", "没有找到对应的楼层！");
				}
				Apartment apart = apartmentService.findApartFromFloorId(floorId);
				if (apart != null) {
					scoreApartId = apart.getApartId();
				} else {
					model.addAttribute("msg", "没有找到对应的公寓！");
				}
				Dormitory dorm = apartmentService.findOneDorm(score.getDormId());
				if (dorm != null) {
					score.setFloorDormNo(floorNo * 100 + dorm.getDormNo());
				}
				score.setApartId(scoreApartId);
			}
			model.addAttribute("apartList", apartList);
			model.addAttribute("newScoreList", newScoreList);
			return "score/list";
		}
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

	@RequiresPermissions("score:view")
	@RequestMapping(value = "/{apartId}", method = RequestMethod.GET)
	public String showCreateForm(@PathVariable("apartId") int apartId, final ModelMap model) {
		String username = SecurityUtils.getSubject().getPrincipal().toString();
		List<Apartment> apartList = null;
		if (username.equals("admin")) {
			apartList = apartmentService.findAll();
		} else {
			apartList = apartmentService.findStaffAparts(Integer.parseInt(username));
		}
		final List<DormScore> newScoreList = dormService.getApartNewScores(apartId);
		for (DormScore score : newScoreList) {
			Floor floor = apartmentService.findFloorFromDormId(score.getDormId());
			int floorId = 0;
			int floorNo = 0;
			int scoreApartId = 0;
			if (floor != null) {
				floorId = floor.getId();
				floorNo = floor.getFloorNo();
			} else {
				model.addAttribute("msg", "没有找到对应的楼层！");
			}
			Apartment apart = apartmentService.findApartFromFloorId(floorId);
			if (apart != null) {
				scoreApartId = apart.getApartId();
			} else {
				model.addAttribute("msg", "没有找到对应的公寓！");
			}
			Dormitory dorm = apartmentService.findOneDorm(score.getDormId());
			if (dorm != null) {
				score.setFloorDormNo(floorNo * 100 + dorm.getDormNo());
			}
			score.setApartId(scoreApartId);
		}
		List<DormScore> apartDormScore = dormService.findApartDormScore(apartId);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<DormScore> apartDormOneDayScore = dormService.findApartDormOneDayScore(apartId,
				df.format(System.currentTimeMillis()) + "%");
		model.addAttribute("apartList", apartList);
		model.addAttribute("newScoreList", newScoreList);
		model.addAttribute("apartDormScore", apartDormScore);
		model.addAttribute("apartDormOneDayScore", apartDormOneDayScore);
		model.addAttribute("apartId", apartId);
		return "score/list";
	}

	@RequestMapping(value = "/{apartId}/dorm/{floorDormNo}", method = RequestMethod.GET)
	public String dormScoreMap(@PathVariable("apartId") int apartId, @PathVariable("floorDormNo") int floorDormNo,
			final ModelMap model) {
		int floorNo = floorDormNo / 100;
		int dormNo = floorDormNo % 100;
		int floorId = 0;
		int dormId = 0;
		Floor floor = apartmentService.findFloorByApartIdFloorNo(apartId, floorNo);
		if (floor != null) {
			floorId = floor.getId();
		} else {
			model.addAttribute("msg", "未找到对应楼层");
		}
		Dormitory dorm = apartmentService.findByDormNoFloorId(dormNo, floorId);
		if (dorm != null) {
			dormId = dorm.getId();
		} else {
			model.addAttribute("msg", "未找到对应寝室");
		}
		List<DormScore> oneDormScores = dormService.findOneDormScore(dormId);
		model.addAttribute("oneDormScores", oneDormScores);
		model.addAttribute("floorDormNo", floorDormNo);
		return "score/dormScoreMap";
	}
}
