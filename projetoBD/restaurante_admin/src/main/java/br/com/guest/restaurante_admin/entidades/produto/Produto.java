package br.com.guest.restaurante_admin.entidades.produto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class Produto {
    private Integer id;
    private String nome;
    private Date validade;
    private Integer quantidade;
    private String distribuidora;
    @JsonProperty("estoques")
    private List<Integer> estoques;
    private String medida;

    public Produto(Integer id, String nome, Date validade, Integer quantidade, String distribuidora, List<Integer> estoques, String medida) {
        this.id = id;
        this.nome = nome;
        this.validade = validade;
        this.quantidade = quantidade;
        this.distribuidora = distribuidora;
        this.estoques = estoques;
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

    public List<Integer> getEstoques() {
        return estoques;
    }

    public void setEstoques(List<Integer> estoques) {
        this.estoques = estoques;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }
}
