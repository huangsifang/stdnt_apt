package com.hsf.stdntapt.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hsf.stdntapt.dao.StudentDao;
import com.hsf.stdntapt.entity.Student;
import com.hsf.stdntapt.service.StudentService;

@Service
public class StudentImpl implements StudentService {
	@Resource
	StudentDao studentDao;

	@Override
	public Student findOneStd(int stdId) {
		return studentDao.findOneStd(stdId);
	}

	@Override
	public String findStdName(int stdId) {
		return studentDao.findStdName(stdId);
	}

	@Override
	public void insertStudentList(int stdId, String stdName, int stdSex, String stdTel, String enterTime,
			boolean isParty, int classId) {
		studentDao.insertStudentList(stdId, stdName, stdSex, stdTel, enterTime, isParty, classId);
	}

	@Override
	public int deleteOne(int stdId) {
		return studentDao.deleteOne(stdId);
	}

	@Override
	public int updateStudent(Student student) {
		return studentDao.updateStudent(student);
	}
}
