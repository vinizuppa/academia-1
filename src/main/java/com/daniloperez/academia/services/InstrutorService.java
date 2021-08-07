package com.daniloperez.academia.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daniloperez.academia.domain.Instrutor;
import com.daniloperez.academia.domain.Cidade;
import com.daniloperez.academia.domain.Endereco;
import com.daniloperez.academia.dto.InstrutorNewDTO;
import com.daniloperez.academia.repositories.EnderecoRepository;
import com.daniloperez.academia.repositories.InstrutorRepository;
import com.daniloperez.academia.services.exceptions.DataIntegrityException;
import com.daniloperez.academia.services.exceptions.ObjectNotFoundException;

//@Service é a anotação para indicar que esse é um service
@Service
public class InstrutorService {
	@Autowired
	private InstrutorRepository repo;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	//Buscar Instrutor por ID
	public Instrutor find(Integer id) {
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
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}
	
	//Incluir Instrutor por DTO
	public Instrutor fromDTO(InstrutorNewDTO objDto) {
		Instrutor ins = new Instrutor(null,objDto.getNome(), objDto.getEmail(), objDto.getCpf(), objDto.getData_nasc(), objDto.getData_cad(), objDto.getSexo(), objDto.getNumCrf());
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), ins, cid);
		ins.getEnderecos().add(end);
		ins.getTelefones().add(objDto.getTelefone1());
			
		if(objDto.getTelefone2()!=null) {
			ins.getTelefones().add(objDto.getTelefone2());
		}
			
		if(objDto.getTelefone3()!=null) {
			ins.getTelefones().add(objDto.getTelefone3());
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
}
