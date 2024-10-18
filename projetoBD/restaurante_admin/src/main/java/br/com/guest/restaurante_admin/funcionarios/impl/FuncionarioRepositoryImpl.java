package br.com.guest.restaurante_admin.funcionarios.impl;

import br.com.guest.restaurante_admin.funcionarios.Funcionario;
import br.com.guest.restaurante_admin.funcionarios.FuncionarioRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public class FuncionarioRepositoryImpl implements FuncionarioRepository {

    private JdbcTemplate jdbcTemplate;

    public FuncionarioRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void salvarFuncionario(Funcionario funcionario) {
        String sql = "INSERT INTO Funcionarios (cpf, data_contratacao, salario, horario_entrada, horario_saida) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, funcionario.getCpf(), new Date(funcionario.getDataContratacao().getTime()), funcionario.getSalario(), funcionario.getHoraEntrada(), funcionario.getHoraSaida());
    }
}
