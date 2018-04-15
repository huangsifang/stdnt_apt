package com.hsf.stdntapt.controller;

import java.text.SimpleDateFormat;
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
import com.hsf.stdntapt.entity.DormScore;
import com.hsf.stdntapt.entity.Dormitory;
import com.hsf.stdntapt.entity.Floor;
import com.hsf.stdntapt.entity.Staff;
import com.hsf.stdntapt.service.ApartmentService;
import com.hsf.stdntapt.service.DormService;
import com.hsf.stdntapt.service.ResourceService;
import com.hsf.stdntapt.service.StaffService;
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

	@Resource
	ResourceService resourceService;

	@Resource
	StaffService staffService;

	@RequestMapping(method = RequestMethod.GET)
	public String scoreList(final ModelMap model) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String username = SecurityUtils.getSubject().getPrincipal().toString();
		if (userService.findRoles(username).contains("student")) {
			int stdId = Integer.parseInt(username);
			Apartment apart = apartmentService.findStdApart(stdId);
			Dormitory dorm = apartmentService.findStdDorm(stdId);
			int floorDormNo = 0;
			int apartId = 0;
			if (apart != null || dorm != null) {
				apartId = apart.getApartId();
				int dormId = dorm.getId();
				int dormNo = dorm.getDormNo();
				Floor floor = apartmentService.findFloorFromDormId(dormId);
				floorDormNo = floor.getFloorNo() * 100 + dormNo;
			}
			return "redirect:/score/" + apartId + "/dorm/" + floorDormNo;
		} else {
			List<Apartment> apartList = null;
			if (username.equals("admin") || userService.findRoles(username).contains("consellor")) {
				apartList = apartmentService.findAll();
			} else {
				apartList = apartmentService.findStaffAparts(Integer.parseInt(username));
			}
			if (!apartList.isEmpty()) {
				int apartId = apartList.get(0).getApartId();
				final List<DormScore> topScoreList = dormService.getApartTopScores(apartId);
				for (DormScore score : topScoreList) {
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

				final List<DormScore> dayTopScoreList = dormService.getApartDayTopScores(apartId,
						df.format(System.currentTimeMillis()) + "%");
				for (DormScore score : dayTopScoreList) {
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

				List<DormScore> apartDormOneDayScore = dormService.findApartDormOneDayScore(apartId,
						df.format(System.currentTimeMillis()) + "%");

				model.addAttribute("apartDormScore", apartDormScore);
				model.addAttribute("apartDormOneDayScore", apartDormOneDayScore);
				model.addAttribute("apartId", apartId);
				model.addAttribute("topScoreList", topScoreList);
				model.addAttribute("dayTopScoreList", dayTopScoreList);
			}
			model.addAttribute("apartList", apartList);

			Set<String> permissions = userService.findPermissions(username);
			List<com.hsf.stdntapt.entity.Resource> menus = resourceService.findMenus(permissions);
			model.addAttribute("menus", menus);
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
			int staffId;
			if (username.equals("admin")) {
				staffId = 0;
			} else {
				staffId = Integer.parseInt(username);
			}
			DormScore dormScore = new DormScore(dormId, score, staffId);
			dormService.createDormScore(dormScore);
			msg = "success";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "error";
		}
		return msg;
	}

	@RequiresPermissions("score:delete")
	@RequestMapping(value = "{scoreId}/delete", method = RequestMethod.POST, produces = "text/html;charset=UTF-8;")
	@ResponseBody
	public String deleteScore(@PathVariable("scoreId") int scoreId) {
		String msg = "";
		try {
			dormService.deleteDormScore(scoreId);
			msg = "success";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "error";
		}
		return msg;
	}

	@RequiresPermissions("score:update")
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = "text/html;charset=UTF-8;")
	@ResponseBody
	public String updateScore(@RequestParam("scoreId") int scoreId, @RequestParam("score") int score) {
		String msg = "";
		try {
			String username = SecurityUtils.getSubject().getPrincipal().toString();
			int staffId;
			if (username.equals("admin")) {
				staffId = 0;
			} else {
				staffId = Integer.parseInt(username);
			}
			DormScore dormScore = new DormScore();
			dormScore.setId(scoreId);
			dormScore.setScore(score);
			dormScore.setStaffId(staffId);
			dormService.updateDormScore(dormScore);
			msg = "success";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "error";
		}
		return msg;
	}

	@RequiresPermissions("score:view")
	@RequestMapping(value = "/{apartId}", method = RequestMethod.GET)
	public String showCreateForm(@PathVariable("apartId") int apartId, final ModelMap model) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String username = SecurityUtils.getSubject().getPrincipal().toString();
		List<Apartment> apartList = null;
		if (username.equals("admin") || userService.findRoles(username).contains("consellor")) {
			apartList = apartmentService.findAll();
		} else {
			apartList = apartmentService.findStaffAparts(Integer.parseInt(username));
		}
		final List<DormScore> topScoreList = dormService.getApartTopScores(apartId);
		for (DormScore score : topScoreList) {
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

		final List<DormScore> dayTopScoreList = dormService.getApartDayTopScores(apartId,
				df.format(System.currentTimeMillis()) + "%");
		for (DormScore score : dayTopScoreList) {
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

		List<DormScore> apartDormOneDayScore = dormService.findApartDormOneDayScore(apartId,
				df.format(System.currentTimeMillis()) + "%");
		model.addAttribute("apartList", apartList);
		model.addAttribute("topScoreList", topScoreList);
		model.addAttribute("dayTopScoreList", dayTopScoreList);
		model.addAttribute("apartDormScore", apartDormScore);
		model.addAttribute("apartDormOneDayScore", apartDormOneDayScore);
		model.addAttribute("apartId", apartId);

		Set<String> permissions = userService.findPermissions(username);
		List<com.hsf.stdntapt.entity.Resource> menus = resourceService.findMenus(permissions);
		model.addAttribute("menus", menus);
		return "score/list";
	}

	@RequestMapping(value = "/{apartId}/dorm/{floorDormNo}", method = RequestMethod.GET)
	public String dormScoreMap(@RequestParam(value = "start", required = false, defaultValue = "0") int start,
			@RequestParam(value = "size", required = false, defaultValue = "10") int size,
			@PathVariable("apartId") int apartId, @PathVariable("floorDormNo") int floorDormNo, final ModelMap model) {
		int floorNo = floorDormNo / 100;
		int dormNo = floorDormNo % 100;
		Floor floor = apartmentService.findFloorByApartIdFloorNo(apartId, floorNo);
		int floorId = 0;
		int dormId = 0;
		if (floor != null) {
			floorId = floor.getId();
		}
		Dormitory dorm = apartmentService.findByDormNoFloorId(dormNo, floorId);
		if (dorm != null) {
			dormId = dorm.getId();
		}
		List<DormScore> oneDormScores = dormService.findOneDormScoreByPage(start, size, dormId);
		for (DormScore score : oneDormScores) {
			Staff staff = staffService.findOneStaff(score.getStaffId());
			if (staff != null) {
				score.setStaffName(staff.getStaffName());
			}
		}
		model.addAttribute("oneDormScores", oneDormScores);
		model.addAttribute("floorDormNo", floorDormNo);
		model.addAttribute("apartId", apartId);
		model.addAttribute("start", start);
		model.addAttribute("allCount", dormService.findOneDormScore(dormId).size());

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		final List<DormScore> dayTopScoreList = dormService.getApartDayTopScores(apartId,
				df.format(System.currentTimeMillis()) + "%");
		for (DormScore score : dayTopScoreList) {
			int dayFloorNo = 0;
			int dayDormNo = 0;
			Dormitory dayDorm = apartmentService.findOneDorm(score.getDormId());
			if (dayDorm != null) {
				dayDormNo = dayDorm.getDormNo();
				Floor dayFloor = apartmentService.findOneFloor(dayDorm.getFloorId());
				if (dayFloor != null) {
					dayFloorNo = dayFloor.getFloorNo();
				}
			}
			score.setApartId(apartId);
			score.setFloorDormNo(dayFloorNo * 100 + dayDormNo);
		}
		model.addAttribute("dayTopScoreList", dayTopScoreList);

		String username = SecurityUtils.getSubject().getPrincipal().toString();
		if (userService.findRoles(username).contains("student")) {
			model.addAttribute("showTop", true);
		}
		Set<String> permissions = userService.findPermissions(username);
		List<com.hsf.stdntapt.entity.Resource> menus = resourceService.findMenus(permissions);
		model.addAttribute("menus", menus);
		return "score/dormScoreMap";
	}
}
