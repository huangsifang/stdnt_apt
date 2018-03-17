package com.hsf.stdntapt.entity;

public class HoliRecord {
	private long id;
	private int holiId;
	private int stdId;
	private String startTime;
	private String endTime;
	private boolean inHome;
	private boolean isOut;
	private String address;
	private int apartId;

	public HoliRecord() {
	}

	public HoliRecord(int holiId, int stdId) {
		this.holiId = holiId;
		this.stdId = stdId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getHoliId() {
		return holiId;
	}

	public void setHoliId(int holiId) {
		this.holiId = holiId;
	}

	public int getStdId() {
		return stdId;
	}

	public void setStdId(int stdId) {
		this.stdId = stdId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public boolean isInHome() {
		return inHome;
	}

	public void setInHome(boolean inHome) {
		this.inHome = inHome;
	}

	public boolean isOut() {
		return isOut;
	}

	public void setOut(boolean isOut) {
		this.isOut = isOut;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getApartId() {
		return apartId;
	}

	public void setApartId(int apartId) {
		this.apartId = apartId;
	}

}
