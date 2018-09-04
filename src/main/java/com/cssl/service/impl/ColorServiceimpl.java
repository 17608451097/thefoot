package com.cssl.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cssl.dao.ColorDao;
import com.cssl.service.ColorService;

@Service
public class ColorServiceimpl implements ColorService {

	@Autowired
	private ColorDao cdao;
	
	@Override
	public List<Map<String, Object>> getcolor() {
		
		return cdao.getcolor();
	}

}
