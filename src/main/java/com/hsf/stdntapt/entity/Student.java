package com.hsf.stdntapt.entity;

import java.util.Date;

public class Student {
	private int stdId;
	private String stdName;
	private int stdSex;
	private String stdTel;
	private Date enterTime;
	private boolean isParty;
	private int classId;

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

	public int getStdSex() {
		return stdSex;
	}

	public void setStdSex(int stdSex) {
		this.stdSex = stdSex;
	}

	public String getStdTel() {
		return stdTel;
	}

	public void setStdTel(String stdTel) {
		this.stdTel = stdTel;
	}

	public Date getEnterTime() {
		return enterTime;
	}

	public void setEnterTime(Date enterTime) {
		this.enterTime = enterTime;
	}

	public boolean isParty() {
		return isParty;
	}

	public void setParty(boolean isParty) {
		this.isParty = isParty;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

}
