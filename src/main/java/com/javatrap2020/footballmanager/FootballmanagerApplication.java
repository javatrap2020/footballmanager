package com.javatrap2020.footballmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@SpringBootApplication
public class FootballmanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FootballmanagerApplication.class, args);
	}

//	@Bean(name="entityManagerFactory")
//	public LocalSessionFactoryBean sessionFactory() {
//		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//		return sessionFactory;
//	}

}
