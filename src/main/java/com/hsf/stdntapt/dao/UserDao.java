package com.hsf.stdntapt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hsf.stdntapt.entity.User;

public interface UserDao {

	public long createUser(@Param("user") User user);

	public long updateUser(@Param("user") User user);

	public void deleteUser(@Param("userId") Long userId);

	User findOne(@Param("userId") Long userId);

	List<User> findAll();

	List<User> findOneRoleAllPage(@Param("start") int start, @Param("size") int size, @Param("roleId") int roleId);

	List<User> findOneRoleAll(@Param("roleId") int roleId);

	User findByUsername(@Param("username") String username);

}
