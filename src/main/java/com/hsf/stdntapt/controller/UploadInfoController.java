package com.hsf.stdntapt.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hsf.stdntapt.entity.Class;
import com.hsf.stdntapt.entity.College;
import com.hsf.stdntapt.entity.Consellor;
import com.hsf.stdntapt.entity.SpeYears;
import com.hsf.stdntapt.entity.Speciality;
import com.hsf.stdntapt.entity.Staff;
import com.hsf.stdntapt.entity.Student;
import com.hsf.stdntapt.entity.User;
import com.hsf.stdntapt.service.InfoService;
import com.hsf.stdntapt.service.UserService;

@Controller
@RequestMapping("/uploadInfo")
public class UploadInfoController {

	@Resource
	InfoService infoService;

	@Resource
	UserService userService;

	@RequiresRoles("admin")
	@RequestMapping(value = "/uploadInfo.do")
	public String uploadInfo() {
		return "uploadInfo";
	}

	/** 接收上传的文件 */
	@RequiresRoles("admin")
	@RequestMapping(value = "/uploadInfoFromType.do")
	public String uploadInfo(@RequestParam(value = "filename") MultipartFile file,
			@RequestParam(value = "filetype") String type, Map<String, Object> map) {
		String msg = "";
		// 获取文件名
		String name = file.getOriginalFilename();
		try {
			if (type.equals("college")) {
				List<College> collegeList = infoService.getCollegeInfo(name, file);
				for (int i = 0; i < collegeList.size(); i++) {
					infoService.insertCollegeList(collegeList.get(i).getCollegeID(),
							collegeList.get(i).getCollegeName());
				}
				msg = "解析成功,总共" + collegeList.size() + "条!";
			} else if (type.equals("speYears")) {
				List<SpeYears> speYearsList = infoService.getSpeYearsInfo(name, file);
				for (int i = 0; i < speYearsList.size(); i++) {
					infoService.insertSpeYearsList(speYearsList.get(i).getSpeYearsID(),
							speYearsList.get(i).getSpeYearsName(), speYearsList.get(i).getSpeYearsLength());
				}
				msg = "解析成功,总共" + speYearsList.size() + "条!";
			} else if (type.equals("speciality")) {
				List<Speciality> speciList = infoService.getSpecialityInfo(name, file);
				for (int i = 0; i < speciList.size(); i++) {
					infoService.insertSpecialityList(speciList.get(i).getSpeciID(), speciList.get(i).getSpeciName(),
							speciList.get(i).getCollegeID(), speciList.get(i).getSpeYearsID());
				}
				msg = "解析成功,总共" + speciList.size() + "条!";
			} else if (type.equals("consellor")) {
				List<Consellor> consellList = infoService.getConsellorInfo(name, file);
				for (int i = 0; i < consellList.size(); i++) {
					infoService.insertConsellorList(consellList.get(i).getConsellID(),
							consellList.get(i).getConsellName(), consellList.get(i).getConsellSex(),
							consellList.get(i).getConsellTel());
					User user = new User(consellList.get(i).getConsellID() + "", "123456");
					user.setRoleIdsStr("3");
					userService.createUser(user);
				}
				msg = "解析成功,总共" + consellList.size() + "条!";
			} else if (type.equals("class")) {
				List<Class> classList = infoService.getClassInfo(name, file);
				for (int i = 0; i < classList.size(); i++) {
					infoService.insertClassList(classList.get(i).getClassID(), classList.get(i).getClassName(),
							classList.get(i).getSpeciID(), classList.get(i).getConsellID());
				}
				msg = "解析成功,总共" + classList.size() + "条!";
			} else if (type.equals("student")) {
				List<Student> studentList = infoService.getStudentInfo(name, file);
				for (int i = 0; i < studentList.size(); i++) {
					infoService.insertStudentList(studentList.get(i).getStdID(), studentList.get(i).getStdName(),
							studentList.get(i).getStdSex(), studentList.get(i).getStdTel(),
							studentList.get(i).getEnterTime(), studentList.get(i).isParty(),
							studentList.get(i).getClassID());
					User user = new User(studentList.get(i).getStdID() + "", "123456");
					user.setRoleIdsStr("4");
					userService.createUser(user);
				}
				msg = "解析成功,总共" + studentList.size() + "条!";
			} else if (type.equals("staff")) {
				List<Staff> staffList = infoService.getStaffInfo(name, file);
				for (int i = 0; i < staffList.size(); i++) {
					infoService.insertStaffList(staffList.get(i).getStaffID(), staffList.get(i).getStaffName(),
							staffList.get(i).getStaffSex(), staffList.get(i).getStaffTel(),
							staffList.get(i).getHiredate(), staffList.get(i).getLeavedate());
					User user = new User(staffList.get(i).getStaffID() + "", "123456");
					user.setRoleIdsStr("2");
					userService.createUser(user);
				}
				msg = "解析成功,总共" + staffList.size() + "条!";
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			msg = "请确认文件内容没有空缺";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "导入失败......";
		}
		map.put("msg", msg);
		return "uploadInfo";
	}
}
