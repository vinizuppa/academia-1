package com.daniloperez.academia;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.daniloperez.academia.domain.Aluno;
import com.daniloperez.academia.domain.Categoria;
import com.daniloperez.academia.domain.Cidade;
import com.daniloperez.academia.domain.Endereco;
import com.daniloperez.academia.domain.EnderecoEstabelecimento;
import com.daniloperez.academia.domain.Estabelecimento;
import com.daniloperez.academia.domain.Estado;
import com.daniloperez.academia.domain.Instrutor;
import com.daniloperez.academia.domain.enums.BioTipo;
import com.daniloperez.academia.repositories.AlunoRepository;
import com.daniloperez.academia.repositories.CategoriaRepository;
import com.daniloperez.academia.repositories.CidadeRepository;
import com.daniloperez.academia.repositories.EnderecoEstabelecimentoRepository;
import com.daniloperez.academia.repositories.EnderecoRepository;
import com.daniloperez.academia.repositories.EstabelecimentoRepository;
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
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private EstabelecimentoRepository estabelecimentoRepository;
	@Autowired
	private EnderecoEstabelecimentoRepository enderecoEstabelecimentoRepository;
	
	
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
		
		// Instanciado 3 Categorias
		Categoria cat1 = new Categoria(null, "Musculação");
		Categoria cat2 = new Categoria(null, "Dança");
		Categoria cat3 = new Categoria(null, "Crossfit");
		
		// Instanciando 3 Endereços de Estabelecimentos
		EnderecoEstabelecimento endEstab1 = new EnderecoEstabelecimento (null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", c1);
		EnderecoEstabelecimento endEstab2 = new EnderecoEstabelecimento (null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", c2);
		EnderecoEstabelecimento endEstab3 = new EnderecoEstabelecimento (null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", c3);
		
		// Salvando Endereços dos Estabelecimentos no banco
		enderecoEstabelecimentoRepository.saveAll(Arrays.asList(endEstab1, endEstab2, endEstab3));
				
		// Instancianto 3 Estabelecimentos
		Estabelecimento estab1 = new Estabelecimento(null, "CrossGym", "Academia CrossGym S.A.", "73205304000169", sdf.parse("15/05/2002"), sdf.parse("11/07/2021"), endEstab1);
		estab1.getTelefones().addAll(Arrays.asList("14665239520", "18569253462"));
		
		Estabelecimento estab2 = new Estabelecimento(null, "HitDance", "Academia de dança de Ourinhos LTDA", "08002666000190", sdf.parse("20/08/2015"), sdf.parse("10/08/2021"), endEstab2);
		estab2.getTelefones().addAll(Arrays.asList("14995642031", "14997652380"));
		
		Estabelecimento estab3 = new Estabelecimento(null, "Monsters Gym", "Monsters Gym Academia S.A.", "45809031000126", sdf.parse("31/05/1996"), sdf.parse("10/08/2021"), endEstab3);
		estab3.getTelefones().addAll(Arrays.asList("11884625310", "2155926644"));
		
		//Instanciando 3 Instrutores
		Instrutor is1 = new Instrutor(null, "Douglas Costa", "jaz@hotmail.com" , "11074798864", sdf.parse("15/05/2002"), sdf.parse("05/08/2021"), 'M', "44588");
		is1.getTelefones().addAll(Arrays.asList("996922381", "996011503"));
		
		Instrutor is2 = new Instrutor(null, "João da Silva", "joão@hotmail.com" , "52768915084", sdf.parse("08/09/1984"), sdf.parse("05/08/2021"), 'F', "88544");
		is2.getTelefones().addAll(Arrays.asList("998154625", "998651520"));
		
		Instrutor is3 = new Instrutor(null, "Pedro Santos", "pedro@hotmail.com" , "69278608009", sdf.parse("26/12/1991"), sdf.parse("05/08/2021"), 'M', "54488");
		is3.getTelefones().addAll(Arrays.asList("995153526", "995451585"));
		
		//Instanciando Endereço
		Endereco e3 = new Endereco (null, "Rua Boituva", "128", "Próximo Rodoviária", "Vila Operária", "38220834", is1, c2);
		Endereco e4 = new Endereco (null, "Rua Juquiá", "560", "Ao lado da caixa d'água da Vila Olinda", "Vila Olinda", "18950250", is2, c1);
		Endereco e5 = new Endereco (null, "Av. Samadelo", "905", "Próximo creche Profª Ana Marcolino", "Jardim Florido", "19750230", is3, c3);
		
		//Indicando quais os endereços dos instrutores
		is1.getEnderecos().addAll(Arrays.asList(e3));
		is2.getEnderecos().addAll(Arrays.asList(e4));
		is3.getEnderecos().addAll(Arrays.asList(e5));
		
		// Criando associação de Instrutores com Estabelecimentos
		is1.getEstabelecimentos().addAll(Arrays.asList(estab1, estab2));
		is2.getEstabelecimentos().addAll(Arrays.asList(estab2));
		is3.getEstabelecimentos().addAll(Arrays.asList(estab1, estab3));
		
		// Criando associação de Estabeleciemntos com Instrutores
		estab1.getInstrutores().addAll(Arrays.asList(is1, is3));
		estab2.getInstrutores().addAll(Arrays.asList(is1, is2));
		estab3.getInstrutores().addAll(Arrays.asList(is3));
		
		//Salvando Instrutor no banco
		instrutorRepository.saveAll(Arrays.asList(is1, is2, is3));
				
		//Salvando Endereco no banco
		enderecoRepository.saveAll(Arrays.asList(e3, e4, e5));		
		
		// Criando associação de Categorias com Estabelecimentos
		cat1.getEstabelecimentos().addAll(Arrays.asList(estab3));
		cat2.getEstabelecimentos().addAll(Arrays.asList(estab2));
		cat3.getEstabelecimentos().addAll(Arrays.asList(estab1, estab3));
		
		// Criando associação de Estabelecimentos com Categorias
		estab1.getCategorias().addAll(Arrays.asList(cat3));
		estab2.getCategorias().addAll(Arrays.asList(cat2));
		estab3.getCategorias().addAll(Arrays.asList(cat1, cat3));
		
		// Salvando Categorias no banco
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		
		// Salvando Estabelecientos no banco
		estabelecimentoRepository.saveAll(Arrays.asList(estab1, estab2, estab3));
	}
}