package br.com.guest.restaurante_admin.entidades.usa.impl;

import br.com.guest.restaurante_admin.entidades.produto.Produto;
import br.com.guest.restaurante_admin.entidades.usa.Usa;
import br.com.guest.restaurante_admin.entidades.usa.UsaRepository;
import br.com.guest.restaurante_admin.entidades.usa.mapper.MapeadorUsa;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsaRepositoryImpl implements UsaRepository {

    private JdbcTemplate jdbcTemplate;

    public UsaRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void salvarUso(Usa usa) {
        String sql = "INSERT INTO usa (produto, prato_menu) VALUES (?, ?)";
        jdbcTemplate.update(sql, usa.getProduto(), usa.getPrato());
    }

    @Override
    public List<Usa> getUsosPorProduto(Integer idProduto) {
        String sql = "SELECT * FROM Produto P join Usa U on U.produto = P.id join Menu M on M.numero = U.prato_menu WHERE produto = ?";
        return jdbcTemplate.query(sql, new MapeadorUsa(), idProduto);
    }

    @Override
    public List<Usa> getUsosPorPrato(Integer idPrato) {
        String sql = "SELECT * FROM Produto P join Usa U on U.produto = P.id join Menu M on M.numero = U.prato_menu WHERE prato_menu = ?";
        return jdbcTemplate.query(sql, new MapeadorUsa(), idPrato);
    }

    @Override
    public void deletarUso(Integer idPrato, Integer idProduto) {
        String sql = "DELETE FROM usa WHERE prato_menu = ? AND produto = ?";
        jdbcTemplate.update(sql, idPrato, idProduto);
    }

    @Override
    public void reduzirQuantidadePorPrato(Integer pratoId) {
        String sql = "UPDATE Produto p JOIN Usa u ON p.id = u.produto SET p.quantidade = p.quantidade - u.quantidade WHERE prato_menu = ?";
        jdbcTemplate.update(sql, pratoId);
    }

    @Override
    public void aumentarQuantidadePorPrato(Integer pratoId) {
        String sql = "UPDATE Produto p JOIN Usa u ON p.id = u.produto SET p.quantidade = p.quantidade + u.quantidade WHERE prato_menu = ?";
        jdbcTemplate.update(sql, pratoId);
    }
}
