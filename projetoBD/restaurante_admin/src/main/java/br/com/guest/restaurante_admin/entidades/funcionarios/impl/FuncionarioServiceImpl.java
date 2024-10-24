package br.com.guest.restaurante_admin.entidades.funcionarios.impl;

import br.com.guest.restaurante_admin.entidades.clientes.ClienteService;
import br.com.guest.restaurante_admin.execoes.CampoDeAlteracaoNaoEncontradoException;
import br.com.guest.restaurante_admin.execoes.FiltroNaoDisponivelException;
import br.com.guest.restaurante_admin.entidades.funcionarios.Funcionario;
import br.com.guest.restaurante_admin.entidades.funcionarios.FuncionarioRepository;
import br.com.guest.restaurante_admin.entidades.funcionarios.FuncionarioService;
import br.com.guest.restaurante_admin.entidades.pessoa.Pessoa;
import br.com.guest.restaurante_admin.entidades.pessoa.PessoaService;
import br.com.guest.restaurante_admin.execoes.PessoaNaoEncontradaException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {
    //todo fazer função para deletar, buscar com filtros da hierarquia
    //todo fazer verificação de deleção de pessoa, caso ela não seja nem cliente nem funcionário

    private FuncionarioRepository funcionarioRepository;
    private PessoaService pessoaService;
    private ClienteService clienteService;
    private  final List<String> colunasFuncionario =  Arrays.asList("cpf", "data_contratacao", "salario", "horario_entrada", "horario_saida");
    private final List<String> colunasPessoa = Arrays.asList("nome", "rua", "bairro", "estado", "cidade", "cep", "email", "data_nascimento", "telefone");

    public FuncionarioServiceImpl(FuncionarioRepository funcionarioRepository, PessoaService pessoaService, ClienteService clienteService) {
        this.funcionarioRepository = funcionarioRepository;
        this.pessoaService = pessoaService;
        this.clienteService = clienteService;
    }

    @Override
    public void salvarFuncionario(Funcionario funcionario) throws PessoaNaoEncontradaException {
        Pessoa pessoa = pessoaService.buscarPessoaPorCpf(funcionario.getCpf());
        if(pessoa == null) {
            throw new PessoaNaoEncontradaException(funcionario.getCpf());
        }
        funcionarioRepository.salvarFuncionario(funcionario);
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
        if(colunasFuncionario.contains(filtro) || colunasPessoa.contains(filtro)) {
            return funcionarioRepository.buscarFuncionarioPorFiltro(filtro, valor);
        }
        throw new FiltroNaoDisponivelException(filtro);
    }

    @Override
    public void deletarFuncionarioPorCpf(String cpf) {
        if(clienteService.buscarClientePorCpf(cpf) == null) {
            pessoaService.deletarPessoaPorCpf(cpf);
            return;
        }
        funcionarioRepository.deletarFuncionarioPorCpf(cpf);
    }

    @Override
    public void deletarFuncionarioPorFiltro(String filtro, String valor) {
        //todo fazer uma funcao para procurar se a pessoa é cliente, se ela não for, deletar ela também
        if(colunasPessoa.contains(filtro)) {
            funcionarioRepository.deletarFuncionarioPorFiltroDePessoa(filtro, valor);
            return;
        }
        if(colunasFuncionario.contains(filtro)) {
            funcionarioRepository.deletarFuncionarioPorFiltro(filtro, valor);
            return;
        }
        throw new FiltroNaoDisponivelException(filtro);
    }

    @Override
    public void atualizarFuncionarioPorFiltro(String filtro, String valor, String campoAlterado, String valorAlterado) throws FiltroNaoDisponivelException, CampoDeAlteracaoNaoEncontradoException {
        if(colunasFuncionario.contains(campoAlterado)) {
            if(colunasFuncionario.contains(filtro)) {
                funcionarioRepository.atualizarFuncionarioPorFiltro(filtro, valor, campoAlterado, valorAlterado);
                return;
            }
            if(colunasPessoa.contains(filtro)) {
                funcionarioRepository.atualizarFuncionarioPorFiltroDePessoa(filtro, valor, campoAlterado, valorAlterado);
                return;
            }
            throw new FiltroNaoDisponivelException(filtro);
        }
        throw new CampoDeAlteracaoNaoEncontradoException(campoAlterado);
    }

    @Override
    public void atualizarFuncionarioPorCpf(String cpf, Funcionario funcionario) {
        funcionarioRepository.atualizarFuncionarioPorCpf(cpf, funcionario);
    }
}
