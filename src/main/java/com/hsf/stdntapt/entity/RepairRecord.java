package com.hsf.stdntapt.entity;

import java.util.Date;

public class RepairRecord {
	private long id;
	private long repairId;
	private int repairmanId;
	private Date acceptTime;
	private Date repairTime;
	private int state;
	private Repair repair;
	private Repairman repairman;

	public RepairRecord() {
	}

	public RepairRecord(long repairId, int repairmanId) {
		this.repairId = repairId;
		this.repairmanId = repairmanId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getRepairId() {
		return repairId;
	}

	public void setRepairId(long repairId) {
		this.repairId = repairId;
	}

	public int getRepairmanId() {
		return repairmanId;
	}

	public void setRepairmanId(int repairmanId) {
		this.repairmanId = repairmanId;
	}

	public Date getAcceptTime() {
		return acceptTime;
	}

	public void setAcceptTime(Date acceptTime) {
		this.acceptTime = acceptTime;
	}

	public Date getRepairTime() {
		return repairTime;
	}

	public void setRepairTime(Date repairTime) {
		this.repairTime = repairTime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Repair getRepair() {
		return repair;
	}

	public void setRepair(Repair repair) {
		this.repair = repair;
	}

	public Repairman getRepairman() {
		return repairman;
	}

	public void setRepairman(Repairman repairman) {
		this.repairman = repairman;
	}

}
