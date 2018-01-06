package com.hsf.stdntapt.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.hsf.stdntapt.entity.Class;
import com.hsf.stdntapt.entity.College;
import com.hsf.stdntapt.entity.SpeYears;

public interface InfoService {
	public List<College> getCollegeInfo(String name, MultipartFile file);

	public List<SpeYears> getSpeYearsInfo(String name, MultipartFile file);

	public List<Class> getClassInfo(String name, MultipartFile file);

	public void insertCollegeList(int collegeID, String collegeName);

	public void insertSpeYearsList(int speYearsID, String speYearsName, int speYearsLength);

	public void insertClassList(int classID, String className, int speciID, int consellID);
}
