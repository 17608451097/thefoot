package com.cssl.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cssl.dao.ShopDao;
import com.cssl.dao.TypeDao;
import com.cssl.service.impl.ShowServiceImpl;

@Controller
@RequestMapping("/templates/page")
public class ShowController {
	
	@Autowired
	private ShowServiceImpl ssi;
	
	@Autowired
	private ShopDao sdao; 
	@Autowired
	private TypeDao tdao; 
	
	@RequestMapping("/showType")
	public String showType(HttpServletRequest req) {
		List<Map<String,Object>> list= ssi.getType();
		List<Map<String,Object>> list1= ssi.getSonType();
		List<Map<String,Object>> list2= ssi.getGrsontype();
		List<Map<String,Object>> list3=ssi.getShopOne_Three();
		List<Map<String,Object>> list4=ssi.getShopThree_Six();
		
		List<Map<String, Object>> typelist =sdao.selectgrson();
		List<Map<String, Object>> dhllist=sdao.selectdhlall();
		List<Map<String, Object>> dhl=tdao.getdhl();
		req.setAttribute("type",typelist);
		req.setAttribute("dhllist", dhl);
		req.setAttribute("dhl", dhllist);
		req.setAttribute("list", list);
		req.setAttribute("list1", list1);
		req.setAttribute("list2", list2);
		req.setAttribute("list3", list3);
		req.setAttribute("list4", list4);
		return "page/index";
	}
	
	@RequestMapping("/showSingle")
	public String showSingle(HttpServletRequest req,int id) {
		List<Map<String,Object>> list= ssi.getType();
		List<Map<String,Object>> list1= ssi.getSonType();
		List<Map<String,Object>> list2= ssi.getGrsontype();
		List<Map<String,Object>> sa= ssi.getShopAll(id);
		List<Map<String,Object>> sz= ssi.getShopsizename(id);
		List<Map<String,Object>> show=ssi.getShopImg();
		List<Map<String,Object>> ic=ssi.getImgColor(id);
		List<Map<String, Object>> typelist =sdao.selectgrson();
		List<Map<String, Object>> dhllist=sdao.selectdhlall();
		List<Map<String, Object>> dhl=tdao.getdhl();
		req.setAttribute("type",typelist);
		req.setAttribute("dhllist", dhl);
		req.setAttribute("dhl", dhllist);
		req.setAttribute("list", list);
		req.setAttribute("list1", list1);
		req.setAttribute("list2", list2);
		req.setAttribute("sa", sa);
		req.setAttribute("sz", sz);
		req.setAttribute("show", show);
		req.setAttribute("ic", ic);
		return "page/single";
	}
}
