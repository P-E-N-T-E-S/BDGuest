package br.com.guest.restaurante_admin.entidades.estoque.mapper;

import br.com.guest.restaurante_admin.entidades.estoque.Estoque;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeadorEstoque implements RowMapper<Estoque> {
    @Override
    public Estoque mapRow(ResultSet rs, int rowNum) throws SQLException {
        Integer id = rs.getInt("id");
        String rua = rs.getString("rua");
        Integer numero = rs.getInt("numero");
        String bairro = rs.getString("bairro");
        String estado = rs.getString("estado");
        String cidade = rs.getString("cidade");
        String cep = rs.getString("cep");
        boolean refrigerado = rs.getBoolean("refrigerado");
        return new Estoque(id, rua, numero, bairro, estado, cidade, cep, refrigerado);
    }
}
