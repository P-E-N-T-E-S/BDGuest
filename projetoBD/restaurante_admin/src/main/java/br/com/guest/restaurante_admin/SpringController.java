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

    @GetMapping("editar_estoque")
    public String editarEstoque(Model model) {
        return "estoque/editar_estoque";
    }

    @GetMapping("adicionar_estoquista")
    public String adicionarEstoquista(Model model) {
        return "estoquista/adicionar_estoquista";
    }

    @GetMapping("recuperar_estoquista")
    public String recuperarEstoquista(Model model) {
        return "estoquista/recuperar_estoquista";
    }

    @GetMapping("editar_estoquista")
    public String editarEstoquista(Model model) {
        return "estoquista/editar_estoquista";
    }

    @GetMapping("adicionar_produto")
    public String adicionarProduto(Model model) {
        return "produto/adicionar_produto";
    }

    @GetMapping("recuperar_produto")
    public String recuperarProduto(Model model) {
        return "produto/recuperar_produto";
    }

    @GetMapping("editar_produto")
    public String editarProduto(Model model) {
        return "produto/editar_produto";
    }

    @GetMapping("adicionar_reserva")
    public String adicionarReserva(Model model) {
        return "reserva/adicionar_reserva";
    }

    @GetMapping("recuperar_reserva")
    public String recuperarReserva(Model model) {
        return "reserva/recuperar_reserva";
    }

    @GetMapping("editar_reserva")
    public String editarReserva(Model model) {
        return "reserva/editar_reserva";
    }

    @GetMapping("adicionar_prato")
    public String adicionarPrato(Model model) {
        return "prato/adicionar_prato";
    }

    @GetMapping("recuperar_prato")
    public String recuperarPrato(Model model) {
        return "prato/recuperar_prato";
    }

    @GetMapping("editar_prato")
    public String editarPrato(Model model) {
        return "prato/editar_prato";
    }
}
