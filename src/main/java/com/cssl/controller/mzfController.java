package com.cssl.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/templates/page")
public class mzfController {
	
	@RequestMapping("/mzf")
	public String mzf(String price,String type,String pay_id,String param,HttpServletResponse response) throws Exception {
		String token = "hMsMVR1xI3yPq6kB64Umch2iMBJmV0fT"; //记得更改 http://codepay.fateqq.com 后台可设置
		String codepay_id ="109711" ;//记得更改 http://codepay.fateqq.com 后台可获得
		
		String notify_url="demo.greatap.cn";//通知地址
		String return_url="http://localhost:8080/templates/page/hd";//支付后同步跳转地址

		System.out.println(param);
		if(price==null){ 
			price="1";
		}
		//参数有中文则需要URL编码
		String url="http://codepay.fateqq.com:52888/creat_order?id="+codepay_id+"&pay_id="
					+pay_id+"&price="+price+"&type="+type+"&token="+token+"&param="+param+"&notify_url="
					+notify_url+"&return_url="+return_url;

		response.sendRedirect(url);
		return "page/login";
	}
	
	@RequestMapping("/hd")
	public String hd(String param) {
		System.out.println(param);
		return "page/login";
	}
	
	/*public void tz(HttpServletRequest request,HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		*//**
		*验证通知 处理自己的业务
		*//*
		String key = "7d3JeIMRIiJpaekHIGECsdJ0i8whkrOr"; //记得更改 http://codepay.fateqq.com 后台可设置
		Map<String,String> params = new HashMap<String,String>(); //申明hashMap变量储存接收到的参数名用于排序
		Map requestParams = request.getParameterMap(); //获取请求的全部参数
		String valueStr = ""; //申明字符变量 保存接收到的变量
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			valueStr = values[0];
			//乱码解决，这段代码在出现乱码时使用。如果sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);//增加到params保存
		}
		List<String> keys = new ArrayList<String>(params.keySet()); //转为数组
	  	Collections.sort(keys); //重新排序
		String prestr = "";
		String sign= params.get("sign"); //获取接收到的sign 参数
		
	        for (int i = 0; i < keys.size(); i++) { //遍历拼接url 拼接成a=1&b=2 进行MD5签名
	            String key_name = keys.get(i);
	            String value = params.get(key_name);
		    	if(value== null || value.equals("") ||key_name.equals("sign")){ //跳过这些 不签名
		    		continue;
		    	}
		    	if (prestr.equals("")){
		    		prestr =  key_name + "=" + value;
		    	}else{
					prestr =  prestr +"&" + key_name + "=" + value;
		    	}
	        }
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update((prestr+key).getBytes());
		String  mySign = new BigInteger(1, md.digest()).toString(16).toLowerCase();
		if(mySign.length()!=32)mySign="0"+mySign;
		if(mySign.equals(sign)){ 
			//编码要匹配 编码不一致中文会导致加密结果不一致
			//参数合法处理业务
			//request.getParameter("pay_no") 流水号
			//request.getParameter("pay_id") 用户唯一标识
			//request.getParameter("money") 付款金额
			//request.getParameter("price") 提交的金额
			out.print("ok");
		}else{
			//参数不合法
			out.print("fail");
		}
	}*/

}
