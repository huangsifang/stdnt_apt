package com.hsf.stdntapt.service;

import java.util.List;

import com.hsf.stdntapt.entity.Apartment;

public interface ApartmentService {
	public int createApartment(Apartment apartment);

	public int updateApartment(Apartment apartment);

	public void deleteApartment(int apartId);

	Apartment findOne(int apartId);

	List<Apartment> findAll();
}
