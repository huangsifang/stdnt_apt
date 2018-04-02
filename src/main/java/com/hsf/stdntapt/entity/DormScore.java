package com.hsf.stdntapt.entity;

import java.util.Date;

public class DormScore {
	private long id;
	private int dormId;
	private int staffId;
	private String staffName;
	private Date createTime;
	private int score;
	private int apartId;
	private int floorNo;
	private int dormNo;
	private int floorDormNo;
	private String grade;
	private int count;

	public DormScore() {
	}

	public DormScore(int dormId, int score, int staffId) {
		this.dormId = dormId;
		this.score = score;
		this.staffId = staffId;
	}

	public DormScore(String grade, int count) {
		this.grade = grade;
		this.count = count;
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

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
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

	public int getFloorNo() {
		return floorNo;
	}

	public void setFloorNo(int floorNo) {
		this.floorNo = floorNo;
	}

	public int getDormNo() {
		return dormNo;
	}

	public void setDormNo(int dormNo) {
		this.dormNo = dormNo;
	}

	public int getFloorDormNo() {
		return floorDormNo;
	}

	public void setFloorDormNo(int floorDormNo) {
		this.floorDormNo = floorDormNo;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
