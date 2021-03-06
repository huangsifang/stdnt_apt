package com.hsf.stdntapt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hsf.stdntapt.entity.HoliBack;
import com.hsf.stdntapt.entity.HoliRecord;
import com.hsf.stdntapt.entity.Holiday;

public interface HolidayDao {
	public List<Holiday> findAllHolidays();

	public int createHoliday(@Param("holiday") Holiday holiday);

	public int updateHoliday(@Param("holiday") Holiday holiday);

	public int deleteHoliday(@Param("holiId") int holiId);

	public String findHoliName(@Param("holiId") int holiId);

	public List<HoliRecord> findHoliRecord(@Param("holiId") int holiId, @Param("stdId") int stdId);

	public List<HoliRecord> findApartAllRecords(@Param("holiId") int holiId, @Param("apartId") int apartId);

	public List<HoliRecord> findApartAllRecordsByPage(@Param("start") int start, @Param("size") int size,
			@Param("holiId") int holiId, @Param("apartId") int apartId);

	public List<HoliRecord> findApartAllHoliRecords(@Param("apartId") int apartId);

	public List<HoliRecord> findApartAllHomeRecords(@Param("holiId") int holiId, @Param("apartId") int apartId);

	public List<HoliRecord> findApartAllSchoolRecords(@Param("holiId") int holiId, @Param("apartId") int apartId);

	public List<HoliRecord> findApartAllHomeRecordsByPage(@Param("start") int start, @Param("size") int size,
			@Param("holiId") int holiId, @Param("apartId") int apartId);

	public List<HoliRecord> findApartAllSchoolRecordsByPage(@Param("start") int start, @Param("size") int size,
			@Param("holiId") int holiId, @Param("apartId") int apartId);

	public int createHoliRecord(@Param("record") HoliRecord record);

	public int deleteHoliRecord(@Param("holiId") int holiId, @Param("stdId") int stdId);

	public List<HoliBack> findStdHoliBack(@Param("holiId") int holiId, @Param("stdId") int stdId);

	public int createHoliBack(@Param("back") HoliBack back);

	public List<HoliRecord> findStdAllHoliRecord(@Param("stdId") int stdId);

	public int deleteHoliBack(@Param("holiId") int holiId, @Param("stdId") int stdId);
}
