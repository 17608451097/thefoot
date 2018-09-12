package com.cssl;

import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.github.pagehelper.PageHelper;

import java.util.Properties;

import javax.sql.DataSource;

/**
 *  Created by pact on 2018/1/24
 */

@Configuration
public class MyBatisConfig {

    /**
<<<<<<< HEAD
     *  第三种方式 代码中指定xml和实体类的对应关系
    */

    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "true");
        pageHelper.setProperties(p);
        return pageHelper;
    }

}
