package com.daniloperez.academia.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.daniloperez.academia.security.JWTAuthenticationFilter;
import com.daniloperez.academia.security.JWTAuthorizationFilter;
import com.daniloperez.academia.security.JWTUtil;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//Anotação para pode configurar para autorizar somente alguns perfis determinado end-point
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private Environment env;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	private static final String[] PUBLIC_MATCHERS = {//Vetor, para definir quais caminhos por padrão serão liberados de autenticação.
			"/h2-console/**"
	};
	
	private static final String[] PUBLIC_MATCHERS_GET = {//Definindo quais caminhos serão liberados somente para GET.
			"/categorias/**",
			"/atividades/**",
			"/estabelecimentos/**",
			"/estados/**",
			"/alunos/**",
			"/usuarios/**"
	};
	
	private static final String[] PUBLIC_MATCHERS_POST = {//Definindo quais end-points serão liberados para POST sem autenticação e autorização.
			"/alunos/**",
			"/instrutores/**",
			"/estabelecimentos/**",
			"/auth/forgot/**"
	};
	
	private static final String[] PUBLIC_MATCHERS_PUT = {//Definindo quais end-points serão liberados para POST sem autenticação e autorização.
			"/alunos/**"
	};
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		if(Arrays.asList(env.getActiveProfiles()).contains("test")) {//Liberando acesso para profile "test", para o banco h2 conseguir funcionar.
			http.headers().frameOptions().disable();
		}
		
		http.cors().and().csrf().disable();
		http.authorizeRequests()
		.antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()// Definindo que somente POST que estiverem em PUBLIC_MATCHERS_POST serão permitidos.
		.antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()// Definindo que somente GET que estiverem em PUBLIC_MATCHERS_GET serão permitidos.
		.antMatchers(HttpMethod.PUT, PUBLIC_MATCHERS_PUT).permitAll()// Definindo que somente GET que estiverem em PUBLIC_MATCHERS_GET serão permitidos.
		.antMatchers(PUBLIC_MATCHERS).permitAll()
		.anyRequest().authenticated(); // Definindo que todos caminhos que estiverem em PUBLIC_MATCHERS serão permitidos.
		http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));//Filtro de autenticação
		http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService));//Filtro de autorização
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//Definindo para BackEnd não criar sessões de usuário.
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
		configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
