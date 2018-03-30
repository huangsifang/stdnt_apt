package com.hsf.stdntapt.service;

import java.util.List;

public interface ClassService {
	public List<Class> findSpeciAllClass(int speciId);

	public int findClassSpeciId(int classId);
}
