package com.hsf.stdntapt.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.hsf.stdntapt.entity.Apartment;
import com.hsf.stdntapt.entity.Class;
import com.hsf.stdntapt.entity.College;
import com.hsf.stdntapt.entity.Consellor;
import com.hsf.stdntapt.entity.DormScore;
import com.hsf.stdntapt.entity.Repairman;
import com.hsf.stdntapt.entity.SpeYears;
import com.hsf.stdntapt.entity.Speciality;
import com.hsf.stdntapt.entity.Staff;
import com.hsf.stdntapt.entity.Student;
import com.hsf.stdntapt.entity.StudentBed;

public interface InfoService {
	public List<College> getCollegeInfo(String name, MultipartFile file);

	public List<SpeYears> getSpeYearsInfo(String name, MultipartFile file);

	public List<Speciality> getSpecialityInfo(String name, MultipartFile file);

	public List<Consellor> getConsellorInfo(String name, MultipartFile file);

	public List<Class> getClassInfo(String name, MultipartFile file);

	public List<Student> getStudentInfo(String name, MultipartFile file);

	public List<StudentBed> getStudentBedInfo(String name, MultipartFile file);

	public List<Staff> getStaffInfo(String name, MultipartFile file);

	public List<Repairman> getRepairmanInfo(String name, MultipartFile file);

	public List<Apartment> getApartmentInfo(String name, MultipartFile file);

	public List<DormScore> getScoreInfo(String name, MultipartFile file);

	public void insertCollegeList(int collegeId, String collegeName);

	public void insertSpeYearsList(int speYearsId, String speYearsName, int speYearsLength);

	public void insertSpecialityList(int speciId, String speciName, int collegeId, int speYearsId);

	public void insertClassList(int classId, String className, int speciId, int consellId);
}
