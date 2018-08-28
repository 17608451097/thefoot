package com.cssl.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.cssl.service.ShopService;
import com.cssl.service.UserService;

@Controller
@RequestMapping("/htpage")
public class MyController {
	
	@Autowired
	public ShopService ssic;
	
	@Autowired
	public UserService usic;

	@RequestMapping("/shop")
	public void shop(HttpServletResponse response) throws IOException {
		
		//这是后台商品数据展示代码
		response.setCharacterEncoding("GBK");
		response.setContentType("text/heml;charset=GBK");
		PrintWriter out = response.getWriter();
		List<Map<String, Object>> list = ssic.getShop();
		String json = JSON.toJSONString(list);
		out.write(json);
		out.flush();
		out.close();
	}
	
	
	@RequestMapping("user")
	public void  user(HttpServletResponse response)throws IOException {
		response.setCharacterEncoding("GBK");
		response.setContentType("text/heml;charset=GBK");
		PrintWriter out = response.getWriter();
		List<Map<String, Object>> list = usic.getuser();
		String json = JSON.toJSONString(list);
		System.out.println(json);
		out.write(json);
		out.flush();
		out.close();
	}
	
}
