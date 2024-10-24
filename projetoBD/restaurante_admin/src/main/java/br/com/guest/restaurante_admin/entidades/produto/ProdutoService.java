package br.com.guest.restaurante_admin.entidades.produto;

import java.util.List;

public interface ProdutoService {
    void salvarProduto(Produto produto);
    Produto buscarProdutoPorId(Integer id);
    List<Produto> listarProdutos();
    List<Produto> buscarProdutoPorFiltro(String filtro, String valor);
    void removerProdutoPorId(Integer id);
    void removerProdutoPorFiltro(String filtro, String valor);
    void atualizarProdutoPorId(Produto produto, Integer id);
    void atualizarProdutoPorFiltro(String filtro, String valor, String campoAlterado, String valorAlterado);
}
