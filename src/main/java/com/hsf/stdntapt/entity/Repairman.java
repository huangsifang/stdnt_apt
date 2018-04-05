package com.hsf.stdntapt.entity;

import java.util.List;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

public class Repairman {
	private int repairmanId;
	private String repairmanName;
	private int repairmanSex;
	private String repairmanTel;
	private String typeIdsStr;
	private List<String> typeIds;
	private List<RepairType> types;

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

	public String getTypeIdsStr() {
		return typeIdsStr;
	}

	public void setTypeIdsStr(String typeIdsStr) {
		this.typeIdsStr = typeIdsStr;
		if (StringUtils.isEmpty(typeIdsStr)) {
			return;
		}
		String[] roleIds = typeIdsStr.split(",");
		for (String roleId : roleIds) {
			if (StringUtils.isEmpty(roleId)) {
				continue;
			}
			getTypeIds().add(roleId);
		}
	}

	public List<String> getTypeIds() {
		return typeIds;
	}

	public void setTypeIds(List<String> typeIds) {
		this.typeIds = typeIds;
		if (!CollectionUtils.isEmpty(typeIds)) {
			StringBuilder s = new StringBuilder();
			for (String typeId : typeIds) {
				s.append(typeId);
				s.append(",");
			}
			this.typeIdsStr = s.toString();
		}
	}

	public List<RepairType> getTypes() {
		return types;
	}

	public void setTypes(List<RepairType> types) {
		this.types = types;
	}

}
