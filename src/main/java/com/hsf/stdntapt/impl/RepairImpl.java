package com.hsf.stdntapt.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hsf.stdntapt.dao.RepairDao;
import com.hsf.stdntapt.entity.Repair;
import com.hsf.stdntapt.entity.RepairRecord;
import com.hsf.stdntapt.entity.RepairType;
import com.hsf.stdntapt.service.RepairService;

@Service
public class RepairImpl implements RepairService {
	@Resource
	RepairDao repairDao;

	@Override
	public List<Repair> getApartRepairs(int apartId) {
		return repairDao.getApartRepairs(apartId);
	}

	@Override
	public String findRepairTypeName(int type) {
		return repairDao.findRepairTypeName(type);
	}

	@Override
	public RepairRecord findRepairRecord(long repairId) {
		return repairDao.findRepairRecord(repairId);
	}

	@Override
	public int findRepairState(long repairId) {
		return repairDao.findRepairState(repairId);
	}

	@Override
	public List<Repair> findDormRepairs(int dormId) {
		return repairDao.findDormRepairs(dormId);
	}

	@Override
	public List<RepairType> findAllRepairType() {
		return repairDao.findAllRepairType();
	}

	@Override
	public int createRepair(Repair repair) {
		return repairDao.createRepair(repair);
	}
}
