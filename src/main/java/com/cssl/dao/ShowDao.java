package com.cssl.dao;

import java.util.List;
import java.util.Map;

public interface ShowDao {
	public List<Map<String,Object>> getType();
	
	public List<Map<String,Object>> getSonType();
	
	public List<Map<String,Object>> getGrsontype();
}
