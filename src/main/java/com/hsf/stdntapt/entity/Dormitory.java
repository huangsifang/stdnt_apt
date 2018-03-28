package com.hsf.stdntapt.entity;

import java.math.BigDecimal;

public class Dormitory {
	private int id;
	private int dormNo;
	private int floorId;
	private int floorNo;
	private BigDecimal fee;
	private int leaderId;
	private String leaderName;
	private boolean deleted = Boolean.FALSE;

	public Dormitory() {
	}

	public Dormitory(int dormNo, int floorId) {
		this.dormNo = dormNo;
		this.floorId = floorId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDormNo() {
		return dormNo;
	}

	public void setDormNo(int dormNo) {
		this.dormNo = dormNo;
	}

	public int getFloorId() {
		return floorId;
	}

	public void setFloorId(int floorId) {
		this.floorId = floorId;
	}

	public int getFloorNo() {
		return floorNo;
	}

	public void setFloorNo(int floorNo) {
		this.floorNo = floorNo;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public int getLeaderId() {
		return leaderId;
	}

	public void setLeaderId(int leaderId) {
		this.leaderId = leaderId;
	}

	public String getLeaderName() {
		return leaderName;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
