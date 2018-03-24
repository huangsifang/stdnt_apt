package com.hsf.stdntapt.entity;

public class HoliRecord {
	private long id;
	private int holiId;
	private String holiName;
	private int stdId;
	private String stdName;
	private String startTime;
	private String endTime;
	private boolean inHome;
	private String homeOrSchool;
	private boolean isOut;
	private String isOutStr;
	private String address;
	private int apartId;
	private String apartName;
	private boolean hasSign;

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

	public String getHoliName() {
		return holiName;
	}

	public void setHoliName(String holiName) {
		this.holiName = holiName;
	}

	public int getStdId() {
		return stdId;
	}

	public void setStdId(int stdId) {
		this.stdId = stdId;
	}

	public String getStdName() {
		return stdName;
	}

	public void setStdName(String stdName) {
		this.stdName = stdName;
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

	public String getHomeOrSchool() {
		return homeOrSchool;
	}

	public void setHomeOrSchool(String homeOrSchool) {
		this.homeOrSchool = homeOrSchool;
	}

	public boolean isOut() {
		return isOut;
	}

	public void setOut(boolean isOut) {
		this.isOut = isOut;
	}

	public String getIsOutStr() {
		return isOutStr;
	}

	public void setIsOutStr(String isOutStr) {
		this.isOutStr = isOutStr;
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

	public String getApartName() {
		return apartName;
	}

	public void setApartName(String apartName) {
		this.apartName = apartName;
	}

	public boolean isHasSign() {
		return hasSign;
	}

	public void setHasSign(boolean hasSign) {
		this.hasSign = hasSign;
	}

}
