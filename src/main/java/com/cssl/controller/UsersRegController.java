package com.cssl.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.cssl.pojo.Users;
import com.cssl.service.UsersRegService;
import com.show.api.ShowApiRequest;

@Controller
@RequestMapping("/templates/page")
public class UsersRegController {
    
	@Autowired
	private UsersRegService us;
	 
	String appid = "75035";
	String secret = "4ccf4beb657041b79a5112e9c41cb96d";

	
	int rodon = (int)((Math.random()*9+1)*100000);
	
	@RequestMapping("dxApi")
	@ResponseBody
	public void yanzhen(String username,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String phone = request.getParameter("phone");
		PrintWriter out  = response.getWriter();
		
		request.getSession().setAttribute("rodon", rodon);
		request.setAttribute("rodon", rodon);
		//调用第三方短信接口
		String res = (new ShowApiRequest("http://route.showapi.com/28-1", this.appid, this.secret)).addTextPara("mobile", phone).addTextPara("content", "{name:'你好,你正在进行注册', code:'"+rodon+"',minute:'3'}").addTextPara("tNum", "T170317003041").addTextPara("big_msg", "1").post();
		JSONObject jsonObject = JSONObject.parseObject(res); 
		JSONObject json =(JSONObject) jsonObject.get("showapi_res_body");
		String ret_code = (String)json.get("ret_code");
		if("0".equals(ret_code)) {
			out.println(1);
		}else {
			out.println(0);
		}
		out.flush();
		out.close();

		
	}
	
	@RequestMapping("yzm")
	@ResponseBody
	public int yzyzm() {
		
		return rodon;
	}
	
	
	
	@RequestMapping("registUser")
	public String regUser(String username,String password,String phone_number,String email) {
	    Users user=new Users();
		user.setName(username);
		user.setPassword(password);
		user.setPhone(phone_number);
		user.setEmail(email);
		user.setDatetime(new Date());
		if(us.regist(user)>=1) {
			return "redirect:login.html";
		}
		return "redirect:register.html";
		
	}
	
	@RequestMapping("exitsUname")
	@ResponseBody
	public String exitsName(String username) {
		if(us.exitsUserName(username)>0) {
			return "true";
		}	
		return "false";
	}
	
}
