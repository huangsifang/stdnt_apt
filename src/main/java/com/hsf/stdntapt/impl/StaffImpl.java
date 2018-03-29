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

	@Override
	public void insertStaffList(int staffId, String staffName, int staffSex, String staffTel, String hiredate,
			String leavedate) {
		staffDao.insertStaffList(staffId, staffName, staffSex, staffTel, hiredate, leavedate);
	}

	@Override
	public int deleteOne(int staffId) {
		return staffDao.deleteOne(staffId);
	}

	@Override
	public int updateStaff(Staff staff) {
		return staffDao.updateStaff(staff);
	}
}
