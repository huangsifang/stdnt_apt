package com.hsf.stdntapt.entity;

import java.util.Date;

public class DormScore {
	private long id;
	private int dormId;
	private int staffId;
	private Date createTime;
	private int score;
	private int apartId;
	private int floorDormNo;

	public DormScore() {
	}

	public DormScore(int dormId, int score, int staffId) {
		this.dormId = dormId;
		this.score = score;
		this.staffId = staffId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getDormId() {
		return dormId;
	}

	public void setDormId(int dormId) {
		this.dormId = dormId;
	}

	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getApartId() {
		return apartId;
	}

	public void setApartId(int apartId) {
		this.apartId = apartId;
	}

	public int getFloorDormNo() {
		return floorDormNo;
	}

	public void setFloorDormNo(int floorDormNo) {
		this.floorDormNo = floorDormNo;
	}

}
