
package br.com.guest.restaurante_admin.entidades.mesa;

import br.com.guest.restaurante_admin.execoes.IdentificadorNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/mesa")
public class MesaController {

    private MesaService mesaService;

    public MesaController(MesaService mesaService) {
        this.mesaService = mesaService;
    }

    @PostMapping("")
    public ResponseEntity<String> salvarMesa(@RequestBody Mesa mesa) {
        mesaService.salvarMesa(mesa);
        return new ResponseEntity<>("Mesa salva com sucesso!", HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Mesa>> listarMesas() {
        return new ResponseEntity<>(mesaService.listarMesas(), HttpStatus.OK);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Object> listarMesasPorGarcom(@PathVariable String cpf) {
        return new ResponseEntity<>(mesaService.listarMesaPorGarcom(cpf), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> alterarMesaPorID(@PathVariable Integer id, @RequestBody Mesa mesa) {
        try {
            mesaService.alterarMesaPorID(mesa, id);
            return new ResponseEntity<>("Mesa atualizada com sucesso!", HttpStatus.OK);
        } catch (IdentificadorNaoEncontradoException e) {
            return new ResponseEntity<>("ID: "+e.getMessage()+" não disponível", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirMesaPorId(@PathVariable Integer id) {
        try {
            mesaService.excluirMesaPorId(id);
            return new ResponseEntity<>("Mesa deletada com sucesso!", HttpStatus.OK);
        } catch (IdentificadorNaoEncontradoException e) {
            return new ResponseEntity<>("ID: "+e.getMessage()+" não disponível", HttpStatus.BAD_REQUEST);
        }
    }
}
