package com.cssl.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cssl.dao.OrderDao;
import com.cssl.service.OrderService;

@Service
public class OrderServiceimpl implements OrderService {
	
	@Autowired
	private OrderDao odao;

	@Override
	public List<Map<String, Object>> getOrder() {
		
		return odao.getOrder();
	}

}
