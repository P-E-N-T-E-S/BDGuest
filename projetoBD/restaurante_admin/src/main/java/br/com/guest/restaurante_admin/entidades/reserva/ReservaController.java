package br.com.guest.restaurante_admin.entidades.reserva;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/reserva")
public class ReservaController {

    private ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }


    @PostMapping
    public ResponseEntity<String> cadastrarReserva(@RequestBody Reserva reserva) {
        reservaService.salvarReserva(reserva);
        return new ResponseEntity<>("Reserva Cadastrado com Sucesso", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Reserva>> listarReservas() {
        return new ResponseEntity<>(reservaService.listarReservas(), HttpStatus.OK);
    }

    @GetMapping("/data") //enviar data por ano-mes-dia
    public ResponseEntity<List<Reserva>> buscarDataReservaPorData(@RequestParam Date data) {
        return new ResponseEntity<>(reservaService.buscarReservaPorData(data), HttpStatus.OK);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<List<Reserva>> buscarReservaPorCpf(@PathVariable String cpf) {
        return new ResponseEntity<>(reservaService.buscarReservaPorCpf(cpf), HttpStatus.OK);
    }

    @GetMapping("/{cpf}/data")
    public ResponseEntity<Reserva> buscarReservaPorDataECpf(@PathVariable String cpf, @RequestParam Date data) {
        return new ResponseEntity<>(reservaService.buscarReservaPorCpfEData(cpf, data), HttpStatus.OK);
    }
    //TODO: Pensar melhor sobre essas coisas de cpf e data
    @PutMapping
    public ResponseEntity<String> atualizarReserva(@RequestBody Reserva reserva) {
        reservaService.atualizarReserva(reserva, reserva.getCpfCliente(), reserva.getData());
        return new ResponseEntity<>("Reserva Atualizado com Sucesso", HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deletarReserva(@RequestBody Reserva reserva) {
        reservaService.excluirReserva(reserva.getCpfCliente(), reserva.getData());
        return new ResponseEntity<>("Reserva Deletado com Sucesso", HttpStatus.OK);
    }
}
