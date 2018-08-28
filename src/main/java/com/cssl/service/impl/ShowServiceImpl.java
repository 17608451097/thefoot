package com.cssl.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cssl.dao.ShowDao;
import com.cssl.service.ShowService;

@Service
public class ShowServiceImpl implements ShowService {

	@Autowired
	public ShowDao sdao;
	
	@Override
	public List<Map<String, Object>> getType() {
		return sdao.getType();
	}

}
