package br.com.guest.restaurante_admin.entidades.estoquista.impl;

import br.com.guest.restaurante_admin.entidades.estoque.EstoqueService;
import br.com.guest.restaurante_admin.entidades.estoquista.Estoquista;
import br.com.guest.restaurante_admin.entidades.estoquista.EstoquistaRepository;
import br.com.guest.restaurante_admin.entidades.estoquista.EstoquistaService;
import br.com.guest.restaurante_admin.execoes.CampoDeAlteracaoNaoEncontradoException;
import br.com.guest.restaurante_admin.execoes.EstoqueNaoEncontrado;
import br.com.guest.restaurante_admin.execoes.FiltroNaoDisponivelException;
import br.com.guest.restaurante_admin.execoes.FuncionarioNaoEncontradoException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class EstoquistaServiceImpl implements EstoquistaService {
    //todo converter as paradas para E. ou Es., ou esperar que o front faça isso

    private EstoqueService estoqueService;

    private EstoquistaRepository estoquistaRepository;

    private List<String> colunasEstoquista = Arrays.asList("cpf", "cpf_gerente", "estoque");
    private  final List<String> colunasFuncionario =  Arrays.asList("data_contratacao", "salario", "horario_entrada", "horario_saida");
    private final List<String> colunasPessoa = Arrays.asList("nome", "rua", "bairro", "estado", "cidade", "cep", "email", "data_nascimento", "telefone");
    private final List<String> colunasEstoque = Arrays.asList("id", "rua", "numero", "bairro", "estado", "cidade", "cep", "refrigerado");

    public EstoquistaServiceImpl(EstoqueService estoqueService, EstoquistaRepository estoquistaRepository) {
        this.estoqueService = estoqueService;
        this.estoquistaRepository = estoquistaRepository;
    }

    @Override
    public Estoquista buscarEstoquistaPorCpf(String cpf) {
        return estoquistaRepository.buscarEstoquistaPorCpf(cpf);
    }

    @Override
    public void salvarEstoquista(Estoquista estoquista) throws FuncionarioNaoEncontradoException, EstoqueNaoEncontrado {
        if(buscarEstoquistaPorCpf(estoquista.getCpf()) == null) {
            throw new FuncionarioNaoEncontradoException(estoquista.getCpf());
        }
        if(estoqueService.buscarEstoque(estoquista.getEstoqueId()) == null) {
            throw new EstoqueNaoEncontrado(estoquista.getEstoqueId()+"");
        }
        estoquistaRepository.salvarEstoquista(estoquista);
    }

    @Override
    public List<Estoquista> listarEstoquistas() {
        return estoquistaRepository.listarEstoquistas();
    }

    @Override
    public List<Estoquista> buscarEstoquistasPorFiltro(String filtro, String valor) throws FiltroNaoDisponivelException {
        if(colunasEstoquista.contains(filtro) || colunasFuncionario.contains(filtro) || colunasPessoa.contains(filtro) || colunasEstoque.contains(filtro) ) {
            return estoquistaRepository.buscarEstoquistasPorFiltro(filtro, valor);
        }
        throw new FiltroNaoDisponivelException(filtro);
    }

    @Override
    public void excluirEstoquistaPorCpf(String cpf) {
        estoquistaRepository.excluirEstoquistaPorCpf(cpf);
    }

    @Override
    public void excluirEstoquistaPorFiltro(String filtro, String valor) {
        if (colunasFuncionario.contains(filtro)) {
            estoquistaRepository.excluirEstoquistaPorFiltroDeFuncionario(filtro, valor);
        }
        if(colunasPessoa.contains(filtro)) {
            estoquistaRepository.excluirEstoquistaPorFiltroDePessoa(filtro, valor);
        }
        if(colunasEstoquista.contains(filtro)) {
            estoquistaRepository.excluirEstoquistaPorFiltro(filtro, valor);
        }
        if(colunasEstoque.contains(filtro)) {
            estoquistaRepository.excluirEstoquistaPorFiltroDeEstoque(filtro, valor);
        }
        throw new FiltroNaoDisponivelException(filtro);
    }

    @Override
    public void alterarEstoquistaPorCpf(String cpf, Estoquista estoquista) {
        estoquistaRepository.alterarEstoquistaPorCpf(cpf, estoquista);
    }

    @Override
    public void alterarEstoquistaPorFiltro(String filtro, String valor, String campoAlterado, String valorAlterado) throws FiltroNaoDisponivelException, CampoDeAlteracaoNaoEncontradoException {
        if(colunasEstoquista.contains(campoAlterado)) {
            if(colunasEstoquista.contains(filtro)) {
                estoquistaRepository.alterarEstoquistaPorFiltro(filtro, valor, campoAlterado, valorAlterado);
            }
            if(colunasFuncionario.contains(filtro)) {
                estoquistaRepository.alterarEstoquistaPorFiltroDeFuncionario(filtro, valor, campoAlterado, valorAlterado);
            }
            if(colunasPessoa.contains(filtro)) {
                estoquistaRepository.alterarEstoquistaPorFiltroDePessoa(filtro, valor, campoAlterado, valorAlterado);
            }
            if (colunasEstoque.contains(filtro)) {
                estoquistaRepository.alterarEstoquistaPorFiltroDeEstoque(filtro, valor, campoAlterado, valorAlterado);
            }
            throw new CampoDeAlteracaoNaoEncontradoException(campoAlterado);
        }
        throw new FiltroNaoDisponivelException(filtro);
    }
}
