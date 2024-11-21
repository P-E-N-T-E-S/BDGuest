package br.com.guest.restaurante_admin.entidades.comanda.impl;

import br.com.guest.restaurante_admin.entidades.comanda.Comanda;
import br.com.guest.restaurante_admin.entidades.comanda.ComandaRepository;
import br.com.guest.restaurante_admin.entidades.comanda.mapper.MapeadorComanda;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ComandaRepositoryImpl implements ComandaRepository {

    private JdbcTemplate jdbcTemplate;

    public ComandaRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void salvarComanda(Comanda comanda) {
        String sql = "INSERT INTO Comanda (numero_id, cpf_pessoa, nome_cliente, mesa, cpf_garcom) values (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, ""+comanda.getNumeroId(), comanda.getCpfPessoa(), comanda.getNomeCliente(), comanda.getMesaId(), comanda.getCpfGarcom());
    }

    @Override
    public Comanda buscarComandaPorId(Integer id) {
        String sql = "SELECT nome, horario_entrada, horario_saida, M.numero_id, quantidade_cadeiras, C.numero_id, cpf_pessoa, acesso, nome_cliente, mesa, chamando_garcom FROM Mesa M JOIN Comanda C on C.mesa = M.numero_id JOIN Garcom G ON G.cpf = C.cpf_garcom JOIN Funcionario F on G.cpf = F.cpf JOIN Pessoa P on F.cpf = P.cpf WHERE C.numero_id = ?";
        return jdbcTemplate.queryForObject(sql, new MapeadorComanda(), ""+id);
    }

    @Override
    public List<Comanda> listarComandas() {
        String sql = "SELECT nome, horario_entrada, horario_saida, M.numero_id, quantidade_cadeiras, C.numero_id, cpf_pessoa, acesso, nome_cliente, mesa, chamando_garcom FROM Mesa M JOIN Comanda C on C.mesa = M.numero_id JOIN Garcom G ON G.cpf = C.cpf_garcom JOIN Funcionario F on G.cpf = F.cpf JOIN Pessoa P on F.cpf = P.cpf";
        return jdbcTemplate.query(sql, new MapeadorComanda());
    }

    @Override
    public void excluirComanda(Integer id) {
        String sql = "DELETE FROM Comanda WHERE numero_id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void alterarComanda(Comanda comanda, Integer id) {
        String sql = "UPDATE Comanda SET cpf_pessoa = ?, nome_cliente = ?, mesa = ? WHERE numero_id = ?";
        jdbcTemplate.update(sql,comanda.getCpfPessoa(), comanda.getNomeCliente(), comanda.getMesaId(), id);
    }

    @Override
    public void chamarGarcom(Integer id) {
        String sql = "UPDATE COMANDA SET chamando_garcom = TRUE WHERE numero_id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void cancelarChamado(Integer id) {
        String sql = "UPDATE COMANDA SET chamando_garcom = FALSE WHERE numero_id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Comanda> buscarComandaPorCpf(String cpf) {
        String sql = "SELECT nome, horario_entrada, horario_saida, M.numero_id, quantidade_cadeiras, C.numero_id, cpf_pessoa, acesso, nome_cliente, mesa, chamando_garcom FROM Mesa M JOIN Comanda C on C.mesa = M.numero_id JOIN Garcom G ON G.cpf = C.cpf_garcom JOIN Funcionario F on G.cpf = F.cpf JOIN Pessoa P on F.cpf = P.cpf WHERE cpf_pessoa = ?";
        return jdbcTemplate.query(sql, new MapeadorComanda(), cpf);
    }
}
