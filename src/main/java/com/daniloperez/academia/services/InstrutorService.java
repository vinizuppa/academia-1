package com.daniloperez.academia.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.daniloperez.academia.domain.Cidade;
import com.daniloperez.academia.domain.Endereco;
import com.daniloperez.academia.domain.Instrutor;
import com.daniloperez.academia.domain.enums.Perfil;
import com.daniloperez.academia.dto.InstrutorDTO;
import com.daniloperez.academia.dto.InstrutorNewDTO;
import com.daniloperez.academia.repositories.EnderecoRepository;
import com.daniloperez.academia.repositories.InstrutorRepository;
import com.daniloperez.academia.security.UserSS;
import com.daniloperez.academia.services.exceptions.AuthorizationException;
import com.daniloperez.academia.services.exceptions.DataIntegrityException;
import com.daniloperez.academia.services.exceptions.ObjectNotFoundException;

//@Service é a anotação para indicar que esse é um service
@Service
public class InstrutorService {
	@Autowired
	private InstrutorRepository repo;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private S3Service s3Service;
	
	@Autowired
	private ImageService imageService;
	
	@Value("${img.prefix.instrutor.profile}")
	private String prefix;
	
	//Buscar Instrutor por ID
	public Instrutor find(Integer id) {
		UserSS user = UserService.authenticated();
		if(user==null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Optional<Instrutor> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Instrutor não encontrado! Id: " + id + ", Tipo: " + Instrutor.class.getName()));
	}
	
	//Buscar todos Alunos
	public List<Instrutor> findAll(){
		return repo.findAll();
	}
	
	//Incluir aluno
	@Transactional
	public Instrutor insert(Instrutor obj) {
		obj.setId(null);
		obj.setData_cad(new Date());
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}
	
	//Incluir Instrutor por DTO
	public Instrutor fromDTO(InstrutorNewDTO objDto) {
		Instrutor ins = new Instrutor(null,objDto.getNome(), objDto.getEmail(), objDto.getCpf(), objDto.getData_nasc(), objDto.getData_cad(), objDto.getSexo(), objDto.getNumCrf(), pe.encode(objDto.getSenha()));
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), ins, cid);
		ins.getEnderecos().add(end);
		ins.getTelefones().add(objDto.getTelefone1());
			
		if(objDto.getTelefone2()!=null) {
			ins.getTelefones().add(objDto.getTelefone2());
		}
			
		return ins;
	}
		
	//Excluir Instrutor
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e){
			throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionados");
		}
	}	
	
	//Alterar Aluno
		public Instrutor update(Instrutor obj) {
			Instrutor newObj = find(obj.getId());
			updateData(newObj, obj);
			return repo.save(newObj);	
		}
		
		private void updateData(Instrutor newObj, Instrutor obj) {//função auxiliar para atualizar o instrutor e manter dados que não foram modificados.
			newObj.setNome(obj.getNome());
			newObj.setEmail(obj.getEmail());
			newObj.setSexo(obj.getSexo());
			newObj.setNumCrf(obj.getNumCrf());
			newObj.setSenha(obj.getSenha());
		}
		
		public Instrutor fromDTO(InstrutorDTO objDto) {
			 Instrutor inst = new Instrutor(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null, null, objDto.getSexo(), objDto.getNumCrf(), null);
			 return inst;
		}
		
		public URI uploadProfilePicture(MultipartFile multiPartFile) {//Função para enviar imagem do instrutor para o S3
			UserSS user = UserService.authenticated();
			if (user == null) {
				throw new AuthorizationException("Acesso negado");
			}
			
			BufferedImage jpgImage = imageService.getJpgImageFromFile(multiPartFile);
			String fileName = prefix + user.getId() + ".jpg";	
			return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
		}
}
