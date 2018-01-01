package com.hsf.stdntapt.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hsf.stdntapt.dao.ClassDao;
import com.hsf.stdntapt.dao.CollegeDao;
import com.hsf.stdntapt.entity.Class;
import com.hsf.stdntapt.entity.College;
import com.hsf.stdntapt.service.InfoService;

@Service
public class InfoImpl implements InfoService {

	@Resource
	CollegeDao collegeDao;

	@Resource
	ClassDao classDao;

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
	public List<Class> getClassInfo(String name, MultipartFile file) {
		ReadExcel ReadExcel = new ReadExcel();
		List<Class> excelInfo = ReadExcel.getClassExcelInfo(name, file);
		return excelInfo;
	}

	@Override
	public void insertClassList(int classID, String className, int speciID, int consellID) {
		classDao.insertClassList(classID, className, speciID, consellID);
	}
}
