package com.hsf.stdntapt.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hsf.stdntapt.dao.ClassDao;
import com.hsf.stdntapt.dao.CollegeDao;
import com.hsf.stdntapt.dao.ConsellorDao;
import com.hsf.stdntapt.dao.SpeYearsDao;
import com.hsf.stdntapt.dao.SpecialityDao;
import com.hsf.stdntapt.dao.StaffDao;
import com.hsf.stdntapt.dao.StudentDao;
import com.hsf.stdntapt.dao.UserDao;
import com.hsf.stdntapt.entity.Class;
import com.hsf.stdntapt.entity.College;
import com.hsf.stdntapt.entity.Consellor;
import com.hsf.stdntapt.entity.SpeYears;
import com.hsf.stdntapt.entity.Speciality;
import com.hsf.stdntapt.entity.Staff;
import com.hsf.stdntapt.entity.Student;
import com.hsf.stdntapt.service.InfoService;

@Service
public class InfoImpl implements InfoService {

	@Resource
	CollegeDao collegeDao;

	@Resource
	SpeYearsDao speYearsDao;

	@Resource
	SpecialityDao specialityDao;

	@Resource
	ConsellorDao consellorDao;

	@Resource
	ClassDao classDao;

	@Resource
	StudentDao studentDao;

	@Resource
	StaffDao staffDao;

	@Resource
	UserDao userDao;

	@Override
	public List<College> getCollegeInfo(String name, MultipartFile file) {
		ReadExcel ReadExcel = new ReadExcel();
		List<College> excelInfo = ReadExcel.getCollegeExcelInfo(name, file);
		return excelInfo;
	}

	@Override
	public void insertCollegeList(int collegeID, String collegeName) {
		collegeDao.insertCollegeList(collegeID, collegeName);
	}

	@Override
	public List<SpeYears> getSpeYearsInfo(String name, MultipartFile file) {
		ReadExcel ReadExcel = new ReadExcel();
		List<SpeYears> excelInfo = ReadExcel.getSpeYearsExcelInfo(name, file);
		return excelInfo;
	}

	@Override
	public void insertSpeYearsList(int speYearsID, String speYearsName, int speYearsLength) {
		speYearsDao.insertSpeYearsList(speYearsID, speYearsName, speYearsLength);
	}

	@Override
	public List<Speciality> getSpecialityInfo(String name, MultipartFile file) {
		ReadExcel ReadExcel = new ReadExcel();
		List<Speciality> excelInfo = ReadExcel.getSpecialityExcelInfo(name, file);
		return excelInfo;
	}

	@Override
	public void insertConsellorList(int consellID, String consellName, int consellSex, String consellTel) {
		consellorDao.insertConsellorList(consellID, consellName, consellSex, consellTel);
	}

	@Override
	public List<Consellor> getConsellorInfo(String name, MultipartFile file) {
		ReadExcel ReadExcel = new ReadExcel();
		List<Consellor> excelInfo = ReadExcel.getConsellorExcelInfo(name, file);
		return excelInfo;
	}

	@Override
	public void insertSpecialityList(int speciID, String speciName, int collegeID, int speYearsID) {
		specialityDao.insertSpecialityList(speciID, speciName, collegeID, speYearsID);
	}

	@Override
	public List<Class> getClassInfo(String name, MultipartFile file) {
		ReadExcel ReadExcel = new ReadExcel();
		List<Class> excelInfo = ReadExcel.getClassExcelInfo(name, file);
		return excelInfo;
	}

	@Override
	public void insertClassList(int classID, String className, int speciID, int consellID) {
		classDao.insertClassList(classID, className, speciID, consellID);
	}

	@Override
	public List<Student> getStudentInfo(String name, MultipartFile file) {
		ReadExcel ReadExcel = new ReadExcel();
		List<Student> excelInfo = ReadExcel.getStudentExcelInfo(name, file);
		return excelInfo;
	}

	@Override
	public void insertStudentList(int stdID, String stdName, int stdSex, String stdTel, Date enterTime, boolean isParty,
			int classID) {
		studentDao.insertStudentList(stdID, stdName, stdSex, stdTel, enterTime, isParty, classID);
	}

	@Override
	public List<Staff> getStaffInfo(String name, MultipartFile file) {
		ReadExcel ReadExcel = new ReadExcel();
		List<Staff> excelInfo = ReadExcel.getStaffExcelInfo(name, file);
		return excelInfo;
	}

	@Override
	public void insertStaffList(int staffID, String staffName, int staffSex, String staffTel, Date hiredate,
			Date leavedate) {
		staffDao.insertStaffList(staffID, staffName, staffSex, staffTel, hiredate, leavedate);
	}

	@Override
	public void insertUserList(int userID, int userType, String password) {
		userDao.insertUserList(userID, userType, password);
	}
}
