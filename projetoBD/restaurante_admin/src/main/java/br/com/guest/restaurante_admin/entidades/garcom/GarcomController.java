package br.com.guest.restaurante_admin.entidades.garcom;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/garcom")
public class GarcomController {

    private GarcomService garcomService;

    public GarcomController(GarcomService garcomService) {
        this.garcomService = garcomService;
    }

    @PostMapping("")
    public ResponseEntity<String> salvarGarcom(@RequestBody Garcom garcom) {
        try {
            garcomService.salvarGarcom(garcom);
            return new ResponseEntity<>("Garcom adicionado com sucesso!", HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>("Pessoa não encontrada", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Garcom>> listarGarcom() {
        return new ResponseEntity<>(garcomService.listarGarcoms(), HttpStatus.OK);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Garcom> buscarGarcomPorCpf(@PathVariable String cpf) {
        return new ResponseEntity<>(garcomService.buscarGarcomPorCpf(cpf), HttpStatus.OK);
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<String> deletarGarcomPorCpf(@PathVariable String cpf) {
        try{
            garcomService.removerGarcomPorCpf(cpf);
            return new ResponseEntity<>("Garcom deletado com sucesso!", HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>("Erro ao deletar garcom", HttpStatus.BAD_REQUEST);
        }
    }
}
