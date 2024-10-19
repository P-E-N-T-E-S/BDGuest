package br.com.guest.restaurante_admin.funcionarios.mapper;

import br.com.guest.restaurante_admin.funcionarios.Funcionario;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.Date;

public class MapeadorFuncionario implements RowMapper<Funcionario> {


    @Override
    public Funcionario mapRow(ResultSet rs, int rowNum) throws SQLException {
        String cpf = rs.getString("cpf");
        Date dataContratacao = rs.getDate("data_contratacao");
        double salario = rs.getDouble("salario");
        LocalTime horaEntrada = rs.getTime("horario_entrada").toLocalTime();
        LocalTime horaSaida = rs.getTime("hora_saida").toLocalTime();
        return new Funcionario(cpf, dataContratacao, salario, horaEntrada, horaSaida);
    }
}
