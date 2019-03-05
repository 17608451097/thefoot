package com.cssl.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cssl.pojo.Users;


public interface UserService {
	
	public int getByUser(String username,String password);
	
	public List<Map<String,Object>> getuser();
	
	public int deluser(String userid);
	
	public int update(Users us);
	
	public void userexit(String username);
	
	public List<Map<String,Object>> getbyuser(String uid);
	
	public int updateRepeat(@Param("repeat")int repeat,@Param("userid")int userid);
}
