package br.com.guest.restaurante_admin.entidades.estoquista.impl;

import br.com.guest.restaurante_admin.entidades.estoquista.Estoquista;
import br.com.guest.restaurante_admin.entidades.estoquista.EstoquistaRepository;
import br.com.guest.restaurante_admin.entidades.estoquista.mapper.MapeadorEstoquista;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EstoquistaRepositoryImpl implements EstoquistaRepository {

    private JdbcTemplate jdbcTemplate;

    public EstoquistaRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void salvarEstoquista(Estoquista estoquista) {
        String sql = "INSERT INTO Estoquista cpf, cpf_gerente, estoque VALUES(?, ?, ?)";
        jdbcTemplate.update(sql, estoquista.getCpf(), estoquista.getCpfGerente(), estoquista.getEstoque());
    }

    @Override
    public Estoquista buscarEstoquistaPorCpf(String cpf) {
        String sql = "SELECT * FROM Estoquista ES join Pessoa P on P.cpf = ES.cpf join Funcionario F on F.cpf = ES.cpf join Estoque E on ES.estoque = E.id WHERE cpf = ?";
        return jdbcTemplate.queryForObject(sql, new MapeadorEstoquista(), cpf);
    }

    @Override
    public List<Estoquista> listarEstoquistas() {
        String sql = "SELECT * FROM Estoquista ES join Pessoa P on P.cpf = ES.cpf join Funcionario F on F.cpf = ES.cpf join Estoque E on ES.estoque = E.id";
        return jdbcTemplate.query(sql, new MapeadorEstoquista());
    }

    @Override
    public List<Estoquista> buscarEstoquistasPorFiltro(String filtro, String valor) {
        String sql = "SELECT * FROM Estoquista ES join Pessoa P on P.cpf = ES.cpf join Funcionario F on F.cpf = ES.cpf join Estoque E on ES.estoque = E.id WHERE "+filtro+" LIKE ?";
        return jdbcTemplate.query(sql, new MapeadorEstoquista(), "%"+valor+"%");
    }

    @Override
    public void excluirEstoquistaPorCpf(String cpf) {
        String sql = "DELETE FROM Estoquista Where cpf = ?";
        jdbcTemplate.update(sql, cpf);
    }

    @Override
    public void excluirEstoquistaPorFiltro(String filtro, String valor) {
        String sql = "DELETE FROM Estoquista WHERE "+filtro+" LIKE ?";
        jdbcTemplate.update(sql, "%"+valor+"%");

    }

    @Override
    public void alterarEstoquistaPorCpf(String cpf, Estoquista estoquista) {
        String sql = "UPDATE Estoquista SET cpf_gerente = ?, estoque = ? WHERE cpf = ?";
        jdbcTemplate.update(sql,estoquista.getCpfGerente(), estoquista.getEstoque(), estoquista.getCpf());
    }

    @Override
    public void alterarEstoquistaPorFiltro(String filtro, String valor, String campoAlterado, String valorAlterado) {
        String sql = "UPDATE Estoquista SET "+ campoAlterado+" =? WHERE "+ filtro +" LIKE ?";
        jdbcTemplate.update(sql, valorAlterado, valor);
    }
}
