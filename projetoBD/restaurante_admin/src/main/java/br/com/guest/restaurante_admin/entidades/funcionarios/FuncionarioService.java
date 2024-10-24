package br.com.guest.restaurante_admin.entidades.funcionarios;

import br.com.guest.restaurante_admin.execoes.FiltroNaoDisponivelException;
import br.com.guest.restaurante_admin.execoes.PessoaNaoEncontradaException;

import java.util.List;

public interface FuncionarioService {
    void salvarFuncionario(Funcionario funcionario) throws PessoaNaoEncontradaException;
    Funcionario buscarFuncionarioPorcpf(String cpf);
    List<Funcionario> listarFuncionarios();
    List<Funcionario> buscarFuncionarioPorFiltro(String filtro, String valor) throws FiltroNaoDisponivelException;
    void deletarFuncionarioPorCpf(String cpf);
    void atualizarFuncionarioPorCpf(String cpf, Funcionario funcionario);
    void atualizarFuncionarioPorFiltro(String filtro, String valor, String campoAlterado, String valorAlterado);
    void deletarFuncionarioPorFiltro(String filtro, String valor);
}
