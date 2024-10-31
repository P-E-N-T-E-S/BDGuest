package br.com.guest.restaurante_admin.entidades.menu.impl;

import br.com.guest.restaurante_admin.entidades.menu.Prato;
import br.com.guest.restaurante_admin.entidades.menu.PratoRepository;
import br.com.guest.restaurante_admin.entidades.menu.mapper.MapeadorPrato;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PratoRepositoryImpl implements PratoRepository {

    private JdbcTemplate jdbcTemplate;

    public PratoRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void salvarPrato(Prato prato) {
        String sql = "INSERT INTO Prato (nome, imagem_link, descricao, preco) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, prato.getNome(), prato.getImagemLink(), prato.getDescricao(), prato.getPreco());
    }

    @Override
    public Prato buscarPratoPorId(Long id) {
        String sql = "SELECT * FROM Prato WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new MapeadorPrato(), id);
    }

    @Override
    public List<Prato> listarPratos() {
        String sql = "SELECT * FROM Prato";
        return jdbcTemplate.query(sql, new MapeadorPrato());
    }

    @Override
    public List<Prato> buscarPratoPorFiltro(String filtro, String valor) {
        String sql = "SELECT * FROM Prato WHERE "+filtro+" LIKE ?";
        return jdbcTemplate.query(sql, new MapeadorPrato(), "%"+valor+"%");
    }

    @Override
    public void removerPratoPorId(Long id) {
        String sql = "DELETE FROM Prato WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void atualizarPrato(Prato prato, Long id) {
        String sql = "UPDATE Prato SET nome = ?, imagem_link = ?, descricao = ?, preco = ? WHERE id = ?";
        jdbcTemplate.update(sql, prato.getNome(), prato.getImagemLink(), prato.getDescricao(), prato.getPreco(), id);
    }
}
