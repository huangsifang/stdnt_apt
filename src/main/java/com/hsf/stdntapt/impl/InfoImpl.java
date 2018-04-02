package com.hsf.stdntapt.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hsf.stdntapt.dao.ApartmentDao;
import com.hsf.stdntapt.dao.ClassDao;
import com.hsf.stdntapt.dao.CollegeDao;
import com.hsf.stdntapt.dao.ConsellorDao;
import com.hsf.stdntapt.dao.RepairDao;
import com.hsf.stdntapt.dao.SpeYearsDao;
import com.hsf.stdntapt.dao.SpecialityDao;
import com.hsf.stdntapt.dao.StaffDao;
import com.hsf.stdntapt.dao.StudentDao;
import com.hsf.stdntapt.dao.UserDao;
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
import com.hsf.stdntapt.service.InfoService;
import com.hsf.stdntapt.tool.ReadExcel;

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
	RepairDao repairDao;

	@Resource
	UserDao userDao;

	@Resource
	ApartmentDao apartmentDao;

	@Override
	public List<College> getCollegeInfo(String name, MultipartFile file) {
		ReadExcel ReadExcel = new ReadExcel();
		List<College> excelInfo = ReadExcel.getCollegeExcelInfo(name, file);
		return excelInfo;
	}

	@Override
	public void insertCollegeList(int collegeId, String collegeName) {
		collegeDao.insertCollegeList(collegeId, collegeName);
	}

	@Override
	public List<SpeYears> getSpeYearsInfo(String name, MultipartFile file) {
		ReadExcel ReadExcel = new ReadExcel();
		List<SpeYears> excelInfo = ReadExcel.getSpeYearsExcelInfo(name, file);
		return excelInfo;
	}

	@Override
	public void insertSpeYearsList(int speYearsId, String speYearsName, int speYearsLength) {
		speYearsDao.insertSpeYearsList(speYearsId, speYearsName, speYearsLength);
	}

	@Override
	public List<Speciality> getSpecialityInfo(String name, MultipartFile file) {
		ReadExcel ReadExcel = new ReadExcel();
		List<Speciality> excelInfo = ReadExcel.getSpecialityExcelInfo(name, file);
		return excelInfo;
	}

	@Override
	public List<Consellor> getConsellorInfo(String name, MultipartFile file) {
		ReadExcel ReadExcel = new ReadExcel();
		List<Consellor> excelInfo = ReadExcel.getConsellorExcelInfo(name, file);
		return excelInfo;
	}

	@Override
	public void insertSpecialityList(int speciId, String speciName, int collegeId, int speYearsId) {
		specialityDao.insertSpecialityList(speciId, speciName, collegeId, speYearsId);
	}

	@Override
	public List<Class> getClassInfo(String name, MultipartFile file) {
		ReadExcel ReadExcel = new ReadExcel();
		List<Class> excelInfo = ReadExcel.getClassExcelInfo(name, file);
		return excelInfo;
	}

	@Override
	public void insertClassList(int classId, String className, int speciId, int consellId) {
		classDao.insertClassList(classId, className, speciId, consellId);
	}

	@Override
	public List<Student> getStudentInfo(String name, MultipartFile file) {
		ReadExcel ReadExcel = new ReadExcel();
		List<Student> excelInfo = ReadExcel.getStudentExcelInfo(name, file);
		return excelInfo;
	}

	@Override
	public List<StudentBed> getStudentBedInfo(String name, MultipartFile file) {
		ReadExcel ReadExcel = new ReadExcel();
		List<StudentBed> excelInfo = ReadExcel.getStudentBedExcelInfo(name, file);
		return excelInfo;
	}

	@Override
	public List<Staff> getStaffInfo(String name, MultipartFile file) {
		ReadExcel ReadExcel = new ReadExcel();
		List<Staff> excelInfo = ReadExcel.getStaffExcelInfo(name, file);
		return excelInfo;
	}

	@Override
	public List<Repairman> getRepairmanInfo(String name, MultipartFile file) {
		ReadExcel ReadExcel = new ReadExcel();
		List<Repairman> excelInfo = ReadExcel.getRepairmanExcelInfo(name, file);
		return excelInfo;
	}

	@Override
	public List<Apartment> getApartmentInfo(String name, MultipartFile file) {
		ReadExcel ReadExcel = new ReadExcel();
		List<Apartment> excelInfo = ReadExcel.getApartmentExcelInfo(name, file);
		return excelInfo;
	}

	@Override
	public List<DormScore> getScoreInfo(String name, MultipartFile file) {
		ReadExcel ReadExcel = new ReadExcel();
		List<DormScore> excelInfo = ReadExcel.getScoreExcelInfo(name, file);
		return excelInfo;
	}
}
