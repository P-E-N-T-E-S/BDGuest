package br.com.guest.restaurante_admin.entidades.pessoa.impl;

import br.com.guest.restaurante_admin.execoes.CampoDeAlteracaoNaoEncontradoException;
import br.com.guest.restaurante_admin.execoes.FiltroNaoDisponivelException;
import br.com.guest.restaurante_admin.entidades.pessoa.Pessoa;
import br.com.guest.restaurante_admin.entidades.pessoa.PessoaRepository;
import br.com.guest.restaurante_admin.entidades.pessoa.PessoaService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PessoaServiceImpl implements PessoaService {

    private final PessoaRepository pessoaRepository;

    List<String> colunasPessoa = Arrays.asList("cpf", "nome", "rua", "bairro", "estado", "cidade", "cep", "email", "data_nascimento", "telefone");

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
        if(colunasPessoa.contains(filtro)){
            if(filtro.equals("telefone")){
                return pessoaRepository.buscarPessoaPorTelefone(valor);
            }
            return pessoaRepository.buscarPessoaPorFiltro(filtro, valor);
        }
        throw new FiltroNaoDisponivelException(filtro);
    }

    @Override
    public void deletarPessoaPorFiltro(String filtro, String valor) {
        if(colunasPessoa.contains(filtro)){
            if(filtro.equals("telefone")){
                pessoaRepository.deletarPessoaPorTelefone(valor);
                return;
            }
            pessoaRepository.deletarPessoaPorFiltro(filtro, valor);
            return;
        }
        throw new FiltroNaoDisponivelException(filtro);
    }

    @Override
    public void atualizarPessoaPorFiltro(String filtro, String valor, String campoAlterado, String valorAlterado) throws CampoDeAlteracaoNaoEncontradoException, FiltroNaoDisponivelException {
        if (colunasPessoa.contains(filtro)) {
            if (colunasPessoa.contains(campoAlterado)) {
                if (filtro.equals("telefone")) {
                    pessoaRepository.atualizarPessoaPorTelefone(valor, campoAlterado, valorAlterado);
                    return;
                }
                pessoaRepository.atualizarPessoaPorFiltro(filtro, valor, campoAlterado, valorAlterado);
                return;
            }
            throw new CampoDeAlteracaoNaoEncontradoException(campoAlterado);
        }
        throw new FiltroNaoDisponivelException(filtro);
    }
}
