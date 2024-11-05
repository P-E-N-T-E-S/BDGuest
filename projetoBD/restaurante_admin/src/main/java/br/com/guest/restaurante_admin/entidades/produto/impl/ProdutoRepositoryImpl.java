package br.com.guest.restaurante_admin.entidades.produto.impl;

import br.com.guest.restaurante_admin.entidades.produto.Produto;
import br.com.guest.restaurante_admin.entidades.produto.ProdutoRepository;
import br.com.guest.restaurante_admin.entidades.produto.mapper.MapeadorProduto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProdutoRepositoryImpl implements ProdutoRepository {

    private JdbcTemplate jdbcTemplate;

    public ProdutoRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void salvarProduto(Produto produto) {
        String sql = "INSERT INTO Produto (id, nome, validade, quantidade, distribuidora, medida) VALUES(?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, produto.getId(), produto.getNome(), produto.getValidade(), produto.getQuantidade(), produto.getDistribuidora(), produto.getMedida());
    }

    @Override
    public Produto buscarProdutoPorId(Integer id) {
        String sql = "SELECT * FROM Produto WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new MapeadorProduto(), id);
    }

    @Override
    public List<Produto> listarProdutos() {
        String sql = "SELECT * FROM Produto";
        return jdbcTemplate.query(sql, new MapeadorProduto());
    }

    @Override
    public List<Produto> buscarProdutoPorFiltro(String filtro, String valor) {
        String sql = "SELECT * FROM Produto WHERE "+filtro+" LIKE ?";
        return jdbcTemplate.query(sql, new MapeadorProduto(), "%"+valor+"%");
    }

    @Override
    public void removerProdutoPorId(Integer id) {
        String sql = "DELETE FROM Produto WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void removerProdutoPorFiltro(String filtro, String valor) {
        String sql = "DELETE FROM Produto WHERE "+filtro+" LIKE ?";
        jdbcTemplate.update(sql, "%"+valor+"%");
    }

    @Override
    public void atualizarProdutoPorId(Produto produto, Integer id) {
        String sql = "UPDATE Produto SET nome = ?, validade = ?, quantidade = ?, distribuidora = ?, medida = ? WHERE id = ?";
        jdbcTemplate.update(sql, produto.getNome(), produto.getValidade(), produto.getQuantidade(), produto.getDistribuidora(), produto.getMedida(), id);
    }

    @Override
    public void atualizarProdutoPorFiltro(String valor, String filtro, String campoAlterado, String valorAlterado) {
        String sql = "UPDATE Produto SET "+campoAlterado+" = ? WHERE "+filtro+" LIKE ?";
        jdbcTemplate.update(sql, valorAlterado, "%"+valor+"%");
    }

    @Override
    public List<Produto> verificarQuantidadePorPrato(Integer pratoId) {
        String sql = "SELECT * FROM Usa U JOIN Produto P ON U.produto = P.id WHERE U.quantidade < P.quantidade AND U.prato_menu = ?";
        return jdbcTemplate.query(sql, new MapeadorProduto(), pratoId);
    }
}
