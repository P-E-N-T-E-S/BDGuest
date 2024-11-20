package br.com.guest.restaurante_admin.entidades.reserva.impl;

import br.com.guest.restaurante_admin.entidades.clientes.ClienteRepository;
import br.com.guest.restaurante_admin.entidades.mesa.Mesa;
import br.com.guest.restaurante_admin.entidades.mesa.MesaRepository;
import br.com.guest.restaurante_admin.entidades.reserva.Reserva;
import br.com.guest.restaurante_admin.entidades.reserva.ReservaRepository;
import br.com.guest.restaurante_admin.entidades.reserva.ReservaService;
import br.com.guest.restaurante_admin.execoes.*;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class ReservaServiceImpl implements ReservaService {

    private ReservaRepository reservaRepository;
    private ClienteRepository clienteRepository;
    private MesaRepository mesaRepository;

    public ReservaServiceImpl(ReservaRepository reservaRepository, ClienteRepository clienteRepository, MesaRepository mesaRepository) {
        this.reservaRepository = reservaRepository;
        this.clienteRepository = clienteRepository;
        this.mesaRepository = mesaRepository;
    }

    @Override
    public void salvarReserva(Reserva reserva) throws MesaNaoEncontradaException {
        Mesa mesa = mesaRepository.acharMesaPorId(reserva.getNumeroMesa());
        if(mesa == null) {
            throw new MesaNaoEncontradaException(""+reserva.getNumeroMesa());
        }
        if(mesa.getQuantidadeCadeiras() < reserva.getQuantidadePessoas()){
            reservaRepository.salvarReserva(reserva);
            throw new QuantidadeDeCadeirasInsuficientesException(""+reserva.getQuantidadePessoas());
        }
        if (clienteRepository.buscarClientePorCpf(reserva.getCpfCliente()) == null) {
            throw new ClienteNaoCadastradoException(reserva.getCpfCliente());
        }
        reservaRepository.salvarReserva(reserva);
    }

    @Override
    public List<Reserva> listarReservas() {
        return reservaRepository.listarReservas();
    }

    @Override
    public List<Reserva> buscarReservasPorFiltro(String filtro, String valor) throws DataInvalidaException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        if (filtro.equals("cpf")) {
            return reservaRepository.buscarReservaPorCpf(valor);
        } else if (filtro.equals("data")) {
            return reservaRepository.buscarReservaPorData(valor);
        }else{
            throw new FiltroNaoDisponivelException(filtro);
        }
    }

    @Override
    public Reserva buscarReservaPorCpfEData(String cpf, String data) {
        return reservaRepository.buscarReservaPorCpfEData(cpf, data);
    }

    @Override
    public void atualizarReserva(Reserva reserva, String cpf, LocalDate data) {
        reservaRepository.atualizarReserva(reserva, cpf, data);
    }

    @Override
    public void excluirReserva(String cpf, LocalDate data) {
        String dataString = data.toString();
        reservaRepository.excluirReserva(cpf, dataString);
    }
}
