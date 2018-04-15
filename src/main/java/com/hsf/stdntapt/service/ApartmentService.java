package com.hsf.stdntapt.service;

import java.util.List;

import com.hsf.stdntapt.entity.Apartment;
import com.hsf.stdntapt.entity.Bed;
import com.hsf.stdntapt.entity.Dormitory;
import com.hsf.stdntapt.entity.Floor;
import com.hsf.stdntapt.entity.Staff;
import com.hsf.stdntapt.entity.StaffRota;

public interface ApartmentService {
	public int createApartment(Apartment apartment);

	public int updateApartment(Apartment apartment);

	public void deleteApartment(int apartId);

	public String findApartName(int apartId);

	public List<Staff> findApartStaffs(int apartId);

	public List<Apartment> findStaffAparts(int staffId);

	public Staff findApartStaff(int apartId, int staffId);

	public int createApartStaff(int apartId, int staffId);

	public int deleteApartStaff(int apartId, int staffId);

	Apartment findOne(int apartId);

	public int createFloor(Floor floor);

	public Floor findFloorFromDormId(int dormId);

	public Apartment findApartFromFloorId(int floorId);

	public int createDorm(Dormitory dormitory);

	List<Apartment> findAll();

	public int findFloorNum(int apartId);

	List<Floor> findFloorAll(int apartId);

	public Floor findOneFloor(int floorId);

	Floor findFloorByApartIdFloorNo(int apartId, int floorNo);

	public int findFloorDormNum(int floorId);

	public int findApartDormNum(int apartId);

	List<Dormitory> findFloorDormAll(int floorId);

	public Dormitory findOneDorm(int dormId);

	public Dormitory findByDormNoFloorId(int dormNo, int floorId);

	public int updateDorm(Dormitory dorm);

	public List<Dormitory> findDormByLeaderId(int stdId);

	public int findDormLeader(int dormId);

	List<Bed> findBedsFromDorm(int dormId);

	Bed findBed(int bedId, int dormId);

	public int createBed(Bed bed);

	public int updateDormStd(Bed bed);

	public int updateDormLeader(int dormId, int stdId);

	public int getDormBedNum(int dormId);

	public int findStdApartId(int stdId);

	public Apartment findStdApart(int stdId);

	public Dormitory findStdDorm(int stdId);

	public int findApartStdNum(int apartId);

	public int deleteFloor(int floorId);

	public int deleteDorm(int dormId);

	public int deleteBed(int dormId, int bedId);

	public List<StaffRota> findApartRotaAll(int apartId);

	public void deleteStaffRota(int apartId, int staffId, int week);

	public int createStaffRota(StaffRota rota);

	public StaffRota findOneStaffRota(int apartId, int staffId, int week);

	public List<StaffRota> findApartRotaAllByWeek(int apartId, int week);
}
