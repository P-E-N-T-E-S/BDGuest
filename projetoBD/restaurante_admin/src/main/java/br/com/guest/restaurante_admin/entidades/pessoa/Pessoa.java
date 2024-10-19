package br.com.guest.restaurante_admin.entidades.pessoa;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Pessoa {
    private String cpf;
    private String nome;
    private String rua;
    private String bairro;
    private String estado;
    private String cidade;
    private String cep;
    private String email;
    @JsonProperty("data_nascimento")
    private Date dataNascimento;
    @JsonProperty("telefone")
    private String telefone;
    private String telefone2;

    public Pessoa(String cpf, String nome, String rua, String bairro, String estado, String cidade, String cep, String email, Date dataNascimento, String telefone, String telefone2) {
        this.cpf = cpf;
        this.nome = nome;
        this.rua = rua;
        this.bairro = bairro;
        this.estado = estado;
        this.cidade = cidade;
        this.cep = cep;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.telefone2 = telefone2;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }
}

