package br.com.guest.restaurante_admin.entidades.estoque.impl;

import br.com.guest.restaurante_admin.entidades.estoque.Estoque;
import br.com.guest.restaurante_admin.entidades.estoque.EstoqueRepository;
import br.com.guest.restaurante_admin.entidades.estoque.mapper.MapeadorEstoque;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EstoqueRepositoryImpl implements EstoqueRepository {

    private JdbcTemplate jdbcTemplate;

    public EstoqueRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void salvarEstoque(Estoque estoque) {
        String sql = "INSERT INTO Estoque (id, rua, numero, bairro, estado, cidade, cep, refrigerado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, estoque.getId(),
                estoque.getRua(),
                estoque.getNumero(),
                estoque.getBairro(),
                estoque.getEstado(),
                estoque.getCidade(),
                estoque.getCep(),
                estoque.getRefrigerado());
    }

    @Override
    public Estoque buscarEstoque(Integer id) {
        String sql = "SELECT * FROM Estoque WHERE id = ?";
        try{
        return jdbcTemplate.queryForObject(sql, new MapeadorEstoque(), id);
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public List<Estoque> listarEstoque() {
        String sql = "SELECT * FROM Estoque";
        return jdbcTemplate.query(sql, new MapeadorEstoque());
    }

    @Override
    public List<Estoque> buscarEstoquePorFiltro(String filtro, String valor) {
        String sql = "SELECT * FROM Estoque WHERE "+filtro+" LIKE ?";
        return jdbcTemplate.query(sql, new MapeadorEstoque(), "%"+valor+"%");
    }

    @Override
    public void excluirEstoquePorId(Integer id) {
        String sql = "DELETE FROM Estoque WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void excluirEstoquePorFiltro(String filtro, String valor) {
        String sql = "DELETE FROM Estoque WHERE "+filtro+" LIKE ?";
        jdbcTemplate.update(sql, "%"+valor+"%");
    }

    @Override
    public void alterarEstoquePorId(Estoque estoque, Integer id) {
        String sql = "UPDATE Estoque SET rua = ?, numero = ?, bairro = ?, estado = ?, cidade = ?, cep = ?, refrigerado = ? WHERE id = ?";
        jdbcTemplate.update(sql, estoque.getRua(), estoque.getNumero(), estoque.getBairro(), estoque.getEstado(), estoque.getCidade(), estoque.getCep(), estoque.getRefrigerado(), id);
    }

    @Override
    public void alterarEstoquePorFiltro(String filtro, String valor, String campoAlterado, String valorAlterado) {
        String sql = "UPDATE Estoque SET "+campoAlterado+" = ? WHERE "+filtro+" LIKE ?";
        jdbcTemplate.update(sql, valorAlterado, "%"+valor+"%");
    }
}
