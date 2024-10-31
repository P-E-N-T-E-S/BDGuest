package br.com.guest.restaurante_admin.entidades.menu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Prato {
    //TODO: ajustar a questão da medida de prato
    private Integer id;
    private String nome;
    @JsonProperty("imagem_link")
    private String imagemLink;
    private String descricao;
    private double preco;
    @JsonIgnore
    private List<Integer> ingredientes;
    private String medida;

    public Prato(String medida, Integer id, String nome, String imagemLink, String descricao, double preco) {
        this.medida = medida;
        this.id = id;
        this.nome = nome;
        this.imagemLink = imagemLink;
        this.descricao = descricao;
        this.preco = preco;
    }

    public Prato(Integer id, String nome, String imagemLink, String descricao, double preco, List<Integer> ingredientes, String medida) {
        this.id = id;
        this.nome = nome;
        this.imagemLink = imagemLink;
        this.descricao = descricao;
        this.preco = preco;
        this.ingredientes = ingredientes;
        this.medida = medida;
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

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public List<Integer> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<Integer> ingredientes) {
        this.ingredientes = ingredientes;
    }
}

