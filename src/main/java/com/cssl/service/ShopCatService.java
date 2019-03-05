package com.cssl.service;

import java.util.List;
import java.util.Map;

import com.cssl.pojo.Orderdetails;
import com.cssl.pojo.Orders;
import com.cssl.pojo.Shop;
import com.cssl.pojo.ShopCat;

public interface ShopCatService {

	
	public Shop getShangping(int shopid);
	
	public int addShopCat(ShopCat sc); 
	
	public int updateShopCat(ShopCat sc);
	
	public int delShopCat(int shopcatid);
	
	public List<ShopCat> getCat(int userid);
	
	public int getsum(int shopcatid);
	
	public int clearShopCat(int userid);

	public int selectid();

	public int getcount(int shopid,int userid);

	
    public int addorders(Orders order);
	
	public int addorderdetails(Orderdetails orderdetails);
}
