package br.com.guest.restaurante_admin.entidades.pedido;

import java.util.List;

public interface PedidoService {
    void salvar(Pedido pedido, Integer idComanda);
    List<Pedido> listarPorComanda(Integer idComanda);
    List<Pedido> listarPorPrato(Integer idComanda, Integer idPrato);
    List<Pedido> listarPorGarcom(String garcom);
    Pedido buscarPorId(Integer id);
    void excluirPedido(Integer idPedido);
    void alterarPedido(Pedido pedido, Integer idComanda);
    double desassociarPedidos(Integer idComanda);
    void alterarStatus(Integer idPedido, String status);
}
