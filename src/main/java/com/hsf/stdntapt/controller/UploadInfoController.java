package com.hsf.stdntapt.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hsf.stdntapt.entity.Apartment;
import com.hsf.stdntapt.entity.Class;
import com.hsf.stdntapt.entity.College;
import com.hsf.stdntapt.entity.Consellor;
import com.hsf.stdntapt.entity.Repairman;
import com.hsf.stdntapt.entity.SpeYears;
import com.hsf.stdntapt.entity.Speciality;
import com.hsf.stdntapt.entity.Staff;
import com.hsf.stdntapt.entity.Student;
import com.hsf.stdntapt.entity.User;
import com.hsf.stdntapt.service.ApartmentService;
import com.hsf.stdntapt.service.InfoService;
import com.hsf.stdntapt.service.UserService;

@Controller
@RequestMapping("/uploadInfo")
public class UploadInfoController {

	@Resource
	InfoService infoService;

	@Resource
	UserService userService;

	@Resource
	ApartmentService apartmentService;

	@RequiresRoles("admin")
	@RequestMapping(value = "/uploadInfo")
	public String uploadInfo() {
		return "uploadInfo";
	}

	/** 接收上传的文件 */
	@RequiresRoles("admin")
	@RequestMapping(value = "uploadInfoFromType", produces = "text/html;charset=UTF-8;")
	@ResponseBody
	public String uploadInfo(@RequestParam(value = "filename") MultipartFile file,
			@RequestParam(value = "filetype") String type) {
		String msg = "";
		// 获取文件名
		String name = file.getOriginalFilename();
		try {
			if (type.equals("college")) {
				List<College> collegeList = infoService.getCollegeInfo(name, file);
				for (int i = 0; i < collegeList.size(); i++) {
					infoService.insertCollegeList(collegeList.get(i).getCollegeId(),
							collegeList.get(i).getCollegeName());
				}
				msg = "解析成功,总共" + collegeList.size() + "条!";
			} else if (type.equals("speYears")) {
				List<SpeYears> speYearsList = infoService.getSpeYearsInfo(name, file);
				for (int i = 0; i < speYearsList.size(); i++) {
					infoService.insertSpeYearsList(speYearsList.get(i).getSpeYearsId(),
							speYearsList.get(i).getSpeYearsName(), speYearsList.get(i).getSpeYearsLength());
				}
				msg = "解析成功,总共" + speYearsList.size() + "条!";
			} else if (type.equals("speciality")) {
				List<Speciality> speciList = infoService.getSpecialityInfo(name, file);
				for (int i = 0; i < speciList.size(); i++) {
					infoService.insertSpecialityList(speciList.get(i).getSpeciId(), speciList.get(i).getSpeciName(),
							speciList.get(i).getCollegeId(), speciList.get(i).getSpeYearsId());
				}
				msg = "解析成功,总共" + speciList.size() + "条!";
			} else if (type.equals("consellor")) {
				List<Consellor> consellList = infoService.getConsellorInfo(name, file);
				for (int i = 0; i < consellList.size(); i++) {
					infoService.insertConsellorList(consellList.get(i).getConsellId(),
							consellList.get(i).getConsellName(), consellList.get(i).getConsellSex(),
							consellList.get(i).getConsellTel());
					User user = new User(consellList.get(i).getConsellId() + "", "123456");
					user.setRoleIdsStr("3");
					userService.createUser(user);
				}
				msg = "解析成功,总共" + consellList.size() + "条!";
			} else if (type.equals("class")) {
				List<Class> classList = infoService.getClassInfo(name, file);
				for (int i = 0; i < classList.size(); i++) {
					infoService.insertClassList(classList.get(i).getClassId(), classList.get(i).getClassName(),
							classList.get(i).getSpeciId(), classList.get(i).getConsellId());
				}
				msg = "解析成功,总共" + classList.size() + "条!";
			} else if (type.equals("student")) {
				List<Student> studentList = infoService.getStudentInfo(name, file);
				for (int i = 0; i < studentList.size(); i++) {
					infoService.insertStudentList(studentList.get(i).getStdId(), studentList.get(i).getStdName(),
							studentList.get(i).getStdSex(), studentList.get(i).getStdTel(),
							studentList.get(i).getEnterTime(), studentList.get(i).isParty(),
							studentList.get(i).getClassId());
					User user = new User(studentList.get(i).getStdId() + "", "123456");
					user.setRoleIdsStr("4");
					userService.createUser(user);
				}
				msg = "解析成功,总共" + studentList.size() + "条!";
			} else if (type.equals("staff")) {
				List<Staff> staffList = infoService.getStaffInfo(name, file);
				for (int i = 0; i < staffList.size(); i++) {
					infoService.insertStaffList(staffList.get(i).getStaffId(), staffList.get(i).getStaffName(),
							staffList.get(i).getStaffSex(), staffList.get(i).getStaffTel(),
							staffList.get(i).getHiredate(), staffList.get(i).getLeavedate());
					User user = new User(staffList.get(i).getStaffId() + "", "123456");
					user.setRoleIdsStr("2");
					userService.createUser(user);
				}
				msg = "解析成功,总共" + staffList.size() + "条!";
			} else if (type.equals("repairman")) {
				List<Repairman> repairmanList = infoService.getRepairmanInfo(name, file);
				for (int i = 0; i < repairmanList.size(); i++) {
					infoService.insertRepairmanList(repairmanList.get(i).getRepairmanId(),
							repairmanList.get(i).getRepairmanName(), repairmanList.get(i).getRepairmanSex(),
							repairmanList.get(i).getRepairmanTel(), repairmanList.get(i).getRepairType());
					User user = new User(repairmanList.get(i).getRepairmanId() + "", "123456");
					user.setRoleIdsStr("5");
					userService.createUser(user);
				}
				msg = "解析成功,总共" + repairmanList.size() + "条!";
			} else if (type.equals("apartment")) {
				List<Apartment> apartList = infoService.getApartmentInfo(name, file);
				for (int i = 0; i < apartList.size(); i++) {
					Apartment apartment = new Apartment(apartList.get(i).getApartId(), apartList.get(i).getApartName());
					apartmentService.createApartment(apartment);
				}
				msg = "解析成功,总共" + apartList.size() + "条!";
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			msg = "请确认文件内容没有空缺";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "导入失败......";
		}
		return msg;
	}
}
