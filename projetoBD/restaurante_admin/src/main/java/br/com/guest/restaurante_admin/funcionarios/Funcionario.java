package br.com.guest.restaurante_admin.funcionarios;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalTime;
import java.util.Date;

public class Funcionario {
    private String cpf;
    @JsonProperty("data_contratacao")
    private Date dataContratacao;
    private Double salario;
    @JsonProperty("hora_entrada")
    private LocalTime horaEntrada;
    @JsonProperty("hora_saida")
    private LocalTime horaSaida;
}
