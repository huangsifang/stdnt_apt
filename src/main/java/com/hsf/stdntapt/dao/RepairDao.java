package com.hsf.stdntapt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hsf.stdntapt.entity.Repair;
import com.hsf.stdntapt.entity.RepairRecord;
import com.hsf.stdntapt.entity.RepairType;

public interface RepairDao {
	public void insertRepairmanList(@Param("repairmanId") int repairmanId, @Param("repairmanName") String repairmanName,
			@Param("repairmanSex") int repairmanSex, @Param("repairmanTel") String repairmanTel,
			@Param("repairType") int repairType);

	public List<Repair> getApartRepairs(@Param("apartId") int apartId);

	public String findRepairTypeName(@Param("type") int type);

	public RepairRecord findRepairRecord(@Param("repairId") long repairId);

	public int findRepairState(@Param("repairId") long repairId);

	public List<Repair> findDormRepairs(@Param("dormId") int dormId);

	public List<RepairType> findAllRepairType();

	public int createRepair(@Param("repair") Repair repair);
}
