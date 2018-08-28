package com.cssl.pojo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Orderdetails {
	private int detailsid ;
	private int orderid ;
	private int  shopid;
	private int  shopprice;
	private int sale;
	private int orderdetailstypeid;
}
