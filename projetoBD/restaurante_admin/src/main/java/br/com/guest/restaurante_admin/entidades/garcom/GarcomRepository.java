package br.com.guest.restaurante_admin.entidades.garcom;

import br.com.guest.restaurante_admin.entidades.funcionarios.Funcionario;

import java.util.List;

public interface GarcomRepository {
    void salvarGarcom(Garcom garcom);
    List<Garcom> listarGarcoms();
    Garcom buscarGarcomPorCpf(String cpf);
    List<Garcom> buscarGarcomPorFiltro(String filtro, String valor);
    void removerGarcomPorCpf(String cpf);
    void removerGarcomPorFiltro(String filtro, String valor);
    void removerGarcomPorFiltroDeFuncionario(String filtro, String valor);
    void removerGarcomPorFiltroDePessoa(String filtro, String valor);
    void atualizarGarcomPorCpf(Garcom garcom, String cpf);
    void atualizarGarcomPorFiltro(String filtro, String valor, String campoAlterado, String valorAlterado);
    void atualizarGarcomPorFiltroDeFuncionario(String filtro, String valor, String campoAlterado, String valorAlterado);
    void atualizarGarcomPorFiltroDePessoa(String filtro, String valor, String campoAlterado, String valorAlterado);
}
