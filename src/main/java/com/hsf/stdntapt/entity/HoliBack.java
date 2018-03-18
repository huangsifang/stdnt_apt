package com.hsf.stdntapt.entity;

import java.util.Date;

public class HoliBack {
	private int id;
	private int holiId;
	private int stdId;
	private Date backTime;

	public HoliBack() {
	}

	public HoliBack(int holiId, int stdId) {
		this.holiId = holiId;
		this.stdId = stdId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public Date getBackTime() {
		return backTime;
	}

	public void setBackTime(Date backTime) {
		this.backTime = backTime;
	}

}
