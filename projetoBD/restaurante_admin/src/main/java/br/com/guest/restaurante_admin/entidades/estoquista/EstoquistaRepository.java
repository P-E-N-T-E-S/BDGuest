package br.com.guest.restaurante_admin.entidades.estoquista;

import java.util.List;

public interface EstoquistaRepository {
    void salvarEstoquista(Estoquista estoquista);
    Estoquista buscarEstoquistaPorCpf(String cpf);
    List<Estoquista> listarEstoquistas();
    List<Estoquista> buscarEstoquistasPorFiltro(String filtro, String valor);
    void excluirEstoquistaPorCpf(String cpf);
    void excluirEstoquistaPorFiltro(String filtro, String valor);
    void alterarEstoquistaPorCpf(String cpf, Estoquista estoquista);
    void alterarEstoquistaPorFiltro(String filtro, String valor, String campoAlterado, String valorAlterado);
}
