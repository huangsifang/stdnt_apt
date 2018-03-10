package com.hsf.stdntapt.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hsf.stdntapt.dao.DormDao;
import com.hsf.stdntapt.entity.DormScore;
import com.hsf.stdntapt.service.DormService;

@Service
public class DormImpl implements DormService {
	@Resource
	DormDao dormDao;

	@Override
	public List<DormScore> getNewScores() {
		return dormDao.getNewScores();
	}

	@Override
	public int createDormScore(DormScore dormScore) {
		return dormDao.createDormScore(dormScore);
	}
}
