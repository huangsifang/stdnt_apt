package com.hsf.stdntapt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hsf.stdntapt.entity.Holiday;

public interface HolidayDao {
	public List<Holiday> findAllHolidays();

	public int createHoliday(@Param("holiday") Holiday holiday);

	public int updateHoliday(@Param("holiday") Holiday holiday);

	public int deleteHoliday(@Param("holiId") int holiId);
}
