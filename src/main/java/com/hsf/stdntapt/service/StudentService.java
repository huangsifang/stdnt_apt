package com.hsf.stdntapt.service;

import com.hsf.stdntapt.entity.Student;

public interface StudentService {
	Student findOneStd(int stdId);

	public String findStdName(int stdId);

	public void insertStudentList(int stdId, String stdName, int stdSex, String stdTel, String enterTime,
			boolean isParty, int classId);

	public int deleteOne(int stdId);

	public int updateStudent(Student student);
}
