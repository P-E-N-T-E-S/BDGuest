package br.com.guest.restaurante_admin.funcionarios.impl;

import br.com.guest.restaurante_admin.funcionarios.Funcionario;
import br.com.guest.restaurante_admin.funcionarios.FuncionarioRepository;
import br.com.guest.restaurante_admin.funcionarios.FuncionarioService;
import br.com.guest.restaurante_admin.pessoa.Pessoa;
import br.com.guest.restaurante_admin.pessoa.PessoaService;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    private FuncionarioRepository funcionarioRepository;
    private PessoaService pessoaService;

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
}
