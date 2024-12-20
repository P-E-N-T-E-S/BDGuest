package br.com.guest.restaurante_admin.entidades.clientes;

import java.util.List;

public interface ClienteRepository {
    void salvarCliente(Cliente cliente);
    Cliente buscarClientePorCpf(String cpf);
    List<Cliente> listarClientes();
    List<Cliente> buscarClientePorFiltro(String filtro, String valor);
    void atualizarClientePorCpf(Cliente cliente, String cpf);
    void atualizarClientePorFiltro(String filtro, String valor, String campoAlterado, String valorAlterado);
    void atualizarClientePorFiltroDePessoa(String filtro, String valor, String campoAlterado, String valorAlterado);
    void deletarClientePorCpf(String cpf);
    void deletarClientePorFiltro(String filtro, String valor);
    void deletarClientePorFiltroDePessoa(String filtro, String valor);
}
