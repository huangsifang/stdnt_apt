package com.hsf.stdntapt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hsf.stdntapt.entity.DormScore;

public interface DormDao {
	public List<DormScore> getNewScores();

	public List<DormScore> getApartNewScores(@Param("apartId") int apartId);

	public int createDormScore(@Param("dormScore") DormScore dormScore);

	public List<DormScore> findOneDormScore(@Param("dormId") int dormId);

	public List<DormScore> findApartDormScore(@Param("apartId") int apartId);
}
