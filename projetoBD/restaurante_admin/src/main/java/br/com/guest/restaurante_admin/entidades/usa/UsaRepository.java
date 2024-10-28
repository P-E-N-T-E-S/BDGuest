package br.com.guest.restaurante_admin.entidades.usa;

import java.util.List;

public interface UsaRepository {
    void salvarUso(Usa usa);
    List<Usa> getUsosPorProduto(Integer idProduto);
    List<Usa> getUsosPorPrato(Integer idPrato);
    void deletarUso(Integer idPrato, Integer idProduto);
}
