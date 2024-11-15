package br.com.guest.restaurante_admin.entidades.comanda;

import java.util.List;

public interface ComandaService {
    void salvarComanda(Comanda comanda);
    Comanda buscarComandaPorId(Integer id);
    List<Comanda> listarComandas();
    void excluirComanda(Integer id);
    void alterarComanda(Comanda comanda, Integer id);
    void chamarGarcom(Integer id);
    void cancelarChamado(Integer id);
}
