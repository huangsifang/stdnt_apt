package com.hsf.stdntapt.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

public interface StudentDao {
	public void insertStudentList(@Param("stdId") int stdId, @Param("stdName") String stdName,
			@Param("stdSex") int stdSex, @Param("stdTel") String stdTel, @Param("enterTime") Date enterTime,
			@Param("isParty") boolean isParty, @Param("classId") int classId);
}
