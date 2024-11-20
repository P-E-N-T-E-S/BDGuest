package br.com.guest.restaurante_admin.entidades.reserva.impl;

import br.com.guest.restaurante_admin.entidades.reserva.Reserva;
import br.com.guest.restaurante_admin.entidades.reserva.ReservaRepository;
import br.com.guest.restaurante_admin.entidades.reserva.mapper.MapeadorReserva;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public class ReservaRepositoryImpl implements ReservaRepository {

    private JdbcTemplate jdbcTemplate;

    public ReservaRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void salvarReserva(Reserva reserva) {
        String sql = "INSERT INTO Reserva (cpf_cliente, data, horario_entrada, quantidade_pessoas, numero_mesa) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, reserva.getCpfCliente(), reserva.getData(), reserva.getHorarioEntrada(), reserva.getQuantidadePessoas(), reserva.getNumeroMesa());
    }

    @Override
    public List<Reserva> listarReservas() {
        String sql = "SELECT * FROM Reserva R join Mesa M on M.numero_id = R.numero_mesa join Cliente C on R.cpf_cliente = C.cpf join Pessoa P on P.cpf = C.cpf";
        return jdbcTemplate.query(sql, new MapeadorReserva());
    }

    @Override
    public List<Reserva> buscarReservaPorCpf(String cpf) {
        String sql = "SELECT * FROM Reserva R join Mesa M on M.numero_id = R.numero_mesa join Cliente C on R.cpf_cliente = C.cpf join Pessoa P on P.cpf = C.cpf WHERE cpf_cliente = ?";
        return jdbcTemplate.query(sql, new MapeadorReserva(), cpf);
    }

    @Override
    public List<Reserva> buscarReservaPorData(String data) {
        String sql = "SELECT * FROM Reserva R join Mesa M on M.numero_id = R.numero_mesa join Cliente C on R.cpf_cliente = C.cpf join Pessoa P on P.cpf = C.cpf WHERE R.data = ?";
        return jdbcTemplate.query(sql, new MapeadorReserva(), data);
    }

    @Override
    public Reserva buscarReservaPorCpfEData(String cpf, String data) {
        String sql = "SELECT * FROM Reserva R join Mesa M on M.numero_id = R.numero_mesa join Cliente C on R.cpf_cliente = C.cpf join Pessoa P on P.cpf = C.cpf WHERE R.cpf_cliente = ? AND R.data = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new MapeadorReserva(), cpf, data);
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public void atualizarReserva(Reserva reserva, String cpf, LocalDate data) {
        String sql = "UPDATE Reserva SET horario_entrada = ?, quantidade_pessoas = ?, numero_mesa = ? WHERE cpf_cliente = ? AND data = ?";
        jdbcTemplate.update(sql, reserva.getHorarioEntrada(), reserva.getQuantidadePessoas(), reserva.getNumeroMesa(), cpf, data);

    }

    @Override
    public void excluirReserva(String cpf, String data) {
    String sql = "DELETE FROM Reserva WHERE cpf_cliente = ? AND data = ?";
    jdbcTemplate.update(sql, cpf, data);
    }
}
