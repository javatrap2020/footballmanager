package com.javatrap2020.footballmanager.controller;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
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
