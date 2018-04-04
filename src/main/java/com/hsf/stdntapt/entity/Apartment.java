package com.hsf.stdntapt.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Apartment {
	private int apartId;
	private String apartName;
	private int floorNum;
	private int dormNum;
	private int aFloorDormNum;
	private int aDormBedNum;
	private BigDecimal aStdYearFee;
	private List<Staff> staffs;
	private String staffsStr;
	private List<StaffRota> rotas;
	private boolean deleted = Boolean.FALSE;

	public Apartment() {
	}

	public Apartment(int apartId, String apartName) {
		this.apartId = apartId;
		this.apartName = apartName;
	}

	public Apartment(int apartId, String apartName, int floorNum) {
		this.apartId = apartId;
		this.apartName = apartName;
		this.floorNum = floorNum;
	}

	public int getApartId() {
		return apartId;
	}

	public void setApartId(int apartId) {
		this.apartId = apartId;
	}

	public String getApartName() {
		return apartName;
	}

	public void setApartName(String apartName) {
		this.apartName = apartName;
	}

	public int getFloorNum() {
		return floorNum;
	}

	public void setFloorNum(int floorNum) {
		this.floorNum = floorNum;
	}

	public int getDormNum() {
		return dormNum;
	}

	public void setDormNum(int dormNum) {
		this.dormNum = dormNum;
	}

	public int getaFloorDormNum() {
		return aFloorDormNum;
	}

	public void setaFloorDormNum(int aFloorDormNum) {
		this.aFloorDormNum = aFloorDormNum;
	}

	public int getaDormBedNum() {
		return aDormBedNum;
	}

	public void setaDormBedNum(int aDormBedNum) {
		this.aDormBedNum = aDormBedNum;
	}

	public BigDecimal getaStdYearFee() {
		return aStdYearFee;
	}

	public void setaStdYearFee(BigDecimal aStdYearFee) {
		this.aStdYearFee = aStdYearFee;
	}

	public List<Staff> getStaffs() {
		return staffs;
	}

	public void setStaffs(List<Staff> staffs) {
		String staffsStr = "";
		for (Staff staff : staffs) {
			staffsStr += staff.getStaffId() + ":" + staff.getStaffName() + ",";
		}
		if (staffsStr != "") {
			staffsStr = staffsStr.substring(0, staffsStr.length() - 1);
		}
		this.staffsStr = staffsStr;
		this.staffs = staffs;
	}

	public String getStaffsStr() {
		return staffsStr;
	}

	public void setStaffsStr(String staffsStr) {
		String[] staffsArray = staffsStr.split(",");
		List<Staff> staffs = new ArrayList<Staff>(staffsArray.length);
		for (int i = 0; i < staffsArray.length; i++) {
			int index = staffsArray[0].indexOf(":");
			int len = staffsArray.length;
			int staffId = Integer.parseUnsignedInt(staffsArray[0].substring(0, index));
			String staffName = staffsArray[0].substring(index + 1, len);
			Staff staff = new Staff(staffId, staffName);
			staffs.add(staff);
		}
		this.staffs = staffs;
		this.staffsStr = staffsStr;
	}

	public List<StaffRota> getRotas() {
		return rotas;
	}

	public void setRotas(List<StaffRota> rotas) {
		this.rotas = rotas;
	}

	public boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
