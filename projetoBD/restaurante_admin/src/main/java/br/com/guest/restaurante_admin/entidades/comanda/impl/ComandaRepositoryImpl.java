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
        String sql = "INSERT INTO Comanda (numero_id, cpf_pessoa, nome_cliente, mesa) values (?, ?, ?, ?)";
        jdbcTemplate.update(sql, ""+comanda.getNumeroId(), comanda.getCpfPessoa(), comanda.getNomeCliente(), comanda.getMesaId());
    }

    @Override
    public Comanda buscarComandaPorId(Integer id) {
        String sql = "SELECT * FROM Mesa M JOIN Comanda C on C.mesa = M.numero_id WHERE C.numero_id = ?";
        return jdbcTemplate.queryForObject(sql, new MapeadorComanda(), ""+id);
    }

    @Override
    public List<Comanda> listarComandas() {
        String sql = "SELECT * FROM Mesa M JOIN Comanda C on C.mesa = M.numero_id";
        return jdbcTemplate.query(sql, new MapeadorComanda());
    }

    @Override
    public void excluirComanda(Integer id) {
        String sql = "DELETE FROM Comanda WHERE numero_id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void alterarComanda(Comanda comanda, Integer id) {
        String sql = "UPDATE Comanda SET cpf_pessoa = ?, nome_cliente = ?, mesa = ? WHERE numero_id = ?";
        jdbcTemplate.update(sql,comanda.getCpfPessoa(), comanda.getNomeCliente(), comanda.getMesaId(), id);
    }
}
