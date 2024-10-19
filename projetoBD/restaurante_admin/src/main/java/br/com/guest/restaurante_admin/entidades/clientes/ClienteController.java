package br.com.guest.restaurante_admin.entidades.clientes;

import br.com.guest.restaurante_admin.entidades.pessoa.PessoaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final PessoaService pessoaService;
    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService, PessoaService pessoaService) {
        this.clienteService = clienteService;
        this.pessoaService = pessoaService;
    }

    @PostMapping("")
    public ResponseEntity<String> salvarCliente(@RequestBody Cliente cliente) {
        clienteService.salvarCliente(cliente);
        return new ResponseEntity("Cliente salvo com sucesso!", HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Cliente>> listarClientes() {
        return new ResponseEntity(clienteService.listarClientes(), HttpStatus.OK);
    }

    @GetMapping("/{filtro}")
    public ResponseEntity<List<Cliente>> filtrarClientes(@PathVariable String filtro, @RequestParam String valor) {
        try{
            return new ResponseEntity<>(clienteService.buscarClientePorFiltro(filtro, valor), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{filtro}")
    public ResponseEntity<String> atualizarClientePorFiltro(@PathVariable String filtro, @RequestParam String valor, @RequestBody HashMap<String, Object> alteracoes) {
        try{
            pessoaService.atualizarPessoaPorFiltro(filtro, valor,(String)alteracoes.get("campo"), (String)alteracoes.get("valor"));
            return new ResponseEntity<>("Cliente atualizado com sucesso!", HttpStatus.OK);
        }catch (Exception e) {
            //todo diferenciar as respostas por exception
            return new ResponseEntity<>("Filtro ou campo a ser atualizado não disponivel", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{filtro}")
    public ResponseEntity<String> deletarClientePorFiltro(@PathVariable String filtro, @RequestParam String valor) {
        try{
            pessoaService.deletarPessoaPorFiltro(filtro, valor);
            return new ResponseEntity<>("Cliente deletado com sucesso!", HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>("Filtro escolhido não disponível", HttpStatus.BAD_REQUEST);
        }
    }
}
