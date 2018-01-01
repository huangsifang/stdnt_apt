package com.hsf.stdntapt.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.hsf.stdntapt.entity.Class;
import com.hsf.stdntapt.entity.College;

public interface InfoService {
	public List<College> getCollegeInfo(String name, MultipartFile file);

	public List<Class> getClassInfo(String name, MultipartFile file);

	public void insertCollegeList(int collegeID, String collegeName);

	public void insertClassList(int classID, String className, int speciID, int consellID);
}
