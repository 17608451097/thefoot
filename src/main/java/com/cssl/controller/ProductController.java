package com.cssl.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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

import com.alibaba.fastjson.JSON;
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
	public String addshop(Shop shop, @RequestPart("file") MultipartFile file, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//
		String time = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String uuid = UUID.randomUUID().toString();
		if (!file.isEmpty()) {
			File fs = new File("C:\\Users\\Administrator\\Desktop\\apache-tomcat-8.5.32\\webapps\\thefootimg\\shopimg",
					time + uuid + file.getOriginalFilename());
			shop.setFilename(time + uuid + file.getOriginalFilename());
			file.transferTo(fs);
			if (ssic.addproduct(shop)) {
				int res = ssic.getaddshopid();
				/*
				 * shop.setShopid(res); ssic.addshopstock(shop);
				 */
			}
		}
		return "redirect:layout.html";
	}

	@RequestMapping("/showoption")
	public String showoption(Shop shop, String gn, HttpServletRequest req) {

		List<Map<String, Object>> sizels = sisc.getsize();
		List<Map<String, Object>> colorls = csic.getcolor();
		List<Map<String, Object>> typels = tsic.gettype();
		req.setAttribute("typels", typels);
		req.setAttribute("sizels", sizels);
		req.setAttribute("colorls", colorls);
		if (gn.equals("update")) {
			List<Map<String, Object>> shopls = ssic.getbyidshop(shop.getShopid() + "");
			req.setAttribute("shopls", shopls);
			return "htpage/updateshop";
		} 
		return "htpage/addshop";
	}

	@RequestMapping("/updateshop")
	public String updateshop(Shop shop, @RequestPart("file") MultipartFile file, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String time = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String uuid = UUID.randomUUID().toString();
		if (!file.isEmpty()) {
			File fs = new File("C:\\Users\\Administrator\\Desktop\\apache-tomcat-8.5.32\\webapps\\thefootimg\\shopimg",
					time + uuid + file.getOriginalFilename());
			shop.setFilename(time + uuid + file.getOriginalFilename());
			file.transferTo(fs);
			ssic.updateshop(shop);
		}
		return "redirect:layout.html";
	}

	@RequestMapping("/delshop")
	public void delshop(String shopid, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=GBK");
		PrintWriter out = response.getWriter();
		int i = ssic.delshop(shopid);
		int json = 0;
		if (i > 0) {
			ssic.delstock(shopid);
			json = 1;
		}
		out.println(json);
		out.flush();
		out.close();
	}

}
