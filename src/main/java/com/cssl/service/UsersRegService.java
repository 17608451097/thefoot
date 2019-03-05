package com.cssl.service;

import com.cssl.pojo.Users;

public interface UsersRegService {

	public int regist(Users user);
	
	public int exitsUserName(String name);
}
