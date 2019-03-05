package com.cssl.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/templates/page")
public class Orderzfm {
	
	@RequestMapping("/ordezfm")
	public String mzf(String orderid,String orderprice,HttpServletRequest re) throws Exception {
		 re.getSession().setAttribute("sum", orderprice);
		 re.getSession().setAttribute("orderid", orderid);
		 re.getSession().setAttribute("orption", "wzf");
		 System.out.println(orderid);
		 System.out.println(orderprice);
		return "page/demo";
	}
	
	

}
