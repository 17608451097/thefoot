package com.cssl.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cssl.pojo.Orderdetails;
import com.cssl.pojo.Orders;
import com.cssl.pojo.Shop;
import com.cssl.pojo.ShopCat;
import com.cssl.pojo.Users;
import com.cssl.service.ShopCatService;
import com.cssl.service.ShopService;

@Controller
@RequestMapping("/templates/page")
public class ShoppingCatController {

	@Autowired
	private ShopCatService sl;

	@Autowired
	private ShopService sdao;
	
	private int sum = 0;

	Users us=null;
	Map<String, ShopCat> smap=null;
	
	@RequestMapping("add")
	@ResponseBody
	public String add(String shopid, String color, String size, HttpServletRequest req, HttpServletResponse res) {
		res.setContentType("text/html;charset=GBK");
		String json = "false";
		us = (Users) req.getSession().getAttribute("user");
		if (us == null) {
			req.getSession().setAttribute("mes", "请先登陆!");
			json = "login";
			return json;
		}

		Map<String, ShopCat> shopmap = new HashMap<String, ShopCat>();// 存放
		Shop shop = sl.getShangping(Integer.valueOf(shopid));// 获取要将加入购物车的商品信息
		ShopCat shopcat = new ShopCat(1, shop);
		List<ShopCat> list = sl.getCat(us.getUserid());
		if (list == null || list.isEmpty()) {// 如果从数据库查不出数据则直接加
			shopmap = new HashMap<String, ShopCat>();
			shopcat.setShop(shop);
			shopcat.setColor(Integer.valueOf(color));
			shopcat.setSize(Integer.valueOf(size));
			shopcat.setPrice(shop.getOriprice());
			shopcat.setShopid(Integer.valueOf(shopid));
			shopcat.setUserid(us.getUserid());
			shopcat.setCounts(1);
			sl.addShopCat(shopcat);
		} else {
			for (ShopCat s : list) {
				if(list.listIterator().hasNext()) {					
					shopmap.put(String.valueOf(s.getShopid()), s);
				}
				
			}
			for (ShopCat s : list) {
				
				if (s.getShopid() == (Integer.valueOf(shopid))
						&& s.getColor() == (Integer.valueOf(color))
						&& s.getSize() == (Integer.valueOf(size))
						&& s.getUserid() == us.getUserid()) {
					shopcat.setCounts(s.getCounts() + 1);
					shopcat.setS_cat_id(s.getS_cat_id());
					System.out.println(shopcat.getS_cat_id()+"-"+shopcat.getCounts());
					int i=sl.updateShopCat(shopcat);
					System.out.println("已有 进行增加");
					break;
				}
				if(s.getShopid() == (Integer.valueOf(shopid))&& s.getUserid() == us.getUserid()&&s.getColor() != (Integer.valueOf(color))
						||s.getShopid() == (Integer.valueOf(shopid))&& s.getUserid() == us.getUserid()&& s.getSize() != (Integer.valueOf(size))) {
					shopcat.setShop(shop);
					shopcat.setColor(Integer.valueOf(color));
					shopcat.setSize(Integer.valueOf(size));
					shopcat.setPrice(shop.getOriprice());
					shopcat.setShopid(Integer.valueOf(shopid));
					shopcat.setUserid(us.getUserid());
					shopcat.setCounts(1);
					sl.addShopCat(shopcat);
					break;
				}
			}
				if(!(shopmap.containsKey(shopid))) {
					shopcat.setShop(shop);
					shopcat.setColor(Integer.valueOf(color));
					shopcat.setSize(Integer.valueOf(size));
					shopcat.setPrice(shop.getOriprice());
					shopcat.setShopid(Integer.valueOf(shopid));
					shopcat.setUserid(us.getUserid());
					shopcat.setCounts(1);
					sl.addShopCat(shopcat);
					
				}
		}

		json = "true";
		sum = sl.getsum(us.getUserid());
		req.getSession().setAttribute("shopmap", shopmap);
		req.getSession().setAttribute("sum", sum);
		return json;

	}

	@ResponseBody
	@RequestMapping("changecount")
	public int changecou(String shopid, Integer count, HttpServletRequest req) {

		int id = Integer.valueOf(shopid);
		int co = Integer.valueOf(count);

		HashMap<String, ShopCat> map = (HashMap<String, ShopCat>) req.getSession().getAttribute("shopmap");
		int sm = 0;
		List<HashMap<String, ShopCat>> list = new ArrayList<HashMap<String, ShopCat>>();
		list.add(map);

		for (Map<String, ShopCat> a : list) {
			if (a.get(shopid).getShopid() == id) {
				a.get(shopid).setCounts(co);
				System.out.println(a.get(shopid).getCounts());
				a.get(shopid).setMoney(a.get(shopid).getShop().getOriprice() * a.get(shopid).getCounts());
				System.out.println("商品ID为" + id + "的数量为:" + a.get(shopid).getCounts() + "$" + a.get(shopid).getMoney());
				ShopCat scat = a.get(shopid);
				sl.updateShopCat(scat);
			}

		}
	   us = (Users) req.getSession().getAttribute("user");
		sm = sl.getsum(us.getUserid());
		req.getSession().setAttribute("sum", sm);
		return sm;

	}

