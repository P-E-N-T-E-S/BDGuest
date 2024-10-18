package br.com.guest.restaurante_admin.funcionarios;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalTime;
import java.util.Date;

public class Funcionario {
    private String cpf; //Foreing key para Pessoa
    @JsonProperty("data_contratacao")
    private Date dataContratacao;
    private Double salario;
    @JsonProperty("hora_entrada")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime horaEntrada;
    @JsonProperty("hora_saida")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime horaSaida;

    public Funcionario(String cpf, Date dataContratacao, Double salario, LocalTime horaEntrada, LocalTime horaSaida) {
        this.cpf = cpf;
        this.dataContratacao = dataContratacao;
        this.salario = salario;
        this.horaEntrada = horaEntrada;
        this.horaSaida = horaSaida;
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

    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalTime getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(LocalTime horaSaida) {
        this.horaSaida = horaSaida;
    }
}
