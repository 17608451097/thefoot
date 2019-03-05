package com.cssl.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopCat {

	
	private int counts;//购物车内商品的数量
	private int shopid;
	private int price;
	private int size;
	private int color;
	private int s_cat_id;

	private int userid;
	private double money;//购物车总价
	private Shop shop;
	
	public ShopCat(int counts, Shop shop) {
		super();
		this.counts = counts;
		this.shop = shop;
	}

	public ShopCat() {
		super();
	}
	
	
}
