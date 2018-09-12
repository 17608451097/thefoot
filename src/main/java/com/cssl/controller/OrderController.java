package com.cssl.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cssl.pojo.Colors;
import com.cssl.service.OrderService;

@Controller
@RequestMapping("/templates/htpage")
public class OrderController {
	
	@Autowired
	private OrderService osic;
	 
	@RequestMapping("/showdd")
	public String showdd(String ordertypeid,String orderid,String ddbh,String name,String date,String price,HttpServletRequest req) {
		List<Map<String,Object>> list = osic.getordertype();
		req.setAttribute("typelist",list);
		req.setAttribute("orderid",orderid);
		req.setAttribute("ddbh",ddbh);
		req.setAttribute("name",name);
		req.setAttribute("date",date);
		req.setAttribute("price",price);
		req.setAttribute("ordertypeid",ordertypeid);
		return "htpage/upddd";
	}
	
	@RequestMapping("/updzt")
	public String updzt(String ordertypeid,String orderid) {
		System.out.println(ordertypeid);
		if("1".equals(ordertypeid)) {
			//订单状态为1未支付,订单详情则为4未发货
			if(osic.updateordertype(ordertypeid, orderid)) {
				updxq("","4","update",orderid);
			}
		}else if("2".equals(ordertypeid)) {
			//订单状态为2已支付,订单详情则为1已发货
			if(osic.updateordertype(ordertypeid, orderid)) {
				updxq("","1","update",orderid);
			}
		}else if("3".equals(ordertypeid)) {
			//订单状态为3退款中,订单详情则为5退款中
			if(osic.updateordertype(ordertypeid, orderid)) {
				updxq("","5","update",orderid);
			}
		}else if("4".equals(ordertypeid)) {
			//订单状态为4已退款,订单详情则为6已退款
			if(osic.updateordertype(ordertypeid, orderid)) {
				updxq("","6","update",orderid);
			}
		}
		return "redirect:layout.html";
	}
	
	@RequestMapping("/updxq")
	public String updxq(String detailsid,String orderdetailstypeid,String gn,String orderid) {
		if("update".equals(gn)) {
			//修改订单状态之后修改详情状态
			boolean flag = osic.updatedetails(orderdetailstypeid, orderid);
			System.out.println(flag);
		}else {
			//修改详情状态
			osic.upddet(detailsid, orderdetailstypeid);
		}
		return "redirect:layout.html";
	}
	
	@RequestMapping("/deldd")
	public void deldd(String orderid,HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=GBK");
		PrintWriter out = response.getWriter();
		int i = 0;
		if(osic.deldd(orderid)) {
			osic.deldddetalis(orderid);
			i=1;
		}
		out.println(i);
		out.flush();
		out.close();
	}
	
	@RequestMapping("/gettype")
	public String gettype(String detailsid,String shopname,String shopprice,String sale,String orderdetailstypeid,HttpServletRequest req) {
		List<Map<String,Object>> list = osic.getddtype();
		req.setAttribute("detailsid",detailsid);
		req.setAttribute("shopname",shopname);
		req.setAttribute("shopprice",shopprice);
		req.setAttribute("sale",sale);
		req.setAttribute("orderdetailstypeid",orderdetailstypeid);
		req.setAttribute("typelist",list);
		return "htpage/updxq";
	}
	
	@RequestMapping("/delxq")
	public void delxq(String detailsid,HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=GBK");
		PrintWriter out = response.getWriter();
		int i = 0;
		if(osic.delxq(detailsid)>0) {
			i=1;
		}
		out.println(i);
		out.flush();
		out.close();
	}


}
