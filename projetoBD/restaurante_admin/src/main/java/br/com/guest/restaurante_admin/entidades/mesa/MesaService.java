package br.com.guest.restaurante_admin.entidades.mesa;

import java.util.List;

public interface MesaService {
    void salvarMesa(Mesa mesa);
    Mesa acharMesaPorId(Integer id);
    List<Mesa> listarMesaPorGarcom(String cpfGarcom);
    List<Mesa> listarMesas();
    void excluirMesaPorId(Integer id);
    void alterarMesaPorID(Mesa mesa, Integer id);
}
