package com.cssl.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.cssl.pojo.Shop;
import com.cssl.service.ColorService;
import com.cssl.service.ShopService;
import com.cssl.service.SizeService;
import com.cssl.service.TypeService;

@Controller
@RequestMapping("/templates/htpage")
public class ProductController {
	
	@Autowired
	private ShopService ssic;
	
	@Autowired
	private SizeService sisc;
	
	@Autowired
	private TypeService tsic;
	
	@Autowired
	private ColorService csic;
	  
	@RequestMapping("/addshop")
	public String addshop(Shop shop,@RequestPart("file")MultipartFile file,HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String time = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String uuid = UUID.randomUUID().toString();
		if (!file.isEmpty()) {
			File fs = new File("C:\\Users\\Administrator\\Desktop\\apache-tomcat-8.5.32\\webapps\\thefootimg\\shopimg",time+uuid+file.getOriginalFilename());
			shop.setFilename(time+uuid+file.getOriginalFilename());
			file.transferTo(fs);
			if(ssic.addproduct(shop)) {
				int res = ssic.getaddshopid();
				shop.setShopid(res);
				ssic.addshopstock(shop);
			}
		}
		return "htpage/layout";
	}
	
	@RequestMapping("/showoption")
	public String showoption(HttpServletRequest req) {
		List<Map<String,Object>> sizels = sisc.getsize();
		List<Map<String,Object>> colorls = csic.getcolor();
		List<Map<String,Object>> typels = tsic.gettype();
		req.setAttribute("typels",typels);
		req.setAttribute("sizels",sizels);
		req.setAttribute("colorls",colorls);
		return "htpage/addshop";
	}

}
