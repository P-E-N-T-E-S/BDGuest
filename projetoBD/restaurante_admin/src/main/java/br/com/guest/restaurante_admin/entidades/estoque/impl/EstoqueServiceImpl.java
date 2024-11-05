package br.com.guest.restaurante_admin.entidades.estoque.impl;

import br.com.guest.restaurante_admin.entidades.estoque.Estoque;
import br.com.guest.restaurante_admin.entidades.estoque.EstoqueRepository;
import br.com.guest.restaurante_admin.entidades.estoque.EstoqueService;
import br.com.guest.restaurante_admin.execoes.FiltroNaoDisponivelException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class EstoqueServiceImpl implements EstoqueService {

    private EstoqueRepository estoqueRepository;

    private final List<String> colunasEstoque = Arrays.asList("id", "rua", "numero", "bairro", "estado", "cidade", "cep", "refrigerado");

    public EstoqueServiceImpl(EstoqueRepository estoqueRepository) {
        this.estoqueRepository = estoqueRepository;
    }

    @Override
    public void salvarEstoque(Estoque estoque) {
        estoqueRepository.salvarEstoque(estoque);
    }

    @Override
    public Estoque buscarEstoque(Integer id) {
        return estoqueRepository.buscarEstoque(id);
    }

    @Override
    public List<Estoque> listarEstoque() {
        return estoqueRepository.listarEstoque();
    }

    @Override
    public List<Estoque> buscarEstoquePorFiltro(String filtro, String valor) {
        return estoqueRepository.buscarEstoquePorFiltro(filtro, valor);
    }

    @Override
    public void excluirEstoquePorId(Integer id) {
        estoqueRepository.excluirEstoquePorId(id);
    }

    @Override
    public void excluirEstoquePorFiltro(String filtro, String valor) throws FiltroNaoDisponivelException {
        if(colunasEstoque.contains(filtro)){
            estoqueRepository.excluirEstoquePorFiltro(filtro, valor);
            return;
        }
        throw new FiltroNaoDisponivelException(filtro);
    }

    @Override
    public void alterarEstoquePorId(Estoque estoque, Integer id) {
        estoqueRepository.alterarEstoquePorId(estoque, id);
    }

    @Override
    public void alterarEstoquePorFiltro(String filtro, String valor, String campoAlterado, String valorAlterado) {
        if(colunasEstoque.contains(filtro)){
            estoqueRepository.alterarEstoquePorFiltro(filtro, valor, campoAlterado, valorAlterado);
            return;
        }
        throw new FiltroNaoDisponivelException(filtro);
    }
}
//TODO: tratar os erros de: SQLIntegrityConstraintViolationException com entrada duplicada