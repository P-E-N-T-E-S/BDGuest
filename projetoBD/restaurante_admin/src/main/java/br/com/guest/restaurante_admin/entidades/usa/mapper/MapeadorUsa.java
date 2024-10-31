package br.com.guest.restaurante_admin.entidades.usa.mapper;

import br.com.guest.restaurante_admin.entidades.menu.Prato;
import br.com.guest.restaurante_admin.entidades.produto.Produto;
import br.com.guest.restaurante_admin.entidades.usa.Usa;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class MapeadorUsa implements RowMapper<Usa> {
    @Override
    public Usa mapRow(ResultSet rs, int rowNum) throws SQLException {
        Integer id = rs.getInt("id");
        String nome = rs.getString("P.nome");
        Date validade = rs.getDate("validade");
        Integer quantidade = rs.getInt("quantidade");
        String distribuidora = rs.getString("distribuidora");
        Produto produto = new Produto(id, nome, validade, quantidade, distribuidora);

        Integer numero = rs.getInt("numero");
        String nomePrato  = rs.getString("M.nome");
        String descricao = rs.getString("descricao");
        double preco = rs.getDouble("preco");
        String imagem = rs.getString("imagem");
        String medida = rs.getString("medida");
        Prato prato = new Prato(medida, numero, nomePrato, descricao, imagem, preco);

        Integer idProduto = rs.getInt("produto");
        Integer idPrato = rs.getInt("prato_menu");
        return new Usa(produto, prato, idProduto, idPrato);
    }
}
