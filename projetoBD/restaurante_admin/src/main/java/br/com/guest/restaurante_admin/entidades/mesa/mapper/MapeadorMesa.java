package br.com.guest.restaurante_admin.entidades.mesa.mapper;

import br.com.guest.restaurante_admin.entidades.mesa.Mesa;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeadorMesa implements RowMapper<Mesa> {

    @Override
    public Mesa mapRow(ResultSet rs, int rowNum) throws SQLException {
        Integer id = rs.getInt("numero_id");
        Integer quantidadeCadeiras = rs.getInt("quantidade_cadeiras");

        return new Mesa(id, quantidadeCadeiras);
    }
}
