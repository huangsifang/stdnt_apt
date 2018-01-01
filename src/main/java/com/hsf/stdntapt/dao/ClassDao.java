package com.hsf.stdntapt.dao;

import org.apache.ibatis.annotations.Param;

public interface ClassDao {
	public void insertClassList(@Param("classID") int classID, @Param("className") String className,
			@Param("speciID") int speciID, @Param("consellID") int consellID);
}
