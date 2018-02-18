package com.hsf.stdntapt.service;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.hsf.stdntapt.entity.Class;
import com.hsf.stdntapt.entity.College;
import com.hsf.stdntapt.entity.Consellor;
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

	public void insertCollegeList(int collegeID, String collegeName);

	public void insertSpeYearsList(int speYearsID, String speYearsName, int speYearsLength);

	public void insertSpecialityList(int speciID, String speciName, int collegeID, int speYearsID);

	public void insertConsellorList(int consellID, String consellName, int consellSex, String consellTel);

	public void insertClassList(int classID, String className, int speciID, int consellID);

	public void insertStudentList(int stdID, String stdName, int stdSex, String stdTel, Date enterTime, boolean isParty,
			int classID);

	public void insertStaffList(int staffID, String staffName, int staffSex, String staffTel, Date hiredate,
			Date leavedate);

}
