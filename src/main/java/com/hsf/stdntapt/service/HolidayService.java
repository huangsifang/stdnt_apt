package com.hsf.stdntapt.service;

import java.util.List;

import com.hsf.stdntapt.entity.Holiday;

public interface HolidayService {
	public List<Holiday> findAllHolidays();

	public int createHoliday(Holiday holiday);

	public int updateHoliday(Holiday holiday);

	public int deleteHoliday(int holiId);
}
