package com.hsf.stdntapt.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hsf.stdntapt.dao.HolidayDao;
import com.hsf.stdntapt.entity.HoliRecord;
import com.hsf.stdntapt.entity.Holiday;
import com.hsf.stdntapt.service.HolidayService;

@Service
public class HolidayImpl implements HolidayService {
	@Resource
	HolidayDao holidayDao;

	@Override
	public List<Holiday> findAllHolidays() {
		return holidayDao.findAllHolidays();
	}

	@Override
	public int createHoliday(Holiday holiday) {
		return holidayDao.createHoliday(holiday);
	}

	@Override
	public int updateHoliday(Holiday holiday) {
		return holidayDao.updateHoliday(holiday);
	}

	@Override
	public int deleteHoliday(int holiId) {
		return holidayDao.deleteHoliday(holiId);
	}

	@Override
	public List<HoliRecord> findHoliRecord(int holiId, int stdId) {
		return holidayDao.findHoliRecord(holiId, stdId);
	}

	@Override
	public List<HoliRecord> findApartAllRecords(int holiId, int apartId) {
		return holidayDao.findApartAllRecords(holiId, apartId);
	}

	@Override
	public List<HoliRecord> findApartAllHomeRecords() {
		return holidayDao.findApartAllHomeRecords();
	}

	@Override
	public List<HoliRecord> findApartAllSchoolRecords() {
		return holidayDao.findApartAllSchoolRecords();
	}

	@Override
	public int createHoliRecord(HoliRecord holiRecord) {
		return holidayDao.createHoliRecord(holiRecord);
	}

	@Override
	public int deleteHoliRecord(int holiId, int stdId) {
		return holidayDao.deleteHoliRecord(holiId, stdId);
	}

}
