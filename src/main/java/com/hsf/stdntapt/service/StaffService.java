package com.hsf.stdntapt.service;

import com.hsf.stdntapt.entity.Staff;

public interface StaffService {
	Staff findOneStaff(int staffId);

	public void insertStaffList(int staffId, String staffName, int staffSex, String staffTel, String hiredate,
			String leavedate);

	public int deleteOne(int staffId);

	public int updateStaff(Staff staff);
}
