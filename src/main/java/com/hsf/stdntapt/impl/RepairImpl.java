package com.hsf.stdntapt.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hsf.stdntapt.dao.RepairDao;
import com.hsf.stdntapt.entity.Repair;
import com.hsf.stdntapt.entity.RepairRecord;
import com.hsf.stdntapt.entity.RepairType;
import com.hsf.stdntapt.entity.Repairman;
import com.hsf.stdntapt.service.RepairService;

@Service
public class RepairImpl implements RepairService {
	@Resource
	RepairDao repairDao;

	@Override
	public void insertRepairmanList(int repairmanId, String repairmanName, int repairmanSex, String repairmanTel) {
		repairDao.insertRepairmanList(repairmanId, repairmanName, repairmanSex, repairmanTel);
	}

	@Override
	public int insertRepairmanTypeRelation(int repairmanId, int typeId) {
		return repairDao.insertRepairmanTypeRelation(repairmanId, typeId);
	}

	@Override
	public Repairman findRepairman(int repairmanId) {
		return repairDao.findRepairman(repairmanId);
	}

	@Override
	public int deleteOneRepairman(int repairmanId) {
		return repairDao.deleteOneRepairman(repairmanId);
	}

	@Override
	public void updateRepairman(Repairman repairman) {
		repairDao.updateRepairman(repairman);
	}

	@Override
	public int deleteRepairmanAllType(int repairmanId) {
		return repairDao.deleteRepairmanAllType(repairmanId);
	}

	@Override
	public void deleteRepairmanType(int repairmanId, int typeId) {
		repairDao.deleteRepairmanType(repairmanId, typeId);
	}

	@Override
	public List<Repair> getApartRepairs(int apartId) {
		return repairDao.getApartRepairs(apartId);
	}

	@Override
	public List<Repair> getApartRepairsByPage(int start, int size, int apartId) {
		return repairDao.getApartRepairsByPage(start, size, apartId);
	}

	@Override
	public List<Repair> getRepairsByType(int typeId) {
		return repairDao.getRepairsByType(typeId);
	}

	@Override
	public List<Repair> getRepairsByTypeByPage(int start, int size, int typeId) {
		return repairDao.getRepairsByTypeByPage(start, size, typeId);
	}

	@Override
	public Repair findOneRepair(long repairId) {
		return repairDao.findOneRepair(repairId);
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

	@Override
	public int createRepairRecord(RepairRecord record) {
		return repairDao.createRepairRecord(record);
	}

	@Override
	public List<String> findRepairmanTypes(int repairmanId) {
		return repairDao.findRepairmanTypes(repairmanId);
	}

	@Override
	public List<RepairType> findRepairmanAllTypes(int repairmanId) {
		return repairDao.findRepairmanAllTypes(repairmanId);
	}

	@Override
	public List<RepairRecord> findMyRepairRecordList(int repairmanId) {
		return repairDao.findMyRepairRecordList(repairmanId);
	}

	@Override
	public List<RepairRecord> findMyRepairRecordListByPage(int start, int size, int repairmanId) {
		return repairDao.findMyRepairRecordListByPage(start, size, repairmanId);
	}

	@Override
	public RepairRecord findOneRepairRecordFromRepairId(long repairId) {
		return repairDao.findOneRepairRecordFromRepairId(repairId);
	}

	@Override
	public List<RepairRecord> findRepairHistoryRecordFromRepairId(long repairId) {
		return repairDao.findRepairHistoryRecordFromRepairId(repairId);
	}

	@Override
	public int finishedRepairRecord(long repairId, String repairTime) {
		return repairDao.finishedRepairRecord(repairId, repairTime);
	}

	@Override
	public int deleteRepair(long repairId) {
		return repairDao.deleteRepair(repairId);
	}

	@Override
	public int deleteRepairRecord(long repairId) {
		return repairDao.deleteRepairRecord(repairId);
	}

	@Override
	public List<Repair> findRepairByApplicantId(int applicantId) {
		return repairDao.findRepairByApplicantId(applicantId);
	}
}
