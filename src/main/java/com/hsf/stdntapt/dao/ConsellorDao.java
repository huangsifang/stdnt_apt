package com.hsf.stdntapt.dao;

import org.apache.ibatis.annotations.Param;

import com.hsf.stdntapt.entity.Consellor;

public interface ConsellorDao {
	public void insertConsellorList(@Param("consellId") int consellId, @Param("consellName") String consellName,
			@Param("consellSex") int consellSex, @Param("consellTel") String consellTel);

	public int deleteOne(@Param("consellId") int consellId);

	public Consellor findOneConsellor(@Param("consellId") int consellId);

	public int updateConsellor(@Param("consellor") Consellor consellor);
}
