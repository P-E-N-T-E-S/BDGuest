package br.com.guest.restaurante_admin.entidades.reserva;

import java.util.Date;
import java.util.List;

public interface ReservaRepository {
    void salvarReserva(Reserva reserva);
    List<Reserva> listarReservas();
    List<Reserva> buscarReservaPorCpf(String cpf);
    List<Reserva> buscarReservaPorData(String data);
    Reserva buscarReservaPorCpfEData(String cpf, String data);
    void atualizarReserva(Reserva reserva, String cpf, Date data);
    void excluirReserva(String cpf, String data);
}
