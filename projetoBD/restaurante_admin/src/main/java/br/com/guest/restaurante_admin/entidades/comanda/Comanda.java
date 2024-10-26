package br.com.guest.restaurante_admin.entidades.comanda;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class Comanda {
    @JsonProperty("numero_id")
    private Integer numeroId;
    @JsonProperty("cpfPessoa")
    private String cpfPessoa;
    @JsonProperty("nome_cliente")
    private String nomeCliente;
    private LocalDateTime acesso;

    public Comanda(Integer numeroId, String cpfPessoa, String nomeCliente, LocalDateTime acesso) {
        this.numeroId = numeroId;
        this.cpfPessoa = cpfPessoa;
        this.nomeCliente = nomeCliente;
        this.acesso = acesso;
    }

    public Integer getNumeroId() {
        return numeroId;
    }

    public void setNumeroId(Integer numeroId) {
        this.numeroId = numeroId;
    }

    public String getCpfPessoa() {
        return cpfPessoa;
    }

    public void setCpfPessoa(String cpfPessoa) {
        this.cpfPessoa = cpfPessoa;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public LocalDateTime getAcesso() {
        return acesso;
    }

    public void setAcesso(LocalDateTime acesso) {
        this.acesso = acesso;
    }
}
