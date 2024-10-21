package br.com.guest.restaurante_admin.entidades.gerente;

import br.com.guest.restaurante_admin.entidades.garcom.Garcom;

import java.util.List;

public interface GerenteService {
    void salvarNovoGerente(Gerente gerente);
    Gerente buscarGerentePorCpf(String cpf);
    List<Gerente> listarGerentes();
    List<Garcom> listarGarconsGerenciados(String cpf);
    void deletarGerentePorCpf(String cpf);
}
