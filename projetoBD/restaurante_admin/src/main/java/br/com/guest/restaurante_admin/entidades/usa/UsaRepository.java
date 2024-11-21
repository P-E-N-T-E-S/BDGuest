package br.com.guest.restaurante_admin.entidades.usa;

import br.com.guest.restaurante_admin.entidades.produto.Produto;

import java.util.List;

public interface UsaRepository {
    void salvarUso(Usa usa);
    List<Usa> getUsosPorProduto(Integer idProduto);
    List<Usa> getUsosPorPrato(Integer idPrato);
    void deletarUso(Integer idPrato, Integer idProduto);
    void deletarUsoPorPrato(Integer idPrato);
}
