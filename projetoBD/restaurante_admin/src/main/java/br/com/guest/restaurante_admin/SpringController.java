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

    @GetMapping("deletar_pessoa")
    public String deletarPessoa(Model model) {
        return "deletar_pessoa";
    }
}
