package com.hsf.stdntapt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hsf.stdntapt.entity.DormScore;

public interface DormDao {
	public List<DormScore> getNewScores();

	public int createDormScore(@Param("dormScore") DormScore dormScore);
}
