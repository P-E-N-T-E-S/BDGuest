package br.com.guest.restaurante_admin.entidades.comanda;

import java.util.List;

public interface ComandaRepository {
    void salvarComanda(Comanda comanda);
    Comanda buscarComandaPorId(Integer id);
    public List<Comanda> listarComandas();
    void excluirComanda(String id);
    void alterarComanda(Comanda comanda, Integer id);
}
