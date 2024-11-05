package br.com.guest.restaurante_admin.entidades.contem.mapper;

import br.com.guest.restaurante_admin.entidades.contem.Contem;
import br.com.guest.restaurante_admin.entidades.estoque.Estoque;
import br.com.guest.restaurante_admin.entidades.produto.Produto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class MapeadorContem implements RowMapper<Contem> {
    @Override
    public Contem mapRow(ResultSet rs, int rowNum) throws SQLException {
        Integer idEstoque = rs.getInt("E.id");
        String rua = rs.getString("rua");
        Integer numero = rs.getInt("numero");
        String bairro = rs.getString("bairro");
        String estado = rs.getString("estado");
        String cidade = rs.getString("cidade");
        String cep = rs.getString("cep");
        boolean refrigerado = rs.getBoolean("refrigerado");
        Estoque estoque = new Estoque(idEstoque, rua, numero, bairro, estado, cidade, cep, refrigerado);

        Integer idProduto = rs.getInt("P.id");
        String nome = rs.getString("nome");
        Date validade = rs.getDate("validade");
        Integer quantidade = rs.getInt("quantidade");
        String distribuidora = rs.getString("distribuidora");
        String medida = rs.getString("medida");

        Produto produto = new Produto(idProduto, nome, validade, quantidade, distribuidora, null, medida);

        return new Contem(produto, estoque, idProduto, idEstoque);
    }
}
