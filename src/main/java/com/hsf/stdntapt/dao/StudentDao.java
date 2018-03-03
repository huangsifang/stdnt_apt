package com.hsf.stdntapt.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.hsf.stdntapt.entity.Student;

public interface StudentDao {
	public void insertStudentList(@Param("stdId") int stdId, @Param("stdName") String stdName,
			@Param("stdSex") int stdSex, @Param("stdTel") String stdTel, @Param("enterTime") Date enterTime,
			@Param("isParty") boolean isParty, @Param("classId") int classId);

	Student findOneStd(@Param("stdId") int stdId);
}
