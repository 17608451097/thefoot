package com.cssl.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

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
	public String shopkccz(String s_sid,String filename,String shopname,String shopid,String sid,String gn, HttpServletRequest req) {
		List<Map<String, Object>> sizels = sisc.getsize();
		List<Map<String, Object>> colorls = csic.getcolor();
		req.setAttribute("sizels", sizels);
		req.setAttribute("colorls", colorls);
		req.setAttribute("shopname",shopname);
		req.setAttribute("shopid",shopid);
		req.setAttribute("s_sid",s_sid);
		req.setAttribute("sid",sid);
		req.setAttribute("filename",filename);
		if (gn.equals("update")) {
			req.setAttribute("hx",ssic.updhx(sid));
			return "htpage/updkc"; 
		}
		return "htpage/kcadd";
	} 
	
	@RequestMapping("/addkc")
	public String addkc(@RequestPart("file") MultipartFile file,Shopstock stock) throws Exception {
		String time = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String name = file.getOriginalFilename();
		String names = name.substring(name.indexOf("."));
		String uuid = UUID.randomUUID().toString();
		if (!file.isEmpty()) {
			File fs = new File("C:\\Users\\Administrator\\Desktop\\apache-tomcat-8.5.32\\webapps\\thefootimg\\shopimg",time + uuid + names);
			if(stock!=null) {
				ssic.addimg(stock.getColorid()+"",stock.getShopid()+"", time + uuid + names);
				//拿到新增之后的imgid  方法名不对义
				int s_sid = ssic.getaddshopid();
				stock.setS_sid(s_sid);
				ssic.addkc(stock);
				kcl(stock.getShopid()+"");
			}
			file.transferTo(fs);
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
	public void delkc(String s_sid,String sid,String shopid, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=GBK");
		PrintWriter out = response.getWriter();
		if(sid!=null && sid!="") {
			int row = ssic.delkc(sid);
			if(row>0) {
				kcl(shopid);
				ssic.delshopimg(s_sid);
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
