package br.com.guest.restaurante_admin.entidades.clientes.impl;

import br.com.guest.restaurante_admin.entidades.clientes.Cliente;
import br.com.guest.restaurante_admin.entidades.clientes.ClienteRepository;
import br.com.guest.restaurante_admin.entidades.clientes.ClienteService;
import br.com.guest.restaurante_admin.entidades.funcionarios.FuncionarioService;
import br.com.guest.restaurante_admin.entidades.pessoa.PessoaService;
import br.com.guest.restaurante_admin.execoes.CampoDeAlteracaoNaoEncontradoException;
import br.com.guest.restaurante_admin.execoes.FiltroNaoDisponivelException;
import br.com.guest.restaurante_admin.execoes.PessoaNaoEncontradaException;
import org.springframework.stereotype.Service;


import java.util.Arrays;
import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    private ClienteRepository clienteRepository;
    private PessoaService pessoaService;

    private final List<String> colunasCliente = Arrays.asList("cpf", "fidelidade", "metodo_pagamento_1", "metodo_pagamento_2");
    private final List<String> colunasPessoa = Arrays.asList("nome", "rua", "bairro", "estado", "cidade", "cep", "email", "data_nascimento", "telefone");

    public ClienteServiceImpl(ClienteRepository clienteRepository, PessoaService pessoaService) {
        this.clienteRepository = clienteRepository;
        this.pessoaService = pessoaService;
    }

    @Override
    public void salvarCliente(Cliente cliente) throws PessoaNaoEncontradaException {
        if(pessoaService.buscarPessoaPorCpf(cliente.getCpf()) == null) {
            throw new PessoaNaoEncontradaException(cliente.getCpf());
        }
        clienteRepository.salvarCliente(cliente);
    }

    @Override
    public Cliente buscarClientePorCpf(String cpf) {
        return clienteRepository.buscarClientePorCpf(cpf);
    }

    @Override
    public List<Cliente> listarClientes() {
        return clienteRepository.listarClientes();
    }

    @Override
    public List<Cliente> buscarClientePorFiltro(String filtro, String valor) throws FiltroNaoDisponivelException {
        if(colunasCliente.contains(filtro) || colunasPessoa.contains(filtro)) {
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
        if(colunasCliente.contains(campoAlterado)) {
            if(colunasPessoa.contains(filtro)) {
                clienteRepository.atualizarClientePorFiltroDePessoa(filtro, valor, campoAlterado, valorAlterado);
                return;
            }
            if(colunasCliente.contains(filtro)) {
                clienteRepository.atualizarClientePorFiltro(filtro, valor, campoAlterado, valorAlterado);
                return;
            }
            throw new FiltroNaoDisponivelException(filtro);
        }
        throw new CampoDeAlteracaoNaoEncontradoException(campoAlterado);
    }

    @Override
    public void deletarClientePorCpf(String cpf) {
        clienteRepository.deletarClientePorCpf(cpf);
    }

    @Override
    public void deletarClientePorFiltro(String filtro, String valor) throws FiltroNaoDisponivelException {
        if(colunasPessoa.contains(filtro)) {
            clienteRepository.deletarClientePorFiltroDePessoa(filtro, valor);
            return;
        }
        if(colunasCliente.contains(filtro)) {
            clienteRepository.deletarClientePorFiltro(filtro, valor);
            return;
        }
        throw new FiltroNaoDisponivelException(filtro);
    }
}
