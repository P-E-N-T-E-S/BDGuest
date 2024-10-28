package br.com.guest.restaurante_admin.entidades.contem.impl;

import br.com.guest.restaurante_admin.entidades.contem.Contem;
import br.com.guest.restaurante_admin.entidades.contem.ContemRepository;
import br.com.guest.restaurante_admin.entidades.contem.mapper.MapeadorContem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContemRepositoryImpl implements ContemRepository {

    private JdbcTemplate jdbcTemplate;

    public ContemRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void salvarContem(Contem contem) {
        String sql = "INSERT INTO Contem (produto, estoque) VALUES (?, ?)";
        jdbcTemplate.update(sql, contem.getProduto(), contem.getEstoque());
    }

    @Override
    public List<Contem> buscarContemPorProduto(Integer idProduto) {
        String sql = "SELECT * FROM Estoque E join Contem C on E.id = C.estoque join Produto P on P.id = C.produto WHERE produto = ?";
        return jdbcTemplate.query(sql, new MapeadorContem(), idProduto);
    }

    @Override
    public List<Contem> buscarContemPorEstoque(Integer idEstoque) {
        String sql = "SELECT * FROM Estoque E join Contem C on E.id = C.estoque join Produto P on P.id = C.produto WHERE estoque = ?";
        return jdbcTemplate.query(sql, new MapeadorContem(), idEstoque);
    }

    @Override
    public void excluirContem(Contem contem) {
        String sql = "DELETE FROM Contem WHERE produto = ? AND estoque = ?";
        jdbcTemplate.update(sql, contem.getIdProduto(), contem.getIdEstoque());
    }
}
