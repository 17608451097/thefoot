package com.cssl.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cssl.service.impl.ShowServiceImpl;

@Controller
@RequestMapping("/page")
public class ShowController {
	
	@Autowired
	private ShowServiceImpl ssi;
	
	@RequestMapping("/showType")
	public String showType(HttpServletRequest req) {
		List<Map<String,Object>> list= ssi.getType();
		List<Map<String,Object>> list1= ssi.getSonType();
		List<Map<String,Object>> list2= ssi.getGrsontype();
		req.setAttribute("list", list);
		req.setAttribute("list1", list1);
		req.setAttribute("list2", list2);
		return "index";
		
	}
	
}
