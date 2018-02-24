package com.hsf.stdntapt.entity;

import java.util.List;

public class Floor {
	private int id;
	private int apartId;
	private int floorNo;
	private int dormNum;
	private List<Dormitory> dormList;
	private boolean deleted = Boolean.FALSE;

	public Floor() {
	}

	public Floor(int apartId, int floorNo) {
		this.apartId = apartId;
		this.floorNo = floorNo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getApartId() {
		return apartId;
	}

	public void setApartId(int apartId) {
		this.apartId = apartId;
	}

	public int getFloorNo() {
		return floorNo;
	}

	public void setFloorNo(int floorNo) {
		this.floorNo = floorNo;
	}

	public int getDormNum() {
		return dormNum;
	}

	public void setDormNum(int dormNum) {
		this.dormNum = dormNum;
	}

	public List<Dormitory> getDormList() {
		return dormList;
	}

	public void setDormList(List<Dormitory> dormList) {
		this.dormList = dormList;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
