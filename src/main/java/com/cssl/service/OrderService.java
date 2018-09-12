package com.cssl.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface OrderService {

	public List<Map<String,Object>> getOrder();
	
	public List<Map<String,Object>> getordertype();
	
	public boolean updateordertype(@Param("ordertypeid")String ordertypeid,@Param("orderid")String orderid);
	
	public boolean updatedetails(@Param("orderdetailstypeid")String orderdetailstypeid,@Param("orderid")String orderid);
	
	public boolean deldd(String orderid);
	
	public boolean deldddetalis(String orderid);

	public List<Map<String,Object>> getddxq(String orderid);
	
	public List<Map<String,Object>> getddtype();
	
	public boolean upddet(@Param("detailsid")String detailsid,@Param("orderdetailstypeid")String orderdetailstypeid);

	public int delxq(String detailsid);
}
