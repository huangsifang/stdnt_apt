package com.hsf.stdntapt.entity;

public class Repairman {
	private int repairmanId;
	private String repairmanName;
	private int repairmanSex;
	private String repairmanTel;
	private String typeIds;

	public Repairman() {
	}

	public Repairman(int repairmanId, String repairmanName) {
		this.repairmanId = repairmanId;
		this.repairmanName = repairmanName;
	}

	public int getRepairmanId() {
		return repairmanId;
	}

	public void setRepairmanId(int repairmanId) {
		this.repairmanId = repairmanId;
	}

	public String getRepairmanName() {
		return repairmanName;
	}

	public void setRepairmanName(String repairmanName) {
		this.repairmanName = repairmanName;
	}

	public int getRepairmanSex() {
		return repairmanSex;
	}

	public void setRepairmanSex(int repairmanSex) {
		this.repairmanSex = repairmanSex;
	}

	public String getRepairmanTel() {
		return repairmanTel;
	}

	public void setRepairmanTel(String repairmanTel) {
		this.repairmanTel = repairmanTel;
	}

	public String getTypeIds() {
		return typeIds;
	}

	public void setTypeIds(String typeIds) {
		this.typeIds = typeIds;
	}

}
