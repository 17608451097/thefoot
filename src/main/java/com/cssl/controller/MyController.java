package com.cssl.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.cssl.service.OrderService;
import com.cssl.service.ShopService;
import com.cssl.service.UserService;

@Controller
@RequestMapping("/templates/htpage")
public class MyController {
	
	@Autowired
	public ShopService ssic;
	
	@Autowired
	public UserService usic;
	
	@Autowired
	private OrderService osic;

	@RequestMapping("/shop")
	public void shop(HttpServletResponse response) throws IOException {
		
		//这是后台商品数据展示代码
		response.setCharacterEncoding("GBK");
		response.setContentType("text/heml;charset=GBK");
		PrintWriter out = response.getWriter();
		List<Map<String, Object>> list = ssic.getShop();
		String json = JSON.toJSONString(list);
		System.out.println(json);
		out.write(json);
		out.flush();
		out.close();
	}
	
	
	@RequestMapping("/user")
	public void  user(HttpServletResponse response)throws IOException {
		response.setCharacterEncoding("GBK");
		response.setContentType("text/heml;charset=GBK");
		PrintWriter out = response.getWriter();
		List<Map<String, Object>> list = usic.getuser();
		if(list != null && list.size() != 0){
			for (int i = 0; i < list.size(); i++) {
				Map<String,Object> map = list.get(i);
				map.put("DATE", new SimpleDateFormat("yyyy-MM-dd").format(map.get("DATE")));
			}
		}
		String json = JSON.toJSONString(list);
		out.write(json);
		out.flush();
		out.close();
	}
	
	@RequestMapping("/kccz")
	public void kccz(String shopid,HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("GBK");
		response.setContentType("text/heml;charset=GBK");
		PrintWriter out = response.getWriter();
		List<Map<String, Object>> list = ssic.getshop(shopid);
		String json = JSON.toJSONString(list);
		out.write(json);
		out.flush(); 
		out.close();
	}
	
	@RequestMapping("/order")
	public void order(HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("GBK");
		response.setContentType("text/heml;charset=GBK");
		PrintWriter out = response.getWriter();
		List<Map<String, Object>> list = osic.getOrder();
		if(list!=null&&list.size()!=0){
			for (int i = 0; i < list.size(); i++) {
				Map<String,Object> map = list.get(i);
				map.put("orderdate", new SimpleDateFormat("yyyy-MM-dd").format(map.get("orderdate")));
			}
		}
		String json = JSON.toJSONString(list);
		out.write(json);
		out.flush();
		out.close();
	}
	
	@RequestMapping("/ddxq")
	public void ddxq(String orderid,HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("GBK");
		response.setContentType("text/heml;charset=GBK");
		PrintWriter out = response.getWriter();
		List<Map<String, Object>> list = osic.getddxq(orderid);
		String json = JSON.toJSONString(list);
		out.write(json);
		out.flush(); 
		out.close();
	}
	
}
