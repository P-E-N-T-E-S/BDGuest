package br.com.guest.restaurante_admin.entidades.garcom.mapper;

import br.com.guest.restaurante_admin.entidades.funcionarios.Funcionario;
import br.com.guest.restaurante_admin.entidades.garcom.Garcom;
import br.com.guest.restaurante_admin.entidades.pessoa.Pessoa;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.Date;

public class MapeadorGarcom implements RowMapper<Garcom> {

    @Override
    public Garcom mapRow(ResultSet rs, int rowNum) throws SQLException {
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

        String cpfGarcom = rs.getString("G.cpf");
        String cpfGerente = rs.getString("cpf_gerente");

        return new Garcom(cpfGarcom, cpfGerente, null, funcionario);
    }
}
