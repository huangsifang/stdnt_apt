package com.hsf.stdntapt.service;

import java.util.List;
import java.util.Set;

import com.hsf.stdntapt.entity.Role;

public interface RoleService {

	public long createRole(Role role);

	public long updateRole(Role role);

	public void deleteRole(Long roleId);

	public Role findOne(Long roleId);

	public List<Role> findAll();

	/**
	 * 根据角色编号得到角色标识符列表
	 *
	 * @param roleIds
	 * @return
	 */
	Set<String> findRoles(Long... roleIds);

	/**
	 * 根据角色编号得到权限字符串列表
	 *
	 * @param roleIds
	 * @return
	 */
	Set<String> findPermissions(Long[] roleIds);
}
