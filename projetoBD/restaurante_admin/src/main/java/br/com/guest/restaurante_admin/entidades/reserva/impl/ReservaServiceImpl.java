package br.com.guest.restaurante_admin.entidades.reserva.impl;

import br.com.guest.restaurante_admin.entidades.clientes.ClienteRepository;
import br.com.guest.restaurante_admin.entidades.mesa.MesaRepository;
import br.com.guest.restaurante_admin.entidades.reserva.Reserva;
import br.com.guest.restaurante_admin.entidades.reserva.ReservaRepository;
import br.com.guest.restaurante_admin.entidades.reserva.ReservaService;
import br.com.guest.restaurante_admin.execoes.ClienteNaoCadastradoException;
import br.com.guest.restaurante_admin.execoes.DataInvalidaException;
import br.com.guest.restaurante_admin.execoes.FiltroNaoDisponivelException;
import br.com.guest.restaurante_admin.execoes.MesaNaoEncontradaException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        if(mesaRepository.acharMesaPorId(reserva.getNumeroMesa()) == null) {
            throw new MesaNaoEncontradaException(""+reserva.getNumeroMesa());
        }
        if (clienteRepository.buscarClientePorCpf(reserva.getCpfCliente()) != null) {
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
            try {
                Date data = formatter.parse(valor);
                return reservaRepository.buscarReservaPorData(data);
            } catch (ParseException e) {
                throw new DataInvalidaException(e.getMessage());
            }
        }else{
            throw new FiltroNaoDisponivelException(filtro);
        }
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
