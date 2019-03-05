package com.cssl.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.cssl.dao.ShopDao;
import com.cssl.pojo.Users;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Controller
@SuppressWarnings("all")
@RequestMapping("/templates/gotopage")
public class MyordersController {
	@Autowired
	private ShopDao sdao; 
	
    @RequestMapping("/order")
	public ModelAndView orderAll(HttpServletRequest req,HttpServletResponse response,ModelAndView mv,
		 String typeid,String page ,String ordeid,String delid,String upid,String upoid) throws Exception 
        {
    	String typesid = typeid == null ? "0" :typeid;
    	String pages = page == null ? "0" :page;
    	String ordeids = ordeid == null ? "0" :ordeid;
    	String delids = delid == null ? "0" :delid;
    	String upids = upid == null ? "0" :upid;
    	String upoids = upoid == null ? "0" :upoid;
    	
    	//拿sis里面的用户id
    	    Users user= (Users)req.getSession().getAttribute("user");
    	
    	
    	
    	int upjg =sdao.updateordetype(Integer.valueOf(upids),Integer.valueOf(upoids));
    	int deljg =sdao.deleteordebyid(Integer.valueOf(delids));
        sdao.deleteordesbyid(Integer.valueOf(delids));
        
        
    	PageHelper.startPage(Integer.valueOf(pages),4);
    	List<Map<String, Object>> orde =sdao.selectorde(Integer.valueOf(typesid),user.getUserid());
    	Page<Map<String, Object>> ma =(Page<Map<String, Object>>) orde;
    	if(orde != null && orde.size() != 0){
			for (int i = 0; i < orde.size(); i++) {
				Map<String,Object> map = orde.get(i);
				map.put("orderdate", new SimpleDateFormat("yyyy/MM/dd").format(map.get("orderdate")));
			    }
		}
    	List<Map<String, Object>> ordes=sdao.selectordes();

    	req.setAttribute("pages", ma.getPages());
        req.setAttribute("orde", orde);
        req.setAttribute("ordes", ordes);
        
        //第一次进网页page为"0"
        if(page!=null) {
			Map<String, Object> op = new HashMap<String, Object>();
			op.put("pages", Integer.valueOf(page));
			op.put("deljg", deljg);
			orde.add(op);
            Map<String, Object> shopdd = new HashMap<String, Object>();
            shopdd.put("orde",orde);
            shopdd.put("ordes",ordes);
            shopdd.put("deljg", deljg);
            shopdd.put("upjg", upjg);
            String jsonq =JSON.toJSONString(shopdd);
            
            OutputStream out = response.getOutputStream();
			out.write(jsonq.getBytes());
			out.flush();
			out.close();
			
        }else {
      	  mv.setViewName("gotopage/dingdanzhongxin");
    	  return mv;	
		}
		return null;	
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
