package com.hsf.stdntapt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hsf.stdntapt.entity.User;

/**
 * <p>
 * User: Zhang Kaitao
 * <p>
 * Date: 14-1-28
 * <p>
 * Version: 1.0
 */
public interface UserDao {

	public long createUser(@Param("user") User user);

	public long updateUser(@Param("user") User user);

	public void deleteUser(@Param("userId") Long userId);

	User findOne(@Param("userId") Long userId);

	List<User> findAll();

	User findByUsername(@Param("username") String username);

}
