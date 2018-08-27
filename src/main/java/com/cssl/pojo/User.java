package com.cssl.pojo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

	private int userid;
	private String name;
	private String companyname;
	private String password;
	private String email;
	private String address;
	private String phone;
	private Date datetime;
	private int countryid;
	private int isAdmin;
	private String userfile;
	
}
