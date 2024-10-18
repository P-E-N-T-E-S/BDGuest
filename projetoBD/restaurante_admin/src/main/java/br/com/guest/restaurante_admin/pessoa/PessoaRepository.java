package br.com.guest.restaurante_admin.pessoa;

import java.util.List;

public interface PessoaRepository {
    void salvarNovaPessoa(Pessoa pessoa);
    List<Pessoa> listarPessoas();
    Pessoa buscarPessoaPorCpf(String cpf);
}
