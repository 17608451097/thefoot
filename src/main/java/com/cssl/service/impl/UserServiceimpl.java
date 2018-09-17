package com.cssl.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cssl.dao.UserDao;
import com.cssl.pojo.Users;
import com.cssl.service.UserService;

@Service
public class UserServiceimpl implements UserService {
	
	@Autowired
	private UserDao udao;

	@Override
	public Users getByUser(String username, String password) {
		
		return udao.getByUser(username, password);
	}

	@Override
	public List<Map<String, Object>> getuser() {
		
		return udao.getuser();
	}

	@Override
	public int deluser(String userid) {
		
		return udao.deluser(userid);
	}

	@Override
	public List<Map<String, Object>> getbyuser(String uid) {
		
		return udao.getbyuser(uid);
	}

	@Override
	public int update(Users us) {
		// TODO Auto-generated method stub
		return udao.update(us);
	}

}
