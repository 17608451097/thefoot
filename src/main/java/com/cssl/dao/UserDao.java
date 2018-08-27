package com.cssl.dao;

import org.apache.ibatis.annotations.Param;

import com.cssl.pojo.User;

public interface UserDao {

	public User getByUser(@Param("username") String username,@Param("password")String password);
	
}
