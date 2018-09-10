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

	@Override
	public List<Map<String, Object>> getordertype() {
		// TODO Auto-generated method stub
		return odao.getordertype();
	}

	@Override
	public boolean updateordertype(String ordertypeid, String orderid) {
		if(odao.updateordertype(ordertypeid, orderid)>0)
			return true;
		return false;
	}

	@Override
	public boolean updatedetails(String orderdetailstypeid, String orderid) {
		if(odao.updatedetails(orderdetailstypeid, orderid)>0)
			return true;
		return false;
	}

	@Override
	public boolean deldd(String orderid) {
		if(odao.deldd(orderid)>0)
			return true;
		return false;
	}

	@Override
	public boolean deldddetalis(String orderid) {
		if(odao.deldddetalis(orderid)>0)
			return true;
		return false;
	}

	@Override
	public List<Map<String, Object>> getddxq(String orderid) {
		// TODO Auto-generated method stub
		return odao.getddxq(orderid);
	}

	@Override
	public List<Map<String, Object>> getddtype() {
		// TODO Auto-generated method stub
		return odao.getddtype();
	}

	@Override
	public boolean upddet(String detailsid, String orderdetailstypeid) {
		if(odao.upddet(detailsid, orderdetailstypeid)>0)
			return true;
		return false;
	}

	@Override
	public int delxq(String detailsid) {
		// TODO Auto-generated method stub
		return odao.delxq(detailsid);
	}

}
