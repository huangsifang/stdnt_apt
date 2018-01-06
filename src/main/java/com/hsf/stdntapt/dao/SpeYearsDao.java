package com.hsf.stdntapt.dao;

import org.apache.ibatis.annotations.Param;

public interface SpeYearsDao {
	public void insertSpeYearsList(@Param("speYearsID") int speYearsID, @Param("speYearsName") String speYearsName,
			@Param("speYearsLength") int speYearsLength);
}
