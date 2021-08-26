package com.daniloperez.academia.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.daniloperez.academia.services.DBservice;
import com.daniloperez.academia.services.EmailService;
import com.daniloperez.academia.services.SmtpEmailService;

@Configuration //Definindo que é um arquivo de configuração
@Profile("test")
public class TestConfig {
	@Autowired
	private DBservice dbService;
	
	@Bean
	public boolean instanciateDatabase() throws ParseException {
		dbService.instanciateTestDatabase();
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
}
