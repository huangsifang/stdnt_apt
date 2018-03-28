package com.hsf.stdntapt.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hsf.stdntapt.dao.ApartmentDao;
import com.hsf.stdntapt.entity.Apartment;
import com.hsf.stdntapt.entity.Bed;
import com.hsf.stdntapt.entity.Dormitory;
import com.hsf.stdntapt.entity.Floor;
import com.hsf.stdntapt.entity.Staff;
import com.hsf.stdntapt.service.ApartmentService;

@Service
public class ApartmentImpl implements ApartmentService {
	@Resource
	ApartmentDao apartmentDao;

	@Override
	public int createApartment(Apartment apartment) {
		return apartmentDao.createApartment(apartment);
	}

	@Override
	public int updateApartment(Apartment apartment) {
		return apartmentDao.updateApartment(apartment);
	}

	@Override
	public void deleteApartment(int apartId) {
		apartmentDao.deleteApartment(apartId);
	}

	@Override
	public String findApartName(int apartId) {
		return apartmentDao.findApartName(apartId);
	}

	@Override
	public List<Staff> findApartStaffs(int apartId) {
		return apartmentDao.findApartStaffs(apartId);
	}

	@Override
	public List<Apartment> findStaffAparts(int staffId) {
		return apartmentDao.findStaffAparts(staffId);
	}

	@Override
	public Staff findApartStaff(int apartId, int staffId) {
		return apartmentDao.findApartStaff(apartId, staffId);
	}

	@Override
	public int createApartStaff(int apartId, int staffId) {
		return apartmentDao.createApartStaff(apartId, staffId);
	}

	@Override
	public int deleteApartStaff(int apartId, int staffId) {
		return apartmentDao.deleteApartStaff(apartId, staffId);
	}

	@Override
	public Apartment findOne(int apartId) {
		return apartmentDao.findOne(apartId);
	}

	@Override
	public List<Apartment> findAll() {
		return apartmentDao.findAll();
	}

	@Override
	public int createFloor(Floor floor) {
		return apartmentDao.createFloor(floor);
	}

	@Override
	public Floor findFloorFromDormId(int dormId) {
		return apartmentDao.findFloorFromDormId(dormId);
	}

	@Override
	public Apartment findApartFromFloorId(int floorId) {
		return apartmentDao.findApartFromFloorId(floorId);
	}

	@Override
	public int createDorm(Dormitory dormitory) {
		return apartmentDao.createDorm(dormitory);
	}

	@Override
	public int findFloorNum(int apartId) {
		return apartmentDao.findFloorNum(apartId);
	}

	@Override
	public List<Floor> findFloorAll(int apartId) {
		return apartmentDao.findFloorAll(apartId);
	}

	@Override
	public Floor findOneFloor(int floorId) {
		return apartmentDao.findOneFloor(floorId);
	}

	@Override
	public Floor findFloorByApartIdFloorNo(int apartId, int floorNo) {
		return apartmentDao.findFloorByApartIdFloorNo(apartId, floorNo);
	}

	@Override
	public int findFloorDormNum(int floorId) {
		return apartmentDao.findFloorDormNum(floorId);
	}

	@Override
	public int findApartDormNum(int apartId) {
		return apartmentDao.findApartDormNum(apartId);
	}

	@Override
	public List<Dormitory> findFloorDormAll(int floorId) {
		return apartmentDao.findFloorDormAll(floorId);
	}

	@Override
	public Dormitory findOneDorm(int dormId) {
		return apartmentDao.findOneDorm(dormId);
	}

	@Override
	public Dormitory findByDormNoFloorId(int dormNo, int floorId) {
		return apartmentDao.findByDormNoFloorId(dormNo, floorId);
	}

	@Override
	public int updateDorm(Dormitory dorm) {
		return apartmentDao.updateDorm(dorm);
	}

	@Override
	public List<Bed> findBedsFromDorm(int dormId) {
		return apartmentDao.findBedsFromDorm(dormId);
	}

	@Override
	public Bed findBed(int dormId, int bedId) {
		return apartmentDao.findBed(dormId, bedId);
	}

	@Override
	public int createBed(Bed bed) {
		return apartmentDao.createBed(bed);
	}

	@Override
	public int updateDormStd(Bed bed) {
		return apartmentDao.updateDormStd(bed);
	}

	@Override
	public int getDormBedNum(int dormId) {
		return apartmentDao.getDormBedNum(dormId);
	}

	@Override
	public int findStdApartId(int stdId) {
		return apartmentDao.findStdApartId(stdId);
	}

	@Override
	public Dormitory findStdDorm(int stdId) {
		return apartmentDao.findStdDorm(stdId);
	}

	@Override
	public int findApartStdNum(int apartId) {
		return apartmentDao.findApartStdNum(apartId);
	}

	@Override
	public int deleteFloor(int floorId) {
		return apartmentDao.deleteFloor(floorId);
	}

	@Override
	public int deleteDorm(int dormId) {
		return apartmentDao.deleteDorm(dormId);
	}

	@Override
	public int deleteBed(int bedId) {
		return apartmentDao.deleteBed(bedId);
	}
}
