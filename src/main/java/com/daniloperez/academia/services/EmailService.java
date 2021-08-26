package com.daniloperez.academia.services;


import org.springframework.mail.SimpleMailMessage;

import com.daniloperez.academia.domain.Usuario;

public interface EmailService {
	void sendEmail(SimpleMailMessage msg);
	
	void sendNewPasswordEmail(Usuario usuario, String newPass);
}
