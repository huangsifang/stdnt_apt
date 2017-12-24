package com.hsf.stdntapt.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.hsf.stdntapt.entity.College;

public interface InfoService {
	public List<College> getCollegeInfo(String name, MultipartFile file, String localfile);
}
