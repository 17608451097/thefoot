package com.cssl.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
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
		System.out.println(shop.getShopname());
		String time = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String qtpaths = aco.getRealPath("/images");
		System.out.println(qtpaths);
		String uuid = UUID.randomUUID().toString();
		System.out.println(uuid+time);
		if (!file.isEmpty()) {
			File fs = new File(qtpaths,time+uuid+".png");
			file.transferTo(fs);
			/*
			  E:\Eclipse\WORKS\eclipse\spring\TheFoot\src\main\resources\page\images
			  E:\Eclipse\WORKS\eclipse\spring\TheFoot\src\main\webapp\image
			 */
		}
		return "redirect:/htpage/layout.html";
	}
	
	
	
	

}
