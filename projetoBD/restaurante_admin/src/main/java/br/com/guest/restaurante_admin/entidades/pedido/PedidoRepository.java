package br.com.guest.restaurante_admin.entidades.pedido;

import java.util.List;

public interface PedidoRepository {
    void salvar(Pedido pedido, Integer idComanda);
    List<Pedido> buscarPedidoPorGarcom(String garcom);
    List<Pedido> listarPorComanda(Integer idComanda);
    List<Pedido> listarPorPrato(Integer idComanda, Integer idPrato);
    void excluirPedido(Integer idPedido);
    void excluirPedidoPorComanda(Integer idComanda);
    void alterarPedido(Pedido pedido, Integer idComanda);
    double calcularTotal(Integer idComanda);
    void alterarStatus(Integer idPedido, String status);
    void definirDelecao(Integer idPedido);
}
