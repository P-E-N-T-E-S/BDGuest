package br.com.guest.restaurante_admin.pessoa.impl;

import br.com.guest.restaurante_admin.execoes.FiltroNaoDisponivelException;
import br.com.guest.restaurante_admin.pessoa.Pessoa;
import br.com.guest.restaurante_admin.pessoa.PessoaRepository;
import br.com.guest.restaurante_admin.pessoa.PessoaService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PessoaServiceImpl implements PessoaService {

    private final PessoaRepository pessoaRepository;

    List<String> filtrosDisponiveis = Arrays.asList("cpf", "nome", "rua", "bairro", "estado", "cidade", "cep", "email", "data_nascimento", "telefone");

    public PessoaServiceImpl(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Override
    public void salvarNovaPessoa(Pessoa pessoa) {
        pessoaRepository.salvarNovaPessoa(pessoa);
    }

    @Override
    public List<Pessoa> listarPessoas() {
        return pessoaRepository.listarPessoas();
    }

    @Override
    public Pessoa buscarPessoaPorCpf(String cpf) {
        return pessoaRepository.buscarPessoaPorCpf(cpf);
    }

    @Override
    public boolean deletarPessoaPorCpf(String cpf) {
        if (pessoaRepository.buscarPessoaPorCpf(cpf) == null) {
            return false;
        }
        pessoaRepository.deletarPessoaPorCpf(cpf);
        return true;
    }

    @Override
    public boolean atualizarPessoaPorCpf(String cpf, Pessoa pessoa) {
        if (pessoaRepository.buscarPessoaPorCpf(cpf) == null) {
            return false;
        }
        pessoaRepository.atualizarPessoaPorCpf(cpf, pessoa);
        return true;
    }

    @Override
    public List<Pessoa> buscarPessoaPorFiltro(String filtro, String valor) throws FiltroNaoDisponivelException {
        if(filtrosDisponiveis.contains(filtro)){
            if(filtro.equals("telefone")){
                return pessoaRepository.buscarPessoaPorTelefone(valor);
            }
            return pessoaRepository.buscarPessoaPorFiltro(filtro, valor);
        }
        throw new FiltroNaoDisponivelException("O filtro informado não está disponível");
    }


}
