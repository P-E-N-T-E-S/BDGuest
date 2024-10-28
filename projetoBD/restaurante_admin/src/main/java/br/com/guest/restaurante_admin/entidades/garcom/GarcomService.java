package br.com.guest.restaurante_admin.entidades.garcom;

import br.com.guest.restaurante_admin.entidades.funcionarios.Funcionario;
import br.com.guest.restaurante_admin.execoes.FuncionarioNaoEncontradoException;

import java.util.List;

public interface GarcomService {
    void salvarGarcom(Garcom garcom) throws FuncionarioNaoEncontradoException;
    List<Garcom> listarGarcoms();
    List<Garcom> buscarGarcomPorFiltro(String filtro, String valor);
    Garcom buscarGarcomPorCpf(String cpf);
    void atualizarGarcomPorCpf(Garcom garcom, String cpf);
    void atualizarGarcomPorFiltro(String filtro, String valor, String campoAlterado, String valorAlterado);
    void atualizarMesas(List<Integer> mesas, String cpfGarcom);
    void removerGarcomPorCpf(String cpf);
    void removerGarcomPorFiltro(String filtro, String valor);
}
