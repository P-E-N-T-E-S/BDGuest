package br.com.guest.restaurante_admin.pessoa;

import java.util.List;

public interface PessoaService {
    void salvarNovaPessoa(Pessoa pessoa);
    List<Pessoa> listarPessoas();
    Pessoa buscarPessoaPorCpf(String cpf);
    boolean deletarPessoa(String cpf);
    boolean atualizarPessoa(String cpf, Pessoa pessoa);
}
