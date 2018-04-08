package com.hsf.stdntapt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hsf.stdntapt.entity.Role;

public interface RoleDao {

	public long createRole(@Param("role") Role role);

	public long updateRole(@Param("role") Role role);

	public void deleteRole(@Param("roleId") Long roleId);

	public Role findOne(@Param("roleId") Long roleId);

	public List<Role> findAll();
}
