package com.hsf.stdntapt.dao;

import org.apache.ibatis.annotations.Param;

public interface UserDao {
	public void insertUserList(@Param("userID") int userID, @Param("userType") int userType,
			@Param("password") String password);
}
