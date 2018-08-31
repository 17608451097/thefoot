package com.cssl.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cssl.dao.TypeDao;
import com.cssl.service.TypeService;

@Service
public class TypeServiceimpl implements TypeService {
	
	@Autowired
	private TypeDao tdao;

	@Override
	public List<Map<String, Object>> gettype() {
		
		return tdao.gettype();
	}

}
