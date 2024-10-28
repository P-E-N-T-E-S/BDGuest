package br.com.guest.restaurante_admin.entidades.contem;

import br.com.guest.restaurante_admin.entidades.estoque.Estoque;
import br.com.guest.restaurante_admin.entidades.produto.Produto;

public class Contem {

    private Produto produto;
    private Estoque estoque;

    private Integer idProduto;
    private Integer idEstoque;

    public Contem(Produto produto, Estoque estoque, Integer idProduto, Integer idEstoque) {
        this.produto = produto;
        this.estoque = estoque;
        this.idProduto = idProduto;
        this.idEstoque = idEstoque;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Estoque getEstoque() {
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public Integer getIdEstoque() {
        return idEstoque;
    }

    public void setIdEstoque(Integer idEstoque) {
        this.idEstoque = idEstoque;
    }
}
