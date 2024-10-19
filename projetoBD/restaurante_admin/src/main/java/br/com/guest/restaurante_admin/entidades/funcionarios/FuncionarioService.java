package br.com.guest.restaurante_admin.entidades.funcionarios;

import java.util.List;

public interface FuncionarioService {
    boolean salvarFuncionario(Funcionario funcionario);
    Funcionario buscarFuncionarioPorcpf(String cpf);
    List<Funcionario> listarFuncionarios();
    List<Funcionario> buscarFuncionarioPorFiltro(String filtro, String valor);
    void deletarFuncionarioPorCpf(String cpf);
    void atualizarFuncionarioPorCpf(String cpf, Funcionario funcionario);
    void atualizarFuncionarioPorFiltro(String filtro, String valor, String campoAlterado, String valorAlterado);
    void deletarFuncionarioPorFiltro(String filtro, String valor);
}
