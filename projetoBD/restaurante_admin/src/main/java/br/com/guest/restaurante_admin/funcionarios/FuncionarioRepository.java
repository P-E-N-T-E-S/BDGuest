package br.com.guest.restaurante_admin.funcionarios;

import java.util.List;

public interface FuncionarioRepository {
    void salvarFuncionario(Funcionario funcionario);
    List<Funcionario> listarFuncionarios();
    Funcionario buscarFuncionarioPorCpf(String cpf);
    List<Funcionario> buscarFuncionarioPorFiltro(String filtro, String valor);
    void atualizarFuncionarioPorCpf(String cpf, Funcionario funcionario);
    void deletarFuncionarioPorCpf(String cpf);
    void atualizarFuncionarioPorFiltro(String filtro, String valor, String campoAlterado, String valorAlterado);
    void deletarFuncionarioPorFiltro(String filtro, String valor);

}
