package com.cssl.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.StyledEditorKit.ForegroundAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties.AmqpContainer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.cssl.dao.ShopDao;
import com.cssl.dao.TypeDao;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/templates/page")
@SuppressWarnings("all")
public class ShopController {
	
	@Autowired
	private ShopDao sdao; 
	
	@Autowired
	private TypeDao tdao; 
	
	@RequestMapping("/shopselectall")
	public ModelAndView SelectAll(ModelAndView mv,HttpServletRequest re,HttpServletResponse response,String pages,
			String pp,String price,String orde
			) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		List<Map<String, Object>> typelist =sdao.selectgrson();
		List<Map<String, Object>> dhllist=sdao.selectdhlall();
		List<Map<String, Object>> dhl=tdao.getdhl();
		re.setAttribute("type",typelist);
		re.setAttribute("dhllist", dhl);
		re.setAttribute("dhl", dhllist);
		
		String page = pages == null ? "0" : pages;
		String order =orde ==null ? "0" :orde;
		String prices =price ==null ? "999999" :price;
		String pps =pp ==null ?"0":pp;
		
		PageHelper.startPage(Integer.valueOf(page),6);
		List<Map<String,Object>> list =sdao.selectshopall(pps,prices,order);
		Page<Map<String, Object>> ma =(Page<Map<String, Object>>) list;
		List<Integer> plist = new ArrayList<Integer>();        

		for (int i = 0; i < ma.getPages(); i++) {
			plist.add(i);
		}
		int p =plist.size();
		mv.addObject("plist",p);
		mv.addObject("pages",pages);
		mv.addObject("shoplist",list);
		
		if(Integer.valueOf(page)==0) {
			mv.addObject("pages",Integer.valueOf(page)+1);
			mv.setViewName("page/shop");
			return mv;			
		}else {
			Map<String, Object> op = new HashMap<String, Object>();
			op.put("pages", Integer.valueOf(pages));
			op.put("ss", ma.getPages());
			list.add(op);
			String json = JSON.toJSONString(list);
			OutputStream out = response.getOutputStream();
			out.write(json.getBytes());
			out.flush();
			out.close();
		}
	return null;
	}	
		
	
}
