package com.hsf.stdntapt.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hsf.stdntapt.dao.HolidayDao;
import com.hsf.stdntapt.entity.HoliBack;
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
	public String findHoliName(int holiId) {
		return holidayDao.findHoliName(holiId);
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
	public List<HoliRecord> findApartAllRecordsByPage(int start, int size, int holiId, int apartId) {
		return holidayDao.findApartAllRecordsByPage(start, size, holiId, apartId);
	}

	@Override
	public List<HoliRecord> findApartAllHoliRecords(int apartId) {
		return holidayDao.findApartAllHoliRecords(apartId);
	}

	@Override
	public List<HoliRecord> findApartAllHomeRecords(int holiId, int apartId) {
		return holidayDao.findApartAllHomeRecords(holiId, apartId);
	}

	@Override
	public List<HoliRecord> findApartAllSchoolRecords(int holiId, int apartId) {
		return holidayDao.findApartAllSchoolRecords(holiId, apartId);
	}

	@Override
	public List<HoliRecord> findApartAllHomeRecordsByPage(int start, int size, int holiId, int apartId) {
		return holidayDao.findApartAllHomeRecordsByPage(start, size, holiId, apartId);
	}

	@Override
	public List<HoliRecord> findApartAllSchoolRecordsByPage(int start, int size, int holiId, int apartId) {
		return holidayDao.findApartAllSchoolRecordsByPage(start, size, holiId, apartId);
	}

	@Override
	public int createHoliRecord(HoliRecord holiRecord) {
		return holidayDao.createHoliRecord(holiRecord);
	}

	@Override
	public int deleteHoliRecord(int holiId, int stdId) {
		return holidayDao.deleteHoliRecord(holiId, stdId);
	}

	@Override
	public List<HoliBack> findStdHoliBack(int holiId, int stdId) {
		return holidayDao.findStdHoliBack(holiId, stdId);
	}

	@Override
	public int createHoliBack(HoliBack back) {
		return holidayDao.createHoliBack(back);
	}

	@Override
	public List<HoliRecord> findStdAllHoliRecord(int stdId) {
		return holidayDao.findStdAllHoliRecord(stdId);
	}

	@Override
	public int deleteHoliBack(int holiId, int stdId) {
		return holidayDao.deleteHoliBack(holiId, stdId);
	}

}
