package br.com.guest.restaurante_admin.entidades.estoquista.impl;

import br.com.guest.restaurante_admin.entidades.estoque.EstoqueService;
import br.com.guest.restaurante_admin.entidades.estoquista.Estoquista;
import br.com.guest.restaurante_admin.entidades.estoquista.EstoquistaRepository;
import br.com.guest.restaurante_admin.entidades.estoquista.EstoquistaService;
import br.com.guest.restaurante_admin.entidades.funcionarios.Funcionario;
import br.com.guest.restaurante_admin.entidades.funcionarios.FuncionarioService;
import br.com.guest.restaurante_admin.execoes.CampoDeAlteracaoNaoEncontradoException;
import br.com.guest.restaurante_admin.execoes.EstoqueNaoEncontrado;
import br.com.guest.restaurante_admin.execoes.FiltroNaoDisponivelException;
import br.com.guest.restaurante_admin.execoes.FuncionarioNaoEncontradoException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class EstoquistaServiceImpl implements EstoquistaService {

    private FuncionarioService funcionarioService;
    private EstoqueService estoqueService;

    private EstoquistaRepository estoquistaRepository;

    private List<String> camposEstoquista = Arrays.asList("cpf", "cpf_gerente", "estoque");

    public EstoquistaServiceImpl(FuncionarioService funcionarioService, EstoqueService estoqueService, EstoquistaRepository estoquistaRepository) {
        this.funcionarioService = funcionarioService;
        this.estoqueService = estoqueService;
        this.estoquistaRepository = estoquistaRepository;
    }

    @Override
    public void salvarEstoquista(Estoquista estoquista) throws FuncionarioNaoEncontradoException, EstoqueNaoEncontrado {
        if(funcionarioService.buscarFuncionarioPorcpf(estoquista.getCpf()) == null) {
            throw new FuncionarioNaoEncontradoException("Cpf nao encontrado");
        }
        if(estoqueService.buscarEstoque(estoquista.getEstoqueId()) == null) {
            throw new EstoqueNaoEncontrado("ID de estoque nao encontrado");
        }
        estoquistaRepository.salvarEstoquista(estoquista);
    }

    @Override
    public Estoquista buscarEstoquistaPorCpf(String cpf) {
        return estoquistaRepository.buscarEstoquistaPorCpf(cpf);
    }

    @Override
    public List<Estoquista> listarEstoquistas() {
        return estoquistaRepository.listarEstoquistas();
    }

    @Override
    public List<Estoquista> buscarEstoquistasPorFiltro(String filtro, String valor) throws FiltroNaoDisponivelException {
        if(camposEstoquista.contains(filtro)) {
            return estoquistaRepository.buscarEstoquistasPorFiltro(filtro, valor);
        }
        throw new FiltroNaoDisponivelException("Filtro nao disponivel");
    }

    @Override
    public void excluirEstoquistaPorCpf(String cpf) {
        estoquistaRepository.excluirEstoquistaPorCpf(cpf);
    }

    @Override
    public void excluirEstoquistaPorFiltro(String filtro, String valor) {
        if(camposEstoquista.contains(filtro)) {
            estoquistaRepository.excluirEstoquistaPorFiltro(filtro, valor);
        }
        throw new FiltroNaoDisponivelException("Filtro nao disponivel");
    }

    @Override
    public void alterarEstoquistaPorCpf(String cpf, Estoquista estoquista) {
        estoquistaRepository.alterarEstoquistaPorCpf(cpf, estoquista);
    }

    @Override
    public void alterarEstoquistaPorFiltro(String filtro, String valor, String campoAlterado, String valorAlterado) throws FiltroNaoDisponivelException, CampoDeAlteracaoNaoEncontradoException {
        if(camposEstoquista.contains(filtro)) {
            if(camposEstoquista.contains(campoAlterado)) {
                estoquistaRepository.alterarEstoquistaPorFiltro(filtro, valor, campoAlterado, valorAlterado);
            }
            throw new CampoDeAlteracaoNaoEncontradoException("Campo de alterado nao encontrado");
        }
        throw new FiltroNaoDisponivelException("Filtro nao disponivel");
    }
}
