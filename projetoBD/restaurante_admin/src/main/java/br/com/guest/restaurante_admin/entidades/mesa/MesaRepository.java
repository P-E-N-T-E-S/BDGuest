package br.com.guest.restaurante_admin.entidades.mesa;

import java.util.List;

public interface MesaRepository {
    void salvarMesa(Mesa mesa);
    Mesa acharMesaPorId(Integer id);
    List<Mesa> listarMesas();
    List<Mesa> listarMesasPorFiltro(String filtro, String valor);
    void excluirMesaPorId(Integer id);
    void alterarMesaPorID(Mesa mesa, Integer id);
}
