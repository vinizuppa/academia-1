package com.daniloperez.academia.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.daniloperez.academia.dto.EmailDTO;
import com.daniloperez.academia.security.JWTUtil;
import com.daniloperez.academia.security.UserSS;
import com.daniloperez.academia.services.AuthService;
import com.daniloperez.academia.services.UserService;

@RestController
@RequestMapping(value="/auth")
public class AuthResource {
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthService service;
	
	@RequestMapping(value="/refresh_token", method=RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {//Gera novo token para o usuário logado
		UserSS user = UserService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value="/forgot", method=RequestMethod.POST)
	public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO objDto) {//Gera nova senha
		service.sendNewPassword(objDto.getEmail());
		return ResponseEntity.noContent().build();
	}
}
