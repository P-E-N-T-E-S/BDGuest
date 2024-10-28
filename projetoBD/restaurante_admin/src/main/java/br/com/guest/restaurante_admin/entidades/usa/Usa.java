package br.com.guest.restaurante_admin.entidades.usa;

import br.com.guest.restaurante_admin.entidades.menu.Prato;
import br.com.guest.restaurante_admin.entidades.produto.Produto;

public class Usa {
    private Produto produto;
    private Prato prato;

    private Integer produtoId;
    private Integer pratoId;

    public Usa(Produto produto, Prato prato, Integer produtoId, Integer pratoId) {
        this.produto = produto;
        this.prato = prato;
        this.produtoId = produtoId;
        this.pratoId = pratoId;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Prato getPrato() {
        return prato;
    }

    public void setPrato(Prato prato) {
        this.prato = prato;
    }

    public Integer getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Integer produtoId) {
        this.produtoId = produtoId;
    }

    public Integer getPratoId() {
        return pratoId;
    }

    public void setPratoId(Integer pratoId) {
        this.pratoId = pratoId;
    }
}
