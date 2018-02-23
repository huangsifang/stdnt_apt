package com.hsf.stdntapt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hsf.stdntapt.entity.Apartment;

public interface ApartmentDao {

	public int createApartment(@Param("apartment") Apartment apartment);

	public int updateApartment(@Param("apartment") Apartment apartment);

	public void deleteApartment(@Param("apartId") int apartId);

	Apartment findOne(@Param("apartId") int apartId);

	List<Apartment> findAll();
}
