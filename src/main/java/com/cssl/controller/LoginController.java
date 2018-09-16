package com.cssl.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cssl.pojo.Users;
import com.cssl.service.UserService;

@Controller
@RequestMapping("/templates/page")
public class LoginController {


	@Autowired
	private UserService usic;
	
	@RequestMapping("/login")
	public String login(String username,String password,HttpServletRequest request,Model model) {
		 Users us = usic.getByUser(username, password);
		 if(us!=null) {
			if(us.getIsAdmin()==1) {
				request.getSession().setAttribute("admin", us);
				return "redirect:/templates/htpage/layout.html";
			}
			request.getSession().setAttribute("user", us);
			return "redirect:/templates/page/showType";
		}else {
			model.addAttribute("result", "账号密码有误！请重新登陆");
			return "page/login.html";
		}	
		
	}
	
}
