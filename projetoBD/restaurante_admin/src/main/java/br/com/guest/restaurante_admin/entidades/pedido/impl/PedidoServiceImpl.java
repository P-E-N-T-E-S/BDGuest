package br.com.guest.restaurante_admin.entidades.pedido.impl;

import br.com.guest.restaurante_admin.entidades.pedido.Pedido;
import br.com.guest.restaurante_admin.entidades.pedido.PedidoRepository;
import br.com.guest.restaurante_admin.entidades.pedido.PedidoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {

    private PedidoRepository pedidoRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public void salvar(Pedido pedido, Integer idComanda) {
        //TODO: verificar o idComanda
        pedidoRepository.salvar(pedido, idComanda);
    }

    @Override
    public List<Pedido> listarPorComanda(Integer idComanda) {
        return pedidoRepository.listarPorComanda(idComanda);
    }

    @Override
    public List<Pedido> listarPorPrato(Integer idComanda, Integer idPrato) {
        return pedidoRepository.listarPorPrato(idComanda, idPrato);
    }

    @Override
    public void excluirPedido(Pedido pedido, Integer idComanda) {
        pedidoRepository.excluirPedido(pedido, idComanda);
    }

    @Override
    public void alterarPedido(Pedido pedido, Integer idComanda) {
        pedidoRepository.alterarPedido(pedido, idComanda);
    }
}
