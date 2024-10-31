package br.com.guest.restaurante_admin.entidades.reserva;

import java.util.Date;
import java.util.List;

public interface ReservaService {
    void salvarReserva(Reserva reserva);
    List<Reserva> listarReservas();
    List<Reserva> buscarReservasPorFiltro(String filtro, String valor);
    Reserva buscarReservaPorCpfEData(String cpf, Date data);
    void atualizarReserva(Reserva reserva, String cpf, Date data);
    void excluirReserva(String cpf, Date data);
}
