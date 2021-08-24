package com.daniloperez.academia.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.daniloperez.academia.domain.Usuario;
import com.daniloperez.academia.repositories.UsuarioRepository;
import com.daniloperez.academia.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario us = usuarioRepo.findByEmail(email);
		if(us==null) {
			throw new UsernameNotFoundException(email);
		}
		
		return new UserSS(us.getId(), us.getEmail(), us.getSenha(), us.getPerfis());
		
	}

}
