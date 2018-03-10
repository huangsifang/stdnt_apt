package com.hsf.stdntapt.service;

import java.util.List;

import com.hsf.stdntapt.entity.DormScore;

public interface DormService {
	public List<DormScore> getNewScores();

	public int createDormScore(DormScore dormScore);
}
