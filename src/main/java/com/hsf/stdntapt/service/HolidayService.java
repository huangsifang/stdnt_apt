package com.hsf.stdntapt.service;

import java.util.List;

import com.hsf.stdntapt.entity.HoliBack;
import com.hsf.stdntapt.entity.HoliRecord;
import com.hsf.stdntapt.entity.Holiday;

public interface HolidayService {
	public List<Holiday> findAllHolidays();

	public int createHoliday(Holiday holiday);

	public int updateHoliday(Holiday holiday);

	public int deleteHoliday(int holiId);

	public String findHoliName(int holiId);

	public List<HoliRecord> findHoliRecord(int holiId, int stdId);

	public List<HoliRecord> findApartAllRecords(int holiId, int apartId);

	public List<HoliRecord> findApartAllRecordsByPage(int start, int size, int holiId, int apartId);

	public List<HoliRecord> findApartAllHoliRecords(int apartId);

	public List<HoliRecord> findApartAllHomeRecords(int holiId, int apartId);

	public List<HoliRecord> findApartAllSchoolRecords(int holiId, int apartId);

	public List<HoliRecord> findApartAllHomeRecordsByPage(int start, int size, int holiId, int apartId);

	public List<HoliRecord> findApartAllSchoolRecordsByPage(int start, int size, int holiId, int apartId);

	public int createHoliRecord(HoliRecord record);

	public int deleteHoliRecord(int holiId, int stdId);

	public List<HoliBack> findStdHoliBack(int holiId, int stdId);

	public int createHoliBack(HoliBack back);

	public List<HoliRecord> findStdAllHoliRecord(int stdId);

	public int deleteHoliBack(int holiId, int stdId);
}
