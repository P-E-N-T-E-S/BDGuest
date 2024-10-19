package br.com.guest.restaurante_admin.entidades.funcionarios;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalTime;
import java.util.Date;

public class Funcionario {
    private String cpf; //Foreing key para Pessoa
    @JsonProperty("data_contratacao")
    private Date dataContratacao;
    private Double salario;
    @JsonProperty("horario_entrada")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime horarioEntrada;
    @JsonProperty("horario_saida")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime horarioSaida;

    public Funcionario(String cpf, Date dataContratacao, Double salario, LocalTime horaEntrada, LocalTime horaSaida) {
        this.cpf = cpf;
        this.dataContratacao = dataContratacao;
        this.salario = salario;
        this.horarioEntrada = horaEntrada;
        this.horarioSaida = horaSaida;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(Date dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public LocalTime getHorarioEntrada() {
        return horarioEntrada;
    }

    public void setHorarioEntrada(LocalTime horaEntrada) {
        this.horarioEntrada = horaEntrada;
    }

    public LocalTime getHorarioSaida() {
        return horarioSaida;
    }

    public void setHorarioSaida(LocalTime horaSaida) {
        this.horarioSaida = horaSaida;
    }
}
