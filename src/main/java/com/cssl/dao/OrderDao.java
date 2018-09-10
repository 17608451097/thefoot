package com.cssl.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface OrderDao {
	
	public List<Map<String,Object>> getOrder();
	
	public List<Map<String,Object>> getordertype();
	
	public int updateordertype(@Param("ordertypeid")String ordertypeid,@Param("orderid")String orderid);

	public int updatedetails(@Param("orderdetailstypeid")String orderdetailstypeid,@Param("orderid")String orderid);
	
	public int deldd(String orderid);
	
	public int deldddetalis(String orderid);
	
	public List<Map<String,Object>> getddxq(String orderid);
	
	public List<Map<String,Object>> getddtype();
	
	public int upddet(@Param("detailsid")String detailsid,@Param("orderdetailstypeid")String orderdetailstypeid);
	
	public int delxq(String detailsid);
}
