package com.hsf.stdntapt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hsf.stdntapt.entity.Apartment;
import com.hsf.stdntapt.entity.Dormitory;
import com.hsf.stdntapt.entity.Floor;

public interface ApartmentDao {

	public int createApartment(@Param("apartment") Apartment apartment);

	public int updateApartment(@Param("apartment") Apartment apartment);

	public void deleteApartment(@Param("apartId") int apartId);

	Apartment findOne(@Param("apartId") int apartId);

	List<Apartment> findAll();

	public int createFloor(@Param("floor") Floor floor);

	public int createDorm(@Param("dormitory") Dormitory dormitory);

	public int findFloorNum(@Param("apartId") int apartId);

	List<Floor> findFloorAll(int floorId);

	public int findFloorDormNum(@Param("floorId") int floorId);

	public int findApartDormNum(@Param("apartId") int apartId);

	List<Dormitory> findFloorDormAll(int floorId);

	public Dormitory findOneDorm(@Param("dormId") int dormId);

	public int updateDorm(@Param("dorm") Dormitory dorm);
}
