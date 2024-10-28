package br.com.guest.restaurante_admin.entidades.contem;

import java.util.List;

public interface ContemRepository {
    void salvarContem(Contem contem);
    List<Contem> buscarContemPorProduto(Integer idProduto);
    List<Contem> buscarContemPorEstoque(Integer idEstoque);
    void excluirContem(Contem contem);
}
