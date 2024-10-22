package br.com.guest.restaurante_admin.entidades.estoque.impl;

import br.com.guest.restaurante_admin.entidades.estoque.Estoque;
import br.com.guest.restaurante_admin.entidades.estoque.EstoqueRepository;
import br.com.guest.restaurante_admin.entidades.estoque.EstoqueService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstoqueServiceImpl implements EstoqueService {

    private EstoqueRepository estoqueRepository;

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
    public void excluirEstoquePorFiltro(String filtro, String valor) {
        estoqueRepository.excluirEstoquePorFiltro(filtro, valor);
    }

    @Override
    public void alterarEstoquePorId(Estoque estoque, Integer id) {
        estoqueRepository.alterarEstoquePorId(estoque, id);
    }

    @Override
    public void alterarEstoquePorFiltro(String filtro, String valor, String campoAlterado, String valorAlterado) {
        estoqueRepository.alterarEstoquePorFiltro(filtro, valor, campoAlterado, valorAlterado);
    }
}
