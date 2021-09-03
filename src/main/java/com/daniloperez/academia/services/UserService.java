package com.daniloperez.academia.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.daniloperez.academia.domain.Usuario;
import com.daniloperez.academia.domain.enums.Perfil;
import com.daniloperez.academia.repositories.UsuarioRepository;
import com.daniloperez.academia.security.UserSS;
import com.daniloperez.academia.services.exceptions.AuthorizationException;
import com.daniloperez.academia.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {
	@Autowired
	private UsuarioRepository repo;
	
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch(Exception e){
			return null;
		}
	}
	
	//Buscar Usuario por Email
	public Usuario findByEmail(String email) {
		UserSS user = UserService.authenticated();
		if(user==null || !user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername())) {
			throw new AuthorizationException("Acesso negado");
		}
			
		Usuario obj = repo.findByEmail(email);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + user.getId() + ", Tipo: " + Usuario.class.getName());
		}
		return (Usuario) obj.getPerfis();
	}
}
