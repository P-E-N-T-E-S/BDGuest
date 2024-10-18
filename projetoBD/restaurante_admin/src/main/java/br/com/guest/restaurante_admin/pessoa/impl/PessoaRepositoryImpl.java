package br.com.guest.restaurante_admin.pessoa.impl;

import br.com.guest.restaurante_admin.pessoa.Pessoa;
import br.com.guest.restaurante_admin.pessoa.PessoaRepository;
import br.com.guest.restaurante_admin.pessoa.mapper.MapeadorPessoa;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PessoaRepositoryImpl implements PessoaRepository {

    private final JdbcTemplate jdbcTemplate;

    public PessoaRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void salvarNovaPessoa(Pessoa pessoa) {
        String sql = "INSERT INTO Pessoas (cpf, nome, rua, bairro, estado, cidade, cep, email, data_nascimento, telefone, telefone_2) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                pessoa.getCpf(),
                pessoa.getNome(),
                pessoa.getRua(),
                pessoa.getBairro(),
                pessoa.getEstado(),
                pessoa.getCidade(),
                pessoa.getCep(),
                pessoa.getEmail(),
                new Date(pessoa.getDataNascimento().getTime()),
                pessoa.getTelefone(),
                pessoa.getTelefone2());
    }

    @Override
    public List<Pessoa> listarPessoas() {
        String sql = "SELECT * FROM Pessoas";
        try{
            return jdbcTemplate.query(sql, new MapeadorPessoa());
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public Pessoa buscarPessoaPorCpf(String cpf) {
        String sql = "SELECT * FROM Pessoas WHERE cpf = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new MapeadorPessoa(), cpf);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void deletarPessoaPorCpf(String cpf) {
        String sql = "DELETE FROM Pessoas WHERE cpf = ?";
        jdbcTemplate.update(sql, cpf);
    }

    @Override
    public void atualizarPessoaPorCpf(String cpf, Pessoa pessoa) {
        String sql = "UPDATE Pessoas SET nome = ?, rua = ?, bairro = ?, estado = ?, cidade = ?, cep = ?, email = ?, data_nascimento = ?, telefone = ?, telefone_2 = ? WHERE cpf = ?";
        jdbcTemplate.update(sql,
                pessoa.getNome(),
                pessoa.getRua(),
                pessoa.getBairro(),
                pessoa.getEstado(),
                pessoa.getCidade(),
                pessoa.getCep(),
                pessoa.getEmail(),
                new Date(pessoa.getDataNascimento().getTime()),
                pessoa.getTelefone(),
                pessoa.getTelefone2(),
                pessoa.getCpf());
    }

    @Override
    public List<Pessoa> buscarPessoaPorFiltro(String filtro, String valor) {
        String sql = "SELECT * FROM Pessoas WHERE " + filtro + " LIKE ?";
        try{
            return jdbcTemplate.query(sql, new MapeadorPessoa(), "%" + valor + "%");
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public List<Pessoa> buscarPessoaPorTelefone(String telefone) {
        String sql = "SELECT * FROM Pessoas WHERE telefone = ? OR telefone_2 = ?";
        try{
            return jdbcTemplate.query(sql, new MapeadorPessoa(), telefone, telefone);
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public void deletarPessoaPorFiltro(String filtro, String valor) {
        String sql = "DELETE FROM Pessoas WHERE " + filtro + " LIKE ?";
        jdbcTemplate.update(sql, "%"+valor+"%");
    }

    @Override
    public void deletarPessoaPorTelefone(String valor) {
        String sql = "DELETE FROM Pessoas WHERE telefone = ? OR telefone_2 = ?";
        jdbcTemplate.update(sql, valor);
    }

    @Override
    public void atualizarPessoaPorFiltro(String filtro, String valor, String campoAlterado, String valorAlterado) {
        //Esse aqui vai ser pegado
        String sql = "UPDATE Pessoas SET "+ campoAlterado + "= ?  WHERE "+filtro+" LIKE ?";
        jdbcTemplate.update(sql, valorAlterado, "%"+valor+"%");
        }

    @Override
    public void atualizarPessoaPorTelefone(String valor, String campoAlterado, String valorAlterado) {
        String sql = "UPDATE Pessoas SET "+ campoAlterado + "= ?  WHERE telefone LIKE ? or telefone_2 LIKE ?";
        jdbcTemplate.update(sql, valorAlterado, "%"+valor+"%", "%"+valor+"%");
    }
}
