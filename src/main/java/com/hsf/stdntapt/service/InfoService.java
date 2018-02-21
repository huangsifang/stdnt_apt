package com.hsf.stdntapt.service;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.hsf.stdntapt.entity.Class;
import com.hsf.stdntapt.entity.College;
import com.hsf.stdntapt.entity.Consellor;
import com.hsf.stdntapt.entity.Repairman;
import com.hsf.stdntapt.entity.SpeYears;
import com.hsf.stdntapt.entity.Speciality;
import com.hsf.stdntapt.entity.Staff;
import com.hsf.stdntapt.entity.Student;

public interface InfoService {
	public List<College> getCollegeInfo(String name, MultipartFile file);

	public List<SpeYears> getSpeYearsInfo(String name, MultipartFile file);

	public List<Speciality> getSpecialityInfo(String name, MultipartFile file);

	public List<Consellor> getConsellorInfo(String name, MultipartFile file);

	public List<Class> getClassInfo(String name, MultipartFile file);

	public List<Student> getStudentInfo(String name, MultipartFile file);

	public List<Staff> getStaffInfo(String name, MultipartFile file);

	public List<Repairman> getRepairmanInfo(String name, MultipartFile file);

	public void insertCollegeList(int collegeId, String collegeName);

	public void insertSpeYearsList(int speYearsId, String speYearsName, int speYearsLength);

	public void insertSpecialityList(int speciId, String speciName, int collegeId, int speYearsId);

	public void insertConsellorList(int consellId, String consellName, int consellSex, String consellTel);

	public void insertClassList(int classId, String className, int speciId, int consellId);

	public void insertStudentList(int stdId, String stdName, int stdSex, String stdTel, Date enterTime, boolean isParty,
			int classId);

	public void insertStaffList(int staffId, String staffName, int staffSex, String staffTel, Date hiredate,
			Date leavedate);

	public void insertRepairmanList(int repairmanId, String repairmanName, int repairmanSex, String repairmanTel,
			int repairType);

}
