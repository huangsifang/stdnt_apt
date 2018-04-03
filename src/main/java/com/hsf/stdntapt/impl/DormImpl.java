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
	public List<DormScore> getApartNewScores(int apartId) {
		return dormDao.getApartNewScores(apartId);
	}

	@Override
	public List<DormScore> getApartTopScores(int apartId) {
		return dormDao.getApartTopScores(apartId);
	}

	@Override
	public List<DormScore> getApartDayTopScores(int apartId, String day) {
		return dormDao.getApartDayTopScores(apartId, day);
	}

	@Override
	public int createDormScore(DormScore dormScore) {
		return dormDao.createDormScore(dormScore);
	}

	@Override
	public int updateDormScore(DormScore dormScore) {
		return dormDao.updateDormScore(dormScore);
	}

	@Override
	public void deleteDormScore(int scoreId) {
		dormDao.deleteDormScore(scoreId);
	}

	@Override
	public List<DormScore> findOneDormScore(int dormId) {
		return dormDao.findOneDormScore(dormId);
	}

	@Override
	public List<DormScore> findOneDormScoreByPage(int start, int size, int dormId) {
		return dormDao.findOneDormScoreByPage(start, size, dormId);
	}

	@Override
	public List<DormScore> findApartDormScore(int apartId) {
		return dormDao.findApartDormScore(apartId);
	}

	@Override
	public List<DormScore> findApartDormOneDayScore(int apartId, String day) {
		return dormDao.findApartDormOneDayScore(apartId, day);
	}
}
