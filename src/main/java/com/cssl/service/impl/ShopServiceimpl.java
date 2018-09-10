package com.cssl.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cssl.dao.ShopDao;
import com.cssl.pojo.Shop;
import com.cssl.service.ShopService;

@Service
public class ShopServiceimpl implements ShopService {

	@Autowired
	public ShopDao sdao;
	
	@Override
	public List<Map<String, Object>> getShop() {
		
		return sdao.getShop();
	}

	@Override
	public boolean addproduct(Shop shop) {
		if(sdao.addproduct(shop)>0)
			return true;
		return false;
	}

	@Override
	public boolean addshopstock(Shop shop) {
		if(sdao.addshopstock(shop)>0)
			return true;
		return false;
	}

	@Override
	public int getaddshopid() {
		
		return sdao.getaddshopid();
	}

	@Override
	public List<Map<String, Object>> getbyidshop(String shopid) {
		
		return sdao.getbyidshop(shopid);
	}

	@Override
	public int updateshop(Shop shop) {
		
		return sdao.updateshop(shop);
	}

	@Override
	public int delshop(String shopid) {
		// TODO Auto-generated method stub
		return sdao.delshop(shopid);
	}

	@Override
	public int delstock(String shopid) {
		// TODO Auto-generated method stub
		return sdao.delstock(shopid);
	}
 
	@Override
	public List<Map<String, Object>> getshop(String shopid) {
		// TODO Auto-generated method stub
		return sdao.getshop(shopid);
	}

	@Override
	public List<Map<String, Object>> selectshopall(String grsonid, String price, String orde) {
		return sdao.selectshopall(grsonid, price, orde);
	}

	@Override
	public List<Map<String, Object>> selectgrson() {
		return sdao.selectgrson();
	}

}
