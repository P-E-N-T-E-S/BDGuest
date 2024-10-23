package br.com.guest.restaurante_admin.entidades.estoquista.mapper;

import br.com.guest.restaurante_admin.entidades.estoque.Estoque;
import br.com.guest.restaurante_admin.entidades.estoquista.Estoquista;
import br.com.guest.restaurante_admin.entidades.funcionarios.Funcionario;
import br.com.guest.restaurante_admin.entidades.pessoa.Pessoa;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.Date;

public class MapeadorEstoquista implements RowMapper<Estoquista> {
    @Override
    public Estoquista mapRow(ResultSet rs, int rowNum) throws SQLException {
        String cpfPessoa = rs.getString("P.cpf");
        String nome = rs.getString("nome");
        String rua = rs.getString("P.rua");
        String bairro = rs.getString("P.bairro");
        String estado = rs.getString("P.estado");
        String cidade = rs.getString("P.cidade");
        String cep = rs.getString("P.cep");
        String email = rs.getString("email");
        Date data_nascimento = rs.getDate("data_nascimento");
        String telefone = rs.getString("telefone");
        String telefone2 = rs.getString("telefone_2");
        Pessoa pessoa = new Pessoa(cpfPessoa, nome, rua, bairro, estado, cidade, cep, email, data_nascimento, telefone, telefone2);

        String cpfFuncionario = rs.getString("F.cpf");
        Date dataContratacao = rs.getDate("data_contratacao");
        double salario = rs.getDouble("salario");
        LocalTime horaEntrada = rs.getTime("horario_entrada").toLocalTime();
        LocalTime horaSaida = rs.getTime("horario_saida").toLocalTime();
        Funcionario funcionario =  new Funcionario(pessoa, cpfFuncionario, dataContratacao, salario, horaEntrada, horaSaida);

        Integer id = rs.getInt("id");
        String ruaEstoque = rs.getString("E.rua");
        Integer numero = rs.getInt("numero");
        String bairroEstoque = rs.getString("E.bairro");
        String estadoEstoque = rs.getString("E.estado");
        String cidadeEstoque = rs.getString("E.cidade");
        String cepEstoque = rs.getString("E.cep");
        boolean refrigerado = rs.getBoolean("refrigerado");
        Estoque estoque = new Estoque(id, rua, numero, bairro, estado, cidade, cep, refrigerado);

        String cpfEstoquista = rs.getString("ES.cpf");
        String cpfGerente = rs.getString("cpfGerente");
        Integer numeroEstoque = rs.getInt("estoque");
        return new Estoquista(funcionario, estoque, cpfEstoquista, cpfGerente, numeroEstoque);
    }
}
