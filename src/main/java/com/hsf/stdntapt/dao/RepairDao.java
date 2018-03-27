package com.hsf.stdntapt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hsf.stdntapt.entity.Repair;
import com.hsf.stdntapt.entity.RepairRecord;
import com.hsf.stdntapt.entity.RepairType;
import com.hsf.stdntapt.entity.Repairman;

public interface RepairDao {
	public void insertRepairmanList(@Param("repairmanId") int repairmanId, @Param("repairmanName") String repairmanName,
			@Param("repairmanSex") int repairmanSex, @Param("repairmanTel") String repairmanTel);

	public int insertRepairmanTypeRelation(@Param("repairmanId") int repairmanId, @Param("typeId") int typeId);

	public Repairman findRepairman(@Param("repairmanId") int repairmanId);

	public List<Repair> getApartRepairs(@Param("apartId") int apartId);

	public List<Repair> getRepairsByType(@Param("typeId") int typeId);

	public Repair findOneRepair(@Param("repairId") long repairId);

	public String findRepairTypeName(@Param("type") int type);

	public RepairRecord findRepairRecord(@Param("repairId") long repairId);

	public int findRepairState(@Param("repairId") long repairId);

	public List<Repair> findDormRepairs(@Param("dormId") int dormId);

	public List<RepairType> findAllRepairType();

	public int createRepair(@Param("repair") Repair repair);

	public int createRepairRecord(@Param("record") RepairRecord record);

	public List<String> findRepairmanTypes(@Param("repairmanId") int repairmanId);

	public List<RepairRecord> findMyRepairRecordList(@Param("repairmanId") int repairmanId);

	public RepairRecord findOneRepairRecordFromRepairId(@Param("repairId") long repairId);

	public List<RepairRecord> findRepairHistoryRecordFromRepairId(@Param("repairId") long repairId);

	public int finishedRepairRecord(@Param("repairId") long repairId, @Param("repairTime") String repairTime);

	public int deleteRepair(@Param("repairId") long repairId);

	public int deleteRepairRecord(@Param("repairId") long repairId);
}
