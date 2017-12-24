package com.hsf.stdntapt.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hsf.stdntapt.entity.College;
import com.hsf.stdntapt.service.InfoService;

@Service
public class InfoImpl implements InfoService {

	@Override
	public List<College> getCollegeInfo(String name, MultipartFile file, String localfile) {
		ReadExcel ReadExcel = new ReadExcel();
		List<College> excelInfo = ReadExcel.getExcelInfo(name, file, localfile);
		return excelInfo;
	}
}
