package com.daniloperez.academia.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.daniloperez.academia.domain.Usuario;

public abstract class AbstractEmailService implements EmailService{
	@Value("${default.sender}")
	private String sender;
	
	
	@Override
	public void sendNewPasswordEmail(Usuario usuario, String newPass) {
			SimpleMailMessage sm = prepareNewPasswordEmail(usuario, newPass);
			sendEmail(sm);
	}

	protected SimpleMailMessage prepareNewPasswordEmail(Usuario usuario, String newPass) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(usuario.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Solicitação de nova Senha");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Nova senha: " + newPass);
		return sm;
	}
}
