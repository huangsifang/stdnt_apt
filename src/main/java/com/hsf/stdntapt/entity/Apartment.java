package com.hsf.stdntapt.entity;

import java.math.BigDecimal;

public class Apartment {
	private int apartId;
	private String apartName;
	private int floorNum;
	private int dormNum;
	private int aFloorDormNum;
	private int aDormBedNum;
	private BigDecimal aStdYearFee;
	private boolean deleted = Boolean.FALSE;

	public Apartment() {
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

	public boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
