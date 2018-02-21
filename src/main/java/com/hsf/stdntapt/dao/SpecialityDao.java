package com.hsf.stdntapt.dao;

import org.apache.ibatis.annotations.Param;

public interface SpecialityDao {
	public void insertSpecialityList(@Param("speciId") int speciId, @Param("speciName") String speciName,
			@Param("collegeId") int collegeId, @Param("speYearsId") int speYearsId);
}
