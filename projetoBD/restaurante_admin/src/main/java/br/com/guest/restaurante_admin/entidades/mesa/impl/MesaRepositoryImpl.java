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
        String sql = "INSERT INTO Mesa (numero_id, quantidade_cadeiras, cpf_garco) VALUES (?,?,?)";
        jdbcTemplate.update(sql, mesa.getNumeroId(), mesa.getQuantidadeCadeiras(), mesa.getCpfGarcom());
    }

    @Override
    public Mesa acharMesaPorId(Integer id) {
        String sql = "SELECT * FROM Mesa M JOIN Garcom G on M.cpf_garcom = G.cpf join Funcionario F on G.cpf = F.cpf join Pessoa P on P.cpf = F.cpf WHERE numero_id = ?";
        return jdbcTemplate.queryForObject(sql, new MapeadorMesa(), id);
    }

    @Override
    public List<Mesa> listarMesas() {
        String sql = "SELECT * FROM Mesa M JOIN Garcom G on M.cpf_garcom = G.cpf join Funcionario F on G.cpf = F.cpf join Pessoa P on P.cpf = F.cpf";
        return jdbcTemplate.query(sql, new MapeadorMesa());
    }

    @Override
    public List<Mesa> listarMesasPorFiltro(String filtro, String valor) {
        String sql = "SELECT * FROM Mesa M JOIN Garcom G on M.cpf_garcom = G.cpf join Funcionario F on G.cpf = F.cpf join Pessoa P on P.cpf = F.cpf WHERE "+filtro+" LIKE ?";
        return jdbcTemplate.query(sql, new MapeadorMesa(), valor);
    }

    @Override
    public void excluirMesaPorId(Integer id) {
        String sql = "DELETE FROM Mesa M WHERE numero_id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void alterarMesaPorID(Mesa mesa, Integer id) {
        String sql = "UPDATE Mesa M SET quantidade_cadeiras = ?, cpf_garcom = ? WHERE numero_id = ?";
        jdbcTemplate.update(sql, mesa.getQuantidadeCadeiras(), mesa.getCpfGarcom(), id);
    }
}
