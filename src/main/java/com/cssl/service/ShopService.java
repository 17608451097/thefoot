package com.cssl.service;

import java.util.List;
import java.util.Map;

import com.cssl.pojo.Shop;

public interface ShopService {

	public List<Map<String,Object>> getShop();
	
	public boolean addproduct(Shop shop);
	
	public boolean addshopstock(Shop shop);
	
	public int getaddshopid();
	
}
