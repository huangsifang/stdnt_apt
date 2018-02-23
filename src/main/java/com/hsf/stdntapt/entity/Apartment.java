package com.hsf.stdntapt.entity;

public class Apartment {
	private int apartId;
	private String apartName;
	private boolean deleted = Boolean.FALSE;

	public Apartment() {
	}

	public Apartment(int apartId, String apartName) {
		this.apartId = apartId;
		this.apartName = apartName;
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

	public boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
