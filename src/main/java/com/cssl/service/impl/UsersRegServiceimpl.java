package com.cssl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.cssl.dao.UsersRegDao;
import com.cssl.pojo.Users;
import com.cssl.service.UsersRegService;

@Service
public class UsersRegServiceimpl implements UsersRegService{

	@Autowired
	private UsersRegDao udao;
	
	
	@Override
	public int regist(Users user) {
		
		return udao.regist(user);
	}

	@Override
	public int exitsUserName(String name) {
		
		return udao.exitsUserName(name);
	}
}
