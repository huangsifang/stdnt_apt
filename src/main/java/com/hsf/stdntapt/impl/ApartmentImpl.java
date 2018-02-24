package com.hsf.stdntapt.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hsf.stdntapt.dao.ApartmentDao;
import com.hsf.stdntapt.entity.Apartment;
import com.hsf.stdntapt.entity.Dormitory;
import com.hsf.stdntapt.entity.Floor;
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
	public int createDorm(Dormitory dormitory) {
		return apartmentDao.createDorm(dormitory);
	}

	@Override
	public int findFloorNum(int apartId) {
		return apartmentDao.findFloorNum(apartId);
	}

	@Override
	public List<Floor> findFloorAll(int floorId) {
		return apartmentDao.findFloorAll(floorId);
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
}
