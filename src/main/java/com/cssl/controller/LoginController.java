package com.cssl.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cssl.pojo.Users;
import com.cssl.service.UserService;

@Controller
@RequestMapping("/page")
public class LoginController {

	@Autowired
	private UserService usic;
	
	@RequestMapping("/login")
	public String login(String username,String password,HttpServletRequest request) {
		Users us = usic.getByUser(username, password);
		if(!username.equals(us.getName()) && password.equals(us.getPassword())) {
			request.setAttribute("result","账号密码有误,请重新登陆。");
			return "login";
		}else {
			if(us.getIsAdmin()==1) {
				request.getSession().setAttribute("admin", us);
				return "redirect:/htpage/layout.html";
			}
			request.getSession().setAttribute("user", us);
			return "index";
		}
	}
	
}
