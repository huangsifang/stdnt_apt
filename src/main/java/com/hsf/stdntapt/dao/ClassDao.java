package com.hsf.stdntapt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hsf.stdntapt.entity.Class;

public interface ClassDao {
	public void insertClassList(@Param("classId") int classId, @Param("className") String className,
			@Param("speciId") int speciId, @Param("consellId") int consellId);

	public List<Class> findSpeciAllClass(@Param("speciId") int speciId);

	public int findClassSpeciId(@Param("classId") int classId);

	public Class findOne(@Param("classId") int classId);
}
