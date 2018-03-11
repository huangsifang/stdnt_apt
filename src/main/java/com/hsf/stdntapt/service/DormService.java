package com.hsf.stdntapt.service;

import java.util.List;

import com.hsf.stdntapt.entity.DormScore;

public interface DormService {
	public List<DormScore> getNewScores();

	public List<DormScore> getApartNewScores(int apartId);

	public int createDormScore(DormScore dormScore);

	public List<DormScore> findOneDormScore(int dormId);

	public List<DormScore> findApartDormScore(int apartId);
}
