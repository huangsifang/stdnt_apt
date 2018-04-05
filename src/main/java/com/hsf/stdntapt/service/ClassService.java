package com.hsf.stdntapt.service;

import java.util.List;

import com.hsf.stdntapt.entity.Class;

public interface ClassService {
	public List<Class> findSpeciAllClass(int speciId);

	public int findClassSpeciId(int classId);

	public Class findOne(int classId);
}
