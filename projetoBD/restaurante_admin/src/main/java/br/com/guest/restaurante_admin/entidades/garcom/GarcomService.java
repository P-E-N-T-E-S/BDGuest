package br.com.guest.restaurante_admin.entidades.garcom;

import br.com.guest.restaurante_admin.entidades.funcionarios.Funcionario;
import br.com.guest.restaurante_admin.execoes.FuncionarioNaoEncontradoException;

import java.util.List;

public interface GarcomService {
    void salvarGarcom(Garcom garcom) throws FuncionarioNaoEncontradoException;
    List<Garcom> listarGarcoms();
    Garcom buscarGarcomPorCpf(String cpf);
    void removerGarcomPorCpf(String cpf);
}
