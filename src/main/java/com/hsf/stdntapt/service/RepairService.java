package com.hsf.stdntapt.service;

import java.util.List;

import com.hsf.stdntapt.entity.Repair;
import com.hsf.stdntapt.entity.RepairRecord;
import com.hsf.stdntapt.entity.RepairType;

public interface RepairService {
	public List<Repair> getApartRepairs(int apartId);

	public String findRepairTypeName(int type);

	public RepairRecord findRepairRecord(long repairId);

	public int findRepairState(long repairId);

	public List<Repair> findDormRepairs(int dormId);

	public List<RepairType> findAllRepairType();

	public int createRepair(Repair repair);
}
