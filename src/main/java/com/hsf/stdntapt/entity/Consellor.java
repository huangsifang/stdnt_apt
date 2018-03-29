package com.hsf.stdntapt.entity;

public class Consellor {
	private int consellId;
	private String consellName;
	private int consellSex;
	private String consellTel;

	public Consellor() {
	}

	public Consellor(int consellId, String consellName) {
		this.consellId = consellId;
		this.consellName = consellName;
	}

	public int getConsellId() {
		return consellId;
	}

	public void setConsellId(int consellId) {
		this.consellId = consellId;
	}

	public String getConsellName() {
		return consellName;
	}

	public void setConsellName(String consellName) {
		this.consellName = consellName;
	}

	public int getConsellSex() {
		return consellSex;
	}

	public void setConsellSex(int consellSex) {
		this.consellSex = consellSex;
	}

	public String getConsellTel() {
		return consellTel;
	}

	public void setConsellTel(String consellTel) {
		this.consellTel = consellTel;
	}

}
