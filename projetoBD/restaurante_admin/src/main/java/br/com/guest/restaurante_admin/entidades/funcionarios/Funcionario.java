package br.com.guest.restaurante_admin.entidades.funcionarios;

import br.com.guest.restaurante_admin.entidades.pessoa.Pessoa;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalTime;
import java.util.Date;

public class Funcionario {
    //todo colocar um campo de pessoa
    private Pessoa pessoa;
    private String cpf;
    @JsonProperty("data_contratacao")
    private Date dataContratacao;
    private Double salario;
    @JsonProperty("horario_entrada")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime horarioEntrada;
    @JsonProperty("horario_saida")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime horarioSaida;

    public Funcionario(Pessoa pessoa, String cpf, Date dataContratacao, Double salario, LocalTime horarioEntrada, LocalTime horarioSaida) {
        this.pessoa = pessoa;
        this.cpf = cpf;
        this.dataContratacao = dataContratacao;
        this.salario = salario;
        this.horarioEntrada = horarioEntrada;
        this.horarioSaida = horarioSaida;
    }

    public LocalTime getHorarioSaida() {
        return horarioSaida;
    }

    public void setHorarioSaida(LocalTime horarioSaida) {
        this.horarioSaida = horarioSaida;
    }

    public LocalTime getHorarioEntrada() {
        return horarioEntrada;
    }

    public void setHorarioEntrada(LocalTime horarioEntrada) {
        this.horarioEntrada = horarioEntrada;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public Date getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(Date dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
