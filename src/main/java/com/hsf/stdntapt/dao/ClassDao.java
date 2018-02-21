package com.hsf.stdntapt.dao;

import org.apache.ibatis.annotations.Param;

public interface ClassDao {
	public void insertClassList(@Param("classId") int classId, @Param("className") String className,
			@Param("speciId") int speciId, @Param("consellId") int consellId);
}
