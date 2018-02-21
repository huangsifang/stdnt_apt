package com.hsf.stdntapt.dao;

import org.apache.ibatis.annotations.Param;

public interface RepairmanDao {
	public void insertRepairmanList(@Param("repairmanId") int repairmanId, @Param("repairmanName") String repairmanName,
			@Param("repairmanSex") int repairmanSex, @Param("repairmanTel") String repairmanTel,
			@Param("repairType") int repairType);
}
