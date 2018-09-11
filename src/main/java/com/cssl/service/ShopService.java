package com.cssl.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cssl.pojo.Shop;
import com.cssl.pojo.Shopstock;

public interface ShopService {

	public List<Map<String,Object>> getShop(String shopname);
	
	public boolean addproduct(Shop shop);
	
	public boolean addshopstock(Shop shop);
	
	public int getaddshopid();
	
	public List<Map<String,Object>> getbyidshop(String shopid);

	public int updateshop(Shop shop);

	public int delshop(String shopid);

	public int delstock(String shopid);

	public List<Map<String,Object>> getshop(String shopid);
	
	public int getshopstock(String shopid);

	public int addkc(Shopstock stock);

	public int updshop(@Param("stock")String stock,@Param("shopid")String shopid);

	public int updshopkc(Shopstock stock);

	public List<Shopstock> updhx(String sid);
	
	public int delkc(String sid);
}
