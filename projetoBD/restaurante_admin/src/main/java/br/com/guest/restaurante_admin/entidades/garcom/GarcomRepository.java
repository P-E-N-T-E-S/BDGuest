package br.com.guest.restaurante_admin.entidades.garcom;

import br.com.guest.restaurante_admin.entidades.funcionarios.Funcionario;

import java.util.List;

public interface GarcomRepository {
    void salvarGarcom(Garcom garcom);
    List<Garcom> listarGarcoms();
    Garcom buscarGarcomPorCpf(String cpf);
    List<Garcom> buscarGarcomPorFiltro(String filtro, String valor);
    void removerGarcomPorCpf(String cpf);
}
