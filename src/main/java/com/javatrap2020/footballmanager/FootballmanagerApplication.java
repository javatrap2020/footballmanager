package com.javatrap2020.footballmanager;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class FootballmanagerApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(FootballmanagerApplication.class, args);
	}

	@Override
	public void run(String... args) {

	}

//	@Bean(name="entityManagerFactory")
//	public LocalSessionFactoryBean sessionFactory() {
//		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//		return sessionFactory;
//	}

}
