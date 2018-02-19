package com.hsf.stdntapt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hsf.stdntapt.entity.Role;

/**
 * <p>
 * User: Zhang Kaitao
 * <p>
 * Date: 14-1-28
 * <p>
 * Version: 1.0
 */
public interface RoleDao {

	public long createRole(@Param("role") Role role);

	public long updateRole(@Param("role") Role role);

	public void deleteRole(@Param("roleId") Long roleId);

	public Role findOne(@Param("roleId") Long roleId);

	public List<Role> findAll();
}
