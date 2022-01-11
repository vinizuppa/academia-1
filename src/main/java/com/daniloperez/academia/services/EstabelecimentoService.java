package com.daniloperez.academia.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.daniloperez.academia.domain.*;
import com.daniloperez.academia.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.daniloperez.academia.domain.enums.Perfil;
import com.daniloperez.academia.dto.EstabelecimentoDTO;
import com.daniloperez.academia.repositories.CategoriaRepository;
import com.daniloperez.academia.repositories.EstabelecimentoRepository;
import com.daniloperez.academia.repositories.PlanoRepository;
import com.daniloperez.academia.security.UserSS;
import com.daniloperez.academia.services.exceptions.AuthorizationException;
import com.daniloperez.academia.services.exceptions.DataIntegrityException;
import com.daniloperez.academia.services.exceptions.ObjectNotFoundException;

@Service
public class EstabelecimentoService {
	
	@Autowired
	private EstabelecimentoRepository repo;
	
	@Autowired
	private PlanoRepository planoRepo;
	
	@Autowired
	private CategoriaRepository categoriaRepo;

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private S3Service s3Service;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private ImageService imageService;
	
	@Value("${img.prefix.estabelecimento.profile}")
	private String prefix;
	
	@Value("${img.profile.size}")
	private Integer size;
	
	public Estabelecimento find(Integer id) {
		Optional<Estabelecimento> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Estabelecimento não encontrado! Id: " + id + ", Tipo: " + Estabelecimento.class.getName()));
	}
	

	public Estabelecimento update(Estabelecimento obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		
		try {
			repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível um estabelecimento que possui instrutores!");
		}		
	}
	
	public List<Estabelecimento> findAll() {
		return repo.findAll();
	}
	
	//Incluir Estabelecinento por DTO
		public Estabelecimento insert(EstabelecimentoDTO objDto) {
			
			Estabelecimento est = new Estabelecimento(null, objDto.getNome(), objDto.getEmail(), objDto.getRazao_social(), objDto.getCnpj(), objDto.getData_nasc(), objDto.getData_cad(), objDto.getSexo(), pe.encode(objDto.getSenha()));
			Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
			Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), est, cid);
			est.getEnderecos().add(end);
			Optional<Categoria> categoria = categoriaRepo.findById(objDto.getCategoriaId());
			est.setCategoria(categoria.get());
			Optional <Plano> plano = planoRepo.findById(objDto.getPlanoId());
			est.setPlano(plano.get());
			est.setData_cad(new Date());
			return repo.save(est);
		}
	
	
	//Função para enviar imagem do estabelecimento para o S3
		public URI uploadProfilePicture(MultipartFile multiPartFile) {
			UserSS user = UserService.authenticated();
			if (user == null) {
				throw new AuthorizationException("Acesso negado");
			}
			
			BufferedImage jpgImage = imageService.getJpgImageFromFile(multiPartFile);
			jpgImage = imageService.cropSquare(jpgImage);
			jpgImage = imageService.resize(jpgImage, size);
			String fileName = prefix + user.getId() + ".jpg";	
			return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
		}
		
		//Buscar Estabelecimento por Email
		public Estabelecimento findByEmail(String email) {
			UserSS user = UserService.authenticated();
			if(user==null || !user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername())) {
				throw new AuthorizationException("Acesso negado");
			}
			
			Estabelecimento obj = repo.findByEmail(email);
			if(obj == null) {
				throw new ObjectNotFoundException("Objeto não encontrado! Id: " + user.getId() + ", Tipo: " + Estabelecimento.class.getName());
			}
			return obj;
		}


		public List <Estabelecimento> findByPlano(Integer id) {
			Plano plano = planoRepo.findById(id).get();
			
			return repo.findByPlano(plano);
		}
}
