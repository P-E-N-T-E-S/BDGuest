package br.com.guest.restaurante_admin.entidades.pedido.impl;

import br.com.guest.restaurante_admin.entidades.comanda.ComandaService;
import br.com.guest.restaurante_admin.entidades.pedido.Pedido;
import br.com.guest.restaurante_admin.entidades.pedido.PedidoRepository;
import br.com.guest.restaurante_admin.entidades.pedido.PedidoService;
import br.com.guest.restaurante_admin.entidades.produto.ProdutoService;
import br.com.guest.restaurante_admin.entidades.usa.UsaService;
import br.com.guest.restaurante_admin.execoes.IngredientesInsuficientesException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {

    private PedidoRepository pedidoRepository;

    private ProdutoService produtoService;

    private UsaService usaService;

    private ComandaService comandaService;

    public PedidoServiceImpl(PedidoRepository pedidoRepository, ProdutoService produtoService, UsaService usaService, ComandaService comandaService) {
        this.pedidoRepository = pedidoRepository;
        this.produtoService = produtoService;
        this.usaService = usaService;
        this.comandaService = comandaService;
    }

    @Override
    public void salvar(Pedido pedido, Integer idComanda) throws IngredientesInsuficientesException {
        if(!produtoService.verificarQuantidadePorPrato(pedido.getIdPrato(), pedido.getQuantidade()).isEmpty()) {
            throw new IngredientesInsuficientesException(pedido.getIdPrato().toString());
        }
        pedidoRepository.salvar(pedido, idComanda);
        usaService.reduzirQuantidadePorPrato(pedido.getIdPrato(), pedido.getQuantidade());
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

    @Override
    public double desassociarPedidos(Integer idComanda) {
        double valorTotal = pedidoRepository.calcularTotal(idComanda);
        pedidoRepository.excluirPedidoPorComanda(idComanda);
        comandaService.excluirComanda(idComanda);
        return valorTotal;
    }
}
