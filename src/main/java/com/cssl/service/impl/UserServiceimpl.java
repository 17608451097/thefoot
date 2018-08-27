package com.cssl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cssl.dao.UserDao;
import com.cssl.pojo.User;
import com.cssl.service.UserService;

@Service
public class UserServiceimpl implements UserService {
	
	@Autowired
	private UserDao udao;

	@Override
	public User getByUser(String username, String password) {
		
		return udao.getByUser(username, password);
	}

}
