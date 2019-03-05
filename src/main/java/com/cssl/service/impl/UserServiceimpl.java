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
	/*@Autowired
	private RedisUtil ru;*/
	@Autowired
	private UserDao udao;
	@Override
	public int getByUser(String username, String password) {

		Users user= udao.getByUser(username, password);

		if(user!=null) {
				//管理员，用户正常登陆
				if(user.getIsAdmin()==1) {/*
					ru.set(user.getCompanyname(), username);*/
					return 1;
				}else {/*
					ru.set(user.getCompanyname(), username);*/
					return 2;
				}
			}
		return 3;
	}
	@Override
	public void userexit(String username) {
		/*ru.remove(username);*/
		
	}
	

	@Override
	public int updateRepeat(int repeat, int userid) {
		
		return udao.updateRepeat(repeat, userid);
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
		
		return udao.update(us);
	}




}
