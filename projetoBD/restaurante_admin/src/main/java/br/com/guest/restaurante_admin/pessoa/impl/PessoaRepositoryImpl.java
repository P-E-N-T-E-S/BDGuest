package br.com.guest.restaurante_admin.pessoa.impl;

import br.com.guest.restaurante_admin.pessoa.Pessoa;
import br.com.guest.restaurante_admin.pessoa.PessoaRepository;
import br.com.guest.restaurante_admin.pessoa.mapper.MapeadorPessoa;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.Date;
import java.util.List;

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
    public void deletarPessoa(String cpf) {
        String sql = "DELETE FROM Pessoas WHERE cpf = ?";
        jdbcTemplate.update(sql, cpf);
    }

    @Override
    public void atualizarPessoa(String cpf, Pessoa pessoa) {
        String sql = "UPDATE Pessoas SET nome = ?, rua = ?, bairro = ?, estado = ?, cidade = ?, cep = ?, email = ?, data_nascimento = ?, telefone = ?, telefone_2 = ? WHERE cpf = ?";
        jdbcTemplate.update(sql,                 pessoa.getNome(),
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
    public List<Pessoa> buscarPessoaPorFiltro(String filtro, String valor, boolean telefoneFlag) {
        if (telefoneFlag) {
            String sql = "SELECT * FROM Pessoas WHERE telefone = ? OR telefone_2 = ?";
            try{
                return jdbcTemplate.query(sql, new MapeadorPessoa(), valor, valor);
            }catch(EmptyResultDataAccessException e){
                return null;
            }
        }else{
            String sql = "SELECT * FROM Pessoas WHERE " + filtro + " LIKE ?";
            try{
                System.out.println(filtro + " " + valor);
                return jdbcTemplate.query(sql, new MapeadorPessoa(), "%" + valor + "%");
            }catch(EmptyResultDataAccessException e){
                return null;
            }
        }
    }
}
