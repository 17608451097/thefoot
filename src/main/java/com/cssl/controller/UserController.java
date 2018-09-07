package com.cssl.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cssl.service.UserService;

@Controller
@RequestMapping("/templates/htpage")
public class UserController {
	
	@Autowired
 	private UserService usic;
	
	@RequestMapping(value="deluser")
	public void deluser(String userid,HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=GBK");
		PrintWriter out = response.getWriter();
			int row = usic.deluser(userid);
			if(row>0) {
				row = 1;
			}else {
				row = 0;
			}
			out.println(row);
			out.flush();
			out.close();
	}

}
