package com.daniloperez.academia.dto;

import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;


public class EstabelecimentoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @NotEmpty(message = "Preenchimento obrigatório")
    //Anotação para validação do nome da pessoa, definindo que nome é um campo de preenchimento obrigatório
    @Size(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
    //Anotação que define o tamanho minimo e máximo que pode ter o nome da pessoa
    private String nome;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Email(message = "Email inválido")
    private String email;

    @NotEmpty(message = "Preenchimento obrigatório")
    @CNPJ
    private String cnpj;
    @Past
    private Date data_nasc;

    private Date data_cad;

    @NotEmpty(message="Preenchimento obrigatório")
    private String logradouro;

    @NotEmpty(message="Preenchimento obrigatório")
    private String numero;

    private String complemento;

    private String bairro;

    @NotEmpty(message="Preenchimento obrigatório")
    private String cep;

    private char sexo;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String senha;

    private String razao_social;

    private Integer categoriaId;

    private Integer planoId;

    private Integer cidadeId;

    public EstabelecimentoDTO() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Date getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(Date data_nasc) {
        this.data_nasc = data_nasc;
    }

    public Date getData_cad() {
        return data_cad;
    }

    public void setData_cad(Date data_cad) {
        this.data_cad = data_cad;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Integer getCidadeId() {
        return cidadeId;
    }

    public void setCidadeId(Integer cidadeId) {
        this.cidadeId = cidadeId;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getRazao_social() {
        return razao_social;
    }

    public void setRazao_social(String razao_social) {
        this.razao_social = razao_social;
    }

    public Integer getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Integer getPlanoId() {
        return planoId;
    }

    public void setPlanoId(Integer planoId) {
        this.planoId = planoId;
    }
}
