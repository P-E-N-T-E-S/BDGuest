package br.com.guest.restaurante_admin.pessoa;

import java.util.List;

public interface PessoaRepository {
    void salvarNovaPessoa(Pessoa pessoa);
    List<Pessoa> listarPessoas();
    Pessoa buscarPessoaPorCpf(String cpf);
    void deletarPessoa(String cpf);
    void atualizarPessoa(String cpf, Pessoa pessoa);
    List<Pessoa> buscarPessoaPorFiltro(String filtro, String valor);
    List<Pessoa> buscarPessoaPorTelefone(String telefone);
}
