package com.hsf.stdntapt.service;

import java.util.List;

import com.hsf.stdntapt.entity.DormScore;

public interface DormService {
	public List<DormScore> getNewScores();

	public List<DormScore> getApartNewScores(int apartId);

	public List<DormScore> getApartTopScores(int apartId);

	public List<DormScore> getApartDayTopScores(int apartId, String day);

	public int createDormScore(DormScore dormScore);

	public int updateDormScore(DormScore dormScore);

	public void deleteDormScore(int scoreId);

	public List<DormScore> findOneDormScore(int dormId);

	public List<DormScore> findOneDormScoreByPage(int start, int size, int dormId);

	public List<DormScore> findApartDormScore(int apartId);

	public List<DormScore> findApartDormOneDayScore(int apartId, String day);
}
