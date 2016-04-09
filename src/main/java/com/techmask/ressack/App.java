package com.techmask.ressack;

import java.security.Principal;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@MapperScan("com.techmask.ressack.*.repository")
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@RequestMapping("/currentuser")
	public Principal user(Principal user) {
		return user;
	} 
	
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
	   SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
	   sqlSessionFactoryBean.setDataSource(dataSource);



	   return sqlSessionFactoryBean.getObject();
	}
}

