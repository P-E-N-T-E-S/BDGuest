package br.com.guest.restaurante_admin.entidades.menu;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Prato {
    private Integer id;
    private String nome;
    @JsonProperty("imagem_link")
    private String imagemLink;
    private String descricao;
    private double preco;

    public Prato(Integer id, String nome, String imagemLink, String descricao, double preco) {
        this.id = id;
        this.nome = nome;
        this.imagemLink = imagemLink;
        this.descricao = descricao;
        this.preco = preco;
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

    public String getImagemLink() {
        return imagemLink;
    }

    public void setImagemLink(String imagemLink) {
        this.imagemLink = imagemLink;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}

