package com.cssl.service;

import java.util.List;
import java.util.Map;

public interface ShowService {
	public List<Map<String,Object>> getType();
	
	public List<Map<String,Object>> getSonType();
	
	public List<Map<String,Object>> getGrsontype();
	
	public List<Map<String,Object>> getShopOne_Three();
	
	public List<Map<String,Object>> getShopThree_Six();
}
