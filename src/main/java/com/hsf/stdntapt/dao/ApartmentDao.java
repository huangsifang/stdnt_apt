package com.hsf.stdntapt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hsf.stdntapt.entity.Apartment;
import com.hsf.stdntapt.entity.Bed;
import com.hsf.stdntapt.entity.Dormitory;
import com.hsf.stdntapt.entity.Floor;
import com.hsf.stdntapt.entity.Staff;

public interface ApartmentDao {

	public int createApartment(@Param("apartment") Apartment apartment);

	public int updateApartment(@Param("apartment") Apartment apartment);

	public void deleteApartment(@Param("apartId") int apartId);

	public String findApartName(@Param("apartId") int apartId);

	public List<Staff> findApartStaffs(@Param("apartId") int apartId);

	public List<Apartment> findStaffAparts(@Param("staffId") int staffId);

	public Staff findApartStaff(@Param("apartId") int apartId, @Param("staffId") int staffId);

	public int createApartStaff(@Param("apartId") int apartId, @Param("staffId") int staffId);

	public int deleteApartStaff(@Param("apartId") int apartId, @Param("staffId") int staffId);

	Apartment findOne(@Param("apartId") int apartId);

	List<Apartment> findAll();

	public int createFloor(@Param("floor") Floor floor);

	public Floor findFloorFromDormId(@Param("dormId") int dormId);

	public Apartment findApartFromFloorId(@Param("floorId") int floorId);

	public int createDorm(@Param("dormitory") Dormitory dormitory);

	public int findFloorNum(@Param("apartId") int apartId);

	List<Floor> findFloorAll(@Param("apartId") int apartId);

	public Floor findOneFloor(@Param("floorId") int floorId);

	Floor findFloorByApartIdFloorNo(@Param("apartId") int apartId, @Param("floorNo") int floorNo);

	public int findFloorDormNum(@Param("floorId") int floorId);

	public int findApartDormNum(@Param("apartId") int apartId);

	List<Dormitory> findFloorDormAll(int floorId);

	public Dormitory findOneDorm(@Param("dormId") int dormId);

	public Dormitory findByDormNoFloorId(@Param("dormNo") int dormNo, @Param("floorId") int floorId);

	public int updateDorm(@Param("dorm") Dormitory dorm);

	public List<Bed> findBedsFromDorm(@Param("dormId") int dormId);

	Bed findBed(@Param("dormId") int dormId, @Param("bedId") int bedId);

	public int createBed(@Param("bed") Bed bed);

	public int updateDormStd(@Param("bed") Bed bed);

	public int updateDormLeader(@Param("dormId") int dormId, @Param("stdId") int stdId);

	public int getDormBedNum(@Param("dormId") int dormId);

	public int findStdApartId(@Param("stdId") int stdId);

	public Dormitory findStdDorm(@Param("stdId") int stdId);

	public int findApartStdNum(@Param("apartId") int apartId);

	public int deleteFloor(@Param("floorId") int floorId);

	public int deleteDorm(@Param("dormId") int dormId);

	public int deleteBed(@Param("bedId") int bedId);
}
