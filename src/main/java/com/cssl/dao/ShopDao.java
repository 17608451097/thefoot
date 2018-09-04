package com.cssl.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cssl.pojo.Shop;

public interface ShopDao {
	
	public List<Map<String,Object>> getShop();
	
	public int addproduct(Shop shop);
	
	public int addshopstock(Shop shop);
	
	public int getaddshopid();
	
	public List<Map<String,Object>> getbyidshop(String shopid);
	
	public int updateshop(Shop shop);

	public int delshop(String shopid);
	
	public int delstock(String shopid);
	
	public List<Map<String,Object>> getshop(String shopid);
}
