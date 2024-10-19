package br.com.guest.restaurante_admin.entidades.clientes;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Cliente {
    private String cpf;
    private Integer fidelidade;
    @JsonProperty("metodo_pagamento_1")
    private String metodoPagamento1;
    @JsonProperty("metodo_pagamento_2")
    private String metodoPagamento2;

    public Cliente(String cpf, Integer fidelidade, String metodoPagamento1, String metodoPagamento2) {
        this.cpf = cpf;
        this.fidelidade = fidelidade;
        this.metodoPagamento1 = metodoPagamento1;
        this.metodoPagamento2 = metodoPagamento2;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getFidelidade() {
        return fidelidade;
    }

    public void setFidelidade(Integer fidelidade) {
        this.fidelidade = fidelidade;
    }

    public String getMetodoPagamento1() {
        return metodoPagamento1;
    }

    public void setMetodoPagamento1(String metodoPagamento1) {
        this.metodoPagamento1 = metodoPagamento1;
    }

    public String getMetodoPagamento2() {
        return metodoPagamento2;
    }

    public void setMetodoPagamento2(String metodoPagamento2) {
        this.metodoPagamento2 = metodoPagamento2;
    }
}
