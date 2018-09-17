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

import com.alibaba.fastjson.JSONObject;
import com.cssl.pojo.Users;
import com.cssl.service.UserService;
import com.show.api.ShowApiRequest;

@Controller
@RequestMapping("/templates/gotopage")
public class DxApiController {
	
	 	String appid = "75035";
	 	String secret = "4ccf4beb657041b79a5112e9c41cb96d";
	 
		@Autowired
		private UserService usic;
	
		@RequestMapping("/dxapi")
		public void dxapi(String phone,HttpServletRequest request,HttpServletResponse response) throws IOException {
			PrintWriter out  = response.getWriter();
			int rodon = (int)((Math.random()*9+1)*100000);
			request.getSession().setAttribute("rodon", rodon);
			//调用第三方短信接口
			String res = (new ShowApiRequest("http://route.showapi.com/28-1", this.appid, this.secret)).addTextPara("mobile", phone).addTextPara("content", "{name:'修改手机号', code:'"+rodon+"',minute:'3'}").addTextPara("tNum", "T170317003067").addTextPara("big_msg", "1").post();
			JSONObject jsonObject = JSONObject.parseObject(res); 
			JSONObject json =(JSONObject) jsonObject.get("showapi_res_body");
			String ret_code = (String)json.get("ret_code");
			if("0".equals(ret_code)) {
				out.println(1);
			}else {
				out.println(0);
			}
			out.flush();
			out.close(); 
		}
		
		@RequestMapping("/getuser")
		public String getuser(HttpServletRequest req) {
			req.getSession().setAttribute("user","1");
			String uid = (String) req.getSession().getAttribute("user");
			List<Map<String,Object>> list = usic.getbyuser(uid);
			req.setAttribute("ulist", list);
			return "gotopage/self";
		}
		
		@RequestMapping("/updateuser")
		public String updateuser(Users us) {
			usic.update(us);
			return "redirect:getuser";
		}
		
		@RequestMapping("/dxyz")
		public void dxyz(String recode,HttpServletRequest req,HttpServletResponse response) throws IOException {
			System.out.println(recode);
			PrintWriter out  = response.getWriter();
			int code = (int) req.getSession().getAttribute("rodon");
			String codes=code+"";
			if(codes.equals(recode)) {
				out.println(1);
			}else {
				out.println(0);
			}	
		}

}
