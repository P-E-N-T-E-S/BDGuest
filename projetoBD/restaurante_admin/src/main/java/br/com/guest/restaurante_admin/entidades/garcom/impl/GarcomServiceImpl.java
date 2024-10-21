package br.com.guest.restaurante_admin.entidades.garcom.impl;

import br.com.guest.restaurante_admin.entidades.funcionarios.Funcionario;
import br.com.guest.restaurante_admin.entidades.funcionarios.FuncionarioService;
import br.com.guest.restaurante_admin.entidades.garcom.Garcom;
import br.com.guest.restaurante_admin.entidades.garcom.GarcomRepository;
import br.com.guest.restaurante_admin.entidades.garcom.GarcomService;
import br.com.guest.restaurante_admin.execoes.FuncionarioNaoEncontradoException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class GarcomServiceImpl implements GarcomService {
    //todo fazer função para deletar, buscar e atualizar com filtros da hierarquia

    private GarcomRepository garcomRepository;
    private FuncionarioService funcionarioService;

    public GarcomServiceImpl(GarcomRepository garcomRepository, FuncionarioService funcionarioService) {
        this.garcomRepository = garcomRepository;
        this.funcionarioService = funcionarioService;
    }

    @Override
    public void salvarGarcom(Garcom garcom) throws FuncionarioNaoEncontradoException {
        if(funcionarioService.buscarFuncionarioPorcpf(garcom.getCpf()) == null) {
            throw new FuncionarioNaoEncontradoException("Funcionário não encontrado");
        }
        garcomRepository.salvarGarcom(garcom);
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
    public void removerGarcomPorCpf(String cpf) {
        garcomRepository.removerGarcomPorCpf(cpf);
    }
}