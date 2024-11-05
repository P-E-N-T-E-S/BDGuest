package br.com.guest.restaurante_admin.entidades.menu;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Prato {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("imagem_link")
    private String imagemLink;
    @JsonProperty("descricao")
    private String descricao;
    @JsonProperty("preco")
    private double preco;
    @JsonProperty("ingredientes")
    private List<List<Integer>> ingredientes;

    public Prato(Integer id, String nome, String imagemLink, String descricao, double preco, List<List<Integer>> ingredientes) {
        this.id = id;
        this.nome = nome;
        this.imagemLink = imagemLink;
        this.descricao = descricao;
        this.preco = preco;
        this.ingredientes = ingredientes;
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

    public List<List<Integer>> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<List<Integer>> ingredientes) {
        this.ingredientes = ingredientes;
    }
}

