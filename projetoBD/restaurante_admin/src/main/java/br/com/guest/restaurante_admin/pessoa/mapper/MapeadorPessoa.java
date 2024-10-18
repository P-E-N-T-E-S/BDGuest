package br.com.guest.restaurante_admin.pessoa.mapper;

import br.com.guest.restaurante_admin.pessoa.Pessoa;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class MapeadorPessoa implements RowMapper<Pessoa> {

    @Override
    public Pessoa mapRow(ResultSet rs, int rowNum) throws SQLException {
        String cpf = rs.getString("cpf");
        String nome = rs.getString("nome");
        String rua = rs.getString("rua");
        String bairro = rs.getString("bairro");
        String estado = rs.getString("estado");
        String cidade = rs.getString("cidade");
        String cep = rs.getString("cep");
        String email = rs.getString("email");
        Date data_nascimento = rs.getDate("data_nascimento");
        String telefone = rs.getString("telefone");
        String telefone2 = rs.getString("telefone_2");
        return new Pessoa(cpf, nome, rua, bairro, estado, cidade, cep, email, data_nascimento, telefone, telefone2);
    }
}
