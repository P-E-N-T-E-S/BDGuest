package br.com.guest.restaurante_admin.entidades.comanda.impl;

import br.com.guest.restaurante_admin.entidades.comanda.Comanda;
import br.com.guest.restaurante_admin.entidades.comanda.ComandaRepository;
import br.com.guest.restaurante_admin.entidades.comanda.mapper.MapeadorComanda;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ComandaRepositoryImpl implements ComandaRepository {

    private JdbcTemplate jdbcTemplate;

    public ComandaRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void salvarComanda(Comanda comanda) {
        String sql = "INSERT INTO Comanda (numero_id) values (?)";
        jdbcTemplate.update(sql, ""+comanda.getNumeroId());
    }

    @Override
    public Comanda buscarComandaPorId(Integer id) {
        String sql = "SELECT * FROM Comanda WHERE numero_id = ?";
        return jdbcTemplate.queryForObject(sql, new MapeadorComanda(), ""+id);
    }

    @Override
    public List<Comanda> listarComandas() {
        String sql = "SELECT * FROM Comanda";
        return jdbcTemplate.query(sql, new MapeadorComanda());
    }

    @Override
    public void excluirComanda(String id) {
        String sql = "DELETE FROM Comanda WHERE numero_id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void alterarComanda(Comanda comanda, Integer id) {
        String sql = "UPDATE Comanda SET cpf_pessoa = ?, acesso = ?, nome_cliente = ? WHERE numero_id = ?";
        jdbcTemplate.update(sql, ""+comanda.getNumeroId(),comanda.getCpfPessoa(), comanda.getAcesso(), comanda.getNomeCliente(), id);
    }
}