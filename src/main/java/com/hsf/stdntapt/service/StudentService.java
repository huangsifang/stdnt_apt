package com.hsf.stdntapt.service;

import com.hsf.stdntapt.entity.Student;

public interface StudentService {
	Student findOneStd(int stdId);

	public String findStdName(int stdId);
}
