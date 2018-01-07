package com.hsf.stdntapt.dao;

import org.apache.ibatis.annotations.Param;

public interface SpecialityDao {
	public void insertSpecialityList(@Param("speciID") int speciID, @Param("speciName") String speciName,
			@Param("collegeID") int collegeID, @Param("speYearsID") int speYearsID);
}
