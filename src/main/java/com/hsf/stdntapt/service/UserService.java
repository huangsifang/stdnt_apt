package com.hsf.stdntapt.service;

import java.util.List;
import java.util.Set;

import com.hsf.stdntapt.entity.User;

public interface UserService {

	/**
	 * 创建用户
	 *
	 * @param user
	 */
	public long createUser(User user);

	public long updateUser(User user);

	public void deleteUser(Long userId);

	/**
	 * 修改密码
	 *
	 * @param userId
	 * @param newPassword
	 */
	public void changePassword(Long userId, String newPassword);

	User findOne(Long userId);

	List<User> findAll();

	List<User> findOneRoleAllPage(int start, int size, int roleId);

	List<User> findOneRoleAll(int roleId);

	/**
	 * 根据用户名查找用户
	 *
	 * @param username
	 * @return
	 */
	public User findByUsername(String username);

	/**
	 * 根据用户名查找其角色
	 *
	 * @param username
	 * @return
	 */
	public Set<String> findRoles(String username);

	/**
	 * 根据用户名查找其权限
	 *
	 * @param username
	 * @return
	 */
	public Set<String> findPermissions(String username);

}
