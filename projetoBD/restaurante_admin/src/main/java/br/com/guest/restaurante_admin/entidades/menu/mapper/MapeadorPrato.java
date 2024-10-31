package br.com.guest.restaurante_admin.entidades.menu.mapper;

import br.com.guest.restaurante_admin.entidades.menu.Prato;
import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeadorPrato implements RowMapper<Prato> {

    @Override
    public Prato mapRow(ResultSet rs, int rowNum) throws SQLException {
        Integer id = rs.getInt("id");
        String nome = rs.getString("nome");
        String imagemLink = rs.getString("imagem_link");
        String descricao = rs.getString("descricao");
        double preco = rs.getDouble("preco");
        String medida = rs.getString("medida");
        return new Prato(medida, id, nome, imagemLink, descricao, preco);
    }
}
