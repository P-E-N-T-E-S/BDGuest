package br.com.guest.restaurante_admin.entidades.pedido.impl;

import br.com.guest.restaurante_admin.entidades.comanda.ComandaService;
import br.com.guest.restaurante_admin.entidades.pedido.Pedido;
import br.com.guest.restaurante_admin.entidades.pedido.PedidoRepository;
import br.com.guest.restaurante_admin.entidades.pedido.PedidoService;
import br.com.guest.restaurante_admin.execoes.ComandaNaoExistenteOuVazia;
import br.com.guest.restaurante_admin.execoes.IngredientesInsuficientesException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {

    private PedidoRepository pedidoRepository;

    private ComandaService comandaService;

    public PedidoServiceImpl(PedidoRepository pedidoRepository, ComandaService comandaService) {
        this.pedidoRepository = pedidoRepository;
        this.comandaService = comandaService;
    }

    @Override
    public void salvar(Pedido pedido, Integer idComanda) throws IngredientesInsuficientesException {
        try {
            pedidoRepository.salvar(pedido, idComanda);
        }catch (Exception e) {
            throw new IngredientesInsuficientesException(e.getMessage());
        }
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
        Pedido pedido_excluir = pedidoRepository.buscarPedido(pedido);
        pedidoRepository.excluirPedido(pedido, idComanda);
        pedidoRepository.apagarLog(pedido_excluir.getIdPedido());
    }

    @Override
    public void alterarPedido(Pedido pedido, Integer idComanda) {
        pedidoRepository.alterarPedido(pedido, idComanda);
    }

    @Override
    public double desassociarPedidos(Integer idComanda) throws ComandaNaoExistenteOuVazia {
        try {
            double valorTotal = pedidoRepository.calcularTotal(idComanda);
            pedidoRepository.excluirPedidoPorComanda(idComanda);
            comandaService.excluirComanda(idComanda);
            return valorTotal;
        }catch (NullPointerException e) {
            throw new ComandaNaoExistenteOuVazia("Comanda: "+idComanda+" não existe ou esta sem pedidos");
        }
    }
}
