package com.daniloperez.academia.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.daniloperez.academia.services.DBservice;

@Configuration //Definindo que é um arquivo de configuração
@Profile("dev")
public class DevConfig {
	@Autowired
	private DBservice dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Bean
	public boolean instanciateDatabase() throws ParseException {
		if (!"create".equals(strategy)) {
			return false;
		}
		
		dbService.instanciateTestDatabase();
		return true;
	}
}
