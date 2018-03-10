package com.hsf.stdntapt.entity;

import java.util.Date;

public class Staff {
	private int staffId;
	private String staffName;
	private int staffSex;
	private String staffTel;
	private Date hiredate;
	private Date leavedate;

	public Staff() {
	}

	public Staff(int staffId, String staffName) {
		this.staffId = staffId;
		this.staffName = staffName;
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

	public int getStaffSex() {
		return staffSex;
	}

	public void setStaffSex(int staffSex) {
		this.staffSex = staffSex;
	}

	public String getStaffTel() {
		return staffTel;
	}

	public void setStaffTel(String staffTel) {
		this.staffTel = staffTel;
	}

	public Date getHiredate() {
		return hiredate;
	}

	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}

	public Date getLeavedate() {
		return leavedate;
	}

	public void setLeavedate(Date leavedate) {
		this.leavedate = leavedate;
	}

}
