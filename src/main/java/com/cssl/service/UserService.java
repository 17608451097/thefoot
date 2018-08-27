package com.cssl.service;

import com.cssl.pojo.User;

public interface UserService {
	
	public User getByUser(String username,String password);

}
