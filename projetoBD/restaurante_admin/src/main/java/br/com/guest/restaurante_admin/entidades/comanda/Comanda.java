package br.com.guest.restaurante_admin.entidades.comanda;

import br.com.guest.restaurante_admin.entidades.mesa.Mesa;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class Comanda {

    private Mesa mesa;

    @JsonProperty("numero_id")
    private Integer numeroId;
    @JsonProperty("cpf_pessoa")
    private String cpfPessoa;
    @JsonProperty("nome_cliente")
    private String nomeCliente;
    private LocalDateTime acesso;
    @JsonProperty("mesa_id")
    private Integer mesaId;

    public Comanda(Mesa mesa, Integer numeroId, String cpfPessoa, String nomeCliente, LocalDateTime acesso, Integer mesaId) {
        this.mesa = mesa;
        this.numeroId = numeroId;
        this.cpfPessoa = cpfPessoa;
        this.nomeCliente = nomeCliente;
        this.acesso = acesso;
        this.mesaId = mesaId;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
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

    public Integer getMesaId() {
        return mesaId;
    }

    public void setMesaId(Integer mesaId) {
        this.mesaId = mesaId;
    }
}
