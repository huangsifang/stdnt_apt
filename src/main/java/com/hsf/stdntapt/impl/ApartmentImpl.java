package com.hsf.stdntapt.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hsf.stdntapt.dao.ApartmentDao;
import com.hsf.stdntapt.entity.Apartment;
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
}
