package com.cssl.dao;

import com.cssl.pojo.Users;

public interface UsersRegDao {
	
    public int regist(Users user);
	
	public int exitsUserName(String name);
	

}
