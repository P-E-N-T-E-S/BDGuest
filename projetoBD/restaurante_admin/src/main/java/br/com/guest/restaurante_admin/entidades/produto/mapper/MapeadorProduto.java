package br.com.guest.restaurante_admin.entidades.produto.mapper;

import br.com.guest.restaurante_admin.entidades.produto.Produto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class MapeadorProduto implements RowMapper<Produto> {
    @Override
    public Produto mapRow(ResultSet rs, int rowNum) throws SQLException {
        Integer id = rs.getInt("id");
        String nome = rs.getString("nome");
        Date validade = rs.getDate("validade");
        Integer quantidade = rs.getInt("quantidade");
        String distribuidora = rs.getString("distribuidora");
        String medida = rs.getString("medida");

        return new Produto(id, nome, validade, quantidade, distribuidora, null, medida);
    }
}
