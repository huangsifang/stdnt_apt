package com.hsf.stdntapt.entity;

public class Bed {
	private int bedId;
	private int dormId;
	private int stdId;
	private String stdName;
	private boolean deleted = Boolean.FALSE;

	public Bed() {
	}

	public Bed(int bedId, int dormId) {
		this.bedId = bedId;
		this.dormId = dormId;
	}

	public int getBedId() {
		return bedId;
	}

	public void setBedId(int bedId) {
		this.bedId = bedId;
	}

	public int getDormId() {
		return dormId;
	}

	public void setDormId(int dormId) {
		this.dormId = dormId;
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

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
