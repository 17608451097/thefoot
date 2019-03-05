package com.cssl.pojo;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Orders {
	private int orderid;
	private int ordertypeid;
	private Date orderdate;
	private int orderprice;
	private int userid;
	
	private String ordernumber;
}
