package br.com.guest.restaurante_admin.entidades.reserva;

import br.com.guest.restaurante_admin.execoes.*;
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
        try {
            reservaService.salvarReserva(reserva);
        }catch (MesaNaoEncontradaException e){
            return new ResponseEntity<>("Mesa: "+e.getMessage()+" nao encontrada", HttpStatus.BAD_REQUEST);
        }catch (ClienteNaoCadastradoException e){
            return new ResponseEntity<>("Cpf: "+e.getMessage()+" nao cadastrado como cliente", HttpStatus.BAD_REQUEST);
        }catch (QuantidadeDeCadeirasInsuficientesException e){
            return new ResponseEntity<>("Reserva Salva com mais cadeiras do que a mesa", HttpStatus.OK);
        }
        return new ResponseEntity<>("Reserva Cadastrado com Sucesso", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Reserva>> listarReservas() {
        return new ResponseEntity<>(reservaService.listarReservas(), HttpStatus.OK);
    }

    @GetMapping("/{filtro}") //enviar data por ano-mes-dia
    public ResponseEntity<Object> buscarReservaPorDataOuCpf(@PathVariable String filtro, @RequestParam String valor) {
        try {
            return new ResponseEntity<>(reservaService.buscarReservasPorFiltro(filtro, valor), HttpStatus.OK);
        }catch (DataInvalidaException e){
            return new ResponseEntity<>("Data: "+e.getMessage()+" em formato inválido", HttpStatus.BAD_REQUEST);
        }catch (FiltroNaoDisponivelException e){
            return new ResponseEntity<>("Filtro: "+e.getMessage()+" inválido", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{cpf}/data")
    public ResponseEntity<Reserva> buscarReservaPorDataECpf(@PathVariable String cpf, @RequestParam String data) {
        return new ResponseEntity<>(reservaService.buscarReservaPorCpfEData(cpf, data), HttpStatus.OK);
    }

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
