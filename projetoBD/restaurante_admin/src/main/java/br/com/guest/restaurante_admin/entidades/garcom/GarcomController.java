package br.com.guest.restaurante_admin.entidades.garcom;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
