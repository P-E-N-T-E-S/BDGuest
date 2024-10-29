package br.com.guest.restaurante_admin.entidades.mesa.impl;

import br.com.guest.restaurante_admin.entidades.mesa.Mesa;
import br.com.guest.restaurante_admin.entidades.mesa.MesaRepository;
import br.com.guest.restaurante_admin.entidades.mesa.mapper.MapeadorMesa;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MesaRepositoryImpl implements MesaRepository {

    private JdbcTemplate jdbcTemplate;

    public MesaRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void salvarMesa(Mesa mesa) {
        String sql = "INSERT INTO Mesa (numero_id, quantidade_cadeiras) VALUES (?,?,?)";
        jdbcTemplate.update(sql, mesa.getNumeroId(), mesa.getQuantidadeCadeiras());
    }

    @Override
    public Mesa acharMesaPorId(Integer id) {
        String sql = "SELECT * FROM Mesa WHERE numero_id = ?";
        return jdbcTemplate.queryForObject(sql, new MapeadorMesa(), id);
    }

    @Override
    public List<Mesa> listarMesas() {
        String sql = "SELECT * FROM Mesa";
        return jdbcTemplate.query(sql, new MapeadorMesa());
    }

    @Override
    public List<Mesa> listarMesaPorGarcom(String cpfGarcom) {
        String sql = "SELECT * FROM Mesa WHERE id in (SELECT fk_Mesas_numero_id FROM Atende WHERE fk_Garcom_cpf = ?)";
        return jdbcTemplate.query(sql, new MapeadorMesa(), cpfGarcom);
    }

    @Override
    public void excluirMesaPorId(Integer id) {
        String sql = "DELETE FROM Mesa WHERE numero_id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void alterarMesaPorID(Mesa mesa, Integer id) {
        String sql = "UPDATE Mesa SET quantidade_cadeiras = ? WHERE numero_id = ?";
        jdbcTemplate.update(sql, mesa.getQuantidadeCadeiras(), id);
    }
}
