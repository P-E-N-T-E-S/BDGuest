package br.com.guest.restaurante_admin.entidades.reserva;

import br.com.guest.restaurante_admin.entidades.clientes.Cliente;
import br.com.guest.restaurante_admin.entidades.mesa.Mesa;
import br.com.guest.restaurante_admin.entidades.pessoa.Pessoa;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalTime;
import java.util.Date;

public class Reserva {

    private Cliente cliente;
    private Mesa mesa;

    @JsonProperty("cpf_cliente")
    private String cpfCliente;
    private Date data;
    @JsonProperty("horario_entrada")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime horarioEntrada;
    @JsonProperty("quantidade_pessoas")
    private Integer quantidadePessoas;
    @JsonProperty("numero_mesa")
    private Integer numeroMesa;

    public Reserva(Cliente cliente, Mesa mesa, String cpfCliente, Date data, LocalTime horarioEntrada, Integer quantidadePessoas, Integer numeroMesa) {
        this.cliente = cliente;
        this.mesa = mesa;
        this.cpfCliente = cpfCliente;
        this.data = data;
        this.horarioEntrada = horarioEntrada;
        this.quantidadePessoas = quantidadePessoas;
        this.numeroMesa = numeroMesa;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public LocalTime getHorarioEntrada() {
        return horarioEntrada;
    }

    public void setHorarioEntrada(LocalTime horarioEntrada) {
        this.horarioEntrada = horarioEntrada;
    }

    public Integer getQuantidadePessoas() {
        return quantidadePessoas;
    }

    public void setQuantidadePessoas(Integer quantidadePessoas) {
        this.quantidadePessoas = quantidadePessoas;
    }

    public Integer getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(Integer numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
