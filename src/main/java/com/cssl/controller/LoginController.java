package com.cssl.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cssl.dao.UserDao;
import com.cssl.pojo.Users;
import com.cssl.service.UserService;

@Controller
@RequestMapping("/templates/page")
public class LoginController {

	@Autowired
	private UserService usic;
	@Autowired
	private UserDao udao;
	
	@RequestMapping("/login")
	public String login(String username,String veryCode,
			String password,HttpServletRequest request,Model model,HttpSession session) {
		if(veryCode=="") {
			request.getSession().setAttribute("ss", "验证码不能为空");
			return "page/login.html";
		}
		if(((String) session.getAttribute("rand")).toLowerCase().equals(veryCode.toLowerCase()) ) {
		int usid =usic.getByUser(username, password);
		Users us=    udao.getByUser(username, password);
		switch (usid) {
		case 1:
			request.getSession().setAttribute("admin", us);
			return "redirect:/templates/htpage/layout.html";
        case 2:
        	webSocketTest wss = webSocketTest.getWebSocket(String.valueOf(us.getUserid()));
			if (wss != null) {  // 如果已经登录，则发送信息给websocket
			    try {
			        wss.sendMessage("101");
			    } catch (IOException e) {
			        e.printStackTrace();
			    }
			}
			request.getSession().setAttribute("sid", us.getUserid());
			request.getSession().setAttribute("user", us);
			return "redirect:/templates/page/showType";
        case 3:
        	request.getSession().setAttribute("ss", "账号密码错误");
			return "page/login.html";
		}
		}
		request.getSession().setAttribute("ss", "验证码错误");
		return "page/login.html";
	}
	
	
	@RequestMapping("exit")
	public String exit(HttpServletRequest request,String username) { 
		 return "redirect:/templates/page/login.html";
	}
	
@RequestMapping("zx")
public String zx() { 
	 return "redirect:/templates/page/zx3.html";
}
	
	
}
