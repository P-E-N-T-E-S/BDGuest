package br.com.guest.restaurante_admin.entidades.garcom;

import br.com.guest.restaurante_admin.entidades.funcionarios.Funcionario;

import java.util.List;

public interface GarcomService {
    void salvarGarcom(String cpf);
    List<Garcom> listarGarcoms();
    Garcom buscarGarcomPorCpf(String cpf);
    List<Garcom> buscarGarcomPorFiltro(String filtro, String valor);
    void removerGarcomPorCpf(String cpf);
    void removerGarcomPorFiltro(String filtro, String valor);
}
