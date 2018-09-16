package com.cssl.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.cssl.service.OrderService;
import com.cssl.service.ShopService;
import com.cssl.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.cssl.VerifyCodeUtils;

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
	public void shop(String shopname,String rows,String page,HttpServletResponse response) throws IOException {
		//这是后台商品数据展示代码
		response.setCharacterEncoding("GBK");
		response.setContentType("text/heml;charset=GBK");
		PrintWriter out = response.getWriter();
		Page<Integer> pages = PageHelper.startPage(Integer.valueOf(page),Integer.valueOf(rows));
		List<Map<String,Object>> list = ssic.getShop(shopname);
		String json = JSON.toJSONString(list);
		json = "{\"total\":"+pages.getTotal()+",\"rows\":"+json+"}";
		out.write(json);
		out.flush();
		out.close();
	}
	
	
	@RequestMapping("/user")
	public void  user(String rows,String page,HttpServletResponse response)throws IOException {
		response.setCharacterEncoding("GBK");
		response.setContentType("text/heml;charset=GBK");
		PrintWriter out = response.getWriter();
		Page<Integer> pages = PageHelper.startPage(Integer.valueOf(page),Integer.valueOf(rows));
		List<Map<String, Object>> list = usic.getuser();
		if(list != null && list.size() != 0){
			for (int i = 0; i < list.size(); i++) {
				Map<String,Object> map = list.get(i);
				map.put("DATE", new SimpleDateFormat("yyyy-MM-dd").format(map.get("DATE")));
			}
		}
		String json = JSON.toJSONString(list);
		json = "{\"total\":"+pages.getTotal()+",\"rows\":"+json+"}";
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
	public void order(String rows,String page,HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("GBK");
		response.setContentType("text/heml;charset=GBK");
		PrintWriter out = response.getWriter();
		Page<Integer> pages = PageHelper.startPage(Integer.valueOf(page),Integer.valueOf(rows));
		List<Map<String, Object>> list = osic.getOrder();
		if(list!=null&&list.size()!=0){
			for (int i = 0; i < list.size(); i++) {
				Map<String,Object> map = list.get(i);
				map.put("orderdate", new SimpleDateFormat("yyyy-MM-dd").format(map.get("orderdate")));
			}
		}
		String json = JSON.toJSONString(list);
		json = "{\"total\":"+pages.getTotal()+",\"rows\":"+json+"}";
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
	
	@RequestMapping("/yzm")
	public void yzm(HttpServletResponse response,HttpServletRequest request) throws IOException {
		response.setHeader("Pragma", "No-cache");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setDateHeader("Expires", 0);  
        response.setContentType("image/jpeg");  
          
        //生成随机字串  
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        //存入会话session  
        HttpSession session = request.getSession(true);  
        session.setAttribute("rand", verifyCode.toLowerCase()); 
        //生成图片  
        int w = 200, h = 80;  
        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);  
	}
	
}
