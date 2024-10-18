package br.com.guest.restaurante_admin.pessoa;

import java.util.List;

public interface PessoaRepository {
    void salvarNovaPessoa(Pessoa pessoa);
    List<Pessoa> listarPessoas();
    Pessoa buscarPessoaPorCpf(String cpf);
    void deletarPessoaPorCpf(String cpf);
    void atualizarPessoaPorCpf(String cpf, Pessoa pessoa);
    List<Pessoa> buscarPessoaPorFiltro(String filtro, String valor);
    List<Pessoa> buscarPessoaPorTelefone(String telefone);
    void deletarPessoaPorFiltro(String filtro, String valor);
    boolean atualizarPessoaPorFiltro(String filtro, String valor);
}
