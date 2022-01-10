package com.daniloperez.academia.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.daniloperez.academia.domain.Aluno;
import com.daniloperez.academia.domain.Atividade;
import com.daniloperez.academia.domain.Categoria;
import com.daniloperez.academia.domain.Cidade;
import com.daniloperez.academia.domain.Endereco;
import com.daniloperez.academia.domain.Estabelecimento;
import com.daniloperez.academia.domain.Estado;
import com.daniloperez.academia.domain.Instrutor;
import com.daniloperez.academia.domain.Matricula;
import com.daniloperez.academia.domain.Plano;
import com.daniloperez.academia.domain.enums.BioTipo;
import com.daniloperez.academia.domain.enums.Perfil;
import com.daniloperez.academia.repositories.AlunoRepository;
import com.daniloperez.academia.repositories.AtividadeRepository;
import com.daniloperez.academia.repositories.CategoriaRepository;
import com.daniloperez.academia.repositories.CidadeRepository;
import com.daniloperez.academia.repositories.EnderecoRepository;
import com.daniloperez.academia.repositories.EstabelecimentoRepository;
import com.daniloperez.academia.repositories.EstadoRepository;
import com.daniloperez.academia.repositories.InstrutorRepository;
import com.daniloperez.academia.repositories.MatriculaRepository;
import com.daniloperez.academia.repositories.PlanoRepository;

@Service
public class DBservice {
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
	AtividadeRepository atividadeRepository;
	@Autowired
	private PlanoRepository planoRepository;
	@Autowired
	private MatriculaRepository matriculaRepository;
	@Autowired
	private BCryptPasswordEncoder pe;
	
