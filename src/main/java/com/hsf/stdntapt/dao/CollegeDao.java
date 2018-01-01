package com.hsf.stdntapt.dao;

import org.apache.ibatis.annotations.Param;

public interface CollegeDao {
	public void insertCollegeList(@Param("collegeID") int collegeID, @Param("collegeName") String collegeName);
}
