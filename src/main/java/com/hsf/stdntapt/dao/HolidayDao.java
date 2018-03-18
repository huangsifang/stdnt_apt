package com.hsf.stdntapt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hsf.stdntapt.entity.HoliRecord;
import com.hsf.stdntapt.entity.Holiday;

public interface HolidayDao {
	public List<Holiday> findAllHolidays();

	public int createHoliday(@Param("holiday") Holiday holiday);

	public int updateHoliday(@Param("holiday") Holiday holiday);

	public int deleteHoliday(@Param("holiId") int holiId);

	public List<HoliRecord> findHoliRecord(@Param("holiId") int holiId, @Param("stdId") int stdId);

	public List<HoliRecord> findApartAllRecords(@Param("holiId") int holiId, @Param("apartId") int apartId);

	public List<HoliRecord> findApartAllHomeRecords();

	public List<HoliRecord> findApartAllSchoolRecords();

	public int createHoliRecord(@Param("record") HoliRecord record);

	public int deleteHoliRecord(@Param("holiId") int holiId, @Param("stdId") int stdId);
}
