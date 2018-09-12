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

import com.cssl.pojo.Shop;
import com.cssl.pojo.Shopstock;
import com.cssl.service.ColorService;
import com.cssl.service.ShopService;
import com.cssl.service.SizeService;
import com.cssl.service.TypeService;

@Controller
@RequestMapping("/templates/htpage")
public class ShopKcController {
	
	@Autowired
	private SizeService sisc;
	
	@Autowired
	private ShopService ssic;

	@Autowired
	private ColorService csic;
	
	@RequestMapping("/shopkccz")
	public String shopkccz(String shopname,String shopid,String sid,String gn, HttpServletRequest req) {
		List<Map<String, Object>> sizels = sisc.getsize();
		List<Map<String, Object>> colorls = csic.getcolor();
		req.setAttribute("sizels", sizels);
		req.setAttribute("colorls", colorls);
		req.setAttribute("shopname",shopname);
		req.setAttribute("shopid",shopid);
		req.setAttribute("sid",sid);
		if (gn.equals("update")) {
			req.setAttribute("hx",ssic.updhx(sid));
			return "htpage/updkc"; 
		}
		return "htpage/kcadd";
	}
	
	@RequestMapping("/addkc")
	public String addkc(Shopstock stock) {
		if(stock!=null) {
			ssic.addkc(stock);
			kcl(stock.getShopid()+"");
		}
		return "redirect:layout.html";
	}
	
	//查询数据库中的库存,并实时同步
	public boolean kcl(String shopid) {
		int stock = ssic.getshopstock(shopid);
		if(ssic.updshop(stock+"", shopid)>0) 
			return true;
		return false;
	}
	
	@RequestMapping("/updkc")
	public String updkc(Shopstock shopstock) {
		if(shopstock!=null) {
			ssic.updshopkc(shopstock);
			kcl(shopstock.getShopid()+"");
		}
		return "redirect:layout.html";
	}
	
	@RequestMapping("/delkc")
	public void delkc(String sid,String shopid, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=GBK");
		PrintWriter out = response.getWriter();
		if(sid!=null && sid!="") {
			int row = ssic.delkc(sid);
			if(row>0) {
				kcl(shopid);
				row = 1;
			}else {
				row = 0;
			}
			out.println(row);
			out.flush();
			out.close();
		}
	}
	
	

}
