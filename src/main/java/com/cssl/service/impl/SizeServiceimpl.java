package com.cssl.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cssl.dao.SizeDao;
import com.cssl.service.SizeService;

@Service
public class SizeServiceimpl implements SizeService {
	
	@Autowired
	private SizeDao sdao;

	@Override
	public List<Map<String, Object>> getsize() {
		
		return sdao.getsize();
	}

}
