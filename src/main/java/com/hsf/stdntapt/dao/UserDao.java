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

	public User createUser(@Param("user") User user);

	public User updateUser(User user);

	public void deleteUser(Long userId);

	User findOne(Long userId);

	List<User> findAll();

	User findByUsername(String username);

}
