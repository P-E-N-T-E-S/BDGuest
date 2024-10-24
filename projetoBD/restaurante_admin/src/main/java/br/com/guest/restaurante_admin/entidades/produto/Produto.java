package br.com.guest.restaurante_admin.entidades.produto;

import java.util.Date;

public class Produto {
    private Integer id;
    private String nome;
    private Date validade;
    private Integer quantidade;
    private String distribuidora;

    public Produto(Integer id, String nome, Date validade, Integer quantidade, String distribuidora) {
        this.id = id;
        this.nome = nome;
        this.validade = validade;
        this.quantidade = quantidade;
        this.distribuidora = distribuidora;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getValidade() {
        return validade;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getDistribuidora() {
        return distribuidora;
    }

    public void setDistribuidora(String distribuidora) {
        this.distribuidora = distribuidora;
    }
}
