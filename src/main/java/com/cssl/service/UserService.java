package com.cssl.service;

import java.util.List;
import java.util.Map;

import com.cssl.pojo.Users;

public interface UserService {
	
	public Users getByUser(String username,String password);
	
	public List<Map<String,Object>> getuser();
	
	public int deluser(String userid);
	
	public List<Map<String,Object>> getbyuser(String uid);

	public int update(Users us);
}
