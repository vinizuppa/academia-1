package com.daniloperez.academia.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.daniloperez.academia.domain.Usuario;
import com.daniloperez.academia.repositories.UsuarioRepository;
import com.daniloperez.academia.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;

	EmailService emailService;
	
	private Random rand = new Random();
	
	public void sendNewPassword(String email) {
		Usuario usuario = usuarioRepository.findByEmail(email);
		
		if(usuario==null) {
			throw new ObjectNotFoundException("Email não encontrado");
		}
		
		String newPass = newPassword();
		usuario.setSenha(pe.encode(newPass));
		
		usuarioRepository.save(usuario);
		emailService.sendNewPasswordEmail(usuario, newPass);
	}

	private String newPassword() { //Função que gera uma senha aleatório que contém digito ou letra.
		char[] vet = new char[10];
		for(int i=0; i<10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3);
		if(opt==0) {//gera um digito
			return (char) (rand.nextInt(10) + 48);//gera digito entre 0 e 9
		}
		
		else if(opt==1){//gera letra maiuscula
			return (char) (rand.nextInt(26) + 65);//gera uma letra maiuscula
		}
		
		else {//gera letra minuscula
			return (char) (rand.nextInt(26) + 97);
		}
	}
	
	
}
