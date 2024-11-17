package br.com.guest.restaurante_admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpringController {
    @GetMapping("")
    public String home(Model model) {
        model.addAttribute("mensagem", "Bem-vindo ao Spring Boot com Thymeleaf!");
        return "home"; // Retorna o nome da página HTML (home.html) a ser renderizada
    }

    @GetMapping("adicionar_pessoa")
    public String adicionarPessoa(Model model) {
        return "adicionar_pessoa";
    }

    @GetMapping("recuperar_pessoa")
    public String recuperarPessoa(Model model) {
        return "recuperar_pessoa";
    }

    @GetMapping("editar_pessoa")
    public String editarPessoa(Model model) {
        return "editar_pessoa";
    }

    @GetMapping("adicionar_cliente")
    public String adicionarCliente(Model model) {
        return "adicionar_cliente";

    }

    @GetMapping("recuperar_cliente")
    public String recuperarCliente(Model model) {
        return "recuperar_cliente";
    }

    @GetMapping("editar_cliente")
    public String editarCliente(Model model) {
        return "editar_cliente";
    }

    @GetMapping("adicionar_funcionario")
    public String adicionarFuncionario(Model model) {
        return "adicionar_funcionario";
    }

    @GetMapping("editar_funcionario")
    public String editarFuncionario(Model model) {
        return "editar_funcionario";
    }

    @GetMapping("recuperar_funcionario")
    public String recuperarFuncionario(Model model) {
        return "recuperar_funcionario";
    }
}
