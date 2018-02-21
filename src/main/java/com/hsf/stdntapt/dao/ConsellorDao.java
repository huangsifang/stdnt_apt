package com.hsf.stdntapt.dao;

import org.apache.ibatis.annotations.Param;

public interface ConsellorDao {
	public void insertConsellorList(@Param("consellId") int consellId, @Param("consellName") String consellName,
			@Param("consellSex") int consellSex, @Param("consellTel") String consellTel);
}
