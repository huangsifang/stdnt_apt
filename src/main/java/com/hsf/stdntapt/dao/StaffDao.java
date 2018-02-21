package com.hsf.stdntapt.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

public interface StaffDao {
	public void insertStaffList(@Param("staffId") int staffId, @Param("staffName") String staffName,
			@Param("staffSex") int staffSex, @Param("staffTel") String staffTel, @Param("hiredate") Date hiredate,
			@Param("leavedate") Date leavedate);
}
