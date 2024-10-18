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
    public boolean deletarPessoa(String cpf) {
        if (pessoaRepository.buscarPessoaPorCpf(cpf) == null) {
            return false;
        }
        pessoaRepository.deletarPessoa(cpf);
        return true;
    }

    @Override
    public boolean atualizarPessoa(String cpf, Pessoa pessoa) {
        if (pessoaRepository.buscarPessoaPorCpf(cpf) == null) {
            return false;
        }
        pessoaRepository.atualizarPessoa(cpf, pessoa);
        return true;
    }

    @Override
    public List<Pessoa> buscarPessoaPorFiltro(String filtro, String valor) throws FiltroNaoDisponivelException {
        boolean telefoneFlag = false;
        if(filtrosDisponiveis.contains(filtro)){
            if(filtro.equals("telefone")){
                telefoneFlag = true;
            }
            return pessoaRepository.buscarPessoaPorFiltro(filtro, valor, telefoneFlag);
        }
        throw new FiltroNaoDisponivelException("O filtro informado não está disponível");
    }


}
