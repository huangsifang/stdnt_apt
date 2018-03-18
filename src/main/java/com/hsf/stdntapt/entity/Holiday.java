package com.hsf.stdntapt.entity;

public class Holiday {
	private int holiId;
	private String holiName;
	private String startTime;
	private String endTime;
	private boolean hasSign;

	public Holiday() {
	}

	public Holiday(int holiId, String holiName) {
		this.holiId = holiId;
		this.holiName = holiName;
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

	public boolean isHasSign() {
		return hasSign;
	}

	public void setHasSign(boolean hasSign) {
		this.hasSign = hasSign;
	}

}
