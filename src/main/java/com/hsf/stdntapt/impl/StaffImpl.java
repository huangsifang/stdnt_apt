package com.hsf.stdntapt.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hsf.stdntapt.dao.StaffDao;
import com.hsf.stdntapt.entity.Staff;
import com.hsf.stdntapt.service.StaffService;

@Service
public class StaffImpl implements StaffService {
	@Resource
	StaffDao staffDao;

	@Override
	public Staff findOneStaff(int staffId) {
		return staffDao.findOneStaff(staffId);
	}
}
