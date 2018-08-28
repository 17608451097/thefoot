package com.cssl.controller;

import java.util.List;
import java.util.Map;

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
	public String showType() {
		List<Map<String,Object>> list= ssi.getType();
		for (Map<String, Object> m : list) {
			System.out.println("id:"+m.get("typeid")+"name:"+m.get("typename"));
		}
		
		return "index";
		
	}
	
}
