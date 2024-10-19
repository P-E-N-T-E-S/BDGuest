package br.com.guest.restaurante_admin.funcionarios.impl;

import br.com.guest.restaurante_admin.funcionarios.Funcionario;
import br.com.guest.restaurante_admin.funcionarios.FuncionarioRepository;
import br.com.guest.restaurante_admin.funcionarios.mapper.MapeadorFuncionario;
import br.com.guest.restaurante_admin.pessoa.mapper.MapeadorPessoa;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class FuncionarioRepositoryImpl implements FuncionarioRepository {

    private JdbcTemplate jdbcTemplate;

    public FuncionarioRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void salvarFuncionario(Funcionario funcionario) {
        String sql = "INSERT INTO Funcionarios (cpf, data_contratacao, salario, horario_entrada, horario_saida) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                funcionario.getCpf(),
                new Date(funcionario.getDataContratacao().getTime()),
                funcionario.getSalario(),
                funcionario.getHorarioEntrada(),
                funcionario.getHorarioSaida());
    }

    @Override
    public List<Funcionario> listarFuncionarios() {
        String sql = "SELECT * FROM Funcionarios";
        try {
            return jdbcTemplate.query(sql, new MapeadorFuncionario());
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public Funcionario buscarFuncionarioPorCpf(String cpf) {
        String sql = "SELECT * FROM Funcionarios WHERE cpf = ?";
        return jdbcTemplate.queryForObject(sql, new MapeadorFuncionario(), cpf);
    }

    @Override
    public List<Funcionario> buscarFuncionarioPorFiltro(String filtro, String valor) {
        String sql = "SELECT * FROM Funcionarios WHERE " + filtro + " LIKE ?";
        try{
            return jdbcTemplate.query(sql, new MapeadorFuncionario(), "%" + valor + "%");
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public void atualizarFuncionarioPorCpf(String cpf, Funcionario funcionario) {
        String sql = "UPDATE Funcionarios SET data_contratacao = ?, salario = ?, horario_entrada = ?, horario_saida = ? Where cpf = ?";

        jdbcTemplate.update(sql,
                new Date(funcionario.getDataContratacao().getTime()),
                funcionario.getSalario(),
                funcionario.getHorarioEntrada(),
                funcionario.getHorarioSaida(),
                cpf);
    }

    @Override
    public void atualizarFuncionarioPorFiltro(String filtro, String valor, String campoAlterado, String valorAlterado) {
        String sql = "UPDATE Funcionarios SET " + campoAlterado + " = ? WHERE " + filtro + " = ?";
        jdbcTemplate.update(sql, valorAlterado, valor);
    }

    @Override
    public void deletarFuncionarioPorCpf(String cpf) {
        String sql = "DELETE FROM Funcionarios WHERE cpf = ?";
        jdbcTemplate.update(sql, cpf);
    }

    @Override
    public void deletarFuncionarioPorFiltro(String filtro, String valor) {
        String sql = "DELETE FROM Funcionarios WHERE " + filtro + " = ?";
        jdbcTemplate.update(sql, valor);
    }
}
