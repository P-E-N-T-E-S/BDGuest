package br.com.guest.restaurante_admin.funcionarios.impl;

import br.com.guest.restaurante_admin.execoes.FiltroNaoDisponivelException;
import br.com.guest.restaurante_admin.funcionarios.Funcionario;
import br.com.guest.restaurante_admin.funcionarios.FuncionarioRepository;
import br.com.guest.restaurante_admin.funcionarios.FuncionarioService;
import br.com.guest.restaurante_admin.pessoa.Pessoa;
import br.com.guest.restaurante_admin.pessoa.PessoaService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    private FuncionarioRepository funcionarioRepository;
    private PessoaService pessoaService;
    private  final List<String> colunasFuncionario =  Arrays.asList("cpf", "data_contratacao", "salario", "horario_entrada", "horario_saida");

    public FuncionarioServiceImpl(FuncionarioRepository funcionarioRepository, PessoaService pessoaService) {
        this.funcionarioRepository = funcionarioRepository;
        this.pessoaService = pessoaService;
    }

    @Override
    public boolean salvarFuncionario(Funcionario funcionario) {
        Pessoa pessoa = pessoaService.buscarPessoaPorCpf(funcionario.getCpf());
        if(pessoa == null) {
            return false;
        }
        funcionarioRepository.salvarFuncionario(funcionario);
        return true;
    }

    @Override
    public List<Funcionario> listarFuncionarios() {
        return funcionarioRepository.listarFuncionarios();
    }

    @Override
    public Funcionario buscarFuncionarioPorcpf(String cpf) {
        return funcionarioRepository.buscarFuncionarioPorCpf(cpf);
    }

    @Override
    public List<Funcionario> buscarFuncionarioPorFiltro(String filtro, String valor) throws FiltroNaoDisponivelException {
        if(colunasFuncionario.contains(filtro)) {
            return funcionarioRepository.buscarFuncionarioPorFiltro(filtro, valor);
        }
        throw new FiltroNaoDisponivelException(filtro);
    }

    @Override
    public void deletarFuncionarioPorCpf(String cpf) {
        funcionarioRepository.deletarFuncionarioPorCpf(cpf);
    }

    @Override
    public void atualizarFuncionarioPorCpf(String cpf, Funcionario funcionario) {
        funcionarioRepository.atualizarFuncionarioPorCpf(cpf, funcionario);
    }
}
