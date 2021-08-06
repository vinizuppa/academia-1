package com.daniloperez.academia;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.daniloperez.academia.domain.Aluno;
import com.daniloperez.academia.domain.Cidade;
import com.daniloperez.academia.domain.Endereco;
import com.daniloperez.academia.domain.Estado;
import com.daniloperez.academia.domain.Instrutor;
import com.daniloperez.academia.domain.enums.BioTipo;
import com.daniloperez.academia.repositories.AlunoRepository;
import com.daniloperez.academia.repositories.CidadeRepository;
import com.daniloperez.academia.repositories.EnderecoRepository;
import com.daniloperez.academia.repositories.EstadoRepository;
import com.daniloperez.academia.repositories.InstrutorRepository;

@SpringBootApplication
public class AcademiaApplication implements CommandLineRunner{
	@Autowired
	private AlunoRepository alunoRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private InstrutorRepository instrutorRepository;
	public static void main(String[] args) {
		SpringApplication.run(AcademiaApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		//Instanciando 2 estados
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
						
		//Instanciando 3 cidades
		Cidade c1 = new Cidade (null, "Uberlândia", est1);
		Cidade c2 = new Cidade (null, "São Paulo", est2);
		Cidade c3 = new Cidade (null, "Campinas", est2);
						
		//Referenciando quais estados estão referenciando as cidades.
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
				
		//Salvando Estados no banco
		estadoRepository.saveAll(Arrays.asList(est1, est2));
				
		//Salvando Cidades no banco
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");//Mascara de formatação para instanciar data
		//Instanciando 1 Aluno
		Aluno al1 = new Aluno(null, BioTipo.ECTOMORFO, "Vinicius Zuppa", "vcordeiro12@gmail.com", "47209082840", sdf.parse("15/02/2001"), sdf.parse("05/08/2021"), 'M', 80.55, 1.71, 100);
		al1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));//Instanciando 2 Telefones)
		
		//Instanciando 2 endereços
		Endereco e1 = new Endereco (null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", al1, c1);
		Endereco e2 = new Endereco (null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", al1, c2);
		
		//Indicando qual os endereços do aluno
		al1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		//Salvando Aluno no banco
		alunoRepository.saveAll(Arrays.asList(al1));
		
		//Salvando Endereco no banco
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		//Instanciando 1 Instrutor
		Instrutor is1 = new Instrutor(null, "Douglas Costa", "jaz@hotmail.com" , "11074798864", sdf.parse("15/05/2002"), sdf.parse("05/08/2021"), 'M', "44588");
		is1.getTelefones().addAll(Arrays.asList("996922381", "996011503"));
		
		//Instanciando Endereço
		Endereco e3 = new Endereco (null, "Rua Boituva", "128", "Próximo Rodoviária", "Vila Operária", "38220834", is1, c2);
		
		//Indicando qual os endereços do instrutor
		is1.getEnderecos().addAll(Arrays.asList(e3));
		//Salvando Aluno no banco
		instrutorRepository.saveAll(Arrays.asList(is1));
				
		//Salvando Endereco no banco
		enderecoRepository.saveAll(Arrays.asList(e3));
	}
}