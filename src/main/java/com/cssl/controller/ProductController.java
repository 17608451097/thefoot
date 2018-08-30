package com.cssl.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.cssl.service.ShopService;

@Controller
@RequestMapping("/htpage")
public class ProductController {
	
	@Autowired
	private ShopService ssic;
	
	@Autowired
	private ServletContext aco;
	  
	@RequestMapping("/addshop")
	public String addshop(Shop shop,@RequestPart("file")MultipartFile file,HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*if(ssic.addproduct(shop))
			ssic.addshopstock(String.valueOf(ssic.getaddshopid()), shop);*/
		String time = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String uuid = UUID.randomUUID().toString();
		if (!file.isEmpty()) {
			File fs = new File("C:\\Users\\Administrator\\Desktop\\apache-tomcat-8.5.32\\webapps\\thefootimg\\shopimg",time+uuid+file.getOriginalFilename());
			file.transferTo(fs);
		}
		return "redirect:/htpage/layout.html";
	}

}
