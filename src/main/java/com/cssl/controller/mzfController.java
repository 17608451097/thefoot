package com.cssl.controller;


import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/templates/page")
public class mzfController {
	
	@RequestMapping("/mzf")
	public void mzf(String price,String type,String pay_id,String param,HttpServletResponse response) throws Exception {
		String token = "hMsMVR1xI3yPq6kB64Umch2iMBJmV0fT"; //记得更改 http://codepay.fateqq.com 后台可设置
		String codepay_id ="109711" ;//记得更改 http://codepay.fateqq.com 后台可获得
		
		String notify_url="demo.greatap.cn";//通知地址
		String return_url="demo.greatap.cn";//支付后同步跳转地址

		if(price==null){ 
			price="1";
		}
		//参数有中文则需要URL编码
		String url="http://codepay.fateqq.com:52888/creat_order?id="+codepay_id+"&pay_id="
					+pay_id+"&price="+price+"&type="+type+"&token="+token+"&param="+param+"&notify_url="
					+notify_url+"&return_url="+return_url;

		response.sendRedirect(url);
	}
	
	@RequestMapping("/hd")
	public String hd(String param) {
		System.out.println(param);
		return "page/login";
	}

}
