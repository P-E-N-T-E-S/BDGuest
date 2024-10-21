package br.com.guest.restaurante_admin.entidades.garcom.impl;

import br.com.guest.restaurante_admin.entidades.funcionarios.Funcionario;
import br.com.guest.restaurante_admin.entidades.funcionarios.FuncionarioService;
import br.com.guest.restaurante_admin.entidades.garcom.Garcom;
import br.com.guest.restaurante_admin.entidades.garcom.GarcomRepository;
import br.com.guest.restaurante_admin.entidades.garcom.GarcomService;
import br.com.guest.restaurante_admin.execoes.FuncionarioNaoEncontradoException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GarcomServiceImpl implements GarcomService {

    private GarcomRepository garcomRepository;
    private FuncionarioService funcionarioService;

    @Override
    public void salvarGarcom(String cpf) throws FuncionarioNaoEncontradoException {
        if(funcionarioService.buscarFuncionarioPorcpf(cpf) == null) {
            throw new FuncionarioNaoEncontradoException("Funcionário não encontrado");
        }
        garcomRepository.salvarGarcom(cpf);
    }

    @Override
    public List<Garcom> listarGarcoms() {
        return garcomRepository.listarGarcoms();
    }

    @Override
    public Garcom buscarGarcomPorCpf(String cpf) {
        return null;
    }

    @Override
    public List<Garcom> buscarGarcomPorFiltro(String filtro, String valor) {
        return List.of();
    }

    @Override
    public void removerGarcomPorCpf(String cpf) {

    }

    @Override
    public void removerGarcomPorFiltro(String filtro, String valor) {

    }
}
