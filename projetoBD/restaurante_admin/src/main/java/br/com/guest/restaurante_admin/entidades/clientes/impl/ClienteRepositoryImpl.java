package br.com.guest.restaurante_admin.entidades.clientes.impl;

import br.com.guest.restaurante_admin.entidades.clientes.Cliente;
import br.com.guest.restaurante_admin.entidades.clientes.ClienteRepository;
import br.com.guest.restaurante_admin.entidades.clientes.mapper.ClienteMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClienteRepositoryImpl implements ClienteRepository {
    //todo fazer função para deletar, buscar e atualizar com filtros da hierarquia

    private JdbcTemplate jdbcTemplate;

    public ClienteRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void salvarCliente(Cliente cliente) {
        String sql = "INSERT INTO Cliente (cpf, fidelidade, metodo_pagamento_1, metodo_pagamento_2) VALUES(?, ?, ?, ?)";
        jdbcTemplate.update(sql, cliente.getCpf(), cliente.getFidelidade(), cliente.getMetodoPagamento1(), cliente.getMetodoPagamento2());
    }

    @Override
    public Cliente buscarClientePorCpf(String cpf) {
        String sql = "SELECT * FROM Pessoa p join Cliente C on p.cpf = C.cpf WHERE cpf = ?";
        return jdbcTemplate.queryForObject(sql, new ClienteMapper(), cpf);
    }

    @Override
    public List<Cliente> listarClientes() {
        String sql = "SELECT * FROM Pessoa p join Cliente C on p.cpf = C.cpf";
        return jdbcTemplate.query(sql, new ClienteMapper());
    }

    @Override
    public List<Cliente> buscarClientePorFiltro(String filtro, String valor) {
        String sql = "SELECT * FROM Cliente WHERE " + filtro + " LIKE ?";
        return jdbcTemplate.query(sql, new ClienteMapper(), "%"+valor+"%");
    }

    @Override
    public void atualizarClientePorCpf(Cliente cliente, String cpf) {
        String sql = "UPDATE Cliente SET fidelidade = ?, metodo_pagamento_1 = ?, metodo_pagamento_2 = ? WHERE cpf = ?";
        jdbcTemplate.update(sql, cliente.getFidelidade(), cliente.getMetodoPagamento1(), cliente.getMetodoPagamento2(), cliente.getCpf());
    }

    @Override
    public void atualizarClientePorFiltro(String filtro, String valor, String campoAlterado, String valorAlterado) {
        String sql ="UPDATE Cliente SET "+campoAlterado+" = ? WHERE " + filtro + " LIKE ?";
        jdbcTemplate.update(sql, valorAlterado, "%"+valor+"%");
    }

    @Override
    public void atualizarClientePorFiltroDePessoa(String filtro, String valor, String campoAlterado, String valorAlterado) {
        String sql = "UPDATE Cliente SET "+campoAlterado+" = ? WHERE cpf in (SELECT cpf FROM Pessoa WHERE "+filtro+" LIKE ?)";
        jdbcTemplate.update(sql, valorAlterado, "%"+valor+"%");
    }

    @Override
    public void deletarClientePorCpf(String cpf) {
        String sql = "DELETE FROM Cliente WHERE cpf = ?";
        jdbcTemplate.update(sql, cpf);
    }

    @Override
    public void deletarClientePorFiltro(String filtro, String valor) {
        String sql = "DELETE FROM Cliente WHERE " + filtro + " LIKE ?";
        jdbcTemplate.update(sql, "%" + valor + "%");
    }

    @Override
    public void deletarClientePorFiltroDePessoa(String filtro, String valor) {
        String sql = "DELETE FROM Cliente WHERE cpf in (SELECT cpf FROM Pessoa WHERE "+filtro+" LIKE ?)";
        jdbcTemplate.update(sql, "%"+valor+"%");
    }
}
