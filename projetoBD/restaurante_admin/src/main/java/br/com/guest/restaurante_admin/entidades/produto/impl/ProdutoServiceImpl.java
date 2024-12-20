package br.com.guest.restaurante_admin.entidades.produto.impl;

import br.com.guest.restaurante_admin.entidades.contem.Contem;
import br.com.guest.restaurante_admin.entidades.contem.ContemService;
import br.com.guest.restaurante_admin.entidades.estoque.EstoqueService;
import br.com.guest.restaurante_admin.entidades.produto.Produto;
import br.com.guest.restaurante_admin.entidades.produto.ProdutoRepository;
import br.com.guest.restaurante_admin.entidades.produto.ProdutoService;
import br.com.guest.restaurante_admin.execoes.CampoDeAlteracaoNaoEncontradoException;
import br.com.guest.restaurante_admin.execoes.EstoqueNaoEncontradoException;
import br.com.guest.restaurante_admin.execoes.EstoqueNaoInformadoException;
import br.com.guest.restaurante_admin.execoes.FiltroNaoDisponivelException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private ProdutoRepository produtoRepository;
    private ContemService contemService;
    private EstoqueService estoqueService;

    private List<String> camposProduto = List.of("id", "nome", "validade", "quantidade", "distribuidora");

    public ProdutoServiceImpl(ProdutoRepository produtoRepository, ContemService contemService, EstoqueService estoqueService) {
        this.produtoRepository = produtoRepository;
        this.contemService = contemService;
        this.estoqueService = estoqueService;
    }

    @Override
    public void salvarProduto(Produto produto) throws EstoqueNaoInformadoException {
        produtoRepository.salvarProduto(produto);
        if (produto.getEstoques() != null) {
            for (Integer estoque : produto.getEstoques()) {
                if(estoqueService.buscarEstoque(estoque) == null){
                    throw new EstoqueNaoEncontradoException(estoque.toString());
                }
                contemService.salvarContem(new Contem(produto.getId(), estoque));
            }
        }else{
            throw new EstoqueNaoInformadoException("Estoque nao informado");
        }
    }

    @Override
    public Produto buscarProdutoPorId(Integer id) {
        return produtoRepository.buscarProdutoPorId(id);
    }

    @Override
    public List<Produto> listarProdutos() {
        return produtoRepository.listarProdutos();
    }

    @Override
    public List<Produto> buscarProdutoPorFiltro(String filtro, String valor) throws FiltroNaoDisponivelException {
        if (camposProduto.contains(filtro)) {
            return produtoRepository.buscarProdutoPorFiltro(filtro, valor);
        }
        throw new FiltroNaoDisponivelException(filtro);
    }

    @Override
    public void removerProdutoPorId(Integer id) {
        produtoRepository.removerProdutoPorId(id);
    }

    @Override
    public void removerProdutoPorFiltro(String filtro, String valor) throws FiltroNaoDisponivelException{
        if (camposProduto.contains(filtro)) {
            produtoRepository.removerProdutoPorFiltro(filtro, valor);
        }
        throw new FiltroNaoDisponivelException(filtro);
    }

    @Override
    public void atualizarProdutoPorId(Produto produto, Integer id) {
        produtoRepository.atualizarProdutoPorId(produto, id);
    }

    @Override
    public void atualizarProdutoPorFiltro(String filtro, String valor, String campoAlterado, String valorAlterado) throws FiltroNaoDisponivelException, CampoDeAlteracaoNaoEncontradoException {
        if (camposProduto.contains(filtro)) {
            if (camposProduto.contains(campoAlterado)) {
                produtoRepository.atualizarProdutoPorFiltro(filtro, valor, campoAlterado, valorAlterado);
            }
            throw new CampoDeAlteracaoNaoEncontradoException(campoAlterado);
        }
        throw new FiltroNaoDisponivelException(filtro);
    }

    @Override
    public List<Produto> verificarQuantidadePorPrato(Integer pratoId, Integer quantidade) {
        return produtoRepository.verificarQuantidadePorPrato(pratoId, quantidade); //TODO: ajustar aqui por quantidade
    }
}
