package br.com.guest.restaurante_admin.entidades.garcom.impl;

import br.com.guest.restaurante_admin.entidades.garcom.Garcom;
import br.com.guest.restaurante_admin.entidades.garcom.GarcomRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GarcomRepositoryImpl implements GarcomRepository {
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
        return List.of();
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
