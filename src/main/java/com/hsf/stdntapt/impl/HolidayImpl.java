package com.hsf.stdntapt.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hsf.stdntapt.dao.HolidayDao;
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

}
