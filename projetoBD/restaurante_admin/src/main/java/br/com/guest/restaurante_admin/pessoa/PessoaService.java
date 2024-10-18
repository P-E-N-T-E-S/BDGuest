package br.com.guest.restaurante_admin.pessoa;

import java.util.HashMap;
import java.util.List;

public interface PessoaService {
    void salvarNovaPessoa(Pessoa pessoa);
    List<Pessoa> listarPessoas();
    Pessoa buscarPessoaPorCpf(String cpf); //Boa para as tabelas relacionadas
    boolean deletarPessoaPorCpf(String cpf);
    boolean atualizarPessoaPorCpf(String cpf, Pessoa pessoa);
    List<Pessoa> buscarPessoaPorFiltro(String filtro, String valor);
    void deletarPessoaPorFiltro(String filtro, String valor);
    void atualizarPessoaPorFiltro(String filtro, String valor, String campoAlterado, String valorAlterado);
}
