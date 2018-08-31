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

}
