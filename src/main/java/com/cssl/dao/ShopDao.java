package com.cssl.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cssl.pojo.Shop;
import com.cssl.pojo.Shopstock;

public interface ShopDao {
	
	public List<Map<String,Object>> getShop(@Param("shopname")String shopname);
	
	public int addproduct(Shop shop);
	
	public int addshopstock(Shop shop);
	
	public int getaddshopid();
	
	public List<Map<String,Object>> getbyidshop(String shopid);
	
	public int updateshop(Shop shop);

	public int delshop(String shopid);
	
	public int delstock(String shopid);
	
	public List<Map<String,Object>> getshop(String shopid);
	
	public int addimg(@Param("colorid")String colorid,@Param("shopid")String shopid,@Param("filename")String filename);

	//shop前台页面查询
	public List<Map<String,Object>> selectshopall(@Param("grsonid") String grsonid,@Param("price")String price,@Param("orde")String orde);
	
	public List<Map<String,Object>> selectgrson();

	public int getshopstock(String shopid);
	
	public int addkc(Shopstock stock);
	
	public int updshop(@Param("stock")String stock,@Param("shopid")String shopid);
	
	public int updshopkc(Shopstock stock);
	
	public List<Shopstock> updhx(String sid);

	public int delkc(String sid);
	
	public int delshopimg(String s_sid);
	
	public List<Map<String, Object>>selectorde(@Param("typeid")int typeid,@Param("userid")int userid);
	
	public int updateordetype(@Param("oid")int oid,@Param("orderid")int orderid);
	
	public List<Map<String, Object>>selectordes();
	
	public int deleteordebyid(int oid);
	
	public int deleteordesbyid(int oid);
	public List<Map<String, Object>>selectdhlall();
	
	public int updateordebyid(@Param("ordeid")int ordeid);
}
