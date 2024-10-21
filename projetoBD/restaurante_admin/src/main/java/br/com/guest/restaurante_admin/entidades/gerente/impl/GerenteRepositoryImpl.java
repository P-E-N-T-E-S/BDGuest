package br.com.guest.restaurante_admin.entidades.gerente.impl;

import br.com.guest.restaurante_admin.entidades.garcom.Garcom;
import br.com.guest.restaurante_admin.entidades.garcom.mapper.MapeadorGarcom;
import br.com.guest.restaurante_admin.entidades.gerente.Gerente;
import br.com.guest.restaurante_admin.entidades.gerente.GerenteRepository;
import br.com.guest.restaurante_admin.entidades.gerente.mapper.MapeadorGerente;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GerenteRepositoryImpl implements GerenteRepository {

    private JdbcTemplate jdbcTemplate;

    public GerenteRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void salvarNovoGerente(Gerente gerente) {
        String sql = "INSERT INTO Gerente (cpf) VALUES (?)";
        jdbcTemplate.update(sql, gerente.getCpf());
    }

    @Override
    public Gerente buscarGerentePorCpf(String cpf) {
        String sql = "SELECT * FROM Gerente G JOIN Funcionario F on G.cpf = F.cpf JOIN Pessoa P on F.cpf = P.cp WHERE cpf = ?";
        return jdbcTemplate.queryForObject(sql, new MapeadorGerente(), cpf);
    }

    @Override
    public List<Gerente> listarGerentes() {
        String sql = "SELECT * FROM Gerente G JOIN Funcionario F on G.cpf = F.cpf JOIN Pessoa P on F.cpf = P.cpf";
        return jdbcTemplate.query(sql, new MapeadorGerente());
    }

    @Override
    public List<Garcom> listarGarconsGerenciados(String cpf) {
        String sql = "SELECT * FROM Garcom G JOIN Funcionario F on G.cpf = F.cpf JOIN Pessoa P on F.cpf = P.cpf JOIN Gerente Ge on G.cpf_gerente = Ge.cpf WHERE Ge.cpf = ?";
        return jdbcTemplate.query(sql, new MapeadorGarcom(), cpf);
    }

    @Override
    public void deletarGerentePorCpf(String cpf) {
        String sql = "DELETE FROM Gerente WHERE Cpf = ?";
        jdbcTemplate.update(sql, cpf);
    }
}
