package br.com.guest.restaurante_admin.entidades.pedido;

import br.com.guest.restaurante_admin.entidades.menu.Prato;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class Pedido {

    private Prato prato;

    @JsonProperty("id_pedido")
    private Integer idPedido;
    @JsonProperty("id_comanda")
    private Integer idComanda;
    @JsonProperty("id_prato")
    private Integer idPrato;
    private LocalDateTime horario; //TODO: AJUSTAR AS QUESTÕES DE STATUS
    private Integer quantidade;
    private String status;

    public Pedido(Prato prato, Integer idPedido, Integer idComanda, Integer idPrato, LocalDateTime horario, Integer quantidade, String status) {
        this.prato = prato;
        this.idPedido = idPedido;
        this.idComanda = idComanda;
        this.idPrato = idPrato;
        this.horario = horario;
        this.quantidade = quantidade;
        this.status = status;
    }

    public Prato getPrato() {
        return prato;
    }

    public void setPrato(Prato prato) {
        this.prato = prato;
    }

    public Integer getIdComanda() {
        return idComanda;
    }

    public void setIdComanda(Integer idComanda) {
        this.idComanda = idComanda;
    }

    public Integer getIdPrato() {
        return idPrato;
    }

    public void setIdPrato(Integer idPrato) {
        this.idPrato = idPrato;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
