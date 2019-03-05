package com.cssl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cssl.dao.ShopCatDao;
import com.cssl.pojo.Orderdetails;
import com.cssl.pojo.Orders;
import com.cssl.pojo.Shop;
import com.cssl.pojo.ShopCat;
import com.cssl.service.ShopCatService;

@Service
public class ShopCatServiceImpl implements ShopCatService {

	@Autowired
	private ShopCatDao sd;
	
	@Override
	public Shop getShangping(int shopid) {
		
		return sd.getShangping(shopid);
	}

	@Override
	public int addShopCat(ShopCat sc) {
		
		return sd.addShopCat(sc);
	}

	@Override
	public int updateShopCat(ShopCat sc) {
		
		return sd.updateShopCat(sc);
	}

	@Override
	public int delShopCat(int shopcatid) {
		
		return sd.delShopCat(shopcatid);
	}

	@Override
	public List<ShopCat> getCat(int userid) {
		
		return sd.getCat(userid);
	}

	@Override
	public int getsum(int userid) {
		
		return sd.getsum(userid);
	}

	@Override
	public int addorders(Orders order) {
		
		return sd.addorders(order);
	}

	@Override
	public int addorderdetails(Orderdetails orderdetails) {
		return sd.addorderdetails(orderdetails);
	}

	@Override
	public int clearShopCat(int userid) {
		return sd.clearShopCat(userid);
	}

	@Override
	public int selectid() {
		return sd.selectid();
	}

	@Override
	public int getcount(int shopid,int userid) {
		
		return sd.getcount(shopid, userid);
	}

}