	 public void instanciateTestDatabase() throws ParseException {
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
				
				//Instanciando 3 Alunos
				Aluno al1 = new Aluno(null, BioTipo.ECTOMORFO, "Vinicius Zuppa", "vcordeiro12@gmail.com", "47209082840", sdf.parse("15/02/2001"), sdf.parse("05/08/2021"), 'M', 80.55, 1.71, 100, pe.encode("senhateste"));
				al1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));//Instanciando 2 Telefones)
				al1.addPerfil(Perfil.ALUNO);
				
				Aluno al2 = new Aluno(null, BioTipo.ECTOMORFO, "Danilo Perez", "daniloperez@gmail.com", "56258756866", sdf.parse("15/02/2001"), sdf.parse("05/08/2021"), 'M', 80.55, 1.71, 100, pe.encode("senhateste"));
				al2.getTelefones().addAll(Arrays.asList("72623332", "3938393"));//Instanciando 2 Telefones)
				al2.addPerfil(Perfil.ALUNO);
				
				Aluno al3 = new Aluno(null, BioTipo.ECTOMORFO, "Renan Ciciliato", "renanzin@gmail.com", "56872345996", sdf.parse("15/02/2001"), sdf.parse("05/08/2021"), 'M', 80.55, 1.71, 100, pe.encode("senhateste"));
				al3.getTelefones().addAll(Arrays.asList("35715964", "35715982"));//Instanciando 2 Telefones)
				al3.addPerfil(Perfil.ALUNO);
				
				//Instanciando 2 endereços
				Endereco e1 = new Endereco (null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", al1, c1);
				Endereco e2 = new Endereco (null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", al1, c2);
				
				//Indicando qual os endereços do aluno
				al1.getEnderecos().addAll(Arrays.asList(e1, e2));
				al2.getEnderecos().addAll(Arrays.asList(e1));
				al3.getEnderecos().addAll(Arrays.asList(e2));
				
				//Salvando Aluno no banco
				alunoRepository.saveAll(Arrays.asList(al1, al2, al3));
				
				//Salvando Endereco no banco
				enderecoRepository.saveAll(Arrays.asList(e1,e2));
				
				// Instanciado 3 Categorias
				Categoria cat1 = new Categoria(null, "Musculação");
				Categoria cat2 = new Categoria(null, "Dança");
				Categoria cat3 = new Categoria(null, "Crossfit");
				
				// Salvando Categorias no banco
				categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
				
				//Instanciando 3 Instrutores
				Instrutor is1 = new Instrutor(null, "Douglas Costa", "jaz@hotmail.com" , "11074798864", sdf.parse("15/05/2002"), sdf.parse("05/08/2021"), 'M', "44588", pe.encode("senha123"));
				is1.getTelefones().addAll(Arrays.asList("996922381", "996011503"));
				is1.addPerfil(Perfil.INSTRUTOR);
				is1.addPerfil(Perfil.ADMIN);
						
				Instrutor is2 = new Instrutor(null, "João da Silva", "joão@hotmail.com" , "52768915084", sdf.parse("08/09/1984"), sdf.parse("05/08/2021"), 'F', "88544", pe.encode("senha123"));
				is2.getTelefones().addAll(Arrays.asList("998154625", "998651520"));
				is2.addPerfil(Perfil.INSTRUTOR);	
				
				Instrutor is3 = new Instrutor(null, "Pedro Santos", "pedro@hotmail.com" , "69278608009", sdf.parse("26/12/1991"), sdf.parse("05/08/2021"), 'M', "54488", pe.encode("senha123"));
				is3.getTelefones().addAll(Arrays.asList("995153526", "995451585"));
				is3.addPerfil(Perfil.INSTRUTOR);	
				
				//Instanciando Endereço
				Endereco e3 = new Endereco (null, "Rua Boituva", "128", "Próximo Rodoviária", "Vila Operária", "38220834", is1, c2);
				Endereco e4 = new Endereco (null, "Rua Juquiá", "560", "Ao lado da caixa d'água da Vila Olinda", "Vila Olinda", "18950250", is2, c1);
				Endereco e5 = new Endereco (null, "Av. Samadelo", "905", "Próximo creche Profª Ana Marcolino", "Jardim Florido", "19750230", is3, c3);
						
				//Indicando quais os endereços dos instrutores
				is1.getEnderecos().addAll(Arrays.asList(e3));
				is2.getEnderecos().addAll(Arrays.asList(e4));
				is3.getEnderecos().addAll(Arrays.asList(e5));
				
				//Salvando Instrutor no banco
				instrutorRepository.saveAll(Arrays.asList(is1, is2, is3));
				
				// Instanciando 3 Planos
				Plano plano1 = new Plano(null, 59.90, "Plano Básico", "Academia possui de 1 a 3 categorias de atividades físicas");
				Plano plano2 = new Plano(null, 119.80, "Plano Intermediário", "Academia possui de 4 a 7 categorias de atividades físicas");
				Plano plano3 = new Plano(null, 179.70, "Plano Avançado", "Academia possui 7 ou mais categorias de atividades físicas");
				
				// Salvando Planos no banco
				planoRepository.saveAll(Arrays.asList(plano1, plano2, plano3));

				
				// Instanciando 3 pagamentos
//				Pagamento pag1 = new PagamentoViaPix(null, EstadoPagamento.PAGO, matri1, sdf.parse("10/09/2021"), sdf.parse("05/09/2021"), "66666666666");
//				Pagamento pag2 = new PagamentoViaPix(null, EstadoPagamento.PAGO, matri2, sdf.parse("10/10/2021"), sdf.parse("05/10/2021"), "66666666666");
//				Pagamento pag3 = new PagamentoViaPix(null, EstadoPagamento.PENDENTE, matri3, sdf.parse("10/10/2021"), null, "66666666666");
//				
//				matri1.setPagamento(pag1);
//				matri2.setPagamento(pag2);
//				matri3.setPagamento(pag3);
				
				// Salvando pagamentos no banco
//				pagamentoRepository.saveAll(Arrays.asList(pag1, pag2, pag3));

				
				// Instancianto 3 Estabelecimentos
				Estabelecimento estab1 = new Estabelecimento(null, "CrossGym", "asv@hotmail.com", "Academia CrossGym S.A.", "73205304000169", sdf.parse("15/05/2002"), sdf.parse("11/07/2021"), 'N', pe.encode("senhateste"));
				estab1.getTelefones().addAll(Arrays.asList("14665239520", "18569253462"));
				estab1.addPerfil(Perfil.ESTABELECIMENTO);
				estab1.setPlano(plano2);
				
				Estabelecimento estab2 = new Estabelecimento(null, "HitDance", "tste@teste.com", "Academia de dança de Ourinhos LTDA", "08002666000190", sdf.parse("20/08/2015"), sdf.parse("10/08/2021"), 'N', pe.encode("senhateste"));
				estab2.getTelefones().addAll(Arrays.asList("14995642031", "14997652380"));
				estab2.addPerfil(Perfil.ESTABELECIMENTO);
				estab2.setPlano(plano2);
				Estabelecimento estab3 = new Estabelecimento(null, "Monsters Gym", "teste@hotmail.com", "Monsters Gym Academia S.A.", "45809031000126", sdf.parse("31/05/1996"), sdf.parse("10/08/2021"), 'N', pe.encode("senhateste"));
				estab3.getTelefones().addAll(Arrays.asList("11884625310", "2155926644"));
				estab3.addPerfil(Perfil.ESTABELECIMENTO);
				estab3.setPlano(plano1);
				
				// Instanciando 3 Endereços de Estabelecimentos
				Endereco endEstab1 = new Endereco (null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", estab1, c3);
				Endereco endEstab2 = new Endereco (null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", estab2, c2);
				Endereco endEstab3 = new Endereco (null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", estab3, c1);
								
				// Criando associação de Categorias com Estabelecimentos
//				cat1.getEstabelecimentos().addAll(Arrays.asList(estab3));
//				cat2.getEstabelecimentos().addAll(Arrays.asList(estab2));
//				cat3.getEstabelecimentos().addAll(Arrays.asList(estab1, estab3));
				
				// Criando associação de Estabelecimentos com Categorias
//				estab1.getCategoria().addAll(Arrays.asList(cat3));
//				estab2.getCategoria().addAll(Arrays.asList(cat2));
//				estab3.getCategoria().addAll(Arrays.asList(cat1, cat3));				
				
				// Criando associação de Instrutores com Estabelecimentos
//				is1.getEstabelecimentos().addAll(Arrays.asList(estab1, estab2));
//				is2.getEstabelecimentos().addAll(Arrays.asList(estab2));
//				is3.getEstabelecimentos().addAll(Arrays.asList(estab1, estab3));
						
				// Criando associação de Estabeleciemntos com Instrutores
//				estab1.getInstrutores().addAll(Arrays.asList(is1, is3));
//				estab2.getInstrutores().addAll(Arrays.asList(is1, is2));
//				estab3.getInstrutores().addAll(Arrays.asList(is3));
				
				//Salvando Endereco no banco
				enderecoRepository.saveAll(Arrays.asList(e3, e4, e5));		
				
				// Salvando Estabelecientos no banco
				estabelecimentoRepository.saveAll(Arrays.asList(estab1, estab2, estab3));
						
				// Salvando Endereços dos Estabelecimentos no banco
				enderecoRepository.saveAll(Arrays.asList(endEstab1, endEstab2, endEstab3));
				
				//Instanciando 3 Atividades no banco.
				Atividade atv1 = new Atividade(null, "Cerrote", "Atividade para melhorar Tricps");
				Atividade atv2 = new Atividade(null, "Rosca Direta", "Atividade para melhorar Biceps");
				Atividade atv3 = new Atividade(null, "Agachamento", "Atividade para melhorar pernas e bumbum");
				//Salvando atividades no banco
				atividadeRepository.saveAll(Arrays.asList(atv1, atv2, atv3));
	 }
}
