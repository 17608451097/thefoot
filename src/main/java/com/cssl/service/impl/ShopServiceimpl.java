package com.cssl.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cssl.dao.ShopDao;
import com.cssl.service.ShopService;

@Service
public class ShopServiceimpl implements ShopService {

	@Autowired
	public ShopDao sdao;
	
	@Override
	public List<Map<String, Object>> getShop() {
		
		return sdao.getShop();
	}

}
