package br.com.guest.restaurante_admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpringController {
    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("mensagem", "Bem-vindo ao Spring Boot com Thymeleaf!");
        return "home"; // Retorna o nome da página HTML (home.html) a ser renderizada
    }

    @GetMapping("adicionar_pessoa")
    public String adicionarPessoa(Model model) {
        return "pessoa/adicionar_pessoa";
    }

    @GetMapping("recuperar_pessoa")
    public String recuperarPessoa(Model model) {
        return "pessoa/recuperar_pessoa";
    }

    @GetMapping("editar_pessoa")
    public String editarPessoa(Model model) {
        return "pessoa/editar_pessoa";
    }

    @GetMapping("adicionar_cliente")
    public String adicionarCliente(Model model) {
        return "cliente/adicionar_cliente";

    }

    @GetMapping("recuperar_cliente")
    public String recuperarCliente(Model model) {
        return "cliente/recuperar_cliente";
    }

    @GetMapping("editar_cliente")
    public String editarCliente(Model model) {
        return "cliente/editar_cliente";
    }

    @GetMapping("adicionar_funcionario")
    public String adicionarFuncionario(Model model) {
        return "funcionario/adicionar_funcionario";
    }

    @GetMapping("editar_funcionario")
    public String editarFuncionario(Model model) {
        return "funcionario/editar_funcionario";
    }

    @GetMapping("recuperar_funcionario")
    public String recuperarFuncionario(Model model) {
        return "funcionario/recuperar_funcionario";
    }

    @GetMapping("cadastrar_mesa")
    public String cadastrarMesa(Model model) {
        return "mesa/adicionar_mesa";
    }

    @GetMapping("recuperar_mesa")
    public String recuperarMesa(Model model) {
        return "mesa/recuperar_mesa";
    }

    @GetMapping("editar_mesa")
    public String editarMesa(Model model) {
        return "mesa/editar_mesa";
    }

    @GetMapping("adicionar_garcom")
    public String adicionarGarcom(Model model) {
        return "garcom/adicionar_garcom";
    }

    @GetMapping("recuperar_garcom")
    public String recuperarGarcom(Model model) {
        return "garcom/recuperar_garcom";
    }

    @GetMapping("editar_garcom")
    public String editarGarcom(Model model) {
        return "garcom/editar_garcom";
    }

    @GetMapping("adicionar_estoque")
    public String adicionarEstoque(Model model) {
        return "estoque/adicionar_estoque";
    }

    @GetMapping("recuperar_estoque")
    public String recuperarEstoque(Model model) {
        return "estoque/recuperar_estoque";
    }
}
