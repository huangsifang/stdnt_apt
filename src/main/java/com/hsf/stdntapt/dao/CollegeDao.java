package com.hsf.stdntapt.dao;

import org.apache.ibatis.annotations.Param;

public interface CollegeDao {
	public void insertCollegeList(@Param("collegeId") int collegeId, @Param("collegeName") String collegeName);
}
