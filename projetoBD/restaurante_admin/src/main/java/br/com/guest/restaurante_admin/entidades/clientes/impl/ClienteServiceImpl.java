package br.com.guest.restaurante_admin.entidades.clientes.impl;

import br.com.guest.restaurante_admin.entidades.clientes.Cliente;
import br.com.guest.restaurante_admin.entidades.clientes.ClienteRepository;
import br.com.guest.restaurante_admin.entidades.clientes.ClienteService;
import br.com.guest.restaurante_admin.entidades.pessoa.PessoaService;
import br.com.guest.restaurante_admin.execoes.CampoDeAlteracaoNaoEncontradoException;
import br.com.guest.restaurante_admin.execoes.FiltroNaoDisponivelException;
import org.springframework.stereotype.Service;


import java.util.Arrays;
import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {
    //todo fazer funções para filtrar por atributos da classe Pessoa

    private ClienteRepository clienteRepository;
    private PessoaService pessoaService;

    private final List<String> camposCliente = Arrays.asList("cpf", "fidelidade", "metodo_pagamento_1", "metodo_pagamento_2");

    public ClienteServiceImpl(ClienteRepository clienteRepository, PessoaService pessoaService) {
        this.clienteRepository = clienteRepository;
        this.pessoaService = pessoaService;
    }

    @Override
    public boolean salvarCliente(Cliente cliente){
        if(pessoaService.buscarPessoaPorCpf(cliente.getCpf()) == null) {
            return false;
        }
        clienteRepository.salvarCliente(cliente);
        return true;
    }

    @Override
    public Cliente buscarClientPorCpf(String cpf) {
        return clienteRepository.buscarClientePorCpf(cpf);
    }

    @Override
    public List<Cliente> listarClientes() {
        return clienteRepository.listarClientes();
    }

    @Override
    public List<Cliente> buscarClientePorFiltro(String filtro, String valor) throws FiltroNaoDisponivelException {
        if(camposCliente.contains(filtro)) {
            return clienteRepository.buscarClientePorFiltro(filtro, valor);
        }
        throw new FiltroNaoDisponivelException(filtro);
    }

    @Override
    public void atualizarClientePorCpf(Cliente cliente, String cpf) {
        clienteRepository.atualizarClientePorCpf(cliente, cpf);
    }

    @Override
    public void atualizarClientePorFiltro(String filtro, String valor, String campoAlterado, String valorAlterado) throws FiltroNaoDisponivelException, CampoDeAlteracaoNaoEncontradoException {
        if(camposCliente.contains(filtro)) {
            if(camposCliente.contains(campoAlterado)) {
                clienteRepository.atualizarClientePorFiltro(filtro, valor, campoAlterado, valorAlterado);
                return;
            }
            throw new CampoDeAlteracaoNaoEncontradoException(campoAlterado);
        }
        throw new FiltroNaoDisponivelException(filtro);
    }

    @Override
    public void deletarClientePorCpf(String cpf) {
        clienteRepository.deletarClientePorCpf(cpf);
    }

    @Override
    public void deletarClientePorFiltro(String filtro, String valor) throws FiltroNaoDisponivelException {
        if(camposCliente.contains(filtro)) {
            clienteRepository.deletarClientePorFiltro(filtro, valor);
            return;
        }
        throw new FiltroNaoDisponivelException(filtro);
    }
}
