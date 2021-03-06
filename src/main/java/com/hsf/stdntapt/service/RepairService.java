package com.hsf.stdntapt.service;

import java.util.List;

import com.hsf.stdntapt.entity.Repair;
import com.hsf.stdntapt.entity.RepairRecord;
import com.hsf.stdntapt.entity.RepairType;
import com.hsf.stdntapt.entity.Repairman;

public interface RepairService {

	public void insertRepairmanList(int repairmanId, String repairmanName, int repairmanSex, String repairmanTel);

	public int insertRepairmanTypeRelation(int repairmanId, int typeId);

	public Repairman findRepairman(int repairmanId);

	public int deleteOneRepairman(int repairmanId);

	public void updateRepairman(Repairman repairman);

	public int deleteRepairmanAllType(int repairmanId);

	public void deleteRepairmanType(int repairmanId, int typeId);

	public List<Repair> getApartRepairs(int apartId);

	public List<Repair> getApartRepairsByPage(int start, int size, int apartId);

	public List<Repair> getRepairsByType(int typeId);

	public List<Repair> getRepairsByTypeByPage(int start, int size, int typeId);

	public Repair findOneRepair(long repairId);

	public String findRepairTypeName(int type);

	public RepairRecord findRepairRecord(long repairId);

	public int findRepairState(long repairId);

	public List<Repair> findDormRepairs(int dormId);

	public List<RepairType> findAllRepairType();

	public int createRepair(Repair repair);

	public int createRepairRecord(RepairRecord record);

	public List<String> findRepairmanTypes(int repairmanId);

	public List<RepairType> findRepairmanAllTypes(int repairmanId);

	public List<RepairRecord> findMyRepairRecordList(int repairmanId);

	public List<RepairRecord> findMyRepairRecordListByPage(int start, int size, int repairmanId);

	public RepairRecord findOneRepairRecordFromRepairId(long repairId);

	public List<RepairRecord> findRepairHistoryRecordFromRepairId(long repairId);

	public int finishedRepairRecord(long repairId, String repairTime);

	public int deleteRepair(long repairId);

	public int deleteRepairRecord(long repairId);

	public List<Repair> findRepairByApplicantId(int applicantId);
}
