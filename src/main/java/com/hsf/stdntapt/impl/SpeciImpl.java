package com.hsf.stdntapt.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hsf.stdntapt.dao.SpeciDao;
import com.hsf.stdntapt.entity.Speciality;
import com.hsf.stdntapt.service.SpeciService;

@Service
public class SpeciImpl implements SpeciService {
	@Resource
	SpeciDao speciDao;

	@Override
	public List<Speciality> findSpeciAll() {
		return speciDao.findSpeciAll();
	}
}
