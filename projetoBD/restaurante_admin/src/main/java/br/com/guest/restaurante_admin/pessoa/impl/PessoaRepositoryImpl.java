package br.com.guest.restaurante_admin.pessoa.impl;

import br.com.guest.restaurante_admin.pessoa.Pessoa;
import br.com.guest.restaurante_admin.pessoa.PessoaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.Date;

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

}
