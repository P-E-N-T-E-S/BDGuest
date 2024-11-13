package br.com.guest.restaurante_admin.entidades.comanda.impl;

import br.com.guest.restaurante_admin.entidades.clientes.ClienteService;
import br.com.guest.restaurante_admin.entidades.comanda.Comanda;
import br.com.guest.restaurante_admin.entidades.comanda.ComandaRepository;
import br.com.guest.restaurante_admin.entidades.comanda.ComandaService;
import br.com.guest.restaurante_admin.execoes.ClienteNaoCadastradoException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComandaServiceImpl implements ComandaService {

    private ComandaRepository comandaRepository;

    private ClienteService clienteService;

    public ComandaServiceImpl(ComandaRepository comandaRepository, ClienteService clienteService) {
        this.comandaRepository = comandaRepository;
        this.clienteService = clienteService;
    }

    @Override
    public void salvarComanda(Comanda comanda) throws ClienteNaoCadastradoException {
        comandaRepository.salvarComanda(comanda);
        if(clienteService.buscarClientePorCpf(comanda.getCpfPessoa()) == null) {
            throw new ClienteNaoCadastradoException("Cliente nao cadastrado");
        }
    }
    @Override
    public Comanda buscarComandaPorId(Integer id) {
        return comandaRepository.buscarComandaPorId(id);
    }

    @Override
    public List<Comanda> listarComandas() {
        return comandaRepository.listarComandas();
    }

    @Override
    public void excluirComanda(Integer id) {
        comandaRepository.excluirComanda(id);
    }

    @Override
    public void alterarComanda(Comanda comanda, Integer id) {
        comandaRepository.alterarComanda(comanda, id);
    }
}
