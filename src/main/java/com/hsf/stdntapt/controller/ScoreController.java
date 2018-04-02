package com.hsf.stdntapt.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
			if (username.equals("admin")) {
				apartList = apartmentService.findAll();
			} else {
				apartList = apartmentService.findStaffAparts(Integer.parseInt(username));
			}
			if (!apartList.isEmpty()) {
				int apartId = apartList.get(0).getApartId();
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

				List<DormScore> apartDormScoreFinal = new ArrayList();
				List<DormScore> apartDormOneDayScoreFinal = new ArrayList();
				apartDormOneDayScoreFinal.add(new DormScore("A", 0));
				apartDormScoreFinal.add(new DormScore("A", 0));
				apartDormOneDayScoreFinal.add(new DormScore("B", 0));
				apartDormScoreFinal.add(new DormScore("B", 0));
				apartDormOneDayScoreFinal.add(new DormScore("C", 0));
				apartDormScoreFinal.add(new DormScore("C", 0));
				apartDormOneDayScoreFinal.add(new DormScore("D", 0));
				apartDormScoreFinal.add(new DormScore("D", 0));

				List<DormScore> apartDormScore = dormService.findApartDormScore(apartId);
				for (DormScore score : apartDormScore) {
					switch (score.getGrade()) {
					case "A":
						for (DormScore scoreFinal : apartDormScoreFinal) {
							if (scoreFinal.getGrade().equals("A")) {
								scoreFinal.setCount(score.getCount());
							}
						}
						break;
					case "B":
						for (DormScore scoreFinal : apartDormScoreFinal) {
							if (scoreFinal.getGrade().equals("B")) {
								scoreFinal.setCount(score.getCount());
							}
						}
						break;
					case "C":
						for (DormScore scoreFinal : apartDormScoreFinal) {
							if (scoreFinal.getGrade().equals("C")) {
								scoreFinal.setCount(score.getCount());
							}
						}
						break;
					case "D":
						for (DormScore scoreFinal : apartDormScoreFinal) {
							if (scoreFinal.getGrade().equals("D")) {
								scoreFinal.setCount(score.getCount());
							}
						}
						break;
					}
				}
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				List<DormScore> apartDormOneDayScore = dormService.findApartDormOneDayScore(apartId,
						df.format(System.currentTimeMillis()) + "%");
				for (DormScore score : apartDormOneDayScore) {
					switch (score.getGrade()) {
					case "A":
						for (DormScore scoreFinal : apartDormOneDayScoreFinal) {
							if (scoreFinal.getGrade().equals("A")) {
								scoreFinal.setCount(score.getCount());
							}
						}
						break;
					case "B":
						for (DormScore scoreFinal : apartDormOneDayScoreFinal) {
							if (scoreFinal.getGrade().equals("B")) {
								scoreFinal.setCount(score.getCount());
							}
						}
						break;
					case "C":
						for (DormScore scoreFinal : apartDormOneDayScoreFinal) {
							if (scoreFinal.getGrade().equals("C")) {
								scoreFinal.setCount(score.getCount());
							}
						}
						break;
					case "D":
						for (DormScore scoreFinal : apartDormOneDayScoreFinal) {
							if (scoreFinal.getGrade().equals("D")) {
								scoreFinal.setCount(score.getCount());
							}
						}
						break;
					}
				}
				model.addAttribute("apartDormScore", apartDormScoreFinal);
				model.addAttribute("apartDormOneDayScore", apartDormOneDayScoreFinal);
				model.addAttribute("apartId", apartId);
				model.addAttribute("newScoreList", newScoreList);
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

		String username = SecurityUtils.getSubject().getPrincipal().toString();
		Set<String> permissions = userService.findPermissions(username);
		List<com.hsf.stdntapt.entity.Resource> menus = resourceService.findMenus(permissions);
		model.addAttribute("menus", menus);
		return "score/dormScoreMap";
	}
}
