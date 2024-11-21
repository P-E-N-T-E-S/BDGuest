package br.com.guest.restaurante_admin.entidades.pedido.impl;

import br.com.guest.restaurante_admin.entidades.clientes.Cliente;
import br.com.guest.restaurante_admin.entidades.clientes.ClienteService;
import br.com.guest.restaurante_admin.entidades.comanda.ComandaService;
import br.com.guest.restaurante_admin.entidades.pedido.Pedido;
import br.com.guest.restaurante_admin.entidades.pedido.PedidoRepository;
import br.com.guest.restaurante_admin.entidades.pedido.PedidoService;
import br.com.guest.restaurante_admin.execoes.ComandaNaoExistenteOuVazia;
import br.com.guest.restaurante_admin.execoes.IngredientesInsuficientesException;
import br.com.guest.restaurante_admin.execoes.PedidosNaoEntreguesException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {

    private PedidoRepository pedidoRepository;

    private ComandaService comandaService;

    private ClienteService clienteService;

    public PedidoServiceImpl(PedidoRepository pedidoRepository, ComandaService comandaService, ClienteService clienteService) {
        this.pedidoRepository = pedidoRepository;
        this.comandaService = comandaService;
        this.clienteService = clienteService;
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
    public List<Pedido> listarPorGarcom(String garcom) {
        return pedidoRepository.buscarPedidoPorGarcom(garcom);
    }

    @Override
    public Pedido buscarPorId(Integer id) {
        return pedidoRepository.buscarPorId(id);
    }

    @Override
    public void excluirPedido(Integer idPedido) {
        pedidoRepository.excluirPedido(idPedido);
    }

    @Override
    public void alterarPedido(Pedido pedido, Integer idComanda) {
        pedidoRepository.alterarPedido(pedido, idComanda);
    }

    @Override
    public double desassociarPedidos(Integer idComanda) throws ComandaNaoExistenteOuVazia, PedidosNaoEntreguesException {
        try {
            if(!pedidoRepository.buscarPedidoNaoEntregue(idComanda).isEmpty()) {
                throw new PedidosNaoEntreguesException("Ainda há pedidos prontos ou fazendo");
            }
            String cpfCliente = comandaService.buscarComandaPorId(idComanda).getCpfPessoa();
            double valorTotal = pedidoRepository.calcularTotal(idComanda);
            pedidoRepository.excluirPedidoPorComanda(idComanda);
            comandaService.excluirComanda(idComanda);
            Cliente cliente = clienteService.buscarClientePorCpf(cpfCliente);
            int pontos = (int) valorTotal / 2;
            cliente.setFidelidade(cliente.getFidelidade() + pontos);
            clienteService.atualizarClientePorCpf(cliente, cpfCliente);
            return valorTotal;
        }catch (NullPointerException e) {
            throw new ComandaNaoExistenteOuVazia("Comanda: "+idComanda+" não existe ou esta sem pedidos");
        }
    }

    @Override
    public void alterarStatus(Integer idPedido, String status) {
        pedidoRepository.alterarStatus(idPedido, status);
    }
}
