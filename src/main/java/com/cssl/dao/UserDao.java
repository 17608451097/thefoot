package com.cssl.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cssl.pojo.Users;

public interface UserDao {

	public Users getByUser(@Param("username") String username,@Param("password")String password);
	
	public List<Map<String,Object>> getuser();
	
}
