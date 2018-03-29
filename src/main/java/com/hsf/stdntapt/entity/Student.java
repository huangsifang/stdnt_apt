package com.hsf.stdntapt.entity;

public class Student {
	private int stdId;
	private String stdName;
	private int stdSex;
	private String stdTel;
	private String enterTime;
	private boolean isParty;
	private int classId;

	public Student() {
	}

	public Student(int stdId, String stdName) {
		this.stdId = stdId;
		this.stdName = stdName;
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

	public String getEnterTime() {
		return enterTime;
	}

	public void setEnterTime(String enterTime) {
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
