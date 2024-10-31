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
    public void salvarComanda(Comanda comanda) {
        comandaRepository.salvarComanda(comanda); //essa função so cria as comandas, para associar uma pessoa a ela temos que usar a de alterar
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
    public void excluirComanda(String id) {
        comandaRepository.excluirComanda(id);
    }

    @Override
    public void alterarComanda(Comanda comanda, Integer id) {
        if(clienteService.buscarClientePorCpf(comanda.getCpfPessoa()) == null){
            comanda.setCpfPessoa(null);
            comandaRepository.salvarComanda(comanda);
            throw new ClienteNaoCadastradoException(comanda.getCpfPessoa());
        }
        comandaRepository.alterarComanda(comanda, id);
    }

    @Override
    public void zerarComanda(Integer id) {
        comandaRepository.zerarComanda(id);
    }
}
