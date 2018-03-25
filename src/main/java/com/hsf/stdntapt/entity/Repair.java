package com.hsf.stdntapt.entity;

import java.util.Date;

public class Repair {
	private long id;
	private String apartName;
	private int dormId;
	private int dormNo;
	private int applicantId;
	private String applicantName;
	private String applicantTel;
	private int repairType;
	private String repairTypeName;
	private Date applyTime;
	private String remark;
	private int state;

	public Repair() {
	}

	public Repair(int dormId, int applicantId, int repairType) {
		this.dormId = dormId;
		this.applicantId = applicantId;
		this.repairType = repairType;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getApartName() {
		return apartName;
	}

	public void setApartName(String apartName) {
		this.apartName = apartName;
	}

	public int getDormId() {
		return dormId;
	}

	public void setDormId(int dormId) {
		this.dormId = dormId;
	}

	public int getDormNo() {
		return dormNo;
	}

	public void setDormNo(int dormNo) {
		this.dormNo = dormNo;
	}

	public int getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(int applicantId) {
		this.applicantId = applicantId;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public String getApplicantTel() {
		return applicantTel;
	}

	public void setApplicantTel(String applicantTel) {
		this.applicantTel = applicantTel;
	}

	public int getRepairType() {
		return repairType;
	}

	public void setRepairType(int repairType) {
		this.repairType = repairType;
	}

	public String getRepairTypeName() {
		return repairTypeName;
	}

	public void setRepairTypeName(String repairTypeName) {
		this.repairTypeName = repairTypeName;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

}