	@RequestMapping("/delcat")
	@ResponseBody
	public int remove(String shopid, HttpServletRequest request) {
		Map<String, ShopCat> shopmap =  (Map<String, ShopCat>) request.getSession().getAttribute("shopmap");
		shopmap.remove(shopid);
		us = (Users) request.getSession().getAttribute("user");

		sl.delShopCat(Integer.valueOf(shopid));
		sum=sl.getsum(us.getUserid());
		return sum;

	}

	
	@RequestMapping("/ts")
	public ModelAndView a(ModelAndView md, HttpServletRequest res) {

		us = (Users) res.getSession().getAttribute("user");
		System.out.println("usid"+us.getUserid());
		if (us != null) {
			int id = us.getUserid();
			List<ShopCat> shoplist = sl.getCat(id);
			for (ShopCat s : shoplist) {
				System.out.println(s.getCounts() + "-" + s.getSize() + "-" + s.getColor() + "-" + s.getShopid() + "-"
						+ s.getColor());
			}
			if (shoplist != null) {
			//循环购物车表数据放入map
				Map<String,ShopCat> shopmap = new HashMap<String, ShopCat>();
			    ShopCat sc=new ShopCat();
			  
			    int num=0;
				for (ShopCat a : shoplist) {
				     String shopid=String.valueOf(a.getShopid());
					 if (shopmap.containsKey(String.valueOf(a.getShopid()))) {
						 num=num+a.getCounts();
						Shop shop = sl.getShangping(a.getShopid());
						a.setShop(shop); 	
						a.setCounts(a.getCounts()+a.getCounts());
						shopmap.put(String.valueOf(a.getShopid()), a);
					} else {
						Shop shop = sl.getShangping(a.getShopid());
						a.setShop(shop);
						shopmap.put(String.valueOf(a.getShopid()),a);
					}
				}
				
				if(shopmap!=null&&shoplist.size()>0) {
					sum =sl.getsum(id);
				}else {
					sum=0;
				}
				smap=shopmap;
				res.getSession().setAttribute("shopmap", shopmap);
				res.getSession().setAttribute("sum", sum);
			}
       
		}
		md.setViewName("page/checkout");
		return md;

	}
	
    
	@RequestMapping("check")
	public String check(String param,HttpServletRequest res) {
		//将该用户购物车商品信息清除
		String orption=(String) res.getSession().getAttribute("orption");
		Orders order=new Orders();
		//Map<String, ShopCat> shopmap =  (Map<String, ShopCat>) res.getSession().getAttribute("shopmap");
		    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
	        Date date = new Date();
	        String str = simpleDateFormat.format(date);
	        int rannum = (int)((Math.random()*9+1)*100000);// 获取6位随机数
			if(orption==null) {
				if("true".equals(param)) {
					//支付成功 删除购物车相关商品 插入订单及订单详情
					order.setOrdertypeid(2);
					order.setOrderdate(new Date());
					order.setOrderprice(sum);
					order.setUserid(us.getUserid());
			        String ran=String.valueOf(rannum);
					order.setOrdernumber(str+ran);
					sl.addorders(order);
					int oid=sl.selectid();
		            for (String key : smap.keySet()) {
		            	Orderdetails o=new Orderdetails();
		            	o.setShopid(Integer.valueOf(key));
		            	o.setOrderid(oid);//
		            	Shop shop=sl.getShangping(Integer.valueOf(key));
		            	o.setShopprice(shop.getOriprice());
		            	o.setSale(smap.get(key).getCounts());
		            	o.setOrderdetailstypeid(4);
		            	sl.addorderdetails(o);
					}
				          }else {
			
					         //任务调度，24小时之内未付款取消该订单4
					         order.setOrdertypeid(1);
					order.setOrderdate(new Date());
					order.setOrderprice(100);
					order.setUserid(us.getUserid());
					String ran=String.valueOf(rannum);
				    order.setOrdernumber(str+ran);
					sl.addorders(order);
					 for (String key : smap.keySet()) {
			            	Orderdetails o=new Orderdetails();
			            	o.setShopid(Integer.valueOf(key));
			            	o.setOrderid(order.getOrderid());
			            	System.out.println(o.getOrderid());
			            	Shop shop=sl.getShangping(Integer.valueOf(key));
			            	o.setShopprice(shop.getOriprice());
			            	o.setSale(smap.get(key).getCounts());
			            	o.setOrderdetailstypeid(4);
			            	sl.addorderdetails(o);
						}
				}
				sl.clearShopCat(us.getUserid());
			}else if("wzf".equals(orption)) {
				if("true".equals(param)) {
					int oid= (int) res.getSession().getAttribute("orderid");
					//根据订单ID拿取订单 修改订单状态
					int ppid =sdao.updateordebyid(oid);
				}

			}
		
		return "gotopage/order";
		
	}
	
	@RequestMapping("zf")
	public String a() {
		return "page/demo";
		
	}

}
