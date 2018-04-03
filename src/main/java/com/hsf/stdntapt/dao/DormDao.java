package com.hsf.stdntapt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hsf.stdntapt.entity.DormScore;

public interface DormDao {
	public List<DormScore> getNewScores();

	public List<DormScore> getApartNewScores(@Param("apartId") int apartId);

	public List<DormScore> getApartTopScores(@Param("apartId") int apartId);

	public List<DormScore> getApartDayTopScores(@Param("apartId") int apartId, @Param("day") String day);

	public int createDormScore(@Param("dormScore") DormScore dormScore);

	public int updateDormScore(@Param("dormScore") DormScore dormScore);

	public void deleteDormScore(@Param("scoreId") int scoreId);

	public List<DormScore> findOneDormScore(@Param("dormId") int dormId);

	public List<DormScore> findOneDormScoreByPage(@Param("start") int start, @Param("size") int size,
			@Param("dormId") int dormId);

	public List<DormScore> findApartDormScore(@Param("apartId") int apartId);

	public List<DormScore> findApartDormOneDayScore(@Param("apartId") int apartId, @Param("day") String day);
}
