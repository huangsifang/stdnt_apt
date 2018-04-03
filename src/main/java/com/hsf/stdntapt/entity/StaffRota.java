package com.hsf.stdntapt.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

public class StaffRota {
	private int rotaId;
	private int apartId;
	private int staffId;
	private String staffIdsStr;
	private List<Integer> staffIds;
	private List<Staff> staffs;
	private int week;

	public StaffRota() {
	}

	public StaffRota(int apartId, int staffId, int week) {
		this.apartId = apartId;
		this.staffId = staffId;
		this.week = week;
	}

	public StaffRota(int apartId, List<Integer> staffIds, int week) {
		this.apartId = apartId;
		this.staffIds = staffIds;
		this.week = week;
	}

	public int getRotaId() {
		return rotaId;
	}

	public void setRotaId(int rotaId) {
		this.rotaId = rotaId;
	}

	public int getApartId() {
		return apartId;
	}

	public void setApartId(int apartId) {
		this.apartId = apartId;
	}

	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public List<Integer> getStaffIds() {
		if (staffIds == null) {
			staffIds = new ArrayList<Integer>();
		}
		return staffIds;
	}

	public String getStaffIdsStr() {
		return staffIdsStr;
	}

	public void setStaffIdsStr(String staffIdsStr) {
		this.staffIdsStr = staffIdsStr;
		if (StringUtils.isEmpty(staffIdsStr)) {
			return;
		}
		String[] staffIds = staffIdsStr.split(",");
		for (String staffId : staffIds) {
			if (StringUtils.isEmpty(staffId)) {
				continue;
			}
			getStaffIds().add(Integer.valueOf(staffId));
		}
	}

	public void setStaffIds(List<Integer> staffIds) {
		this.staffIds = staffIds;
		if (!CollectionUtils.isEmpty(staffIds)) {
			StringBuilder s = new StringBuilder();
			for (int staffId : staffIds) {
				s.append(staffId);
				s.append(",");
			}
			this.staffIdsStr = s.toString();
		}
	}

	public List<Staff> getStaffs() {
		return staffs;
	}

	public void setStaffs(List<Staff> staffs) {
		this.staffs = staffs;
	}

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

}
