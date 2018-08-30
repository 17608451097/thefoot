package com.cssl.dao;

import java.util.List;
import java.util.Map;

import com.cssl.pojo.Shop;

public interface ShopDao {
	
	public List<Map<String,Object>> getShop();
	
	public int addproduct(Shop shop);
	
	public int addshopstock(String shopid,Shop shop);
	
	public int getaddshopid();

}
