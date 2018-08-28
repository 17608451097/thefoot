package com.cssl.service;

import java.util.List;
import java.util.Map;

import com.cssl.pojo.User;

public interface UserService {
	
	public User getByUser(String username,String password);

	
	public List<Map<String,Object>> getuser();
}
