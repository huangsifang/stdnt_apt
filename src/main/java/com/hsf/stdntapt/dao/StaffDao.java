package com.hsf.stdntapt.dao;

import org.apache.ibatis.annotations.Param;

import com.hsf.stdntapt.entity.Staff;

public interface StaffDao {
	public void insertStaffList(@Param("staffId") int staffId, @Param("staffName") String staffName,
			@Param("staffSex") int staffSex, @Param("staffTel") String staffTel, @Param("hiredate") String hiredate,
			@Param("leavedate") String leavedate);

	Staff findOneStaff(@Param("staffId") int staffId);

	public int deleteOne(@Param("staffId") int staffId);

	public int updateStaff(@Param("staff") Staff staff);
}
