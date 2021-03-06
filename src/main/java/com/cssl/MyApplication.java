package com.cssl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan(basePackages="com.cssl.dao")
@EnableTransactionManagement
@ServletComponentScan
@SpringBootApplication
public class MyApplication  {

	public static void main(String[] args) {
		SpringApplication sc =  new SpringApplication(MyApplication.class);
		sc.run(args); 
	} 
	
}