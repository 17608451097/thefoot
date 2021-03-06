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

	@Override
	public List<Map<String, Object>> getSonType() {
		return sdao.getSonType();
	}

	@Override
	public List<Map<String, Object>> getGrsontype() {
		return sdao.getGrsontype();
	}

	@Override
	public List<Map<String, Object>> getShopOne_Three() {
		return sdao.getShopOne_Three();
	}

	@Override
	public List<Map<String, Object>> getShopThree_Six() {
		return sdao.getShopThree_Six();
	}

	@Override
	public List<Map<String, Object>> getShopAll(int id) {
		return sdao.getShopAll(id);
	}

	@Override
	public List<Map<String, Object>> getShopsizename(int id) {
		return sdao.getShopsizename(id);
	}

	@Override
	public List<Map<String, Object>> getShopImg() {
		return sdao.getShopImg();
	}

	@Override
	public List<Map<String, Object>> getImgColor(int id) {
		return sdao.getImgColor(id);
	}

}
