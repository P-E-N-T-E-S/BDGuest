package br.com.guest.restaurante_admin.entidades.garcom.impl;

import br.com.guest.restaurante_admin.entidades.garcom.Garcom;
import br.com.guest.restaurante_admin.entidades.garcom.GarcomRepository;
import br.com.guest.restaurante_admin.entidades.garcom.mapper.MapeadorGarcom;
import br.com.guest.restaurante_admin.entidades.gerente.mapper.MapeadorGerente;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GarcomRepositoryImpl implements GarcomRepository {
    //todo fazer função para deletar, buscar e atualizar com filtros da hierarquia
    private final JdbcTemplate jdbcTemplate;

    public GarcomRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void salvarGarcom(Garcom garcom) {
        String sql = "INSERT INTO Garcom (cpf, cpf_gerente) VALUES (?, ?)";
        jdbcTemplate.update(sql, garcom.getCpf(), garcom.getGerenteCpf());
    }

    @Override
    public List<Garcom> listarGarcoms() {
        String sql = "SELECT * FROM Garcom G JOIN Funcionario F on G.cpf = F.cpf JOIN Pessoa P on F.cpf = P.cpf";
        return jdbcTemplate.query(sql, new MapeadorGarcom());
    }

    @Override
    public Garcom buscarGarcomPorCpf(String cpf) {
        return null;
    }

    @Override
    public List<Garcom> buscarGarcomPorFiltro(String filtro, String valor) {
        return List.of();
    }

    @Override
    public void removerGarcomPorCpf(String cpf) {

    }

    @Override
    public void removerGarcomPorFiltro(String filtro, String valor) {

    }
}
