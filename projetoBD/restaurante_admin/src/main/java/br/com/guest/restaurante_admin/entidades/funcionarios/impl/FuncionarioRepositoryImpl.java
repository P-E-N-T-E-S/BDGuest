package br.com.guest.restaurante_admin.entidades.funcionarios.impl;

import br.com.guest.restaurante_admin.entidades.funcionarios.Funcionario;
import br.com.guest.restaurante_admin.entidades.funcionarios.FuncionarioRepository;
import br.com.guest.restaurante_admin.entidades.funcionarios.mapper.MapeadorFuncionario;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class FuncionarioRepositoryImpl implements FuncionarioRepository {
    //todo fazer função para deletar, buscar e atualizar com filtros da hierarquia

    private JdbcTemplate jdbcTemplate;

    public FuncionarioRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void salvarFuncionario(Funcionario funcionario) {
        String sql = "INSERT INTO Funcionario (cpf, data_contratacao, salario, horario_entrada, horario_saida) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                funcionario.getCpf(),
                new Date(funcionario.getDataContratacao().getTime()),
                funcionario.getSalario(),
                funcionario.getHorarioEntrada(),
                funcionario.getHorarioSaida());
    }

    @Override
    public List<Funcionario> listarFuncionarios() {
        String sql = "SELECT *FROM Pessoa P join Funcionario F on p.cpf = F.cpf";
        try {
            return jdbcTemplate.query(sql, new MapeadorFuncionario());
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public Funcionario buscarFuncionarioPorCpf(String cpf) {
        String sql = "SELECT * FROM Pessoa P join Funcionario F on p.cpf = F.cpf WHERE F.cpf = ?";
        return jdbcTemplate.queryForObject(sql, new MapeadorFuncionario(), cpf);
    }

    @Override
    public List<Funcionario> buscarFuncionarioPorFiltro(String filtro, String valor) {
        String sql = "SELECT * FROM Pessoa P join Funcionario F on p.cpf = F.cpf  WHERE F." + filtro + " LIKE ?";
        try{
            return jdbcTemplate.query(sql, new MapeadorFuncionario(), "%" + valor + "%");
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public void atualizarFuncionarioPorCpf(String cpf, Funcionario funcionario) {
        String sql = "UPDATE Funcionario SET data_contratacao = ?, salario = ?, horario_entrada = ?, horario_saida = ? Where cpf = ?";

        jdbcTemplate.update(sql,
                new Date(funcionario.getDataContratacao().getTime()),
                funcionario.getSalario(),
                funcionario.getHorarioEntrada(),
                funcionario.getHorarioSaida(),
                cpf);
    }

    @Override
    public void atualizarFuncionarioPorFiltro(String filtro, String valor, String campoAlterado, String valorAlterado) {
        //todo perguntar a Gabi como fazer essa bomba daq, preciso dar um filtro com o atributo de pessoa
        String sql = "UPDATE Funcionario SET " + campoAlterado + " = ? WHERE " + filtro + " LIKE ?";
        jdbcTemplate.update(sql, valorAlterado, "%"+valor+"%");
    }

    @Override
    public void atualizarFuncionarioPorFiltroDePessoa(String filtro, String valor, String campoAlterado, String valorAlterado) {
        String sql = "UPDATE Funcionario SET " + campoAlterado + " = ? WHERE cpf in (select cpf from Pessoa where "+filtro+" LIKE ?)";
        jdbcTemplate.update(sql, valorAlterado, "%"+valor+"%");
    }

    @Override
    public void deletarFuncionarioPorCpf(String cpf) {
        String sql = "DELETE FROM Funcionario WHERE cpf = ?";
        jdbcTemplate.update(sql, cpf);
    }

    @Override
    public void deletarFuncionarioPorFiltro(String filtro, String valor) {
        String sql = "DELETE FROM Funcionario WHERE " + filtro + " LIKE ?";
        jdbcTemplate.update(sql, "%"+valor+"%");
    }

    @Override
    public void deletarFuncionarioPorFiltroDePessoa(String filtro, String valor) {
        String sql ="DELETE FROM Funcionario WHERE cpf in (select cpf from Pessoa where "+filtro+" LIKE ?)";
        jdbcTemplate.update(sql, "%"+valor+"%");
    }
}
