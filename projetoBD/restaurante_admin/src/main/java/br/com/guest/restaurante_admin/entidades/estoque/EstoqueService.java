package br.com.guest.restaurante_admin.entidades.estoque;

import java.util.List;

public interface EstoqueService {
    void salvarEstoque(Estoque estoque);
    Estoque buscarEstoque(Integer id);
    List<Estoque> listarEstoque();
    List<Estoque> buscarEstoquePorFiltro(String filtro, String valor);
    void excluirEstoquePorId(Integer id);
    void excluirEstoquePorFiltro(String filtro, String valor);
    void alterarEstoquePorId(Estoque estoque, Integer id);
    void alterarEstoquePorFiltro(String filtro, String valor, String campoAlterado, String valorAlterado);
}
