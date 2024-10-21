package br.com.guest.restaurante_admin.entidades.gerente.impl;

import br.com.guest.restaurante_admin.entidades.funcionarios.FuncionarioRepository;
import br.com.guest.restaurante_admin.entidades.garcom.Garcom;
import br.com.guest.restaurante_admin.entidades.gerente.Gerente;
import br.com.guest.restaurante_admin.entidades.gerente.GerenteRepository;
import br.com.guest.restaurante_admin.entidades.gerente.GerenteService;
import br.com.guest.restaurante_admin.execoes.FuncionarioNaoEncontradoException;
import br.com.guest.restaurante_admin.execoes.IdentificadorNaoEncontradoException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GerenteServiceImpl implements GerenteService {

    private GerenteRepository gerenteRepository;
    private FuncionarioRepository funcionarioRepository;

    public GerenteServiceImpl(GerenteRepository gerenteRepository, FuncionarioRepository funcionarioRepository) {
        this.gerenteRepository = gerenteRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    @Override
    public void salvarNovoGerente(Gerente gerente) throws FuncionarioNaoEncontradoException {
        if(funcionarioRepository.buscarFuncionarioPorCpf(gerente.getCpf()) == null) {
            throw new FuncionarioNaoEncontradoException("Cpf não encontrado como funcionario");
        }
        gerenteRepository.salvarNovoGerente(gerente);
    }

    @Override
    public Gerente buscarGerentePorCpf(String cpf) {
        return gerenteRepository.buscarGerentePorCpf(cpf);
    }

    @Override
    public List<Gerente> listarGerentes() {
        return gerenteRepository.listarGerentes();
    }

    @Override
    public List<Garcom> listarGarconsGerenciados(String cpf) {
        return gerenteRepository.listarGarconsGerenciados(cpf);
    }

    @Override
    public void deletarGerentePorCpf(String cpf) throws IdentificadorNaoEncontradoException {
        if(gerenteRepository.buscarGerentePorCpf(cpf) == null) {
            throw new IdentificadorNaoEncontradoException("Cpf não encontrado");
        }
        gerenteRepository.deletarGerentePorCpf(cpf);
    }
}
