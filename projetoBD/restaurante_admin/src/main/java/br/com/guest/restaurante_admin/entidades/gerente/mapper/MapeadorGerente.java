package br.com.guest.restaurante_admin.entidades.gerente.mapper;

import br.com.guest.restaurante_admin.entidades.funcionarios.Funcionario;
import br.com.guest.restaurante_admin.entidades.gerente.Gerente;
import br.com.guest.restaurante_admin.entidades.pessoa.Pessoa;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.Date;

public class MapeadorGerente implements RowMapper<Gerente> {
    @Override
    public Gerente mapRow(ResultSet rs, int rowNum) throws SQLException {
        String cpfPessoa = rs.getString("P.cpf");
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
        Pessoa pessoa = new Pessoa(cpfPessoa, nome, rua, bairro, estado, cidade, cep, email, data_nascimento, telefone, telefone2);

        String cpfFuncionario = rs.getString("F.cpf");
        Date dataContratacao = rs.getDate("data_contratacao");
        double salario = rs.getDouble("salario");
        LocalTime horaEntrada = rs.getTime("horario_entrada").toLocalTime();
        LocalTime horaSaida = rs.getTime("horario_saida").toLocalTime();
        Funcionario funcionario =  new Funcionario(pessoa, cpfFuncionario, dataContratacao, salario, horaEntrada, horaSaida);

        String cpfGerente = rs.getString("G.cpf");
        return new Gerente(cpfGerente, funcionario);
    }
}