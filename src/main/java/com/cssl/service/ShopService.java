package com.cssl.service;

import java.util.List;
import java.util.Map;

import com.cssl.pojo.Shop;

public interface ShopService {

	public List<Map<String,Object>> getShop();
	
	public boolean addproduct(Shop shop);
	
	public boolean addshopstock(Shop shop);
	
	public int getaddshopid();
	
	public List<Map<String,Object>> getbyidshop(String shopid);

	public int updateshop(Shop shop);

	public int delshop(String shopid);

	public int delstock(String shopid);

	public List<Map<String,Object>> getshop(String shopid);

	//shop前台页面查询
	public List<Map<String,Object>> selectshopall(String grsonid,String price,String orde);
	
	public List<Map<String,Object>> selectgrson();
}
