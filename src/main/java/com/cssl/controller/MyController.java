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

@Controller
@RequestMapping("/htpage")
public class MyController {
	
	@Autowired
	public ShopService ssic;

	@RequestMapping("/xx")
	public void xx(HttpServletResponse response) throws IOException {
		
		response.setCharacterEncoding("GBK");
		response.setContentType("text/heml;charset=GBK");
		PrintWriter out = response.getWriter();
		List<Map<String, Object>> list = ssic.getShop();
		String json = JSON.toJSONString(list);
		out.println(json);
		System.out.println(json);
		out.flush();
		out.close();
	}
	
}
