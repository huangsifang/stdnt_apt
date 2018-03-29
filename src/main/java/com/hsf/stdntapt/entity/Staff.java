package com.hsf.stdntapt.entity;

public class Staff {
	private int staffId;
	private String staffName;
	private int staffSex;
	private String staffTel;
	private String hiredate;
	private String leavedate;

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

	public String getHiredate() {
		return hiredate;
	}

	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}

	public String getLeavedate() {
		return leavedate;
	}

	public void setLeavedate(String leavedate) {
		this.leavedate = leavedate;
	}

}
