package com.hsf.stdntapt.service;

import java.util.List;

import com.hsf.stdntapt.entity.Apartment;
import com.hsf.stdntapt.entity.Dormitory;
import com.hsf.stdntapt.entity.Floor;

public interface ApartmentService {
	public int createApartment(Apartment apartment);

	public int updateApartment(Apartment apartment);

	public void deleteApartment(int apartId);

	Apartment findOne(int apartId);

	public int createFloor(Floor floor);

	public int createDorm(Dormitory dormitory);

	List<Apartment> findAll();

	public int findFloorNum(int apartId);

	List<Floor> findFloorAll(int floorId);

	public int findFloorDormNum(int floorId);

	public int findApartDormNum(int apartId);

	List<Dormitory> findFloorDormAll(int floorId);
}