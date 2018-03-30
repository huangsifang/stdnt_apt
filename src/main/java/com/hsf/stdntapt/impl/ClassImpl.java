package com.hsf.stdntapt.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hsf.stdntapt.dao.ClassDao;
import com.hsf.stdntapt.service.ClassService;

@Service
public class ClassImpl implements ClassService {
	@Resource
	ClassDao classDao;

	@Override
	public List<Class> findSpeciAllClass(int speciId) {
		return classDao.findSpeciAllClass(speciId);
	}

	@Override
	public int findClassSpeciId(int classId) {
		return classDao.findClassSpeciId(classId);
	}
}
