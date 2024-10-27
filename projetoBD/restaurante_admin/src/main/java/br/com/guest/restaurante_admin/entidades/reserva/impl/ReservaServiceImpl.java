package br.com.guest.restaurante_admin.entidades.reserva.impl;

import br.com.guest.restaurante_admin.entidades.reserva.Reserva;
import br.com.guest.restaurante_admin.entidades.reserva.ReservaRepository;
import br.com.guest.restaurante_admin.entidades.reserva.ReservaService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReservaServiceImpl implements ReservaService {

    private ReservaRepository reservaRepository;

    public ReservaServiceImpl(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    @Override
    public void salvarReserva(Reserva reserva) {
        //TODO: verificar cpf cliente e numero da mesa
        reservaRepository.salvarReserva(reserva);
    }

    @Override
    public List<Reserva> listarReservas() {
        return reservaRepository.listarReservas();
    }

    @Override
    public List<Reserva> buscarReservaPorCpf(String cpf) {
        //TODO: transformar isso em uma coisa só
        return reservaRepository.buscarReservaPorCpf(cpf);
    }

    @Override
    public List<Reserva> buscarReservaPorData(Date data) {
        return reservaRepository.buscarReservaPorData(data);
    }

    @Override
    public Reserva buscarReservaPorCpfEData(String cpf, Date data) {
        return reservaRepository.buscarReservaPorCpfEData(cpf, data);
    }

    @Override
    public void atualizarReserva(Reserva reserva, String cpf, Date data) {
        reservaRepository.atualizarReserva(reserva, cpf, data);
    }

    @Override
    public void excluirReserva(String cpf, Date data) {
        reservaRepository.excluirReserva(cpf, data);
    }
}
