package com.cssl.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ShowService {
	public List<Map<String,Object>> getType();
	
	public List<Map<String,Object>> getSonType();
	
	public List<Map<String,Object>> getGrsontype();
	
	public List<Map<String,Object>> getShopOne_Three();
	
	public List<Map<String,Object>> getShopThree_Six();
	
	public List<Map<String,Object>> getShopAll(int id);
	
	public List<Map<String,Object>> getShopsizename(int id);
	
	public List<Map<String,Object>> getShopImg();
	
	public List<Map<String,Object>> getImgColor(int id);
}
